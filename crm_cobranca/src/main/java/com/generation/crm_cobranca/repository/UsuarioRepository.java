package com.generation.crm_cobranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm_cobranca.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método de consulta específico de busca por CPF
    public Optional<Usuario> findByCpf(String cpf);
}