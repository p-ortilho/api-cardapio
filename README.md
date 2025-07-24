# Api Cardápio

Uma API REST desenvolvida com Java 17 e Spring Boot, destinada ao gerenciamento de alimentos (cadastro, listagem e deleção).

# 📦 Endpoints

🔹 GET /food
Lista todos os alimentos cadastrados no banco de dados.

~~~
[
  {
    "name": "Arroz",
    "image": "https://exemplo.com/imagem.jpg",
    "price": 12
  }
]

~~~

🔹 POST /food
Cadastra um novo alimento.

~~~
{
  "name": "Feijão",
  "image": "https://exemplo.com/imagem.jpg",
  "price": 9
}
~~~

*A aplicação tem outras rotas como PUT, DELETE, PATCH, porém para os estudos aplicados foram somente GET e POST implementados pelo front end.* 

### Configuração Banco de dados

Não se esqueça de configurar o banco de dados no arquivo `application.properties`.

~~~
spring.application.name=cardapio
spring.datasource.url=jdbc:postgresql://localhost:5432/{nome_database}
spring.datasource.username={nome_usuario}
spring.datasource.password={senha_usuario}

spring.jpa.hibernate.ddl-auto=update
~~~