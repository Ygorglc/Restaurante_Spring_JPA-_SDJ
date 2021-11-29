package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {

}
