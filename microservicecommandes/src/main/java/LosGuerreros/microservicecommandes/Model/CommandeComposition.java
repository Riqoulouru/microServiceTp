package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commande_composition")
@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandeComposition {

    @EmbeddedId
    private CommandeCompositionId commandeCompositionId;
    @ManyToOne
    @JoinColumn(name = "id_commande", insertable = false, updatable = false)
    private Commande commande;

}
