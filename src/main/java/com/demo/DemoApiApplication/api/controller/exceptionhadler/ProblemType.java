package com.demo.DemoApiApplication.api.controller.exceptionhadler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensage-incompreensivel", "Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-naoencontrada","Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio","Violação de regra de negócio");


    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://localhost:8080" + path;
        this.title = title;
    }

}
