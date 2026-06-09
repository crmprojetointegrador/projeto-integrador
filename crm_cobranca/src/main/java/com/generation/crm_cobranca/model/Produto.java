package com.generation.crm_cobranca.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @NotNull(message = "O número máximo de parcelas é obrigatório!")
    @Positive(message = "O número de parcelas deve ser maior que zero.")
    private Integer numeroParcelas;

    @NotNull(message = "O desconto à vista é obrigatório!")
    @PositiveOrZero(message = "O desconto à vista deve ser maior ou igual a zero.")
    private BigDecimal descontoAVista; // Ex: 0.10 para 10% de desconto

    @NotNull(message = "O desconto por tempo é obrigatório!")
    @PositiveOrZero(message = "O desconto por tempo deve ser maior ou igual a zero.")
    private BigDecimal descontoPorTempo; // Desconto condicional/pontualidade

    @UpdateTimestamp
    private LocalDateTime data;

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

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public BigDecimal getDescontoAVista() {
		return descontoAVista;
	}

	public void setDescontoAVista(BigDecimal descontoAVista) {
		this.descontoAVista = descontoAVista;
	}

	public BigDecimal getDescontoPorTempo() {
		return descontoPorTempo;
	}

	public void setDescontoPorTempo(BigDecimal descontoPorTempo) {
		this.descontoPorTempo = descontoPorTempo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

    
    
}