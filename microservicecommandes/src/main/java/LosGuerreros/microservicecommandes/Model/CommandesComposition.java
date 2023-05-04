package LosGuerreros.microservicecommandes.Model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commandesComposition")
@Getter
@Setter
@NoArgsConstructor
@Data
public class CommandesComposition {

    @EmbeddedId
    private CommandesCompositionId commandesCompositionId;

}
