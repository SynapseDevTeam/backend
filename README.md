# 🚀 Synapse Ecosystem: Backend & Infrastructure

Bienvenido al núcleo de **Synapse**, el ecosistema de Gemelo Digital. Este repositorio contiene la arquitectura de microservicios, la configuración de red y el despliegue en la nube que da vida a las aplicaciones web y móviles.

## 🏗️ Arquitectura del Sistema

El backend de Synapse no es un bloque monolítico; es un ecosistema distribuido diseñado bajo el patrón de **Microservicios**, lo que nos permite escalar cada parte del búnker de forma independiente.

### 🧩 Desglose de Microservicios

1.  **Gateway Service (Spring Cloud Gateway)**: El único punto de entrada público (Puerto 8080). Gestiona el enrutamiento, la seguridad y las políticas de **CORS** centralizadas para permitir la comunicación con el frontend en `localhost`.
2.  **Auth-Service (Quarkus)**: El portero del búnker. Gestiona el registro, login y la emisión de tokens **JWT** para asegurar que solo los usuarios autorizados accedan a sus datos.
3.  **Catalog-Service (Quarkus)**: El cerebro del inventario. Administra más de 50 dispositivos, sus especificaciones, precios y sirve los manuales técnicos y fotografías de los productos.
4.  **User-Service (Quarkus)**: Gestión de perfiles y suscripciones (Free/Premium). Es un servicio reactivo que inicializa perfiles automáticamente al detectar nuevos registros.
5.  **Home-Service (Quarkus)**: La lógica del hogar inteligente. Vincula a los usuarios con sus viviendas virtuales y sus dispositivos IoT específicos.



---

## 📡 Comunicación e Interconectividad

### 🔄 Comunicación Síncrona (REST)
Utilizamos **HTTP/REST** para operaciones que requieren respuesta inmediata (como el login o la consulta de productos). El Gateway actúa como *Reverse Proxy* redirigiendo el tráfico a través de la red interna de Docker.

### 📬 Comunicación Asíncrona (RabbitMQ)
Para desacoplar los servicios, utilizamos un **Broker de Mensajería**. 
* **Ejemplo**: Cuando un usuario se registra en `Auth-Service`, se emite un evento a una cola de RabbitMQ. `User-Service` escucha esta cola y crea el perfil en segundo plano, garantizando que el sistema sea resiliente aunque un servicio esté temporalmente offline.

---

## 💾 Persistencia y Gestión Multimedia

Uno de los puntos clave del proyecto es la gestión de archivos (fotos de perfil y de dispositivos) sin pérdida de datos:
* **Volúmenes de Docker**: Mapeamos carpetas físicas del servidor AWS (`./profile_storage`) con las carpetas internas de los contenedores (`/deployments/profilephotos`).
* **Seeding Inteligente**: La base de datos MySQL se autogestiona con scripts de carga que vinculan cada electrodoméstico con su precio y su ruta de imagen dinámica.



---

## ☁️ Despliegue e Infraestructura

El sistema ronea en la nube gracias a **AWS (Amazon Web Services)**:
* **Instancia**: Amazon Linux (EC2).
* **Seguridad**: Configuración de *Security Groups* para blindar todos los puertos excepto el 8080.
* **Aislamiento**: Todo el ecosistema vive dentro de una red virtual privada de Docker (`synapse-net`), siendo invisible desde el exterior.

## 🛠️ Stack Tecnológico
* **Java 17 / 21**
* **Quarkus** (RestEasy Reactive, Hibernate Panache)
* **Spring Boot** (Cloud Gateway)
* **MySQL** (Persistencia)
* **RabbitMQ** (Mensajería)
* **Docker & Docker Compose** (Contenerización)

---
*Desarrollado por Jorge Matias y Alejandro Roca para el proyecto Synapse - 2026.*
