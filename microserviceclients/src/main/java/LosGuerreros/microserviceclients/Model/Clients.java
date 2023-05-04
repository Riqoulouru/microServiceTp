package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Clients {

    @Id
    private String login;
    private String password;
    private String firstname;
    private String lastname;


}
