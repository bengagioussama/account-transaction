package esprit.tn.ms_compte.FeignClient;

import esprit.tn.ms_compte.Dto.CompteDto;
import esprit.tn.ms_compte.Dto.TransactionDto;
import esprit.tn.ms_compte.FallBack.CompteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.events.Event;

import java.util.Map;

@FeignClient(
        name = "prospect-client",
        url = "http://localhost:9090/transactions",
        fallback = CompteClientFallback.class)  // classe fallback à implémenter
public interface CompteClient {
    @GetMapping("/{id}")
    TransactionDto getTransaction(@PathVariable("id") String id);

    @PatchMapping("/{id}")
    TransactionDto patchUpdate(@RequestBody Map<String, Object> fields, @PathVariable("id") String id);
}
