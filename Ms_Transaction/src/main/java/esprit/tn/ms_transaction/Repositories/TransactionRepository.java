package esprit.tn.ms_transaction.Repositories;

import esprit.tn.ms_transaction.Entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Optional<Transaction> findByType(String type);
}
