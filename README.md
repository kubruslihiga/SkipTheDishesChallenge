# SkipTheDishesChallenge
Aplicação backend de cadastro e consulta com Springboot. Importante ter o Java 8 instalado na máquina
Para rodar uma build, rodar testes e gerar um pacote:
- ./gradlew clean build

Para subir a aplicação e disponibilizar os serviços. Importar o banco de dados num servidor MYSQL (arquivo skipthedishes_challenge.mwb):
- ./gradlew clean bootRun

No desenvolvimento é possível subir a aplicação como um standalone. Rodar pelo eclipse um "Run" na classe OrderSpringBootApplication. Dessa forma sobe-se a aplicação e os serviços sem precisar de uma build.

Serviços disponíveis:
GET - /order/{code}/status => recuperar um pedido
PUT - /order => cadastrar um pedido
DELETE - /order/{code} => excluir um pedido
GET - /product => consultar produto (parametros opcionais: name, code, price, color, width, height)
