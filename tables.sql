CREATE TABLE IF NOT EXISTS Rol(
tipo VARCHAR(12) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Usuario(
  id NUMBER(12) NOT NULL,
  tid VARCHAR(2) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  telefono NUMBER(12) NOT NULL,
  correo VARCHAR(50) NOT NULL,
  clave VARCHAR(20) NOT NULL,
  estado VARCHAR(15) NOT NULL,
  rol VARCHAR(12) REFERENCES Rol(tipo),
  PRIMARY KEY (correo),
  UNIQUE KEY (id),
  UNIQUE KEY (telefono));
  
CREATE TABLE IF NOT EXISTS Iniciativa (
   nombre VARCHAR(50) NOT NULL,
   id int  NOT NULL,
   descripcion varchar(150)  NOT NULL,
   fechaInicio date  NOT NULL,
   --palabrasClaves varchar(20)  REFERENCES PalabraClave(id), aun no se como ponerlo
   area varchar(100)  NOT NULL,
   user_id int REFERENCES Usuario(id),
   estado VARCHAR(50) NOT NULL,
   UNIQUE KEY (nombre),
   PRIMARY KEY (id));
   
CREATE TABLE IF NOT EXISTS Estado (
   id int  NOT NULL,
   estado varchar(50) NOT NULL,
   UNIQUE KEY (estado),
   PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS PalabraClave (
   id int  NOT NULL,
   palabraClave varchar(20)  NOT NULL,
   PRIMARY KEY (id));