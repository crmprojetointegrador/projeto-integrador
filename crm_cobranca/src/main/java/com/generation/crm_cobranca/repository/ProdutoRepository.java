package com.generation.crm_cobranca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.generation.crm_cobranca.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//Adicionando método de busca por categoria.
	List<Produto> findAllByCategoriaId(Long categoriaId);
    
    // Método especial de consulta por Situação/Status
    public List<Produto> findAllByStatusContainingIgnoreCase(@Param("status") String status);
    
    // Método especial para atualizar o status
    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.status = :status WHERE p.id = :id")
    public int updateStatusById(@Param("id") Long id, @Param("status") String status);
}
