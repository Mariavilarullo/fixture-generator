-- password is 'somePassword' hashed con http://www.nitrxgen.net/hashgen/ - Ojo que tal vez haya que agregar un '*' adelante
CREATE USER 'vilarullo'@'localhost' IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194'; 

CREATE SCHEMA IF NOT EXISTS `parcial2`;

USE `parcial2`;
  
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(20) NOT NULL ,
  `pass` VARCHAR(30) NOT NULL ,
  `prefLang` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`)  );
  
DROP TABLE IF EXISTS `equipos`;
CREATE TABLE `equipos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL ,
  `foto` VARCHAR(100) NOT NULL ,
  `historia` VARCHAR(500),
  `dt` VARCHAR(60) NOT NULL ,  
  `categoria` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`id`)  );
  
DROP TABLE IF EXISTS `canchas`;
CREATE TABLE `canchas` (
  `id_cancha` INT NOT NULL AUTO_INCREMENT,
  `id_sede` INT NOT NULL ,
  `numero` INT NOT NULL ,
  `tipo` VARCHAR(60) NOT NULL ,
 
  PRIMARY KEY (`id_cancha`)  );
  
DROP TABLE IF EXISTS `partidos`;
CREATE TABLE `partidos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_cancha` INT NOT NULL ,
  `orden` INT NOT NULL ,
  `id_equipo1` INT NOT NULL ,
  `id_equipo2` INT NOT NULL ,
  `jugado` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`)  );
  
DROP TABLE IF EXISTS `sedes`;
CREATE TABLE `sedes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`)  );

  
GRANT ALL PRIVILEGES ON `parcial2`.* TO 'vilarullo'@'localhost'
IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194';
  

INSERT INTO usuarios(prefLang, user, pass) VALUES('es_ar', 'admin', 'admin');
INSERT INTO usuarios(prefLang, user, pass) VALUES('en_us', 'maria', 'admin');
INSERT INTO usuarios(prefLang, user, pass) VALUES('en', 'asd', '123');

INSERT INTO sedes(nombre) VALUES('paraguay');
INSERT INTO sedes(nombre) VALUES('palermo');
INSERT INTO sedes(nombre) VALUES('almagro');
INSERT INTO sedes(nombre) VALUES('belgrano');
INSERT INTO sedes(nombre) VALUES('flores');

INSERT INTO canchas(id_sede, numero, tipo) VALUES(1, 1, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(1, 2, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(1, 3, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(1, 4, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(1, 5, 'alto rendimiento');

INSERT INTO canchas(id_sede, numero, tipo) VALUES(2, 1, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(2, 2, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(2, 3, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(2, 4, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(2, 5, 'alto rendimiento');

INSERT INTO canchas(id_sede, numero, tipo) VALUES(3, 1, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(3, 2, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(3, 3, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(3, 4, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(3, 5, 'alto rendimiento');

INSERT INTO canchas(id_sede, numero, tipo) VALUES(4, 1, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(4, 2, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(4, 3, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(4, 4, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(4, 5, 'alto rendimiento');

INSERT INTO canchas(id_sede, numero, tipo) VALUES(5, 1, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(5, 2, 'normal');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(5, 3, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(5, 4, 'alto rendimiento');
INSERT INTO canchas(id_sede, numero, tipo) VALUES(5, 5, 'alto rendimiento');