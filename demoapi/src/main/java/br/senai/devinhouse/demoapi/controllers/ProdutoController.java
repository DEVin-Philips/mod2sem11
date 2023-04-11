package br.senai.devinhouse.demoapi.controllers;

import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable int id) {
        return service.busca(id);
    }

    @GetMapping("/{nome}")
    public List<Produto> getProduto(@PathVariable String nome) {
        return service.busca(nome);
    }

    @PostMapping
    public void cadastra(@RequestBody ProdutoRequest request) {
        service.cadastra(request);
    }
}
