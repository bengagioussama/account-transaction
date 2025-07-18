package esprit.tn.ms_compte.Dto;

import java.time.LocalDate;


public record CompteDto (String id, String name, LocalDate dateCreation) {
    public CompteDto withName(String name) {
        return new CompteDto(this.id, name, this.dateCreation);
    }

    public CompteDto withDateCreation(LocalDate dateCreation) {
        return new CompteDto(this.id, this.name, dateCreation);
    }
}