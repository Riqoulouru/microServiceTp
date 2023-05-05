package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_composition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartComposition {

    @EmbeddedId
    private CartCompositionId cartCompositionId;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("login")
    private Client client;

}
