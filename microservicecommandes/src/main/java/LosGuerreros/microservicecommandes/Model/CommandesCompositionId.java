package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CommandesCompositionId implements Serializable {

    @Column(name = "idCommande")
    private int idCommande;

    @Column(name = "idProduit")
    private int idProduit;



}
