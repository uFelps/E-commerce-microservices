# E-commerce microservices

Repositório dedicado para a implementação prática de um [projeto teórico](https://www.youtube.com/watch?v=XyV2fgyM7lQ&t=712s)
apresentado pelo [Matheus Pieropan](https://www.linkedin.com/in/matheuspieropan/)
do canal [Matheus do Java](https://www.youtube.com/@MatheusdoJava).

### Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- AMQP
- RabbitMQ
- Docker

## Sobre
O projeto simula um sistema de E-commerce, contendo diversos componentes como:

- Order-ms (Serviço de Pedido)
- Payment-ms (Serviço de Pagamento)
- Notification-ms (Serviço de Notificação)
- Eureka-server
- Gateway

A cada pedido novo realizado, ou a cada pagamento realizado, os microsserviços devem
se comunicar entre si através de mensagens assíncronas, gerando a transição e persistência
de dados.

<hr>

<br>

## Principais Funcionalidades

### Criar pedido
Quando um pedido for criado a partir do order-ms, os serviços de pagamento e notificação
devem ser avisados, para que seja gerado um novo pagamento pendente, e para que o cliente
dono do pedido seja alertado de que o seu pedido foi criado.

<br>

<img src="https://github.com/uFelps/assets/blob/main/E-commerce-microsservices/img1.png?raw=true">


### Efetuar Pagamento
Quando um pagamento for efetuado a partir do payment-ms, os serviços de pedido e notificação
devem ser avisados, para que o pedido seja atualizado e confirmado, e para que o cliente
dono do pedido seja alertado de que foi efetuado com sucesso o pagamento.

<br>

<img src="https://github.com/uFelps/assets/blob/main/E-commerce-microsservices/img2.png?raw=true">



<hr>

<br>

## Clonando o Repositório

```bash
git clone https://github.com/uFelps/E-commerce-microservices.git
cd E-commerce-microservices
```

### Rodando o Docker-Compose

```bash
docker-compose up
```

### AWS SES
No notification-ms, é possível utilizar o [AWS Simple Email Service](https://aws.amazon.com/pt/ses/) para
enviar notificações reais no email desejado.
Para isso, deve ser configurado no `docker-compose.yml`, nas propriedades do
notification-ms, as variáveis de ambiente:

```bash
- AWS_ACCESSKEY=
- AWS_SECRETKEY=
- EMAIL_SOURCE=
```

** EMAIL_SOURCE: email responsável por enviar os emails, e deve estar pré-configurado
em sua conta da AWS

## Utilizando as API's

### 1. | Criando Customer

Você pode começar criando um novo customer enviando uma request `POST` para a url:`http://localhost:8080/order-ms/api/customers/createCustomer`

Body
```bash
{
    "name": "John Frusciante",
    "cpf": "999.999.999-99",
    "email": "johnfrusciante@gmail.com"
}
```

<br>

### 2. | Criando Order

Após criar o customer, você já consegue criar o seu pedido enviando uma request `POST` na URL `http://localhost:8080/order-ms/api/orders/createOrder`

Body
```bash
{
    "customerId": 1,
    "items": [
        {
            "item": {
                "name": "PC Gamer",
                "price": 3000
            },
            "quantity": 1
        },
        {
            "item": {
                "name": "Monitor",
                "price": 1200
            },
            "quantity": 2
        }
    ]
}
```

Você pode conferir se o pedido foi criado com sucesso mandando uma request `GET` na url: `http://localhost:8080/order-ms/api/orders/{id}`

Quando criado o pedido, é enviado mensagem para os serviços de <b>pagamento</b> e <b>notificação</b>:

<img src="https://github.com/uFelps/assets/blob/main/E-commerce-microsservices/img3.png?raw=true">

Quando os serviços receberem a mensagem:

- O <b>payment-ms</b> irá criar um novo pagamento e deixa-lo como `PENDING`
- O <b>notification-ms</b> irá enviar uma notificação ao customer

<br>

### 3. | Efetuando Pagamento

Após criar o pedido, e gerar um pagamento pendente, você pode efetuar esse pagamento
enviando uma request `PUT` na url: `http://localhost:8080/payment-ms/api/payment/pay/{id_payment}`

No Body, você deve passar o método de pagamento `(PIX, CREDIT_CARD, DEBIT_CARD;)`

Body
```bash
"PIX"
```

Quando o pagamento for efetuado pelo payment-ms, será enviado mensagem para os serviços
de <b>pedido</b> e <b>notificação</b>:

<img src="https://github.com/uFelps/assets/blob/main/E-commerce-microsservices/img4.png?raw=true">

Quando os serviços receberem a mensagem:

- O <b>order-ms</b> irá atualizar o status do pedido para `CONFIRMED`
- O <b>notification-ms</b> irá enviar uma notificação ao customer