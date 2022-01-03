package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestaurateRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {
// Caso não use o left join e não encontrar nenhuma forma de pagamento ou outro atributo o spring dara erro 
//    @Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamentos")
    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

    //List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional< Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List< Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);


}
