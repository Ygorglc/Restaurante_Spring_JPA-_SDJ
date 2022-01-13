package com.demo.DemoApiApplication.api.controller.exceptionhadler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;

}
