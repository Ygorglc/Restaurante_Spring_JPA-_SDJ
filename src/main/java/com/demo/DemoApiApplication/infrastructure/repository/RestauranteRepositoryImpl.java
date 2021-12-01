package com.demo.DemoApiApplication.infrastructure.repository;

import com.demo.DemoApiApplication.domain.model.Restaurante;
import com.demo.DemoApiApplication.domain.repository.RestauranteRepository;
import com.demo.DemoApiApplication.domain.repository.RestaurateRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static com.demo.DemoApiApplication.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.demo.DemoApiApplication.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestaurateRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

        var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0");

        var parametros =new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = manager
                .createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll((Sort) comFreteGratis().and(comNomeSemelhante(nome)));
    }
}
