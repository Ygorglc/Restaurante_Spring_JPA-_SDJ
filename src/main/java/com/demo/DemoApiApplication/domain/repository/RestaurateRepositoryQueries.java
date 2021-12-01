package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurateRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findComFreteGratis(String nome);

}
