INSERT INTO region (code, name) VALUES
('02-01', 'Andalucía'),
('07-01', 'Castilla y León'),
('06-01', 'Cataluña'),
('03-01', 'Comunidad de Madrid'),
('08-01', 'Comunidad Valenciana'),
('01-01', 'País Vasco'),
('05-01', 'Extremadura'),
('04-01', 'Aragón'),
('08-02', 'Murcia'),
('07-02', 'Galicia');

INSERT INTO province (code, name, region_id) VALUES
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
('07-02-01', 'A Coruña', 10),
('07-02-02', 'Pontevedra', 10),
('07-02-03', 'Ourense', 10);

