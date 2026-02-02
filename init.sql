-- Creamos las bases de datos por si acaso
CREATE DATABASE IF NOT EXISTS `synapse-accounts`;
CREATE DATABASE IF NOT EXISTS `synapse-catalog`;
CREATE DATABASE IF NOT EXISTS `synapse-home`;

GRANT ALL PRIVILEGES ON `synapse-catalog`.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON `synapse-accounts`.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON `synapse-home`.* TO 'user'@'%';
-- Aplicamos cambios
FLUSH PRIVILEGES;