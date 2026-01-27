-- Creamos las bases de datos por si acaso
CREATE DATABASE IF NOT EXISTS `synapse-accounts`;
CREATE DATABASE IF NOT EXISTS `synapse-catalog`;

GRANT ALL PRIVILEGES ON `synapse-catalog`.* TO 'user'@'%';
-- Aplicamos cambios
FLUSH PRIVILEGES;