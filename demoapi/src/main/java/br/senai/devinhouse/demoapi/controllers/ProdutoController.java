package br.senai.devinhouse.demoapi.controllers;

import br.senai.devinhouse.demoapi.dtos.ProdutoGetRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoResponse;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}") // /produtos/12321
    public ProdutoResponse getProduto(@PathVariable int id) {
        return service.busca(id);
    }

    // ambíguo com o de cima
    /*@GetMapping("/{nome}")
    public List<Produto> getProduto(@PathVariable String nome) {
        return service.busca(nome);
    }*/

    @GetMapping
    public List<ProdutoResponse> getProduto(ProdutoGetRequest params) {
        return service.busca(params);
    }

    @PostMapping
    public void cadastra(@RequestBody ProdutoRequest request) {
        service.cadastra(request);
    }
}
