package LosGuerreros.microservicepaiements.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "paiements")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Paiements {

    @Id
    private int id;

    private float value;

    private Date date;

}
