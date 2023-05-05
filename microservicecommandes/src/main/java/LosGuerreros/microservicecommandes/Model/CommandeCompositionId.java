package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandeCompositionId implements Serializable {
    @Column(name = "id_commande")
    private int idCommande;
    @Column(name = "id_produit")
    private int idProduit;
}
