package com.demo.DemoApiApplication.domain.repository;

import com.demo.DemoApiApplication.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.Normalizer;
import java.util.List;

public interface FormaPagamentoRepository  extends JpaRepository<FormaPagamento, Long> {

}
