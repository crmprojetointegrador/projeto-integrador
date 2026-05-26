package com.generation.crm_cobranca.model;

	import java.time.LocalDate;
	import java.time.LocalDateTime;

	import org.hibernate.annotations.UpdateTimestamp;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.Size;

	@Entity // a classe postagem vai virar uma tabela
	@Table(name = "tb_usuario") // define o nome da tabela
	public class Usuario {
		
		@Id // define a chave primaria
		@GeneratedValue(strategy = GenerationType.IDENTITY) // define que será autoencrementado
		private Long id;
		
		@NotBlank(message = "O atributo CPF é obrigatório!")
		@Size(min =11, max = 11, message = "O atributo CPF deve ter 11 caracteres.")
		private int cpf; //obrigatório não deixar vazio
		
		@NotBlank(message = "O atributo nome é obrigatório!")
		@Size(min = 10, max = 100)
		private String nome; //obrigatório não deixar vazio
		
		@NotBlank(message = "A Data de Nascimento é obrigatória!")
		@Size(min = 10, max = 10, message = "Digite no formato xx/xx/xxxx")
		private LocalDate dataNascimento; //obrigatório não deixar vazio
		
		@NotBlank(message = "O atributo e-mail é obrigatório!")
		@Size(min = 10, max = 100, message = "O atributo e-mail deve ter no minimo 10 e no máximo 100 caracteres.")
		private String email; //obrigatório não deixar vazio
		
		@NotBlank(message = "Valor do débito")
		@Size(min = 1, max = 1000000)
		private double valor; //obrigatório não deixar vazio
		
		@NotBlank(message = "Data do débito")
		@Size(min = 10, max = 10, message = "Digite no formato xx/xx/xxxx")
		private LocalDate dataDebito; //obrigatório não deixar vazio
		
		@NotBlank(message = "Status")
		@Size(min = 10, max = 50, message = "Pendente / Pago / Em acordo / Em atraso / Cancelado")
		private String status; //obrigatório não deixar vazio
		
		
		@UpdateTimestamp
		private LocalDateTime data;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public int getCpf() {
			return cpf;
		}


		public void setCpf(int cpf) {
			this.cpf = cpf;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public LocalDate getDataNascimento() {
			return dataNascimento;
		}


		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public double getValor() {
			return valor;
		}


		public void setValor(double valor) {
			this.valor = valor;
		}


		public LocalDate getDataDebito() {
			return dataDebito;
		}


		public void setDataDebito(LocalDate dataDebito) {
			this.dataDebito = dataDebito;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public LocalDateTime getData() {
			return data;
		}


		public void setData(LocalDateTime data) {
			this.data = data;
		}
		
		
		

	}

