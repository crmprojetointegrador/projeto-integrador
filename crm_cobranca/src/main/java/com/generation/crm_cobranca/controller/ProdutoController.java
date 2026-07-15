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

import com.generation.crm_cobranca.model.Produto;
import com.generation.crm_cobranca.repository.ProdutoRepository;
import com.generation.crm_cobranca.repository.CategoriaRepository;
import com.generation.crm_cobranca.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// adicinando dependência
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	// Listar todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// Buscar produto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Buscar por situação
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Produto>> getByStatus(@PathVariable String status) {
		return ResponseEntity.ok(produtoRepository.findAllByStatusContainingIgnoreCase(status));
	}

	// buscando pelo ID da categoria

	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<List<Produto>> getByCategoria(@PathVariable Long categoriaId) {
		// Verifica se a categoria existe
		if (!categoriaRepository.existsById(categoriaId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada!");
		}
		return ResponseEntity.ok(produtoRepository.findAllByCategoriaId(categoriaId));
	}

	// buscando pelo ID do cliente

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<Produto>> getByCliente(@PathVariable Long clienteId) {
		// Verifica se o cliente existe
		if (!clienteRepository.existsById(clienteId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado!");
		}
		return ResponseEntity.ok(produtoRepository.findAllByClienteId(clienteId));
	}

	// Criar novo produto
	// Validando categoria e cliente
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		// Verifica se a categoria existe
		if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida ou não encontrada!");
		}
		// Verifica se o cliente existe
		if (produto.getCliente() == null || !clienteRepository.existsById(produto.getCliente().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inválido ou não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	// Atualizar produto existente
	// Validando categoria e cliente
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		// Verifica se o produto existe
		if (produtoRepository.existsById(produto.getId())) {
			// Verifica se a categoria existe
			if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida ou não encontrada!");
			}
			// Verifica se o cliente existe
			if (produto.getCliente() == null || !clienteRepository.existsById(produto.getCliente().getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inválido ou não encontrado!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// Deletar produto
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");

		produtoRepository.deleteById(id);
	}
	
	@PutMapping("/atualizar-status/{id}")
	public ResponseEntity<Void> putStatusDireto(@PathVariable Long id, @RequestBody String novoStatus) {
	    String statusLimpo = novoStatus.replace("\"", "").trim();
	    
	    // O método retorna a quantidade de linhas alteradas (0 se não achou o ID, 1 se atualizou)
	    int linhasAfetadas = produtoRepository.updateStatusById(id, statusLimpo);
	    
	    if (linhasAfetadas == 0) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    
	    return ResponseEntity.status(HttpStatus.OK).build();
	}

}