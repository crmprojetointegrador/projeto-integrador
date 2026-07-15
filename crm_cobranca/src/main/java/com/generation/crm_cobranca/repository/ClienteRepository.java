package com.generation.crm_cobranca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.crm_cobranca.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findAllByNomeContainingIgnoreCase(String nome);

    public Optional<Cliente> findByCpfCnpj(String cpfCnpj);

}
