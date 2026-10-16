# API REST: Gestión de Productos y Proveedores

Proyecto de prueba técnica desarrollado con **Java 17**, **Spring Boot 3.2.5**, **Spring Data JPA** y **PostgreSQL**. Implementa una arquitectura modular en capas para la gestión de un catálogo de productos y sus proveedores, e incluye documentación interactiva con **Swagger / OpenAPI**.

---

## 🚀 Características Principales

*   **Arquitectura Modular:** Separación de capas (`controllers`, `services`, `repositories`, `entities`, `dtos`).
*   **DTOs Separados:** Objetos independientes para peticiones (`RequestDto`) y respuestas (`ResponseDto`), para no exponer directamente las entidades de la base de datos.
*   **Paginación y Ordenamiento:** Listados con `Pageable` para manejar grandes volúmenes de datos.
*   **Validaciones de Negocio:** Prevención de duplicados (como correos únicos en proveedores) y validación de relaciones (un producto debe pertenecer a un proveedor válido).
*   **Documentación Interactiva (Swagger/OpenAPI 3):** Interfaz para probar y consultar los endpoints en tiempo real.
*   **Contenedores:** `Dockerfile` y `docker-compose.yml` para levantar el proyecto sin configuración manual.

---

## 🛠️ Tecnologías Utilizadas

*   **Java JDK 17**
*   **Spring Boot 3.2.5**
*   **Spring Data JPA** (Hibernate)
*   **Spring Web** (servicios RESTful)
*   **PostgreSQL**
*   **Lombok**
*   **Springdoc OpenAPI UI** (Swagger UI v3)
*   **Maven** y **Docker**

---

## 🗂️ Estructura del Proyecto

```
mr-prueba-tecnica-java/
├── api_productos_proveedores/   # Código fuente (Spring Boot)
│   ├── src/main/java/...        # controllers, services, repositories, entities, dtos
│   ├── src/main/resources/      # application.properties
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── pom.xml
└── documentacion/               # Postman, script SQL, PDF y comandos Docker
```

---

## ⚙️ Configuración del Entorno

### 1. Requisitos Previos
*   **Java JDK 17** o superior.
*   **PostgreSQL** y un cliente de gestión (como pgAdmin), si no usarás Docker.
*   **Docker** y **Docker Compose**, si prefieres la ejecución con contenedores.
*   Un IDE compatible (se recomienda **IntelliJ IDEA**).

### 2. Base de Datos
Crea una base de datos vacía en PostgreSQL:

```sql
CREATE DATABASE db_productos_proveedores;
```

En `documentacion/script_db_productos.sql` encontrarás el script de la base de datos.

### 3. Configuración de Propiedades
Archivo: `api_productos_proveedores/src/main/resources/application.properties`

```properties
spring.application.name=productos_proveedores

# Conexión a PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/db_productos_proveedores
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

# Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Servidor
server.port=8080

# Swagger UI
springdoc.swagger-ui.path=/swagger-ui/index.html
```

Ajusta `username` y `password` a los de tu PostgreSQL local.

---

## ▶️ Ejecución del Proyecto

### Opción 1: Con Maven / IntelliJ

1. Clona el repositorio:
```bash
   git clone https://github.com/mrodriguez882013/mr-prueba-tecnica-java.git
   cd mr-prueba-tecnica-java/api_productos_proveedores
```
2. Ejecuta la aplicación:
```bash
   ./mvnw spring-boot:run
```
   (En Windows: `mvnw.cmd spring-boot:run`)

   También puedes abrir la carpeta `api_productos_proveedores` en **IntelliJ IDEA**, esperar a que Maven descargue las dependencias y ejecutar la clase principal:
   `src/main/java/com/example/api_productos_proveedores/ProductosProveedoresApplication.java`
3. La aplicación iniciará en el puerto `8080`.

### Opción 2: Con Docker

Desde la carpeta `api_productos_proveedores/`:

```bash
docker-compose up --build
```

La API quedará disponible en `http://localhost:8080`. Los comandos usados durante el desarrollo están en `documentacion/docker-comandos.txt`.

---

## 📖 Documentación y Pruebas

### Swagger UI

Con la aplicación en ejecución:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Postman

Importa la colección `documentacion/api_productos_proveedores.postman_collection.json` en Postman (*File → Import*). Los endpoints apuntan a `http://localhost:8080`.

### Endpoints Principales

**Proveedores (`/api/proveedores`)**

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/proveedores` | Listar proveedores (con paginación) |
| GET | `/api/proveedores/{id}` | Buscar proveedor por ID |
| POST | `/api/proveedores` | Registrar un proveedor |
| PUT | `/api/proveedores/{id}` | Actualizar un proveedor |

**Productos (`/api/productos`)**

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/productos` | Listar productos (con paginación) |
| GET | `/api/productos/{id}` | Buscar producto por ID |
| POST | `/api/productos` | Registrar un producto asociado a un proveedor |
| PUT | `/api/productos/{id}` | Actualizar un producto |
| DELETE | `/api/productos/{id}` | Eliminar un producto |

---

## 📂 Documentación Adicional

En la carpeta [`documentacion/`](documentacion/) encontrarás:

*   `Prueba Tecnica-Avanzada.pdf`: enunciado de la prueba técnica.
*   `script_db_productos.sql`: script de la base de datos.
*   `api_productos_proveedores.postman_collection.json`: colección de Postman para probar los endpoints.
*   `docker-comandos.txt`: comandos para ejecutar el proyecto con Docker.
*   `spring initializer.png`: captura de la configuración usada en Spring Initializr.

---

## 👤 Autor

**Ing. Miguel Antonio Rodríguez Guevara**

*   GitHub: [mrodriguez882013](https://github.com/mrodriguez882013)
*   Correo: mrodriguez882013@gmail.com

> Proyecto desarrollado como prueba técnica Java.