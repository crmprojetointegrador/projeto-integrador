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

import com.generation.crm_cobranca.model.Usuario;
import com.generation.crm_cobranca.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


 

    // 3. Método de consulta específico - Buscar por Status (ex: /usuarios/status/Pendente)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Usuario>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(usuarioRepository.findAllByStatusContainingIgnoreCase(status));
    }


    // 6. delete() - Excluir um usuário por ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");

        usuarioRepository.deleteById(id);
    }
}