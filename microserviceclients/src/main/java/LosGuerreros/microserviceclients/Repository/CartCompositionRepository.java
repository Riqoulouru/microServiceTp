package LosGuerreros.microserviceclients.Repository;

import LosGuerreros.microserviceclients.Model.CartComposition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartCompositionRepository extends CrudRepository<CartComposition, String> {

    @Query("SELECT cart FROM CartComposition cart WHERE cart.cartCompositionId.login = :login AND cart.cartCompositionId.idProduit = :idProduit")
    public Optional<CartComposition> findCartCompositionByLoginAndIdProduit(@Param("login") String login, @Param("idProduit") Integer idProduit);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartComposition cart WHERE cart.cartCompositionId.login = :login AND cart.cartCompositionId.idProduit = :idProduit")
    public void deleteByLoginAndIdProduit(@Param("login") String login, @Param("idProduit") Integer idProduit);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartComposition cart WHERE cart.cartCompositionId.login = :login")
    public void deleteByLogin(@Param("login") String login);


}
