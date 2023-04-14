package br.senai.devinhouse.demoapi.controllers;

import br.senai.devinhouse.demoapi.dtos.ProdutoDetalhadoDTO;
import br.senai.devinhouse.demoapi.dtos.ProdutoGetRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoResponse;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.services.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Page<ProdutoResponse>> getProduto(
            @Valid ProdutoGetRequest params,
            Pageable paginacao) {
        return  ResponseEntity.ok(service.busca(params, paginacao));
    }

    @PostMapping
    public ResponseEntity<Produto> cadastra(@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder uriBuilder) {
        Produto produto = service.cadastra(request);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    public ResponseEntity<Void> atualiza(@RequestBody ProdutoDetalhadoDTO request) {
        service.atualiza(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable int id) {
        service.excluir(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    @DeleteMapping("/logico/{id}")
    public ResponseEntity<Void> excluirLogicamente(@PathVariable int id) {
        service.excluirLogicamente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public void reativar(@PathVariable int id) {
        service.ativar(id);
    }
}
