package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commandesComposition")
@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandesComposition {

    @EmbeddedId
    private CommandesCompositionId commandesCompositionId;

    @ManyToOne
    @JoinColumn(name = "idCommande", insertable = false, updatable = false)
    private Commandes commande;

}
