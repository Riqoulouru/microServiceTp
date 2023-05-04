package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CommandesCompositionId implements Serializable {
    @Column(name = "idCommande")
    private Long id1;

    @Column(name = "idProduit")
    private Long id2;
}
