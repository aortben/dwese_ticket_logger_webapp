-- Crear tabla para las regiones
CREATE TABLE IF NOT EXISTS regions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Crear tabla para las Comunidades Autónomas de España
CREATE TABLE IF NOT EXISTS comunidades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    region_id INT NOT NULL,
    CONSTRAINT fk_comunidades_region
        FOREIGN KEY (region_id) REFERENCES regions(id)
        ON DELETE CASCADE
);

-- Crear tabla para las provincias
CREATE TABLE IF NOT EXISTS provincias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    comunidad_id INT NOT NULL,
    CONSTRAINT fk_provincias_comunidad
        FOREIGN KEY (comunidad_id) REFERENCES comunidades(id)
        ON DELETE CASCADE
);
