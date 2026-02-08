# 🏡 Home Service
El corazón del Gemelo Digital. Gestiona la relación entre el usuario y su casa inteligente.

### 🛠️ Stack Técnico
* **Quarkus**: Lógica de negocio.
* **MySQL**: Gestión de relaciones Casa <-> Dispositivo.

### 🎯 Lógica de Negocio
* **Viviendas**: Creación y gestión de estancias.
* **Device Instances**: Vincula dispositivos del catálogo general a la casa específica del usuario. 
* **Telemetría**: Punto de enlace para futuros datos de sensores.

### 🔗 Integración
Relaciona el `userId` del token con el `catalogProductId` para crear el gemelo digital del electrodoméstico en la vivienda del usuario.
