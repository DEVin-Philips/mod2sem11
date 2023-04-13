package br.senai.devinhouse.demoapi.services;

import br.senai.devinhouse.demoapi.dtos.ProdutoDetalhadoDTO;
import br.senai.devinhouse.demoapi.dtos.ProdutoGetRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoRequest;
import br.senai.devinhouse.demoapi.dtos.ProdutoResponse;
import br.senai.devinhouse.demoapi.mappers.ProdutoMapper;
import br.senai.devinhouse.demoapi.models.Categoria;
import br.senai.devinhouse.demoapi.models.Produto;
import br.senai.devinhouse.demoapi.repositories.CategoriaRepository;
import br.senai.devinhouse.demoapi.repositories.ProdutoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoMapper mapper;

    // ProdutoMapper mapper = Mappers.getMapper(ProdutoMapper.class);

    public ProdutoResponse busca(int id) {
        return mapper.map(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    /*public List<Produto> busca(String nome) {
        return repository.findByAtivoAndNome('1', nome);
    }*/

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
            return mapper.map(repository.findByAtivoAndNome('1', requestParams.getNome()));
        } else if (requestParams.getPrecoMin() != null && requestParams.getPrecoMax() != null) {
            return mapper.map(
                    repository.findByAtivoAndPrecoBetween('1',
                            requestParams.getPrecoMin(), requestParams.getPrecoMax()));
        }

        return mapper.map(repository.findByAtivo('1'));

    }

    public void atualiza(ProdutoDetalhadoDTO request){
        Produto produto = repository.findById(request.getId()).orElseThrow(RuntimeException::new);

        if (request.getNome() != null && request.getNome().length() > 0) {
            produto.setNome(request.getNome());
        }
        if (request.getDescricao() != null && request.getDescricao().length() > 0) {
            produto.setDescricao(request.getDescricao());
        }
        if (request.getPreco() != null) {
            produto.setPreco(request.getPreco());
        }
        if (request.getQtdEstoque() != null) {
            produto.setQtdEstoque(request.getQtdEstoque());
        }
        if (request.getCategoria_id() != null) {
            Categoria categoria = categoriaRepository.findById(request.getCategoria_id()).orElseThrow(RuntimeException::new);
            produto.setCategoria(categoria);
        }

        repository.save(produto);

    }

    public void excluir(int id) {
        repository.deleteById(id);
    }

    public void excluirLogicamente(int id) {
        Produto produto = repository.findById(id).orElseThrow(RuntimeException::new);

        produto.setAtivo('0');

        repository.save(produto);
    }

    public List<Produto> findByCategoria(Integer categoriaId) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);

        return repository.findByCategoria(categoria);
    }

    public void ativar(int id) {
        Produto produto = repository.findById(id).orElseThrow(RuntimeException::new);
        produto.setAtivo('1');
        repository.save(produto);
    }
}
