package com.generation.crm_cobranca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity 
@Table(name = "tb_usuario") 
public class Usuario {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Schema(example = "12345678901")
	@NotBlank(message = "O atributo CPF é obrigatório!")
	@Size(min = 11, max = 11, message = "O atributo CPF deve ter 11 caracteres.")
	private String cpf; // Alterado para String para aceitar zeros à esquerda e validação de tamanho
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.")
	private String nome; 
	
	@NotNull(message = "A Data de Nascimento é obrigatória!")
	private LocalDate dataNascimento; // Removido @NotBlank/@Size pois é um objeto LocalDate
	
	@NotBlank(message = "O atributo e-mail é obrigatório!")
	@Email(message = "O atributo e-mail deve ser um email válido!")
	private String email; 
	
	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
	private String senha;
	
	@NotBlank(message = "O status é obrigatório!")
	private String tipo; // se admin, se user
	
	
	
	@UpdateTimestamp
	private LocalDateTime data;

	// Getters e Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCpf() { return cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public LocalDate getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public LocalDateTime getData() { return data; }
	public void setData(LocalDateTime data) { this.data = data; }
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
