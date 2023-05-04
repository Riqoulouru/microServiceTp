package LosGuerreros.microserviceproduits.Repository;


import LosGuerreros.microserviceproduits.Model.Produits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProduitsRepository extends CrudRepository<Produits, String> {

    public Page<Produits> findAll(Pageable pageable);

}
