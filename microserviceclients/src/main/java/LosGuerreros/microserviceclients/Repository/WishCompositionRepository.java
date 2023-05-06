package LosGuerreros.microserviceclients.Repository;

import LosGuerreros.microserviceclients.Model.WishComposition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface WishCompositionRepository extends CrudRepository<WishComposition, String> {

    @Query("SELECT wish FROM WishComposition wish WHERE wish.wishCompositionId.login = :login AND wish.wishCompositionId.idProduit = :idProduit")
    public Optional<WishComposition> findWishCompositionByLoginAndIdProduit(@Param("login") String login, @Param("idProduit") Integer idProduit);

    @Transactional
    @Modifying
    @Query("DELETE FROM WishComposition wish WHERE wish.wishCompositionId.login = :login AND wish.wishCompositionId.idProduit = :idProduit")
    public void deleteByLoginAndIdProduit(@Param("login") String login, @Param("idProduit") Integer idProduit);

}
