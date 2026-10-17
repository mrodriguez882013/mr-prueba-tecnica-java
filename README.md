# API REST: Gestión de Productos y Proveedores

Proyecto de prueba técnica desarrollado con **Java 17**, **Spring Boot 4.1.1**, **Spring Data JPA** y **PostgreSQL**. Permite administrar productos y proveedores, asociar una fotografía a cada producto e incluye documentación interactiva con **Swagger / OpenAPI**.

---

## 🚀 Características Principales

**Requerimientos de la prueba**
*   CRUD de proveedores: registrar, listar, consultar por ID y actualizar.
*   CRUD de productos: registrar, listar, consultar por ID, actualizar y eliminar.
*   Fotografía asociada a cada producto (almacenada como URL).
*   Persistencia en PostgreSQL con Spring Data JPA.

**Extras implementados**
*   **Paginación y ordenamiento** en los listados con `Pageable`.
*   **Validaciones de negocio:** correos únicos en proveedores y verificación de que el producto pertenezca a un proveedor válido.
*   **DTOs separados** para peticiones (`RequestDto`) y respuestas (`ResponseDto`), para no exponer directamente las entidades.
*   **Arquitectura modular** en capas (`controllers`, `services`, `repositories`, `entities`, `dtos`).
*   **Swagger / OpenAPI 3** para probar y consultar los endpoints.
*   **Docker y Docker Compose** para levantar la API y la base de datos sin configuración manual.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión |
|------------|---------|
| Java (JDK) | 17 |
| Spring Boot | 4.1.1 |
| Spring Data JPA (Hibernate) | Gestionada por Spring Boot |
| Spring Web (REST) | Gestionada por Spring Boot |
| PostgreSQL | 17 (imagen `postgres:17-alpine` en Docker) |
| Springdoc OpenAPI (Swagger UI) | 3.1.1 |
| Lombok | Gestionada por Spring Boot |
| Maven (con Maven Wrapper) | 3.9.x |
| Docker / Docker Compose | — |

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

## ⚙️ Requisitos Previos

*   **Java JDK 17** o superior.
*   **Docker** y **Docker Compose** (opción recomendada, no requiere instalar PostgreSQL).
*   **PostgreSQL**, solo si ejecutarás la app con Maven/IntelliJ sin Docker.
*   Un IDE compatible (se recomienda **IntelliJ IDEA**).

---

## ▶️ Ejecución del Proyecto

### Opción 1: Con Docker (recomendada)

1. Clona el repositorio:
   ```bash
   git clone https://github.com/mrodriguez882013/mr-prueba-tecnica-java.git
   cd mr-prueba-tecnica-java/api_productos_proveedores
   ```
2. Levanta la API y la base de datos:
   ```bash
   docker-compose up --build
   ```
3. Espera a que la consola muestre `Started ProductosProveedoresApplication`.

Docker crea la base de datos `db_productos_proveedores` y ejecuta automáticamente el script `documentacion/script_db_productos.sql`.

Con la aplicación en ejecución, abre **Swagger UI**: http://localhost:8080/swagger-ui/index.html

Para detener los contenedores: `Ctrl+C` o `docker-compose down`.

> **Notas:**
> *   Las credenciales del entorno Docker (`postgres` / `*Alfa2026`) son solo para esta prueba técnica.
> *   Si ya habías levantado el proyecto antes con otra configuración, ejecuta `docker-compose down -v` antes de volver a iniciarlo (borra el volumen de la base de datos y la recrea desde el script SQL).
> *   Si el puerto `5432` está ocupado por un PostgreSQL local, cambia en el `docker-compose.yml` `"5432:5432"` por `"5433:5432"`.

### Opción 2: Con Maven / IntelliJ

Requiere PostgreSQL instalado y en ejecución.

1. Crea la base de datos vacía:
   ```sql
   CREATE DATABASE db_productos_proveedores;
   ```
   Opcionalmente, ejecuta `documentacion/script_db_productos.sql` para cargar el esquema y los datos de ejemplo.

2. Revisa las credenciales en `api_productos_proveedores/src/main/resources/application.properties`:
   ```properties
   spring.application.name=productos_proveedores

   # Conexión a PostgreSQL
   spring.datasource.url=jdbc:postgresql://localhost:5432/db_productos_proveedores
   spring.datasource.username=postgres
   spring.datasource.password=*Alfa2026

   # Hibernate / JPA
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   # Servidor
   server.port=8080

   # Swagger UI
   springdoc.swagger-ui.path=/swagger-ui/index.html
   ```
   Ajusta `username` y `password` si tu PostgreSQL local usa otros datos.

3. Ejecuta la aplicación desde la carpeta `api_productos_proveedores/`:
   ```bash
   ./mvnw spring-boot:run
   ```
   (En Windows: `mvnw.cmd spring-boot:run`)

   También puedes abrir esa carpeta en **IntelliJ IDEA**, esperar a que Maven descargue las dependencias y ejecutar la clase principal:
   `src/main/java/com/example/api_productos_proveedores/ProductosProveedoresApplication.java`

4. Con la aplicación en ejecución, abre Swagger en http://localhost:8080/swagger-ui/index.html

---

## 📖 Documentación y Pruebas

### Swagger UI

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

### Fotografía del producto

La fotografía de cada producto se almacena como una **URL** (campo `fotografia`) que apunta a la imagen. Se envía como texto dentro del JSON en el `POST` y `PUT` de `/api/productos`, y se devuelve en las respuestas de consulta.

Ejemplo:

```json
{
  "fotografia": "https://ejemplo.com/imagenes/producto1.jpg"
}
```

---

## 📂 Documentación Adicional

En la carpeta [`documentacion/`](documentacion/) encontrarás:

*   `Prueba Tecnica-Avanzada.pdf`: enunciado de la prueba técnica.
*   `script_db_productos.sql`: script de la base de datos.
*   `api_productos_proveedores.postman_collection.json`: colección de Postman para probar los endpoints.
*   `docker-comandos.txt`: comandos usados para ejecutar el proyecto con Docker.
*   `spring initializer.png`: captura de la configuración usada en Spring Initializr.

---

## 👤 Autor

**Ing. Miguel Antonio Rodríguez Guevara**

*   GitHub: [mrodriguez882013](https://github.com/mrodriguez882013)
*   Correo: mrodriguez882013@gmail.com

> Proyecto desarrollado como prueba técnica Java.
