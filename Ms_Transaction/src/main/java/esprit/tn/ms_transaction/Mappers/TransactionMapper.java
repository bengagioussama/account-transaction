package esprit.tn.ms_transaction.Mappers;

import esprit.tn.ms_transaction.Dto.TransactionDto;
import esprit.tn.ms_transaction.Entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction mapToEntity(TransactionDto transactionDto);
    TransactionDto mapToDto(Transaction transaction);
}
