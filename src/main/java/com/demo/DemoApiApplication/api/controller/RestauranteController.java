package com.demo.DemoApiApplication.api.controller;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import javax.servlet.http.HttpServletRequest;
import com.demo.DemoApiApplication.domain.exception.CozinhaNaoEncontradaException;
import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.exception.NegocioException;
import com.demo.DemoApiApplication.domain.model.Restaurante;
import com.demo.DemoApiApplication.domain.repository.RestauranteRepository;
import com.demo.DemoApiApplication.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }


    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId){
        return cadastroRestaurante.buscarOuFalhar(restauranteId);
    }

//    @PostMapping
//    public ResponseEntity <?> adicionar (@RequestBody Restaurante restaurante){
//         try {
//             restaurante = cadastroRestaurante.salvar(restaurante);
//
//             return ResponseEntity.status(HttpStatus.CREATED)
//                     .body(restaurante);
//         }catch (EntidadeNaoEncontradaException e){
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        try {
            return cadastroRestaurante.salvar(restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }


    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual,
                "id", "formasPagamento","endereco", "dataCadastro","produtos");
        try {
            return cadastroRestaurante.salvar(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @PatchMapping("/{restauranteId")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
                                        @RequestBody Map<String, Object> campos, HttpServletRequest request){

        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual, request);

        return atualizar(restauranteId,restauranteAtual);
    }

    private void merge (Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request){

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
       try{
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true); // setado para não permitir que propriedades inoradas sejam anexadas
           objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

           Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

           dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
               Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
               field.setAccessible(true);

               Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

               ReflectionUtils.setField(field, restauranteDestino, novoValor);
           });
       } catch (IllegalArgumentException e){
           //Define o erro que tá sendo dado relançando ele para ele ser tratado na Api
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
       }

    }
}
