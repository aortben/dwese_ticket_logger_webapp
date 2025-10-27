-- Inserts de las Comunidades Autónomas, ignora si se produce un error en la insercción
INSERT IGNORE INTO regions (id, code, name) VALUES
(1, '01', 'ANDALUCÍA'),
(2, '02', 'ARAGÓN'),
(3, '03', 'ASTURIAS'),
(4, '04', 'BALEARES'),
(5, '05', 'CANARIAS'),
(6, '06', 'CANTABRIA'),
(7, '07', 'CASTILLA Y LEÓN'),
(8, '08', 'CASTILLA-LA MANCHA'),
(9, '09', 'CATALUÑA'),
(10, '10', 'COMUNIDAD VALENCIANA'),
(11, '11', 'EXTREMADURA'),
(12, '12', 'GALICIA'),
(13, '13', 'MADRID'),
(14, '14', 'MURCIA'),
(15, '15', 'NAVARRA'),
(16, '16', 'PAÍS VASCO'),
(17, '17', 'LA RIOJA'),
(18, '18', 'CEUTA Y MELILLA');

INSERT IGNORE INTO comunidades (code, name, region_id) VALUES
('01-01', 'Sevilla', 1),
('01-02', 'Málaga', 1),
('01-03', 'Granada', 1),
('09-01', 'Barcelona', 9),
('09-02', 'Girona', 9),
('13-01', 'Madrid Capital', 13),
('13-02', 'Área Metropolitana de Madrid', 13),
('12-01', 'A Coruña', 12),
('12-02', 'Pontevedra', 12),
('07-01', 'León', 7);

INSERT IGNORE INTO provincias (code, name, comunidad_id) VALUES
('P-001', 'Dos Hermanas', 1),
('P-002', 'Utrera', 1),
('P-003', 'Fuengirola', 2),
('P-004', 'Marbella', 2),
('P-005', 'Motril', 3),
('P-006', 'Mataró', 4),
('P-007', 'Figueres', 5),
('P-008', 'Getafe', 6),
('P-009', 'Leganés', 6),
('P-010', 'Santiago de Compostela', 8),
('P-011', 'Vigo', 9),
('P-012', 'Ponferrada', 10);
