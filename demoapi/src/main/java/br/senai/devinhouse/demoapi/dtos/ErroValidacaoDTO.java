package br.senai.devinhouse.demoapi.dtos;

import org.springframework.validation.FieldError;

public class ErroValidacaoDTO {
    private final String campo;
    private final String mensagem;

    public ErroValidacaoDTO(FieldError erro) {
        this.campo = erro.getField();
        this.mensagem = erro.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
