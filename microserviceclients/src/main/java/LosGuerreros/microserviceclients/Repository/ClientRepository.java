package LosGuerreros.microserviceclients.Repository;

import LosGuerreros.microserviceclients.Model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, String> {

    public Page<Client> findAll(Pageable pageable);
    Optional<Client> findByLogin(String login);

}
