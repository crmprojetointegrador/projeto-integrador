package com.generation.crm_cobranca.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.generation.crm_cobranca.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método de consulta específico
    // Busca todos os usuários pelo status
    public List<Usuario> findAllByStatusContainingIgnoreCase(@Param("status") String status);
    
    
}
