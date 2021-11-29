package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
