<a href="https://eclub.com.py/" target="blank">
	<img src="https://eclub.com.py/static/web/img/svg/eclub-logo.svg" width="320" alt="eClub Logo" />
</a>

# conti-connec-service

MicroServicio de eCLUB que da de Alta a un Nuevo usuario en el Banco Continental
y tambien realiza el envio de documentos firmados(Solicitud de Clientes) al Banco 
para registrar el seguimiento de la tarjeta.

# Contenido

- [Configuración](#configuracion)
    - [Variables de entorno](#variables-de-entorno)
    - [Base de datos](#base-de-datos)
    - [Certificados](#certificados)
- [Directorios](#carpetas)
    - [Carpetas](#carpetas)	
- [Docker](#docker)
    - [Crear imágenes](#crear-imagenes)
    - [Recomendaciones](#recomendaciones)
- [Comandos maven](#comandos-maven)

<a name="configuracion"></a>
# Configuración

Para poder correr exitosamente el proyecto, en el entorno que fuese, se recomienda seguir los pasos expuestos a
continuación en las secciones [Variables de entorno](#variables-de-entorno) y [Base de datos](#base-de-datos).

Una vez configurado el proyecto puede correrse desde Docker, maven, desde la terminal o desde el IDE que se use.

<a name="variables-de-entorno"></a>
## Variables de entorno

Independientemente del IDE que utilices, debes asignar el valor correspondiente a las siguientes variables de entorno
para levantar el proyecto:

```dotenv
API_CONTI_CLIENT_ID=
API_CONTI_CLIENT_SECRET=
API_CONTI_COMM_PROTOCOL=
API_CONTI_CONNTIMEOUT=
API_CONTI_READTIMEOUT=
API_CONTI_SSLFILEPATH=
API_CONTI_SSLPWD=
API_CONTI_SUBSCRIPTION_KEY=
API_CONTI_URL_BASE=
API_CONTI_URL_GET_BY_NRO_SOLICITUD=
API_CONTI_URL_GET_BY_STATE=
API_CONTI_URL_POST_REGISTRAR_SEGUIMIENTO=
API_CONTI_URL_POST_SOLICITUD=
API_CONTI_URL_POST_TOKEN=
CONFIGURACION_AMBIENTE=
DATASOURCE_DRIVER_CLASS_NAME=
DATASOURCE_PASSWORD=
DATASOURCE_URL=
DATASOURCE_USERNAME=
FEING_CLIENT_CONFIG_DEFAULT_CONNECTIONTIMEOUT=
FEING_CLIENT_CONFIG_DEFAULT_LOGGERLEVEL=
FEING_CLIENT_CONFIG_DEFAULT_READTIMEOUT=
FEING_HTTPCLIENT_DISABLESSLVALIDATION=
FEING_HTTPCLIENT_ENABLED=
FEING_OKHTTP_ENABLED=
JPA_DATABASE=
JPA_DATABASE_PLATFORM=
JPA_HIBERNATE_DDL_AUTO=
JPA_HIBERNATE_PROPERTIES_HIBERNATE_DIALECT=
JPA_HIBERNATE_PROPERTIES_HIBERNATE_FORMAT_SQL=
JPA_SHOW_SQL=
LOGGING_LEVEL_ECLUB_COM_CONTICONNEC_CLIENT=
LOGGING_LEVEL_ROOT=
MS_USER_ACCOUNT_URL_BASE=
MS_USER_AUTORIZATION=
RESOURCES_FILES_PENDIENTES=
RESOURCES_FILES_PROCESADOS=
SCHEDULED_TASK_REGISTRARSEGUIMIENTOPORLOTES=
SERVER_ADDRESS_IP=
SERVER_DEBUG=
SERVER_PORT=
SPRING_APPLICATION_NAME=
SPRING_OUTPUT_ANSI_ENABLED=
```

Para obtener más información sobre las variables de entorno consultá el archivo `application.yml` localizado
en `src/main/resources`.

<a name="carpetas"></a>
## Directorios

Se deben crear dos directorios que serviran como almacenamiento de archivos de Solicitudes de clientes:
La ruta de los directorios se debe de especificar en la variable de entorno: 
- RESOURCES_FILES_PENDIENTES (Donde se almacenaran los archivos que estan pendientes de procesamiento).
- RESOURCES_FILES_PROCESADOS (Donde se almacenaran los archivos que ya se enviaron al Banco y se procesaron).

<a name="base-de-datos"></a>
## Base de datos

Para correr la base de datos PostgreSQL como contenedor en Docker, configurar las variables de acuerdo al caso:

```dotenv
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
```

<a name="certificados"></a>
## Certificados

El proyecto utiliza certificados .pfx, Se debe crear una carpeta 'certificados' y almacenarlo(copiar) 
dentro de dicho directorio.

La ruta donde se almacenara dicho archivo se definir en la variable de entorno: API_CONTI_SSLFILEPATH

<a name="docker"></a>
# Docker

El proyecto está preparado para correrse en Docker. Contiene dos servicios: el primero de ellos es `postgreSQL` para levantar
una nueva instancia de base de datos, y el otro es llamado `api` que contiene el microservicio en Spring Boot.

En la raíz del repositorio se encuentra el archivo `docker-compose.example.yml` que contiene la configuración de Docker
para correr el proyecto a través de docker-compose. Recordá renombrar este archivo como `docker-compose.yml`.

Para levantar todos los servicios del proyecto en Docker como ambiente de pruebas de forma local, se debe ejecutar el
comando `docker-compose up -d` o `docker compose up -d` según la versión con la que se esté trabajando.

Para levantar servicios de forma individual nada más, se debe ejecutar el comando `docker-compose up -d <servicio>`,
donde `<servicio>` corresponde al nombre del servicio que se desea levantar como figura en el
archivo `docker-compose.example.yml`.

<a name="crear-imagenes"></a>
## Crear imágenes

Para crear imágenes de Docker del proyecto, tener en cuenta la versión actual y poner el tag correspondiente ejecutando
el siguiente comando estando situado en la raíz del proyecto en donde se encuantra el archivo `Dockerfile`:

```bash
docker build -t <nombre_imagen>:<version> .
```

Donde `<nombre_imagen>` puede corresponder al nombre del proyecto y `<version>` a la versión actual. Ejemplo:

```bash
docker build -t conti-connect-service:v1.0.0 .
```

Una vez creada la imagen, esta estará disponible en el repositorio local de la computadora o servidor. Para consultar el
listado de imágenes disponibles correr el comando `docker image ls`.

<a name="recomendaciones"></a>
## Recomendaciones

Al momento de subir imágenes de Docker al repositorio de imágenes correspondiente sea en GitHub, DockerHub u otro lugar,
se debe usar el tag `latest` para producción y el tag `dev` para staging, ejemplo:

```bash
docker build -t conti-connect-service:latest .
docker build -t conti-connect-service:dev .
```

<a name="comandos-maven"></a>
# Comandos maven

A continuación se reúnen algunos comandos útiles para trabajar con el proyecto desde la línea de comando.

Empaquetar el proyecto:

```bash
mvn clean package
```

Correr el servicio:

```bash
mvn spring-boot:run
```

[1]: https://docs.oracle.com/en/java/javase/12/tools/keytool.html