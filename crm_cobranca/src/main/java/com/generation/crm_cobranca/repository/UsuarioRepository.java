package com.generation.crm_cobranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm_cobranca.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método de consulta específico de busca por CPF (usado para localizar o usuário)
    public Optional<Usuario> findByCpf(String cpf);

    // Usado para autenticação (login por e-mail)
    public Optional<Usuario> findByEmail(String email);
}