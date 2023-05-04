package LosGuerreros.microserviceclients.Repository;

import LosGuerreros.microserviceclients.Model.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository extends CrudRepository<Clients, String> {

    public Page<Clients> findAll(Pageable pageable);
    Optional<Clients> findByLogin(String login);

}
