package br.senai.devinhouse.demoapi.dtos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProdutoGetRequest {
    private String nome;
    private Double precoMin;
    private Double precoMax;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(double precoMin) {
        this.precoMin = precoMin;
    }

    public Double getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(double precoMax) {
        this.precoMax = precoMax;
    }
}
