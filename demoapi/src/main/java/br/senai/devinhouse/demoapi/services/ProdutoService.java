package br.senai.devinhouse.demoapi.services;

import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto busca(int id) {
        return repository.findById(id);
    }

    public List<Produto> busca(String nome) {
        return repository.findByNome(nome);
    }

    public void cadastra(ProdutoRequest produtoRequest) {

        Produto produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setDescricao(produto.getDescricao());
        produto.setPreco(produto.getPreco());

        repository.save(produto);
    }
}
