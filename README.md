# 🏦 Sistema CRM: GESTÃO DE INAPIMPLÊNCIA - Projeto Integrador

Este projeto consiste num sistema de CRM de Cobrança desenvolvido com o objetivo de otimizar o gerenciamento de clientes, contratos e o controlo de pagamentos. A aplicação permite a realização completa de operações de CRUD (Create, Read, Update e Delete) , viabilizando o cadastro, a visualização, a atualização e a remoção de informações de utilizadores, contas a receber e registos financeiros.

---

## 🎯 Objetivo do Projeto

O principal objetivo deste sistema é centralizar e otimizar o fluxo de comunicação entre a empresa e os clientes inadimplentes, permitindo o acompanhamento detalhado de cobranças, segmentação de perfis de risco e facilitação de acordos financeiros.

---

## 🛠️ Tecnologias Utilizadas

A API foi construída utilizando o ecossistema do **Java** e boas práticas de desenvolvimento:

* **Java 17** (ou superior)
* **Spring Boot 3.x**
  * Spring Data JPA (Camada de persistência de dados)
  * Spring Web (Criação dos Endpoints REST)
  * Spring Validation (Validação de campos com Annotations)
  * Spring Security & JWT *(Opcional: se o vosso projeto incluir login/autenticação)*
* **MySQL / MariaDB** (Base de dados relacional)
* **Hibernate** (Framework ORM)
* **Insomnia / Postman** (Para a validação e testes das rotas)

---

## 📐 Estrutura do Projeto (Padrão MVC)

O código dentro da diretoria `crm_cobranca` está organizado seguindo o padrão de arquitetura em camadas:

* **Model (`.model`):** Definição das entidades do banco de dados (ex: `Cliente`, `Cobranca`, `Utilizador`) com as suas respetivas regras de validação (`@NotNull`, `@Size`, etc).
* **Repository (`.repository`):** Interfaces que estendem o `JpaRepository`, permitindo a comunicação com a base de dados e consultas customizadas.
* **Controller (`.controller`):** Camada de controlo que expõe os endpoints HTTP (`GET`, `POST`, `PUT`, `DELETE`) e faz a ponte com as regras de negócio.

---

## 🛑 Pré-requisitos para Execução

Antes de rodar a aplicação localmente, certifique-se de que possui:
1. **Java JDK 17** ou superior configurado no sistema.
2. Uma ferramenta de base de dados como o **MySQL Workbench** ou o **XAMPP** ativo.
3. Uma IDE instalada (Spring Tool Suite - STS, Eclipse, VS Code ou IntelliJ).

---

## 🚀 Como Rodar a Aplicação Localmente

1. **Clonar este Repositório:**
   ```bash
   git clone [https://github.com/crmprojetointegrador/projeto-integrador.git](https://github.com/crmprojetointegrador/projeto-integrador.git)

 ---

 ## 🚀 Equipe de Desenvolvimento
Alanis Santos - https://github.com/alanis-santos

Bruna Mendes - https://github.com/bruna-dsmendes

Eliane Orlandin - https://github.com/Eliane-orlandin

Patricia Rocha - https://github.com/patriciarocha1805

Queren Gomes - https://github.com/Quequels
