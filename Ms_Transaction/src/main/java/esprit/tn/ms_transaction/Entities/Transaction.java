package esprit.tn.ms_transaction.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @Indexed
    @Setter(AccessLevel.MODULE)
    String id;
    String type;
    Double montant;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String idCompte;
}
