package br.senai.devinhouse.demoapi.services;

import br.senai.devinhouse.demoapi.dtos.ProdutoGetRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoResponse;
import br.senai.devinhouse.demoapi.mappers.ProdutoMapper;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.repositories.ProdutoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    // ProdutoMapper mapper = Mappers.getMapper(ProdutoMapper.class);

    public ProdutoResponse busca(int id) {
        return mapper.map(repository.findById(id));
    }

    public List<Produto> busca(String nome) {
        return repository.findByNome(nome);
    }

    public void cadastra(ProdutoRequest produtoRequest) {

        /*Produto produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setDescricao(produtoRequest.getDescricao());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQtdEstoque(produtoRequest.getQtdEstoque());*/

        //Produto produto = new Produto(produtoRequest);

        Produto produto = mapper.map(produtoRequest);

        repository.save(produto);
    }

    public List<ProdutoResponse> busca(ProdutoGetRequest requestParams) {
        if (requestParams.getNome() != null) {
            return mapper.map(repository.findByNome(requestParams.getNome()));
        } else if (requestParams.getPrecoMin() != null && requestParams.getPrecoMax() != null) {
            return mapper.map(
                    repository.findByPrecoBetween(
                            requestParams.getPrecoMin(), requestParams.getPrecoMax()));
        }

        return mapper.map(repository.findAll());

    }
}
