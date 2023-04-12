package br.senai.devinhouse.demoapi.repositories;

import br.senai.devinhouse.demoapi.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findById(int id);

    List<Produto> findByPrecoOrderByQtdEstoque(double preco);

    List<Produto> findByNome(String nome);

    @Query("SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria")
    List<Produto> findByNomeDaCategoria(String nomeCategoria);

    @Query("SELECT p FROM Produto p WHERE p.preco BETWEEN :precoMin AND :precoMax")
    List<Produto> findBetweenPreco(double precoMin, double precoMax);

    List<Produto> findByPrecoBetween(double precoMin, double precoMax);
}
