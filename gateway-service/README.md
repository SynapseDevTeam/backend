# 🚪 Synapse API Gateway
El portero del ecosistema. Este servicio es el único punto de entrada público y se encarga de que nadie entre al búnker sin permiso.

### 🛠️ Stack Técnico
* **Spring Cloud Gateway MVC**: Orquestación de rutas.
* **Java 21**: Runtime.
* **CORS Centralizado**: Configurado para permitir tráfico desde React y App Móvil.

### 🎯 Responsabilidades
* **Enrutamiento Dinámico**: Redirige peticiones a los microservicios internos usando la red de Docker.
* **Seguridad**: Filtrado de cabeceras y validación preliminar.
* **Abstracción**: Oculta la complejidad de la red interna (puertos de Quarkus, etc.).



### 📡 Puertos y Rutas
* **Puerto Público**: `8080`
* `/auth/**` -> `auth-service`
* `/profiles/**` -> `user-service`
* `/catalog/**` -> `catalog-service`
