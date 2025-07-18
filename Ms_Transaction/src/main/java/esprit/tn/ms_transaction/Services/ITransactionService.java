package esprit.tn.ms_transaction.Services;

import esprit.tn.ms_transaction.Dto.TransactionDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ITransactionService {
    TransactionDto add(TransactionDto transactionDto);
    TransactionDto update(String idTransaction, Map<Object, Object> fields);
    boolean delete(String idTransaction);
    Page<TransactionDto> getTransactions(int pageNbr, int pageSize);
    TransactionDto getTransaction(String id);
    TransactionDto getTransactionByType(String type);
}
