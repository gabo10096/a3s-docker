# Proyecto MicroServicios

## Requerimientos

- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Liquibase 3.5.5](https://download.liquibase.org/download/?frm=n)

## Compilación

```bash

cd api-manager/
mvn clean package

cd order-service/
mvn clean package

cd product-service/
mvn clean package

cd user-service/
mvn clean package

```

## Levantar DB
User: postgres

```bash

docker run --name postgresdb -p 5432:5432 -e POSTGRES_DB=unbound -e POSTGRES_PASSWORD=postgres123 -d postgres

```

## Configurar liquibase

Despues de descargar liquibase-3.5.5-bin.tar.gz, descomprimirlo y ejecutar lo siguiente:

```bash

sudo mv liquibase-3.5.5-bin/ /opt/liquibase-3.5.5-bin
export PATH=/opt/liquibase-3.5.5-bin:$PATH

```

## Actualizar DB

```bash

cd liquibase/
liquibase --changeLogFile="changesets/db.changelog-master.xml" update

```

## Levantar servicios

```bash

cd api-manager/
java -jar target/*.jar

cd order-service/
java -jar order-web/target/*.jar

cd product-service/
java -jar product-web/target/*.jar

cd user-service/
java -jar user-web/target/*.jar

```
## Probar 

### Desde Api Manager
  
```http

GET http://localhost:8000/api/orders/order/test
GET http://localhost:8000/api/products/product/test
GET http://localhost:8000/api/users/user/test

```

### Directo a los servicios

```http

GET http://localhost:8200/order/test
GET http://localhost:8300/product/test
GET http://localhost:8100/user/test

```

[Postman Collection](https://www.getpostman.com/collections/0e7c92036c7de539b9c3)


## Generar los contenedores

# Dockerfile-apimanager

```bash 

docker build -t api_manager -f Dockerfile-apimanager .
docker run -d -p 8000:8000 api_manager

```

# Dockerfile-orderservice

```bash 

docker build -t order_service -f Dockerfile-orderservice .
docker run -d -p 8200:8200 order_service

```

# Dockerfile-productservice

```bash 

docker build -t product_service -f Dockerfile-productservice .
docker run -d -p 8300:8300 product_service

```

# Dockerfile-userservice

```bash 

docker build -t user_service -f Dockerfile-userservice .
docker run -d -p 8100:8100 user_service

```

## Generar docker-compose

```bash 

docker-compose up -d
docker-compose down --rmi all      

```

## Red de contenedores 

```bash 

docker network ls 
docker inspect [NETWORK_ID]

```

## Agregar contenedor a red

```bash 

docker network connect [NETWORK_ID] [CONTAINER_ID]

```

## Quitar contenedor de red

```bash 

docker network disconnect [NETWORK_ID] [CONTAINER_ID]

```



