-- Inserts de las Comunidades Autónomas, ignora si se produce un error en la insercción
INSERT IGNORE INTO regions (id, code, name) VALUES
(1, '01', 'NORTE'),
(2, '02', 'SUR'),
(3, '03', 'ESTE'),
(4, '04', 'OESTE'),
(5, '05', 'SURESTE'),
(6, '06', 'NORESTE'),
(7, '07', 'SUROESTE'),
(8, '08', 'NOROESTE');


INSERT IGNORE INTO comunidades (code, name, region_id) VALUES
('02-01', 'Andalucía', 2),
('07-01', 'Castilla y León', 7),
('06-01', 'Cataluña', 6),
('03-01', 'Comunidad de Madrid', 3),
('08-01', 'Comunidad Valenciana', 8),
('01-01', 'País Vasco', 1),
('05-01', 'Extremadura', 5),
('04-01', 'Aragón', 4),
('08-02', 'Murcia', 8),
('07-02', 'Galicia', 7);

INSERT IGNORE INTO provincias (code, name, comunidad_id) VALUES
('02-01-01', 'Sevilla', 1),
('02-01-02', 'Málaga', 1),
('02-01-03', 'Granada', 1),
('07-01-01', 'León', 2),
('07-01-02', 'Valladolid', 2),
('07-01-03', 'Burgos', 2),
('06-01-01', 'Barcelona', 3),
('06-01-02', 'Girona', 3),
('06-01-03', 'Tarragona', 3),
('03-01-01', 'Madrid Capital', 4),
('03-01-02', 'Área Metropolitana de Madrid', 4),
('08-01-01', 'Valencia', 5),
('08-01-02', 'Alicante', 5),
('08-01-03', 'Castellón', 5),
('07-02-01', 'A Coruña', 7),
('07-02-02', 'Pontevedra', 7),
('07-02-03', 'Ourense', 7);
