package br.senai.devinhouse.demoapi.repositories;

import br.senai.devinhouse.demoapi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {}
