## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

- Build common API
```
cd common-api
mvn clean install
```

- Run each service
```
# new terminal
cd order
mvn clean spring-boot:run

# new terminal
cd supporting
mvn clean spring-boot:run

```

- Run API gateway
```
cd gateway
mvn clean spring-boot:run
```

- Run frontend server
```
cd frontend
npm i
npm run serve

```

## Test By UI
Head to http://localhost:8088 with a web browser

## Test Rest APIs
- order
```
 http :8088/orders id="id" customId="customId" productId="productId" qty="qty" status="status" address="address" 
```
- supporting
```
 http :8088/inventories id="id" qty="qty" name="name" 
 http :8088/deliveries id="id" orderId="orderId" customId="customId" productId="productId" address="address" qty="qty" 
```

## Test RSocket APIs

- Download RSocket client
```
wget -O rsc.jar https://github.com/making/rsc/releases/download/0.4.2/rsc-0.4.2.jar
```
- Subscribe the stream
```
java -jar rsc.jar --stream  --route orders.all ws://localhost:8088/rsocket/orders

java -jar rsc.jar --stream  --route supportings.all ws://localhost:8088/rsocket/supportings

```
