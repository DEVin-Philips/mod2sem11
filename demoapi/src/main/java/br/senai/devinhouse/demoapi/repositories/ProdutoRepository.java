package br.senai.devinhouse.demoapi.repositories;

import br.senai.devinhouse.demoapi.models.Categoria;
import br.senai.devinhouse.demoapi.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Produto findById(int id);

    // List<Produto> findByPrecoOrderByQtdEstoque(double preco);

    Page<Produto> findByAtivoAndNome(char ativo, String nome, Pageable paginacao);

    /*@Query("SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria")
    List<Produto> findByNomeDaCategoria(String nomeCategoria);*/

    /*@Query("SELECT p FROM Produto p WHERE p.preco BETWEEN :precoMin AND :precoMax")
    List<Produto> findBetweenPreco(double precoMin, double precoMax);*/

    Page<Produto> findByAtivoAndPrecoBetween(char ativo, double precoMin, double precoMax, Pageable paginacao);

    Page<Produto> findByAtivo(char ativo, Pageable paginacao);

    List<Produto> findByCategoria(Categoria categoria);
}
