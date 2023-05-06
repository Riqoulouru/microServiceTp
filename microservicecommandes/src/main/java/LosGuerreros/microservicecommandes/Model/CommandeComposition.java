package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commande_composition")
@Getter
@Setter
@NoArgsConstructor
public class CommandeComposition {

    @EmbeddedId
    private CommandeCompositionId commandeCompositionId;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idCommande")
    private Commande commande;

}
