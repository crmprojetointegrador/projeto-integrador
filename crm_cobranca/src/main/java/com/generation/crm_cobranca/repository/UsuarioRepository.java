package com.generation.crm_cobranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm_cobranca.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Login agora é feito por e-mail
    public Optional<Usuario> findByEmail(String email);

    // Método especial de busca por CPF (não usado para login)
    public Optional<Usuario> findByCpf(String cpf);
}