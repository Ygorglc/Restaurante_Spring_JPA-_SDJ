package com.demo.DemoApiApplication.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public abstract class FormaPagamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    public abstract List<FormaPagamento> listar();

    public abstract FormaPagamento buscar(Long id);

    @Transactional
    public abstract FormaPagamento salvar(FormaPagamento formaPagamento);

    @Transactional
    public abstract void remover(FormaPagamento formaPagamento);
}


