package esprit.tn.ms_compte.Services;

import esprit.tn.ms_compte.Dto.CompteDto;
import esprit.tn.ms_compte.Entities.Compte;
import esprit.tn.ms_compte.Mappers.CompteMapper;
import esprit.tn.ms_compte.Repositories.CompteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ICompteServiceImp implements ICompteService {
    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteDto add(CompteDto compteDto) {
        log.info("Adding new compte: {}", compteDto);
        Compte compte = compteMapper.mapToEntity(compteDto);
        compte.setCreatedAt(LocalDateTime.now());
        return compteMapper.mapToDto(compteRepository.save(compte));
    }

    @Override
    @Transactional
    public CompteDto update(String idCompte, Map<Object, Object> fields) {
        Compte compte = compteRepository.findById(idCompte)
                .orElseThrow(() -> new EntityNotFoundException("Compte not found with id: " + idCompte));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Compte.class, (String) key);
            if (field == null) {
                throw new IllegalArgumentException("Invalid field name: " + key);
            }
            field.setAccessible(true);
            try {
                if (field.getType().equals(LocalDate.class)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(value.toString(), formatter);
                    ReflectionUtils.setField(field, compte, localDate);
                } else {
                    ReflectionUtils.setField(field, compte, value);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Error updating field " + key + ": " + e.getMessage());
            }
        });

        compte.setUpdatedAt(LocalDateTime.now());
        return compteMapper.mapToDto(compteRepository.save(compte));
    }

    @Override
    @Transactional
    public boolean delete(String idCompte) {
        if (!compteRepository.existsById(idCompte)) {
            throw new EntityNotFoundException("Compte not found with id: " + idCompte);
        }
        compteRepository.deleteById(idCompte);
        return !compteRepository.existsById(idCompte);
    }
    @Override
    public Page<CompteDto> getComptes(int pageNbr, int pageSize) {
        return compteRepository.findAll(PageRequest.of(pageNbr,pageSize))
                .map(compteMapper::mapToDto);
    }
    @Override
    public CompteDto getCompte(String id) {
        return compteRepository.findById(id)
                .map(compteMapper::mapToDto)
                .orElseThrow(()-> new IllegalArgumentException("Compte not found"));
    }
    @Override
    public CompteDto getCompteByDateCreation(LocalDate dateCreation) {
        return compteRepository.findByDateCreation(dateCreation)
                .map(compteMapper::mapToDto)
                .orElseThrow(()->new IllegalArgumentException("Compte not found with this Date"));
    }
}
