package LosGuerreros.microservicecommandes.Repository;

import LosGuerreros.microservicecommandes.Model.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

    public Page<Commande> findAll(Pageable pageable);
    public List<Commande> findAllByLogin(String login);

}
