# 🔐 Auth Service
Gestión de identidad y seguridad basada en tokens. Si no pasas por aquí, no eres nadie en Synapse.

### 🛠️ Stack Técnico
* **Quarkus**: Framework principal.
* **SmallRye JWT**: Generación y firmado de tokens RSA.
* **MySQL**: Persistencia de credenciales.

### 🚀 Funcionalidades Clave
* **Registro**: Crea credenciales y emite un evento a **RabbitMQ** para que el `user-service` inicialice el perfil.
* **Login**: Valida credenciales y devuelve un **JWT** con los claims del usuario.

### 📬 Comunicación Asíncrona
* **Emite**: `user-created-event` al canal `auth-out`.
