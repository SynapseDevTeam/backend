-- Aseguramos que estamos en la DB correcta
USE `synapse-catalog`;

-- Borramos datos previos para evitar duplicados si relanzas (Opcional pero recomendado)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE manual;
TRUNCATE TABLE catalog_electrodomestico;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertar Electrodomésticos (Base del Catálogo)
-- Lavadoras y Secadoras
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d473', '-', '')), 'Bosch', 'Serie 6 i-DOS', 'Lavadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d474', '-', '')), 'Siemens', 'iQ700', 'Lavadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d475', '-', '')), 'Miele', 'W1 Excellence', 'Lavadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d476', '-', '')), 'Beko', 'IronFast', 'Lavadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d477', '-', '')), 'Whirlpool', 'FreshCare+', 'Lavadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d478', '-', '')), 'Balay', 'ExtraSilencio 3TS', 'Lavadora');

-- Frigoríficos
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d479', '-', '')), 'Haier', 'Cube 90 Series 7', 'Frigorífico');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d480', '-', '')), 'Liebherr', 'BluPerformance', 'Frigorífico');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d481', '-', '')), 'Samsung', 'Family Hub', 'Frigorífico');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d482', '-', '')), 'Hisense', 'PureFlat', 'Frigorífico');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d483', '-', '')), 'Panasonic', 'Multi-Door', 'Frigorífico');

-- Lavavajillas
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d484', '-', '')), 'Bosch', 'PerfectDry Serie 8', 'Lavavajillas');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d485', '-', '')), 'AEG', 'ComfortLift', 'Lavavajillas');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d486', '-', '')), 'Candy', 'Brava', 'Lavavajillas');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d487', '-', '')), 'Teka', 'Premium Care', 'Lavavajillas');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d488', '-', '')), 'Zanussi', 'AirDry', 'Lavavajillas');

-- Hornos y Microondas
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d489', '-', '')), 'Balay', 'Cristal Negro Plus', 'Horno');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d490', '-', '')), 'Neff', 'Slide & Hide', 'Horno');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d491', '-', '')), 'Electrolux', 'SteamBoost', 'Horno');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d492', '-', '')), 'Cecotec', 'Bolero Hexa', 'Horno');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d493', '-', '')), 'Sharp', 'Healslo', 'Microondas');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d494', '-', '')), 'LG', 'NeoChef', 'Microondas');

-- Pequeño electrodoméstico y Clima
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d495', '-', '')), 'Dyson', 'V15 Detect', 'Aspiradora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d496', '-', '')), 'Rowenta', 'Air Force Flex', 'Aspiradora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d497', '-', '')), 'Philips', 'Airfryer XXL', 'Freidora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d498', '-', '')), 'Xiaomi', 'Mi Smart Air Fryer', 'Freidora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d499', '-', '')), 'Cosori', 'Premium Chef', 'Freidora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d500', '-', '')), 'Daikin', 'Emura 3', 'Aire Acondicionado');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d501', '-', '')), 'Mitsubishi', 'MSZ-HR', 'Aire Acondicionado');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d502', '-', '')), 'Fujitsu', 'Serie KM', 'Aire Acondicionado');

-- TVs (Sé que no son "electro" clásicos, pero suelen estar en el catálogo)
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d503', '-', '')), 'Sony', 'Bravia XR A80L', 'Televisión');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d504', '-', '')), 'LG', 'OLED C3', 'Televisión');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d505', '-', '')), 'TCL', 'Mini-LED C845', 'Televisión');

-- Resto hasta 50 (Variados)
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d506', '-', '')), 'Smeg', 'Anni 50', 'Tostadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d507', '-', '')), 'DeLonghi', 'Magnifica S', 'Cafetera');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d508', '-', '')), 'Nespresso', 'Vertuo Pop', 'Cafetera');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d509', '-', '')), 'Krups', 'Evidence One', 'Cafetera');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d510', '-', '')), 'iRobot', 'Roomba j7+', 'Robot Aspirador');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d511', '-', '')), 'Roborock', 'S8 Pro Ultra', 'Robot Aspirador');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d512', '-', '')), 'Kenwood', 'kMix', 'Batidora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d513', '-', '')), 'KitchenAid', 'Artisan', 'Amasadora');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d514', '-', '')), 'Moulinex', 'Cookeo', 'Robot Cocina');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d515', '-', '')), 'Vorwerk', 'Thermomix TM6', 'Robot Cocina');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d516', '-', '')), 'Jura', 'E8', 'Cafetera');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d517', '-', '')), 'Polti', 'Vaporella Next', 'Centro Planchado');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d518', '-', '')), 'Braun', 'TexStyle 9', 'Plancha');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d519', '-', '')), 'Babyliss', 'Sensor Pro', 'Secador');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d520', '-', '')), 'GHD', 'Gold Styler', 'Plancha Pelo');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d521', '-', '')), 'Oral-B', 'iO Series 10', 'Cepillo Eléctrico');
INSERT INTO catalog_electrodomestico (id, marca, modelo, categoria) VALUES (UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d522', '-', '')), 'Philips', 'Sonicare 9900', 'Cepillo Eléctrico');