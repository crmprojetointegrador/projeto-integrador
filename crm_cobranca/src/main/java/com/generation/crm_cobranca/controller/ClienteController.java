package com.generation.crm_cobranca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crm_cobranca.model.Cliente;
import com.generation.crm_cobranca.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // 1. Buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    // 2. Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 3. Buscar cliente por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(clienteRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    // 4. Buscar cliente por CPF/CNPJ
    @GetMapping("/cpfCnpj/{cpfCnpj}")
    public ResponseEntity<Cliente> getByCpfCnpj(@PathVariable String cpfCnpj) {
        return clienteRepository.findByCpfCnpj(cpfCnpj)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 5. Criar novo cliente
    @PostMapping
    public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
        if (clienteRepository.findByCpfCnpj(cliente.getCpfCnpj()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Já existe um cliente cadastrado com esse CPF/CNPJ!");
        }

        cliente.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteRepository.save(cliente));
    }

    // 6. Atualizar cliente
    @PutMapping
    public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado com o ID: " + cliente.getId());
        }

        Optional<Cliente> clienteComMesmoDocumento = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());

        if (clienteComMesmoDocumento.isPresent() && !clienteComMesmoDocumento.get().getId().equals(cliente.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Já existe outro cliente cadastrado com esse CPF/CNPJ!");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteRepository.save(cliente));
    }

    // 7. Deletar cliente por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado com o ID: " + id);
        }

        clienteRepository.deleteById(id);
    }
}
