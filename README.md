# RABBITMQ Config

### Start rabbitmq container
- Run:
```shell
 docker-compose up --build -d
```
- Open http://localhost:15673
    - username: guest
    - password: guest
    - tags: Management
- Go to Admin > Virtual Hosts
  - Create a virtual host called 'batch_consuming'
- Go to Admin > Users
  - Create an user 'consumer'
  - Set password as 'consumer'
  - At the users list, click on 'consumer' user
  - In Permissions, select the 'batch_consuming' virtual host.
  - Click on 'Set Permission'
- Go to Exchanges > Add new exchange
  - Name: batch_exchange
  - Type: direct
  - Durability: Durable
  - Auto delete: No
  - Internal: no
- Go to Queues & Streams > Add new queue
  - Select the virtual host 'batch_consuming'
  - Type: Classic
  - Name: batch_queue
  - Durability: Durable
  - Auto delete: No
- At the queues list, click on 'batch_queue'
  - Go to Bindings > Add binding to this queue
  - From exchange: batch_exchange
  - Routing key: batch_key
  - Click on Bind


## IncomingMessage
```json
{"name": "John Doe","document": "123456789"}
```