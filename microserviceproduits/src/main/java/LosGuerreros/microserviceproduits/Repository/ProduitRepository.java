package LosGuerreros.microserviceproduits.Repository;


import LosGuerreros.microserviceproduits.Model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProduitRepository extends CrudRepository<Produit, Integer> {

    public Page<Produit> findAll(Pageable pageable);
    public Page<Produit> findAllByCategory(String category, Pageable pageable);
    public List<Produit> findAllByIdProduitIsIn(List<Integer> idProduits);
    @Query("SELECT prod FROM Produit prod" +
            "   WHERE coalesce(prod.category ,'') LIKE concat('%',:search, '%') OR" +
            "       coalesce(prod.name ,'') LIKE concat('%',:search, '%')")
    public Page<Produit> findAllBySearch(Pageable pageable, String search);

    @Query("SELECT prod.category FROM Produit prod GROUP BY prod.category")
    public List<String> findAllCategory();

}
