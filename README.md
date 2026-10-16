# API REST: Gestión de Productos y Proveedores (Enterprise)

Proyecto de prueba técnica desarrollado con **Java 17**, **Spring Boot 3.2.5**, **Spring Data JPA**, y **PostgreSQL**. Esta aplicación implementa una arquitectura limpia y modular de nivel enterprise orientada a la gestión y administración segura de catálogos de productos y sus respectivos proveedores, incluyendo soporte para documentación interactiva con **Swagger / OpenAPI**.

---

## 🚀 Características Principales

*   **Arquitectura Modular:** Separación estricta de capas (`controllers`, `services`, `repositories`, `entities`, `dtos`).
*   **DTOs Separados:** Implementación de objetos de transferencia de datos independientes para peticiones (`RequestDto`) y respuestas (`ResponseDto`) para proteger las entidades de la base de datos.
*   **Manejo de Paginación y Ordenamiento:** Consultas optimizadas con `Pageable` para listar grandes volúmenes de datos.
*   **Validaciones de Negocio:** Prevención de duplicados (como correos electrónicos únicos en proveedores) y validación de existencia de relaciones (asegurar que un producto pertenezca a un proveedor válido).
*   **Documentación Interactiva (Swagger/OpenAPI 3):** Interfaz gráfica integrada para probar y documentar todos los endpoints en tiempo real.

---

## 🛠️ Tecnologías Utilizadas

*   **Java JDK 17**
*   **Spring Boot 3.2.5**
*   **Spring Data JPA** (Persistencia y ORM con Hibernate)
*   **Spring Web** (Creación de servicios RESTful)
*   **PostgreSQL** (Base de datos relacional)
*   **Lombok** (Reducción de código repetitivo)
*   **Springdoc OpenAPI UI** (Swagger UI v3)

---

## ⚙️ Configuración del Entorno

### 1. Requisitos Previos
*   Tener instalado **Java JDK 17** o superior.
*   Tener instalado **PostgreSQL** y un cliente de gestión (como pgAdmin).
*   Un IDE compatible (se recomienda **IntelliJ IDEA**).

### 2. Configuración de la Base de Datos
Crea una base de datos vacía en PostgreSQL con el siguiente nombre:
```sql
CREATE DATABASE db_productos_proveedores;
```

### 3. Configuración de Propiedades (`application.properties`)
En la ruta `src/main/resources/application.properties`, asegúrate de tener configurados los accesos a tu base de datos:

```properties
spring.application.name=productos_proveedores

# Conexión a PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/db_productos_proveedores
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

# Configuración de Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configuración del Puerto del Servidor
server.port=8080

# Configuración de Swagger UI
springdoc.swagger-ui.path=/swagger-ui/index.html
```

---

## ▶️ Ejecución del Proyecto

1. Clona este repositorio o abre la carpeta del proyecto en **IntelliJ IDEA**.
2. Espera a que **Maven** descargue e indexe todas las dependencias del archivo `pom.xml`.
3. Ejecuta la clase principal del proyecto:
   `src/main/java/com/example/productos_proveedores/ProductosProveedoresApplication.java`
4. La aplicación iniciará en el puerto `8080`.

---

## 📖 Documentación y Pruebas (Swagger UI)

Una vez que la aplicación esté ejecutándose, puedes acceder a la interfaz gráfica interactiva de Swagger para probar los endpoints directamente desde tu navegador:

👉 **URL de Swagger:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Endpoints Principales Disponibles:

*   **Proveedores (`/api/proveedores`):**
    *   `GET /api/proveedores` - Listar todos los proveedores (con soporte de paginación).
    *   `GET /api/proveedores/{id}` - Buscar proveedor por su ID.
    *   `POST /api/proveedores` - Registrar un nuevo proveedor.
    *   `PUT /api/proveedores/{id}` - Actualizar la información de un proveedor existente.

*   **Productos (`/api/productos`):**
    *   `GET /api/productos` - Listar todos los productos (con soporte de paginación).
    *   `GET /api/productos/{id}` - Buscar producto por su ID.
    *   `POST /api/productos` - Registrar un nuevo producto asociado a un proveedor.
    *   `PUT /api/productos/{id}` - Actualizar la información de un producto.
    *   `DELETE /api/productos/{id}` - Eliminar un producto del sistema.
	

## 📂 Documentación adicional

En la carpeta [`documentacion/`](documentacion/) encontrarás:

*   `Prueba Tecnica-Avanzada.pdf`: enunciado de la prueba técnica.
*   `script_db_productos.sql`: script de la base de datos (tablas y datos de ejemplo).
*   `api_productos_proveedores.postman_collection.json`: colección de Postman para probar todos los endpoints (importar desde Postman con *File → Import*).
*   `docker-comandos.txt`: comandos para ejecutar el proyecto con Docker.
*   `spring initializer.png`: captura de la configuración usada en Spring Initializr.

## 👤 Autor

**Ing.Miguel Antonio Rodríguez Guevara**

*   GitHub: [mrodriguez882013](https://github.com/mrodriguez882013)
*   LinkedIn: [tu-perfil](https://www.linkedin.com/in/tu-perfil)
*   Correo: mrodriguez882013@gmail.com

> Proyecto desarrollado como prueba técnica Java.