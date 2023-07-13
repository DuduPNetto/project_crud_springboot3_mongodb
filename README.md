# Project Workshop Springboot 3 and MongoDB

Projeto feito no curso Java Completo 2023. Como introdução ao Java Spring usando o MongoDB.

# Detalhes

O projeto contém um sistema com base no cadastro de usuários, posts e comentários.
É possível criar usuários e posts, e com isso adicionar comentários de outros usuários no post.

Todas as alterações são salvas no banco de dados MongoDB.

URLS Disponíveis:<br/>
http://localhost:8080/users/<br/>
http://localhost:8080/posts/<br/>
Todas as URLS podem ter um ID adicionado após a barra '/'. Para busca de um ID específico.

É possível ver todos os posts de um usuário por meio da pesquisa:<br/>
http://localhost:8080/users/{id}/posts<br/>

Exemplos:<br/>
GET - http://localhost:8080/users/ -> Retorna todos os usuários<br/>
GET - http://localhost:8080/users/100 -> Retorna o usuário de ID 100<br/>
GET - http://localhost:8080/users/{id}/posts -> Retorna os post do usuário 100<br/>
POST - http://localhost:8080/users/ -> Adiciona um usuário com informações via JSON<br/>
DELETE - http://localhost:8080/users/3 -> Deleta o usuário de ID 3<br/>
PUT - http://localhost:8080/users/7 -> Atualiza o usuário de ID 7<br/>
GET - http://localhost:8080/posts/ -> Retorna todos os posts<br/>
GET - http://localhost:8080/posts/10 -> Retorna o post de ID 10<br/>