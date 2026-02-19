🗂️ Visão Geral do Projeto

Este repositório é uma API RESTful em Java com Spring Boot que representa um sistema de compras, com funcionalidades para:

Gerenciar Produtos;<br>
Cadastrar e gerenciar Fornecedores;<br>
Registrar Notas Fiscais de Entrada;<br>
Lançar Itens de Nota de Entrada;<br>
Atualizar automaticamente o estoque de produtos;<br>
<br>
API organizada com camadas controller / service / repository<br>
<br>
Utiliza DTOs para entrada e saída de dados<br>
📌 A API implementa não apenas CRUD de produto como também processos ligados à entrada de mercadorias e controle de estoque, integrando as entidades de forma funcional.<br>
<br>
🧠 Tecnologias Utilizadas<br>
<br>
O projeto faz uso das principais tecnologias e frameworks do ecossistema Java moderno:<br>

✅ Java<br>
✅ Spring Boot como framework principal<br>

Spring Web MVC (API REST);<br>
Spring Data JPA (persistência com Hibernate);<br>
Spring Validation;<br>

Lombok
✅ Banco de dados H2 (em memória)<br>
📌 Bancos externos não estão configurados por padrão.<br>
Também há convenções no projeto para facilitar testes e desenvolvimento rápido com H2.<br>
<br>
📁 Estrutura de Pastas e Camadas
<br>
O projeto segue boas práticas de arquitetura:
<br>
src/main/java/com/inovatte/compras<br><br>
├── controller     → Endpoints REST (Produto, Fornecedor, Nota, Item)<br>
├── service        → Regras de negócio e lógica de estoquev
├── repository     → Acesso a dados via Spring Data JPA<br>
├── model          → Entidades JPA<br>
└── dto            → Classes de entrada e saída (DTOs)<br>
<br>
📦 Principais Entidades (Model)<br>
<br>
Aqui estão as classes de modelo e seus principais campos:<br>
<br>
🟡 1. Produto<br>
<br>
Representa um produto cadastrado:<br>
<br>
id;
nome;
descrição;
sku;
preço;
ativo;
datas de cadastro; e 
atualização;
<br>
📌 Relacionamentos:<br>
<br>
Estoque → OneToOne<br>
Itens de nota → OneToMany<br>
<br>
🟢 2. Fornecedor<br>
<br>
Cadastro de fornecedor, com informações como:<br>
<br>
id;
razão social;
cnpj;
ativo;
<br>
📌 Relaciona-se com notas fiscais como proprietário fornecedor.<br>
<br>
📘 3. NotaFiscalEntrada<br>
<br>
Representa uma nota fiscal registrada pelo fornecedor:<br>
<br>
número;
série;
data de emissão;
data de entrada;
valor total da nota;
status (ex.: pendente, conferido, etc.)
<br>
📌 Relaciona-se com itens da nota e com o fornecedor.<br>
<br>
📑 4. ItemNotaEntrada<br>
<br>
Itens dentro de uma nota fiscal:<br>
<br>
Quantidade comprada;
Preço unitário;
Subtotal;
<br>
Relacionamento com Produto e NotaFiscalEntrada<br>
📌 Cada item impacta diretamente no estoque do produto associado.<br>
<br>
📊 5. Estoque<br>
<br>
Entidade que controla o saldo do produto:<br>
<br>
quantidade atual;
quantidade reservada;
<br>
📌 Mantém um relacionamento OneToOne com Produto e é atualizado sempre que uma nota de entrada é registrada.<br>
<br>
🛠️ Controllers e Funcionalidades<br>
<br>
📌 ProdutoController<br>
<br>
Oferece endpoints para:<br>
<br>
Método	Rota	Descrição<br>
GET	/produto	Lista todos produtos<br>
GET	/produto/{id}	Retorna produto por id<br>
POST	/produto/novo	Cria produto<br>
PUT	/produto/{id}	Atualiza produto<br>
DELETE	/produto/{id}	Exclui produto<br>
GET	/produto/teste	Endpoint de teste<br>
<br>
📌 FornecedorController<br>
<br>
Oferece endpoints para:<br>
<br>
Método	Rota	Descrição<br>
GET	/fornecedor	Lista todos fornecedores<br>
GET	/fornecedor/{id}	Retorna fornecedor por id<br>
POST	/fornecedor/novo	Cria fornecedor<br>
PUT	/fornecedor/{id}	Atualiza fornecedor<br>
DELETE	/fornecedor/{id}	Exclui fornecedor<br>
<br>
📌 NotaFiscalEntradaController<br>
<br>
Gere notas fiscais de entrada:<br>

Método	Rota	Descrição<br>
GET	/nota	Lista todas as notas<br>
GET	/nota/{id}	Retorna nota por id<br>
POST	/nota/novo	Cria e registra uma nota<br>
PUT	/nota/{id}	Atualiza nota existente<br>
DELETE	/nota/{id}	Deleta nota<br>
<br>
📌 Importante: Ao cadastrar uma nota, os itens são processados e o estoque atual dos produtos é atualizado automaticamente com base nas quantidades compradas.<br>
<br>
📌 ItemNotaEntradaController<br>
<br>
Endpoint especializado para itens de nota:<br>
<br>
Método	Rota	Descrição<br>
GET	/itemNota	Lista todos itens<br>
GET	/itemNota/{id}	Busca item por id<br>
POST	/itemNota/novo	Cria item de nota associado à nota fiscal e produto<br>
DELETE	/itemNota/{id}	Remove item específico<br>
<br>
🔁 Services — Lógica de Negócio<br>
<br>
Cada entidade (Produto, Fornecedor, Nota, Item) possui um service que encapsula regras de negócio, como:<br>
<br>
✔ Prevenção de duplicidade (ex.: produto com mesmo SKU)<br>
✔ Associação de notas com itens e fornecedores<br>
✔ Atualização de estoque ao registrar itens da nota de entrada<br>
✔ Tratamento de erros e validações<br>
✔ Lançamento de exceções quando algo não existe<br>
📌 Essa camada isola a complexidade da interação com o banco e mantém os controllers simples<br>
<br>
📈 Processo de Atualização de Estoque<br>
<br>
O fluxo principal implementado é:<br>
<br>
O cliente envia uma requisição para cadastrar nota de entrada com itens;<br>
A API cria a nota e lista o relacionamento;<br>
Para cada item, o produto é identificado;<br>
A quantidade comprada é somada ao Estoque do Produto correspondente;<br>
<br>
📌 Assim, o estoque reflete automaticamente as entradas realizadas.<br>
<br>
📌 Resumo das Funcionalidades Principais<br>
<br>
✔ Cadastro e gestão de fornecedores<br>
✔ CRUD completo de produtos<br>
✔ Gestão de notas fiscais de entrada<br>
✔ Lançamento de itens da nota<br>
✔ Atualização automática do estoque<br>
✔ Validações e regras de negócios robustas<br>
✔ Uso de DTO para separar modelo de persistência e API pública<br>
<br>
🧠 Conclusão<br>
<br>
Este não é apenas um simples CRUD de produto — trata-se de um sistema de compras funcional que integra:<br>
<br>
✔ Produtos;<br>
✔ Fornecedores;<br>
✔ Notas fiscais de entrada;<br>
✔ Itens da nota;<br>
✔ Atualização automática de estoque;<br>
<br>
Todas essas funcionalidades estão implementadas via Spring Boot, JPA e MVC com boas práticas de arquitetura em camadas e divisão clara de responsabilidades.
