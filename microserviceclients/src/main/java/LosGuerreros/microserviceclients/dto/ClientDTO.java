package LosGuerreros.microserviceclients.dto;

import LosGuerreros.microserviceclients.Model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private List<Integer> cartCompositions = new ArrayList<>();
    private List<Integer> wishCompositions = new ArrayList<>();

    public ClientDTO(Client client) {
        if(client != null) {
            this.login = client.getLogin();
            this.password = client.getPassword();
            this.firstname = client.getFirstname();
            this.lastname = client.getLastname();
            this.cartCompositions = client.getCartCompositions().stream().map(CartComposition::getCartCompositionId).toList().stream().map(CartCompositionId::getIdProduit).toList();
            this.wishCompositions = client.getWishCompositions().stream().map(WishComposition::getWishCompositionId).toList().stream().map(WishCompositionId::getIdProduit).toList();
        }
    }

}
