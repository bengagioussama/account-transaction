package esprit.tn.ms_transaction.Controllers;

import esprit.tn.ms_transaction.Dto.TransactionDto;
import esprit.tn.ms_transaction.Services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionRestContoller {

    private final ITransactionService transactionService;

    @PostMapping
    public TransactionDto add(@RequestBody TransactionDto transactionDto) {
        return transactionService.add(transactionDto);
    }

    @PatchMapping("{id}")
    public TransactionDto patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        return transactionService.update(id, fields);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        return transactionService.delete(id);
    }
    @GetMapping
    public Page<TransactionDto> getTransactions(@RequestParam int pageNbr,
                                                @RequestParam int pageSize) {
        return transactionService.getTransactions(pageNbr, pageSize);
    }

    @GetMapping("{id}")
    public TransactionDto getTransaction(@PathVariable String id) {
        return transactionService.getTransaction(id);
    }
    @GetMapping("type/{type}")
    public TransactionDto getTransactionByType(@PathVariable String type) {
        return transactionService.getTransactionByType(type);
    }
}
