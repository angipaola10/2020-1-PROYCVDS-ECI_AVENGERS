CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(80) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(14) REFERENCES Rol(tipo));

ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

ALTER TABLE Usuario ADD CONSTRAINT telefono_unico UNIQUE(telefono);

CREATE TABLE IF NOT EXISTS Iniciativa(nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date, area varchar(50)  NOT NULL, usuario VARCHAR(50) REFERENCES Usuario(correo), estado_Propuesta VARCHAR(30) NOT NULL);

ALTER TABLE Iniciativa ADD CONSTRAINT nombrePropuesta_unico UNIQUE(nombrePropuesta);

CREATE TABLE IF NOT EXISTS PalabraClave (id SERIAL PRIMARY KEY, palabraClave VARCHAR(20) NOT NULL);

CREATE TABLE IF NOT EXISTS PCIniciativa (ini_id INT NOT NULL, palabras_clave INT NOT NULL);

ALTER TABLE PCIniciativa ADD CONSTRAINT PK_PCIniciativa_ids PRIMARY KEY(ini_id, palabras_clave);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_iniciativa FOREIGN KEY (ini_id) REFERENCES Iniciativa(id);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_palabraClave FOREIGN KEY (palabras_clave) REFERENCES PalabraClave(id);

CREATE TABLE IF NOT EXISTS MeGusta (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

CREATE TABLE IF NOT EXISTS Comentario (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo), comentario VARCHAR(600) NOT NULL, fecha date);

CREATE TABLE IF NOT EXISTS MeInteresa (id SERIAl PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

CREATE TABLE IF NOT EXISTS Grupo (id SERIAL PRIMARY KEY, idiniciativa INT NOT NULL REFERENCES Iniciativa(id), nombre varchar(30) NOT NULL);