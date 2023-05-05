package LosGuerreros.microservicecommandes.dto;

import LosGuerreros.microservicecommandes.Model.Commande;
import LosGuerreros.microservicecommandes.Model.CommandeComposition;
import LosGuerreros.microservicecommandes.Model.CommandeCompositionId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandeDto {

    private Integer idCommande;
    private String login;
    private Integer idPaiement;
    private List<Integer> produitId;

    public CommandeDto(Commande commande) {
        this.idCommande = commande.getIdCommande();
        this.login = commande.getLogin();
        this.idPaiement = commande.getIdPaiement();
        this.produitId = commande.getCommandeCompositions().stream().map(CommandeComposition::getCommandeCompositionId).toList().stream().map(CommandeCompositionId::getIdProduit).toList();
    }

}
