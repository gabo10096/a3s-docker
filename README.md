# Proyecto MicroServicios

## Requerimientos

- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Liquibase 3.5.5](https://download.liquibase.org/download/?frm=n)

# Configuración

## Levantar DB

User: postgres

```bash

docker run --name postgresdb -p 5432:5432 -e POSTGRES_DB=catalogs -e POSTGRES_PASSWORD=postgres123 -d postgres

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

## Levantar API

```bash

cd SpringbootCourse/
mvn clean package -DskipTests
java -jar catalogos-web/target/*.jar

```
## Probar 

[Swagger UI](http://localhost:8090/swagger-ui.html)
  
```http

GET http://localhost:8090/catalogos/test
GET http://localhost:8090/admin/users
POST http://localhost:8090/admin/users
PUT http://localhost:8090/admin/users
DELETE http://localhost:8090/admin/users/id

```

[Postman Collection](https://www.getpostman.com/collections/ffc69f49936c30c2c9e7)


# Generar los contenedores

## Dockerfile

```bash 

docker build -t api_catalogs -f Dockerfile .
docker run -d -p 8090:8090 api_catalogs

```

## docker-compose

```bash 

docker-compose up -d
docker-compose down --rmi all      

```

# Configuración de red de contenedores

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