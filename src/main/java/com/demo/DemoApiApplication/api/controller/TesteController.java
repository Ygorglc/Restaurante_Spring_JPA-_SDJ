package com.demo.DemoApiApplication.api.controller;

import com.demo.DemoApiApplication.domain.model.Cozinha;
import com.demo.DemoApiApplication.domain.repository.CozinhaRepository;
import com.demo.DemoApiApplication.domain.repository.RestauranteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {

    private CozinhaRepository cozinhaRepository;

    private RestauranteRepository restauranteRepository;

}
