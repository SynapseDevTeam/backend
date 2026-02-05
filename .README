# 🧠 Synapse Backend: El Búnker de Microservicios

Bienvenido a **Synapse**, un ecosistema de microservicios diseñado para el control de hogares inteligentes. Aquí no hay espacio para el código **delulu**; todo está blindado, desacoplado y listo para escalar. Si buscas un CRUD de juguete, te has equivocado de repo. Esto es arquitectura **Giga-Chad**.

---

## 🏗️ Arquitectura del Sistema

El búnker se divide en mercenarios especializados que se comunican mediante REST (síncrono) y RabbitMQ (asíncrono) para mantener la consistencia total.

* **Gateway Service**: La única puerta de entrada (Puerto 8080). Gestiona el CORS y rutea al búnker interno.
* **Auth-Service**: El cerebro de identidad. Emite tokens **JWT** y gestiona credenciales.
* **Catalog-Service**: La enciclopedia de dispositivos. Gestiona el catálogo maestro y los manuales técnicos.
* **User-Service**: Gestión de perfiles y suscripciones. Implementa lógica **1:1** (Suscripción única sin basura de historial).
* **Home-Service**: El centro de control. Gestiona casas y el inventario de dispositivos por usuario.

---

## 🛠️ Stack Tecnológico

| Componente | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 (LTS) |
| **Framework Base** | Quarkus 3.30.6 (Reactivo/Bloqueante) |
| **Gateway** | Spring Cloud Gateway (MVC) |
| **Mensajería** | RabbitMQ (AMQP) |
| **Persistencia** | Hibernate Panache / MySQL |
| **Seguridad** | JWT (SmallRye / Elytron) |

---

## 🗺️ Mapa de Endpoints (Base URL: `http://localhost:8080`)

### 🔑 Auth-Service (`/auth`)
| Método | Path | Descripción |
| :--- | :--- | :--- |
| `POST` | `/auth/register` | Registro de reclutas. Dispara evento a RabbitMQ. |
| `POST` | `/auth/login` | Validación y entrega de llave (JWT). |
| `PATCH` | `/auth/change-password` | Cambio de pass blindado (extrae ID del Token). |

### 📖 Catalog-Service (`/catalog` & `/manuals`)
| Método | Path | Descripción |
| :--- | :--- | :--- |
| `GET` | `/catalog/search` | Búsqueda avanzada paginada (`marca`, `modelo`, `categoria`). |
| `GET` | `/catalog/{id}` | Detalle técnico de un electrodoméstico. |
| `POST` | `/manuals/upload` | Subida de manuales (Solo ADMIN). |
| `GET` | `/manuals/{id}` | Descarga de manuales en stream. |

### 👤 User-Service (`/profiles` & `/subscriptions`)
| Método | Path | Descripción |
| :--- | :--- | :--- |
| `GET` | `/profiles/{id}` | Ver perfil y plan actual. |
| `PATCH` | `/profiles/{id}` | Update de datos (Sin machacar campos ausentes). |
| `POST` | `/subscriptions/change-plan` | Upgrade/Downgrade de plan inmediato. |

### 🏠 Home-Service (`/home`)
| Método | Path | Descripción |
| :--- | :--- | :--- |
| `POST` | `/home` | Crea una nueva Home vinculada a tu ID. |
| `GET` | `/home` | Lista de todas tus propiedades. |
| `POST` | `/home/{homeId}/devices` | Añade un cacharro del catálogo a tu casa. |
| `PATCH` | `/home/devices/{id}/transfer/{target}` | Mueve dispositivos entre tus casas. |

---