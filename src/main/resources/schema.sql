CREATE SCHEMA IF NOT EXISTS wallapop2;
DROP TABLE IF EXISTS wallapop2.producto;
DROP TABLE IF EXISTS wallapop2.compra;
DROP TABLE IF EXISTS wallapop2.usuario;
DROP TABLE IF EXISTS wallapop2.roles;

DROP SEQUENCE IF EXISTS wallapop2.usuario_seq;
DROP SEQUENCE IF EXISTS wallapop2.producto_seq;
DROP SEQUENCE IF EXISTS wallapop2.compra_seq;

CREATE SEQUENCE wallapop2.usuario_seq INCREMENT BY 1 START WITH 5;
CREATE SEQUENCE wallapop2.producto_seq INCREMENT BY 1 START WITH 21;
CREATE SEQUENCE wallapop2.compra_seq INCREMENT BY 1 START WITH 0;

CREATE TABLE wallapop2.roles (
  ID INT PRIMARY KEY NOT NULL,
  NOMBRE VARCHAR(100) NOT NULL 
);

INSERT into wallapop2.roles(id,nombre) values(0,'ROLE_USER');
INSERT into wallapop2.roles(id,nombre) values(1,'ROLE_ADMIN');


CREATE TABLE wallapop2.usuario (
  ID INT PRIMARY KEY NOT NULL,
  NOMBRE VARCHAR(100) NOT NULL,
  APELLIDOS VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(100) NOT NULL,
  PASSWORD VARCHAR(100) NOT NULL,
  AVATAR VARCHAR(1000),
  FECHA_ALTA TIMESTAMP NOT NULL DEFAULT now(),
  ID_ROL INT NOT NULL,
  FOREIGN KEY (ID_ROL) REFERENCES wallapop2.roles(ID)

);

INSERT into wallapop2.usuario(id,nombre,apellidos,email,password,avatar,id_rol) values(0,'admin','admin sistemas','admin@market.com','$2a$10$6QfTBtrR1QIKb1qn6LOW9Or/tzztgtuISmLjBC3ttrXse3QtCrHRC','avatar',1);
INSERT into wallapop2.usuario(id,nombre,apellidos,email,password,avatar,id_rol) values(1,'usuario','usuario Prueba','usuario@market.com','$2a$10$6QfTBtrR1QIKb1qn6LOW9Or/tzztgtuISmLjBC3ttrXse3QtCrHRC','avatar',0);
INSERT into wallapop2.usuario(id,nombre,apellidos,email,password,avatar,id_rol) values(2,'Manuel','Castillo Perez','manuel@market.com','$2a$10$6QfTBtrR1QIKb1qn6LOW9Or/tzztgtuISmLjBC3ttrXse3QtCrHRC','avatar',0);
INSERT into wallapop2.usuario(id,nombre,apellidos,email,password,avatar,id_rol) values(3,'Carmen','Romero Martinez','carmen@market.com','$2a$10$6QfTBtrR1QIKb1qn6LOW9Or/tzztgtuISmLjBC3ttrXse3QtCrHRC','avatar',0);
INSERT into wallapop2.usuario(id,nombre,apellidos,email,password,avatar,id_rol) values(4,'Marta','Casas Boija','marta@market.com','$2a$10$6QfTBtrR1QIKb1qn6LOW9Or/tzztgtuISmLjBC3ttrXse3QtCrHRC','avatar',0);

CREATE TABLE wallapop2.compra (
  ID INT PRIMARY KEY NOT NULL,
  FECHA_COMPRA TIMESTAMP NOT NULL DEFAULT now(),  
  ID_COMPRADOR INT NOT NULL,
  FOREIGN KEY (ID_COMPRADOR) REFERENCES wallapop2.usuario(ID)
);

