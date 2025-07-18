package esprit.tn.ms_compte.Repositories;

import esprit.tn.ms_compte.Entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, String> {
Optional<Compte> findByDateCreation(LocalDate dateCreation);
}
