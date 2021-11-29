package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRespository extends JpaRepository<Cidade, Long> {

}
