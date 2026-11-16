# Full Stack Developer — Aula 01

Projeto com a implementação dos exercícios de APIs REST utilizando **Java 17**, **Spring Boot**, **Spring Data JPA** e banco de dados **H2**.

## Requisitos

- Java 17 ou superior
- Maven 3.6+ (ou utilizar o Maven integrado da IDE)

## Como executar

1. Abra a pasta do projeto na IDE (IntelliJ IDEA, por exemplo).
2. Aguarde o Maven baixar as dependências.
3. Execute a classe `AulaApplication`.
4. A API ficará disponível em `http://localhost:8080`.

## Exercício 1 — Endpoints

### 1. Saudação

`GET /hello/{nome}`

Exemplo:

```http
GET http://localhost:8080/hello/Leonel
```

Resposta:

```text
Olá, Leonel!
```

### 2. Soma

`GET /calc/soma/{a}/{b}`

Exemplo:

```http
GET http://localhost:8080/calc/soma/10/5
```

Resposta:

```json
{
  "resultado": 15.0
}
```

Se os parâmetros não forem informados, a API retorna HTTP 400 com uma mensagem clara:

- `GET /calc/soma`
- `GET /calc/soma/10`

### 3. Conversão de temperatura

`GET /temperatura/{valor}/{de}/{para}`

As escalas aceitas são `C` (Celsius), `F` (Fahrenheit) e `K` (Kelvin).

Exemplos:

```http
GET http://localhost:8080/temperatura/100/C/F
GET http://localhost:8080/temperatura/32/F/C
GET http://localhost:8080/temperatura/0/C/K
GET http://localhost:8080/temperatura/300/K/F
```

A resposta informa o valor original, as escalas e o resultado. Se `de` ou `para` não for C, F ou K, a API retorna HTTP 400.

## Exercício 2 — Pessoas

A entidade `Pessoa` possui `id` e `nome`. Os dados são persistidos no H2 em memória.

### Listar pessoas

`GET /pessoa`

### Cadastrar pessoa

`POST /pessoa`

Corpo JSON:

```json
{
  "nome": "Maria"
}
```

### PUT — substituir os dados

`PUT /pessoa/{id}`

Corpo JSON:

```json
{
  "nome": "Maria Silva"
}
```

O PUT substitui os dados da pessoa. Neste exercício, o nome é o único campo editável.

### PATCH — atualização parcial

`PATCH /pessoa/{id}`

Corpo JSON:

```json
{
  "nome": "Maria Souza"
}
```

Atualiza somente o campo enviado.

### DELETE — excluir pessoa

`DELETE /pessoa/{id}`

Quando a exclusão é realizada, retorna **HTTP 204 No Content**, sem corpo na resposta.

Se o ID não existir, os endpoints de alteração e exclusão retornam HTTP 404.

## Console do banco H2

Com a aplicação em execução, acesse:

`http://localhost:8080/h2-console`

Configurações:

- JDBC URL: `jdbc:h2:mem:aula01db`
- User Name: `sa`
- Password: deixe em branco

> Observação: o H2 está configurado em memória. Os registros são apagados quando a aplicação é encerrada.

## Organização do projeto

```text
src/main/java/br/com/aula/fullstack
├── AulaApplication.java
├── controller
│   ├── CalculadoraController.java
│   ├── HelloController.java
│   ├── PessoaController.java
│   └── TemperaturaController.java
├── model
│   └── Pessoa.java
└── repository
    └── PessoaRepository.java
```

## Subir para o GitHub

Dentro da pasta do projeto, execute:

```bash
git init
git add .
git commit -m "Implementa exercicios da aula 01"
git branch -M main
git remote add origin URL_DO_SEU_REPOSITORIO
git push -u origin main
```

Substitua `URL_DO_SEU_REPOSITORIO` pela URL do repositório criado no GitHub.
