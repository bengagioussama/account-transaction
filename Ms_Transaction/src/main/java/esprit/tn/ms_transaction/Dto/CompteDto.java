package esprit.tn.ms_transaction.Dto;

import java.time.LocalDate;


public record CompteDto (String id, String name, LocalDate dateCreation) {
}