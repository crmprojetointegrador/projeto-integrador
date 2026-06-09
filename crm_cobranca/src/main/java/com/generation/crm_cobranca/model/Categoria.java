package com.generation.crm_cobranca.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O atributo nome é obrigatório!")
    @Size(min = 3, max = 50, message = "O atributo nome deve ter entre 3 e 50 caracteres")
    @Column(length = 50)
    private String nome;
    
    @NotBlank(message = "O atributo descricao é obrigatório!")
    @Size(min = 5, max = 200, message = "O atributo descricao deve ter entre 5 e 200 caracteres")
    @Column(length = 200)
    private String descricao;
    
    // Relação, é bidirecional
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("categoria")
    private List<Produto> produto;
    
    public List<Produto> getProduto() {
        return produto;
    }
    
    public void setProduto(List<Produto> produto) {
        this.produto = produto;
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}