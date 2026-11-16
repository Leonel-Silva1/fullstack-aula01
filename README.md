# Full Stack Developer --- Aula 01

Este projeto foi desenvolvido como atividade da primeira aula de Full
Stack Developer.

O objetivo foi praticar a criação de APIs utilizando Java e Spring Boot,
trabalhando com operações básicas, conversões e cadastro de pessoas.

## Tecnologias utilizadas

-   Java 17
-   Spring Boot
-   Maven
-   H2 (banco de dados)

## Exercício 1 --- Endpoints

### 1. Saudação

Criei uma rota que recebe um nome e retorna uma mensagem de boas-vindas.

Exemplo:

`GET /hello/Leonel`

Resposta:

``` text
Olá, Leonel!
```

### 2. Soma

Criei uma rota que recebe dois números e retorna o resultado da soma.

Exemplo:

`GET /calc/soma/10/5`

Resposta:

``` json
{
  "resultado": 15.0
}
```

Também foi incluído um tratamento para quando os números não forem
informados.

### 3. Conversão de temperatura

Desenvolvi uma rota para converter temperaturas entre Celsius,
Fahrenheit e Kelvin.

Exemplos:

-   Celsius para Fahrenheit
-   Fahrenheit para Celsius
-   Celsius para Kelvin
-   Kelvin para Fahrenheit

A rota recebe o valor, a escala inicial e a escala desejada.

`GET /temperatura/100/C/F`

## Exercício 2 --- Cadastro de pessoas

Nesta parte, desenvolvi um cadastro de pessoas utilizando o banco de
dados H2.

A pessoa possui dois campos: ID e nome.

Foram criadas as seguintes operações:

  Método   Função
  -------- --------------------------------
  GET      Listar pessoas cadastradas
  POST     Cadastrar uma nova pessoa
  PUT      Alterar os dados de uma pessoa
  PATCH    Alterar apenas uma informação
  DELETE   Excluir uma pessoa

### Exemplo de cadastro

``` json
{
  "nome": "Maria"
}
```

### Exemplo de atualização

``` json
{
  "nome": "Maria Silva"
}
```

## Observação

O banco de dados utilizado foi o H2 em memória. Por isso, os cadastros
são reiniciados quando a aplicação é encerrada.

Este projeto faz parte dos meus estudos e foi desenvolvido para praticar
os conteúdos apresentados na aula.
