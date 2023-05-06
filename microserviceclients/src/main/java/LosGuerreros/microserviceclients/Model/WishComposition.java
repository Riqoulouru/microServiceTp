package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wish_composition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishComposition {

    @EmbeddedId
    private WishCompositionId wishCompositionId;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("login")
    private Client client;


}
