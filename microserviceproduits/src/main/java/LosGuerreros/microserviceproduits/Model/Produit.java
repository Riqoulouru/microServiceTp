package LosGuerreros.microserviceproduits.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "produit")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;
    private String name;
    private float price;
    private int stock;
    private String description;
    private String category;
    private Date insertDate;

}
