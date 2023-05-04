package LosGuerreros.microservicepaiements.Repository;


import LosGuerreros.microservicepaiements.Model.Paiements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PaiementsRepository extends CrudRepository<Paiements, String> {

    public Page<Paiements> findAll(Pageable pageable);

}
