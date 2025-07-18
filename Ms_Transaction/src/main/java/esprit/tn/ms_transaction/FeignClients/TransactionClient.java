package esprit.tn.ms_transaction.FeignClients;

import esprit.tn.ms_transaction.Dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@FeignClient(name = "transaction-client", url = "http://localhost:8080/transactions")
public interface TransactionClient {
    @GetMapping("/{id}")
    TransactionDto getTransaction(@PathVariable("id") String id);
    @PatchMapping("/{id}")
    TransactionDto patchUpdate(@RequestBody Map<String, Object> fields, @PathVariable("id") String id);
}
