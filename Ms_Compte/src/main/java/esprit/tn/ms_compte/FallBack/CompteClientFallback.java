package esprit.tn.ms_compte.FallBack;

import esprit.tn.ms_compte.Dto.TransactionDto;
import esprit.tn.ms_compte.FeignClient.CompteClient;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class CompteClientFallback implements CompteClient {
    @Override
    public TransactionDto getTransaction(String id) {
        // Retourne un TransactionDto "vide" ou par défaut en fallback
        return new TransactionDto(id, "", 0.0, "");
    }

    @Override
    public TransactionDto patchUpdate(Map<String, Object> fields, String id) {
        System.out.println("Fallback for patchUpdate called");
        // Même logique : retour par défaut
        return new TransactionDto(id, "", 0.0, "");
    }
}
