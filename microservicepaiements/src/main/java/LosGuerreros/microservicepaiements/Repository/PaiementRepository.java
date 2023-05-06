package LosGuerreros.microservicepaiements.Repository;


import LosGuerreros.microservicepaiements.Model.Paiement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaiementRepository extends CrudRepository<Paiement, Integer> {

    public Page<Paiement> findAll(Pageable pageable);
    public List<Paiement> findAllByIdPaiementIsInOrderByDateDesc(List<Integer> idPaiements);

}
