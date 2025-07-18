package esprit.tn.ms_transaction.Services;

import esprit.tn.ms_transaction.Dto.TransactionDto;
import esprit.tn.ms_transaction.Entities.Transaction;
import esprit.tn.ms_transaction.Kafka.KafkaProducerService;
import esprit.tn.ms_transaction.Mappers.TransactionMapper;
import esprit.tn.ms_transaction.Repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ITransactionServiceImp implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final KafkaProducerService kafkaProducerService;
    @Override
    @Transactional
    public TransactionDto add(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.mapToEntity(transactionDto);
        transaction.setCreatedAt(LocalDateTime.now());
        Transaction saved = transactionRepository.save(transaction);

        // Envoi vers Kafka après insertion
        kafkaProducerService.sendMessage("prospect-topic", "PROSPECT_CREATED: " + saved.getId());

        return transactionMapper.mapToDto(saved);
    }

    @Override
    public TransactionDto update(String idTransaction, Map<Object, Object> fields) {
        Transaction transaction = transactionRepository.findById(idTransaction)
                .orElseThrow(()-> new IllegalArgumentException("Transaction not found: " + idTransaction));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Transaction.class,(String) key);
            field.setAccessible(true);
            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse((String)value,formatter);
                ReflectionUtils.setField(field,transaction,localDate);
            }else {
                ReflectionUtils.setField(field,transaction,value);
            }
        } );
        transaction.setUpdatedAt(LocalDateTime.now());
        return transactionMapper.mapToDto(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction not found");
        }
        transactionRepository.deleteById(id);
        kafkaProducerService.sendMessage("transaction-topic",
                "TRANSACTION_DELETED:" + id);
        return transactionRepository.existsById(id);

    }

    @Override
    public Page<TransactionDto> getTransactions(int pageNbr, int pageSize) {
        return transactionRepository.findAll(PageRequest.of(pageNbr,pageSize))
                .map(transactionMapper::mapToDto);
    }

    @Override
    public TransactionDto getTransaction(String id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::mapToDto)
                .orElseThrow(()-> new IllegalArgumentException("Transaction not found"));
    }

    @Override
    public TransactionDto getTransactionByType(String type) {
        return transactionRepository.findByType(type)
                .map(transactionMapper::mapToDto)
                .orElseThrow(()->new IllegalArgumentException("Transaction not found with this filed"));
    }

}