CREATE TABLE wallapop2.producto (
  ID INT PRIMARY KEY NOT NULL,
  NOMBRE VARCHAR(100) NOT NULL,
  PRECIO DOUBLE NOT NULL,
  DESCRIPCION_CORTA VARCHAR(250) NOT NULL,
  DESCRIPCION_LARGA VARCHAR(4000),
  IMAGEN VARCHAR(1000) NOT NULL,
  ID_VENDEDOR INT NOT NULL,
  ID_COMPRA INT,
  FOREIGN KEY (ID_VENDEDOR) REFERENCES wallapop2.usuario(ID),
  FOREIGN KEY (ID_COMPRA) REFERENCES wallapop2.compra(ID)
);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(0,'Reloj manillas','10','Reloj de manillas','Un bonito relog negro con solo un mes de uso','imagen',2);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(1,'Mesa cocina','110','Mesa de Ikea de cocina sin sillas','Una mesa de ikea de 4 plazas en casi perfecto estado, solo tiene un pequeño golpe','imagen',4);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(2,'Funda portatil','10','Funda portatil','Una funda para portatil de 15','imagen',4);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(3,'Reloj digital','15','Reloj despertador','Sin uso, fue un regalo y no lo estoy usando','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(4,'Mesa con 4 sillas','110','Mesa pequeña de salón con 4 sillas','Una mesa para el salón con 4 sillas a juego en perfecto estado, salvo algún arañazo','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(5,'Bicileta niño','90','Bicicleta para niño de 5 años','Bicicleta pequeña con ruedines, la vendo porque el niño ya ha crecido','imagen',2);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(6,'SmartWatch','120','SmartWatch para salir a correr','En perfecto estado, usado durante 6 meses hasta que compré un modelo superior','imagen',4);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(7,'Sofá cama','100','Sofá cama 2 plazas','Perfecto para invitados, si no queremos que se queden mucho tiempo. En perfecto estado.','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(8,'Cubierta coche','30','Funda para tapar el coche','Funda para cubrir el coche en la calle, caben vehículos de 5 puertas, furgonetas no.','imagen',2);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(9,'iPone 8','400','iPhone un año de uso','Funciona perfectamente, cambiado por el siguiente modelo','imagen',2);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(10,'Estantería 6 zonas','80','Estanteria Expedit de Ikea','Estantería en perfecto estado, con 6 espacios y 4 cajones','imagen',2);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(11,'Acuario 50l','60','Acuario de 50 litro con accesorios','Vendo por no usar, contiene luz, calentador y lo que queda de la comida de los peces que había','imagen',4);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(12,'iPad 2','500','iPad funcionando sin problemas','No me gusta apple, me he comprado otro android','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(13,'Mesa baja','210','Mesa baja para salón','Mesa baja de diseño para salón hecha en roble','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(14,'Cinta de correr','180','Cinta para correr cecotec','La vendo por comprar una superior. Ideal para confinamientos.','imagen',2);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(15,'Mi 9','500','Móvil Mi 9, ocasión','Nuevo, en perfecto estado, viene desde china','imagen',2);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(16,'Silla gaming','230','Silla gamin casi nueva','Silla gamin con 3 meses de uso, la vendo porque no soy bueno con los videojuegos','imagen',4);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(17,'TV 32 pulgadas','260','SmartTv LG 32 pulgadas','Se vende por la compra de una de tamaño mayor, en perfecto estado.','imagen',3);

INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(18,'Pocophone f2','450','Pocoophone f2','Increible móvil a un precio increible, sólo un mes de uso','imagen',3);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(19,'Mesa escritorio','50','Mesa de escritorio antigua','Mesa con tiempo y algunos desperfectos, por eso la vendo a precio bajo. Algunos arañazos y rozaduras pero sigue siendo estable.','imagen',4);
INSERT into wallapop2.producto(id,nombre,precio,descripcion_corta,descripcion_larga,imagen,id_vendedor) values(20,'Kit de escalada','70','Kit de escalada amateur','Cuerdas, anclajes, seguros, martillo, mosquetones, etc. Todo casi nuevo, usado sólo una vez hasta que me dio vértigo.','imagen',4);