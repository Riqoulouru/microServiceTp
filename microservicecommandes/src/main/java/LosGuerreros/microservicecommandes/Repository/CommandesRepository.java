package LosGuerreros.microservicecommandes.Repository;

import LosGuerreros.microservicecommandes.Model.Commandes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommandesRepository extends CrudRepository<Commandes, String> {

    public Page<Commandes> findAll(Pageable pageable);

}
