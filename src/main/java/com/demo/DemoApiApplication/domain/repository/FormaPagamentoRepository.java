package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.List;

@Repository
public interface FormaPagamentoRepository  extends JpaRepository<FormaPagamento, Long> {

}
