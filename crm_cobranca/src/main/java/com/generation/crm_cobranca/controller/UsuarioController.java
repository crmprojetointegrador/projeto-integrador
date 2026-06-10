package com.generation.crm_cobranca.controller;

import com.generation.crm_cobranca.service.UsuarioService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.generation.crm_cobranca.model.Usuario;
import com.generation.crm_cobranca.model.UsuarioLogin;
import com.generation.crm_cobranca.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite requisições de qualquer front-end
public class UsuarioController {

    private final UsuarioService usuarioService;
	@Autowired
    private UsuarioRepository usuarioRepository;

	UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
    
	@GetMapping ("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id) 
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

    // POST - Cadastrar novo usuário
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario) {
	    return usuarioService.cadastrarUsuario(usuario)
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
 

    // PUT - Atualizar usuário existente
	@PutMapping("/atualizar")
    public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
       return usuarioService.atualizarUsuario(usuario)
               .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
               .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    
     // GET - Metodo especial de busca por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Usuario> getByCpf(@PathVariable String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE - Deletar usuário pelo ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");

        usuarioRepository.deleteById(id);
    }
    // LOGAR - logando para security
    @PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticar(@Valid @RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
