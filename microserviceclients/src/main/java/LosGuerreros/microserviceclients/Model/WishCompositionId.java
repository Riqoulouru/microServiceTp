package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishCompositionId implements Serializable {
    private String login;
    private int idProduit;
}
