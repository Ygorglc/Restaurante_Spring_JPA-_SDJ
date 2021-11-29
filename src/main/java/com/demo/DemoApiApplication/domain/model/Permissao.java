package com.demo.DemoApiApplication.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public abstract class Permissao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;


    public abstract List<Permissao> listar();

    public abstract Permissao buscar(Long id);

    @Transactional
    public abstract Permissao salvar(Permissao permissao);

    @Transactional
    public abstract void remover(Permissao permissao);
}
