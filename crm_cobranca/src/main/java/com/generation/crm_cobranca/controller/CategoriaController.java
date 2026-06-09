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

import com.generation.crm_cobranca.model.Categoria;
import com.generation.crm_cobranca.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // 1. Buscar todas as categorias  GETALL
    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
    
    // 2. Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    // 3. Buscar categoria por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
    }
    
    // 4. Criando nova categoria
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
        categoria.setId(null);  // Garante que é uma nova categoria
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoria));
    }
    
    // 5. Atualizando categoria
    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
        // Verifica se a categoria existe
        if (categoriaRepository.existsById(categoria.getId())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoriaRepository.save(categoria));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Categoria não encontrada com o ID: " + categoria.getId());
        }
    }
    
    // 6. Deletar por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        
        if (categoria.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Categoria não encontrada com o ID: " + id);
        }
        
        categoriaRepository.deleteById(id);
    }
}