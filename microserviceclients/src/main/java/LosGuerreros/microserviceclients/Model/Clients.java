package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Clients {

    @Id
    public String login;
    public String password;
    public String fistname;
    public String lastname;


}
