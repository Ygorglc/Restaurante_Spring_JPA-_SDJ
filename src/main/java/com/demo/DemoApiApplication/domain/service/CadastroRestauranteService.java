package com.demo.DemoApiApplication.domain.service;

import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.model.Cozinha;
import com.demo.DemoApiApplication.domain.model.Restaurante;
import com.demo.DemoApiApplication.domain.repository.CozinhaRepository;
import com.demo.DemoApiApplication.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository. findById(cozinhaId)
                .orElseThrow(()->new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d ", cozinhaId)));
        return restauranteRepository.save(restaurante);
    }




}
