-- Tabla de regiones
CREATE TABLE IF NOT EXISTS region (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Tabla de comunidades autónomas
CREATE TABLE IF NOT EXISTS comunidad_autonoma (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    region_id INT NOT NULL,
    CONSTRAINT fk_comunidad_region
        FOREIGN KEY (region_id) REFERENCES region(id)
        ON DELETE CASCADE
);

-- Tabla de provincias
CREATE TABLE IF NOT EXISTS province (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    comunidad_id INT NOT NULL,  -- 🔹 Solo aquí se cambia el nombre para que Hibernate lo reconozca
    CONSTRAINT fk_province_comunidad
        FOREIGN KEY (comunidad_id) REFERENCES comunidad_autonoma(id)
        ON DELETE CASCADE
);

