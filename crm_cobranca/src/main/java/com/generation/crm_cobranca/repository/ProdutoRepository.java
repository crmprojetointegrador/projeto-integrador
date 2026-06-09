package com.generation.crm_cobranca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.generation.crm_cobranca.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Método de consulta específico por termo do nome
    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}