🗂️ Visão Geral do Projeto

Este repositório é uma API RESTful em Java com Spring Boot que representa um sistema de compras, com funcionalidades para:

Gerenciar Produtos

Cadastrar e gerenciar Fornecedores

Registrar Notas Fiscais de Entrada

Lançar Itens de Nota de Entrada

Atualizar automaticamente o estoque de produtos

API organizada com camadas controller / service / repository

Utiliza DTOs para entrada e saída de dados
📌 A API implementa não apenas CRUD de produto como também processos ligados à entrada de mercadorias e controle de estoque, integrando as entidades de forma funcional.

🧠 Tecnologias Utilizadas

O projeto faz uso das principais tecnologias e frameworks do ecossistema Java moderno:

✅ Java
✅ Spring Boot como framework principal

Spring Web MVC (API REST)

Spring Data JPA (persistência com Hibernate)

Spring Validation

Lombok
✅ Banco de dados H2 (em memória)
📌 Bancos externos não estão configurados por padrão.
Também há convenções no projeto para facilitar testes e desenvolvimento rápido com H2.

📁 Estrutura de Pastas e Camadas

O projeto segue boas práticas de arquitetura:

src/main/java/com/inovatte/compras
├── controller     → Endpoints REST (Produto, Fornecedor, Nota, Item)
├── service        → Regras de negócio e lógica de estoque
├── repository     → Acesso a dados via Spring Data JPA
├── model          → Entidades JPA
└── dto            → Classes de entrada e saída (DTOs)

📦 Principais Entidades (Model)

Aqui estão as classes de modelo e seus principais campos:

🟡 1. Produto

Representa um produto cadastrado:

id;
nome;
descrição;
sku;
preço;
ativo;
datas de cadastro; e 
atualização;

📌 Relacionamentos:

Estoque → OneToOne
Itens de nota → OneToMany

🟢 2. Fornecedor

Cadastro de fornecedor, com informações como:

id;
razão social;
cnpj;
ativo;

📌 Relaciona-se com notas fiscais como proprietário fornecedor.

📘 3. NotaFiscalEntrada

Representa uma nota fiscal registrada pelo fornecedor:

número;
série;
data de emissão;
data de entrada;
valor total da nota;
status (ex.: pendente, conferido, etc.)

📌 Relaciona-se com itens da nota e com o fornecedor.

📑 4. ItemNotaEntrada

Itens dentro de uma nota fiscal:

Quantidade comprada;
Preço unitário;
Subtotal;

Relacionamento com Produto e NotaFiscalEntrada
📌 Cada item impacta diretamente no estoque do produto associado.

📊 5. Estoque

Entidade que controla o saldo do produto:

quantidade atual;
quantidade reservada;

📌 Mantém um relacionamento OneToOne com Produto e é atualizado sempre que uma nota de entrada é registrada.

🛠️ Controllers e Funcionalidades

📌 ProdutoController

Oferece endpoints para:

Método	Rota	Descrição
GET	/produto	Lista todos produtos
GET	/produto/{id}	Retorna produto por id
POST	/produto/novo	Cria produto
PUT	/produto/{id}	Atualiza produto
DELETE	/produto/{id}	Exclui produto
GET	/produto/teste	Endpoint de teste

📌 FornecedorController

Oferece endpoints para:

Método	Rota	Descrição
GET	/fornecedor	Lista todos fornecedores
GET	/fornecedor/{id}	Retorna fornecedor por id
POST	/fornecedor/novo	Cria fornecedor
PUT	/fornecedor/{id}	Atualiza fornecedor
DELETE	/fornecedor/{id}	Exclui fornecedor

📌 NotaFiscalEntradaController

Gere notas fiscais de entrada:

Método	Rota	Descrição
GET	/nota	Lista todas as notas
GET	/nota/{id}	Retorna nota por id
POST	/nota/novo	Cria e registra uma nota
PUT	/nota/{id}	Atualiza nota existente
DELETE	/nota/{id}	Deleta nota

📌 Importante: Ao cadastrar uma nota, os itens são processados e o estoque atual dos produtos é atualizado automaticamente com base nas quantidades compradas.

📌 ItemNotaEntradaController

Endpoint especializado para itens de nota:

Método	Rota	Descrição
GET	/itemNota	Lista todos itens
GET	/itemNota/{id}	Busca item por id
POST	/itemNota/novo	Cria item de nota associado à nota fiscal e produto
DELETE	/itemNota/{id}	Remove item específico

🔁 Services — Lógica de Negócio

Cada entidade (Produto, Fornecedor, Nota, Item) possui um service que encapsula regras de negócio, como:

✔ Prevenção de duplicidade (ex.: produto com mesmo SKU)
✔ Associação de notas com itens e fornecedores
✔ Atualização de estoque ao registrar itens da nota de entrada
✔ Tratamento de erros e validações
✔ Lançamento de exceções quando algo não existe
📌 Essa camada isola a complexidade da interação com o banco e mantém os controllers simples

📈 Processo de Atualização de Estoque

O fluxo principal implementado é:

O cliente envia uma requisição para cadastrar nota de entrada com itens;
A API cria a nota e lista o relacionamento;
Para cada item, o produto é identificado;
A quantidade comprada é somada ao Estoque do Produto correspondente;

📌 Assim, o estoque reflete automaticamente as entradas realizadas.

📌 Resumo das Funcionalidades Principais
✔ Cadastro e gestão de fornecedores
✔ CRUD completo de produtos
✔ Gestão de notas fiscais de entrada
✔ Lançamento de itens da nota
✔ Atualização automática do estoque
✔ Validações e regras de negócios robustas
✔ Uso de DTO para separar modelo de persistência e API pública
🧠 Conclusão

Este não é apenas um simples CRUD de produto — trata-se de um sistema de compras funcional que integra:

✔ Produtos
✔ Fornecedores
✔ Notas fiscais de entrada
✔ Itens da nota
✔ Atualização automática de estoque

Todas essas funcionalidades estão implementadas via Spring Boot, JPA e MVC com boas práticas de arquitetura em camadas e divisão clara de responsabilidades.
