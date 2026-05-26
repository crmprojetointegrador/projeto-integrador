package com.generation.crm_cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.crm_cobranca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
