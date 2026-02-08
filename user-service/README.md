# 👥 User & Profile Service
Gestión de la experiencia de usuario y personalización del búnker.

### 🛠️ Stack Técnico
* **RESTEasy Reactive**: Para una subida de fotos ultra rápida.
* **RabbitMQ**: Consumidor de eventos de registro.
* **Multi-part Form Data**: Gestión de archivos binarios para fotos de perfil.

### 📡 Comunicación (Event-Driven)
* **Escucha**: `user-created-event` desde RabbitMQ para crear el `UserProfile` automáticamente al registrarse.



### 🖼️ Gestión Multimedia
* **Upload**: `POST /profiles/photo-profile` (Acepta JPG/PNG).
* **Streaming**: `GET /profiles/photo` (Sirve la foto del usuario logueado usando el Subject del JWT).
