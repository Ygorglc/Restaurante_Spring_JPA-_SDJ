package com.demo.DemoApiApplication.api.controller;


import com.demo.DemoApiApplication.domain.exception.EntidadeEmUsoException;
import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.model.Cidade;
import com.demo.DemoApiApplication.domain.repository.CidadeRespository;
import com.demo.DemoApiApplication.domain.service.CadastroCidadeService;
import com.demo.DemoApiApplication.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRespository cidadeRespository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRespository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId){
        return cadastroCidade.buscarOuFalhar(cidadeId);
//        Optional<Cidade> cidade =  cidadeRespository.findById(cidadeId);
//
//        if(cidade != null){
//            return ResponseEntity.ok(cidade.get());
//        }
//
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        return cadastroCidade.salvar(cidade);
    }

//    @PostMapping
//    public ResponseEntity<?> adicionar(@PathVariable Long cidadeId,
//                                       @RequestBody Cidade cidade){
//
//        try {
//            cidade = cadastroCidade.salvar(cidade);
//            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
//        } catch(EntidadeNaoEncontradaException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @PutMapping("/{cidadeId}")
//    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
//        try{
//            Cidade cidadeAtual = cidadeRespository.findById(cidadeId).orElse(null);
//
//            if(cidadeAtual != null){
//
//                BeanUtils.copyProperties(cidade,cidadeAtual,"id");
//                cidadeAtual = cadastroCidade.salvar(cidadeAtual);
//
//                return ResponseEntity.ok(cidadeAtual);
//            }
//            return ResponseEntity.notFound().build();
//        } catch (EntidadeNaoEncontradaException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    @PutMapping("/{cidadeId}")
    public Cidade aualizar(@PathVariable Long cidadeId,@RequestBody Cidade cidade){
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

        //O beanUtils serve para instanciar, copiar ou comparar propriedades, é um método de conveniência estático
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cadastroCidade.salvar(cidadeAtual);
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId){
            cadastroCidade.excluir(cidadeId);
    }

}
