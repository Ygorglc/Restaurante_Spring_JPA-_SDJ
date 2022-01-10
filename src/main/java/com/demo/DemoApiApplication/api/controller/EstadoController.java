package com.demo.DemoApiApplication.api.controller;

import com.demo.DemoApiApplication.domain.exception.EntidadeEmUsoException;
import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.model.Estado;
import com.demo.DemoApiApplication.domain.repository.EstadoRepository;
import com.demo.DemoApiApplication.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return  cadastroEstado.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){
        return cadastroEstado.salvar(estado);
    }

    @PostMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado){
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual,"id");

        return cadastroEstado.salvar(estadoAtual);
    }


    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId){
       cadastroEstado.excluir(estadoId);
    }

}
