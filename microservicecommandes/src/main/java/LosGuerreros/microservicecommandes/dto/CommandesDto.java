package LosGuerreros.microservicecommandes.dto;

import LosGuerreros.microservicecommandes.Model.Commandes;
import LosGuerreros.microservicecommandes.Model.CommandesComposition;
import LosGuerreros.microservicecommandes.Model.CommandesCompositionId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandesDto {

    private Integer idCommande;
    private String loginUser;
    private Integer idPaiement;
    private List<Integer> produitId;

    public CommandesDto(Commandes commandes) {
        this.idCommande = commandes.getId();
        this.loginUser = commandes.getLoginUser();
        this.idPaiement = commandes.getIdPaiement();
        this.produitId = commandes.getCommandesCompositions().stream().map(CommandesComposition::getCommandesCompositionId).toList().stream().map(CommandesCompositionId::getIdProduit).toList();
    }

}
