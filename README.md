# dscommerce
[![NPM](https://img.shields.io/npm/l/react)]([https://github.com/devsuperior/sds1-wmazoni/blob/master/LICENSE](https://github.com/jorgehauck/dscommerce/blob/main/LICENSE)) 

## Descrição

O `dscommerce` é uma aplicação back-end desenvolvida em Java 21 com Spring Boot 3.2.5, que tem como objetivo fornecer uma plataforma de e-commerce. O sistema permite o cadastro e gerenciamento de usuários, produtos, categorias e pedidos. 

## Funcionalidades

- **Cadastro de Usuários**: Gerenciamento de usuários, que podem ser clientes ou administradores. 
- **Gerenciamento de Produtos**: Cadastro de produtos com nome, descrição, preço e imagem.
- **Catálogo de Produtos**: Exibição de produtos com possibilidade de filtragem por nome.
- **Carrinho de Compras**: Funcionalidade para adicionar, remover e alterar a quantidade de itens no carrinho.
- **Registro de Pedidos**: Após a finalização do pedido, ele é salvo com o status "aguardando pagamento". O status do pedido pode ser atualizado para pago, enviado, entregue ou cancelado.
- **Área Administrativa**: Acesso exclusivo para administradores para gerenciar usuários, produtos e categorias.

## Modelo conceitual
![Modelo Conceitual](https://github.com/jorgehauck/assets/blob/main/dscommerce/modelo-conceitual.png)

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.2.5**
- **OAuth2 e JWT**: Implementação de autenticação e autorização segura para acesso às funcionalidades do sistema.

## Perfis de Usuários

- **Usuário Cliente**: Pode se cadastrar, navegar pelo catálogo, adicionar produtos ao carrinho, registrar pedidos e visualizar seus próprios pedidos.
- **Usuário Administrador**: Acesso completo à área administrativa para gerenciar usuários, produtos e categorias.

## Instalação e Execução

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seuusuario/dscommerce.git

2. **Navegar até o diretório do projeto**:
   ```bash
   cd dscommerce
3. **Construir e executar o projeto**:
   ```bash
   ./mvnw spring-boot:run

# Autor

José Jorge Hauck Júnior

https://www.linkedin.com/in/jorgehauck/
