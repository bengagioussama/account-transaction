package esprit.tn.ms_compte.Mappers;

import esprit.tn.ms_compte.Dto.CompteDto;
import esprit.tn.ms_compte.Entities.Compte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    Compte mapToEntity(CompteDto compteDto);
    CompteDto mapToDto(Compte compte);
}
