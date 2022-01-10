package com.demo.DemoApiApplication.domain.service;


import com.demo.DemoApiApplication.domain.exception.EntidadeEmUsoException;
import com.demo.DemoApiApplication.domain.exception.EntidadeNaoEncontradaException;
import com.demo.DemoApiApplication.domain.model.Cidade;
import com.demo.DemoApiApplication.domain.model.Estado;
import com.demo.DemoApiApplication.domain.repository.CidadeRespository;
import com.demo.DemoApiApplication.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    private static final String MSG_CIDADE_NAO_ENCONTRADA
            = "Não existe um cadastro de cidade com código %d";

    @Autowired
    private CidadeRespository cidadeRespository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRespository.save(cidade);
    }

    public void excluir(Long cidadeId){
        try {
            cidadeRespository.deleteById(cidadeId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)
            );
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
              String.format(MSG_CIDADE_EM_USO, cidadeId)
            );
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRespository.findById(cidadeId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA,cidadeId)));
    }
}
