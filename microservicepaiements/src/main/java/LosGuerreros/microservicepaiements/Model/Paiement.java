package LosGuerreros.microservicepaiements.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "paiement")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaiement;
    private float value;
    private Date date;

}
