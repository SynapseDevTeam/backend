-- Aseguramos que estamos en la DB correcta
USE `synapse-catalog`;

-- Borramos datos previos para evitar duplicados si relanzas (Opcional pero recomendado)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE manual;
TRUNCATE TABLE catalog_electrodomestico;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertar Electrodomésticos (Base del Catálogo)
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) 
VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d471', '-', '')), 'Samsung', 'AddWash Series 6', 'Lavadora');

INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) 
VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d472', '-', '')), 'LG', 'InstaView ThinQ', 'Frigorífico');

-- 2. Insertar Manuales asociados (Relación 1:N)
-- Usamos UUID_TO_BIN(UUID()) para generar IDs nuevos al vuelo en MySQL
INSERT INTO manual (id, catalog_id, custom, file_url) 
VALUES (UUID_TO_BIN(UUID()), UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d471'), 'Guía de instalación rápida', 'https://assets.synapse.com/manuals/samsung-ww6-quick.pdf');

INSERT INTO manual (id, catalog_id, custom, file_url) 
VALUES (UUID_TO_BIN(UUID()), UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d471'), 'Manual de usuario completo', 'https://assets.synapse.com/manuals/samsung-ww6-full.pdf');

INSERT INTO manual (id, catalog_id, custom, file_url) 
VALUES (UUID_TO_BIN(UUID()), UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d472'), 'Configuración SmartThinQ', 'https://assets.synapse.com/manuals/lg-fridge-smart.pdf');

INSERT INTO manual (id, catalog_id, custom, file_url) 
VALUES (UUID_TO_BIN(UUID()), UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d473'), 'Ajuste de dureza del agua', '