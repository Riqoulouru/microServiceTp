package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "commandes")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Commandes {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "loginUser")
    private String loginUser;

    @Column(name = "idPaiement")
    private int idPaiement;


    private ArrayList<Integer> idProduitsList;


}