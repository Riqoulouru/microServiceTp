package LosGuerreros.microserviceproduits.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "produits")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Produits {

    @Id
    private int id;

    private String name;

    private float price;

    private int stock;

    private String description;

}
