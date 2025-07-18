package esprit.tn.ms_compte.Services;

import esprit.tn.ms_compte.Dto.CompteDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Map;

public interface ICompteService {
    CompteDto add(CompteDto compteDto);
    CompteDto update(String idCompte, Map<Object, Object> fields);
    boolean delete(String idCompte);
    Page<CompteDto> getComptes(int pageNbr, int pageSize);
    CompteDto getCompte(String id);
    CompteDto getCompteByDateCreation(LocalDate dateCreation);
}
