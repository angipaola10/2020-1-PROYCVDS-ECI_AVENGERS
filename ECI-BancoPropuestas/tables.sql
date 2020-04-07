CREATE table if not exists Rol(
tipo varchar(30) primary key)
;

CREATE table if not exists  Usuario(
  id number(12) primary key,
  tid varchar(2) not null,
  nombre varchar(50) not null,
  telefono number(12)unique key not null,
  correo varchar(50)unique key not null,
  clave varchar(20) not null,
  estado varchar(15) not null,
  rol varchar(12) references Rol(tipo))
;
