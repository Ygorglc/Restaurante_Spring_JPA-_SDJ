package com.demo.DemoApiApplication.domain.service;


import com.demo.DemoApiApplication.domain.exception.EntidadeEmUsoException;
import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.model.Estado;
import com.demo.DemoApiApplication.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {


    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId)
    {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId)
            );
        }   catch (DataIntegrityViolationException e){
                throw new EntidadeEmUsoException(
                        String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId)
                );
        }
    }
}
