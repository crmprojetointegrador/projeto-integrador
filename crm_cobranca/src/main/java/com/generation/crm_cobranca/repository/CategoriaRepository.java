package com.generation.crm_cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.generation.crm_cobranca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);

}