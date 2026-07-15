package com.generation.crm_cobranca.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório!")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "O valor do débito é obrigatório!")
    @PositiveOrZero(message = "O valor do débito deve ser maior ou igual a zero.")
    private BigDecimal valorDebito;

    @NotNull(message = "A data do débito é obrigatória!")
    private LocalDate dataDebito;
    
    @NotBlank(message = "O status do produto é obrigatório!")
    private String status; // Exemplos: "Pago", "Em acordo", "Em atraso", "Sem negociação"
    
    
    //Relação
    
    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;
    
    //Adcionando segurança
    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Usuario usuario;

    // A quem pertence a dívida (diferente do Usuario, que é quem opera o CRM)
    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Cliente cliente;
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
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

	//Adicionando gettes and setters usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}