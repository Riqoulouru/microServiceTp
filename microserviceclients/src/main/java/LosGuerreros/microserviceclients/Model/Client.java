package LosGuerreros.microserviceclients.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Client implements Serializable {

    @Id
    @Column(name = "login")
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartComposition> cartCompositions = new ArrayList<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WishComposition> wishCompositions = new ArrayList<>();


}
