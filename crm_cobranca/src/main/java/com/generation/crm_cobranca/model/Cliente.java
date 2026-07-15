package com.generation.crm_cobranca.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório!")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(length = 100)
    private String nome;

    @NotBlank(message = "O atributo cpfCnpj é obrigatório!")
    @Size(min = 11, max = 14, message = "Informe um CPF (11) ou CNPJ (14) válido.")
    @Column(length = 14, unique = true)
    private String cpfCnpj;

    private String telefone;

    @Email(message = "O atributo e-mail deve ser um email válido!")
    private String email;

    // Um cliente pode ter várias cobranças (produtos) associadas.
    // Sem cascade: excluir um cliente com cobranças vinculadas deve falhar
    // (proteção contra apagar histórico financeiro sem querer), a não ser
    // que essas cobranças sejam reatribuídas ou removidas antes.
    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Produto> produtos;

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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
