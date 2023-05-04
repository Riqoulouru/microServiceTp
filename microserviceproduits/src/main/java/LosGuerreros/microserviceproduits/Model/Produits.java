package LosGuerreros.microserviceproduits.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Produits {

    @Id
    public String login;
    public String password;
    public String fistname;
    public String lastname;


}
