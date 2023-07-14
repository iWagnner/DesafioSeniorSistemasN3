# Desafio Senior Sistemas III

Este projeto é uma solução desenvolvida como parte de um desafio de programação de nível III. O objetivo do desafio é criar um sistema de cadastro de pedidos utilizando tecnologias específicas.

## Descrição do Projeto

O sistema permite o cadastro, consulta, atualização e exclusão de produtos/serviços, clientes/fornecedores, usuarios , pedidos e itens de pedido. É possível aplicar filtros na listagem dos registros. Além disso, o sistema inclui um ControllerAdvice para customizar as respostas HTTP das requisições, garantindo a consistência e a padronização das respostas.

Cada produto/serviço possui um indicador para diferenciar se é um produto ou um serviço. No cadastro de produto/serviço, é possível aplicar um percentual de desconto no pedido, mas somente para os itens que são produtos (não serviços). O desconto é calculado sobre o valor total dos produtos. A aplicação de desconto só é permitida se o pedido estiver na situação "Aberto" (bloqueado quando "Fechado"). Além disso, é impedida a exclusão de um produto/serviço caso esteja associado a algum pedido, e também não é possível adicionar um produto desativado a um pedido.

## Regras do Desafio

Você pode encontrar as regras completas do desafio [aqui](https://github.com/iWagnner/DesafioSeniorSistemasN3/blob/main/src/main/resources/Doc/1-DESAFIO%20DE%20BACKEND%20JAVA%20PARA%20ERP%20.pdf)

## Recursos

- Cadastro de produtos/serviços: Permite criar, visualizar, atualizar e excluir/inativar produtos/serviços no sistema.
- Cadastro de pedidos: Permite criar, visualizar, atualizar e excluir/cancelar pedidos no sistema.
- Cadastro de itens de pedido: Permite adicionar, remover e atualizar os itens associados a um pedido.
- Listagem com paginação: Possibilita a exibição dos registros de forma paginada, para facilitar a visualização e navegação.
- Filtros de busca: Permite aplicar filtros na listagem de produtos/serviços e pedidos com base em critérios específicos.
- Validação de dados: Utiliza o Bean Validation para garantir a integridade dos dados inseridos no sistema.
- ControllerAdvice personalizado: Customiza as respostas HTTP das requisições para manter a consistência e a padronização.
- ID único para entidades: Utiliza UUID para gerar identificadores únicos para cada produto/serviço, pedido e item de pedido.
- Aplicação de desconto: Permite a aplicação de um percentual de desconto no valor total dos produtos de um pedido.
- Restrições de ações: Impede a exclusão de produtos/serviços associados a pedidos e a adição de produtos desativados em um pedido.

## Tecnologias Mínimas

- Banco de dados PostgreSQL
- Java 17+
- Maven
- Spring
- JPA
- Bean Validation
- QueryDSL
- REST com JSON
  
## Configuração do Ambiente de Desenvolvimento
- Para configurar e executar o projeto no ambiente da IDE Eclipse, siga as etapas abaixo:
- Certifique-se de ter o Eclipse IDE instalado em seu computador. Caso precise instalar o Eclipse, você pode baixá-lo [aqui](eclipse.org).
- Faça o clone deste repositório do GitHub em sua máquina local, utilizando o seguinte comando:
```
git clone https://github.com/iWagnner/DesafioSeniorSistemasN3.git
```
- Abra o Eclipse IDE e importe o projeto clicando em File > Import > Existing Maven Projects.
- Navegue até o diretório onde você fez o clone do repositório e selecione o diretório do projeto.
- Configure o banco de dados PostgreSQL. Certifique-se de ter o PostgreSQL instalado em seu sistema. Crie um banco de dados com o nome desejado para o projeto.
- Abra o arquivo application-dev.properties localizado em src/main/resources e atualize as configurações do banco de dados de acordo com as informações do seu ambiente:
```java
spring.datasource.url=jdbc:postgresql://localhost:5432/seu-banco-de-dados
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```
- Substitua seu-banco-de-dados, seu-usuario e sua-senha pelas informações correspondentes.
- Salve as alterações no arquivo application-dev.properties.
- Uma segunda opção é ultilizar as configurações de teste com o banco de dados H2.
- Configurada no arquivo application-test.properties, caso seja necessario alterar as configurações basta seguir o exemplo abaixo.
- Abra o arquivo application-test.properties localizado em src/main/resources e atualize as configurações do banco de dados de acordo com as informações do seu ambiente:
 ```java
 spring.datasource.url=jdbc:h2:mem:testdb
 spring.datasource.username=sa
 spring.datasource.password=
 spring.jpa.hibernate.ddl-auto=create-drop
 ```
- Essas configurações são adequadas para executar testes com o banco de dados H2 em memória.
- Após escolher e configurar uma das opções acima.
- Certifique-se de que o arquivo application.properties esteja configurado corretamente com o modo de configuração desejada para o seu ambiente ambiente de desenvolvimento.
```java
spring.profiles.active=suaaplication-desejada.properties
```
- Agora você pode executar o projeto clicando com o botão direito no arquivo principal (Prova3Application.java) e selecionando Run As > Java Application.
- O projeto será iniciado e estará disponível em http://localhost:8080.

## Contato
[Linkedin](https://www.linkedin.com/in/wagner-ribeiro-256a221b2/)
