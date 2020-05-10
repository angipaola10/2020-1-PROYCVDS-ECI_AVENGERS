# Banco de Iniciativas ECI. 🚀


_Proyecto Final: Ciclos de Vida de Desarrollo de Software._


_Banco de Iniciativas Escuela Colombiana de Ingeniería Julio Garavito._


_ECI_AVENGERS: 2020-1._


### Integrantes: ✒️
+ _Angi Jiménez. (Team developer)_
+ _Daniela Ruiz. (Team developer)_
+ _Edwin Rodríguez. (Team developer)_
+ _Henry Sánchez. (Team developer)_


### Profesor: 📌
+ _Julián Mauricio Velazco Briceño. (Product Owner)_


## Descripción del producto. 📄
### Descripción general: 


_La Plataforma banco de iniciativas de proyectos, es una herramienta donde la comunidad universitaria 
de la Escuela Colombiana de Ingenieria Julio Garavito pueden registrar sus iniciativas e ideas de 
proyectos para ser desarrollados o gestionados por la PMO de la Escuela._


### Manual de usuario: 
acá va el manual, equis de.
en el manual van --> Imágenes y descripción de las funcionalidades más importantes.


## Arquitectura y Diseño detallado. 🔧


### Modelos Entidad - Relación:
_MD:_
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/md.jpeg) 


_BD:_
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/bd.jpeg) 


## Descripción de la arquitectura y tecnologías utilizadas. 🛠️
_La aplicación está construida en 3 capas: Aplicación, Presentación y Persistencia sobre los Datos._


_A continuación explicaremos las tecnologias usadas en cada una de ellas._

### Capa de aplicación:
+ _Java._
+ _Google Guice (Un framework para la inyección de dependencias)._
+ _Maven (Herramienta para la gestión de dependencias)._
+ _Apache Shiro (Framework para la autentiticación)._


### Capa de presentación:
+ _JSF (Java Server Faces)._
+ _Primefaces._


### Capa de persistencia sobre los datos:
+ _Se usó un motor de bases de datos **PostgreSQL** y la herramienta **myBatis**, la cual se encarga de la persistencia 
(mappea sentencias SQL y procedimientos almacenados con objetos a partir de ficheros XML o anotaciones)._


## Enlace a la aplicación en Heroku. 📢
https://cvds-proyecto-eci.herokuapp.com


## Enlace al sistema de integración continua. 😊
[![CircleCI](https://circleci.com/gh/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS.svg?style=svg)](https://circleci.com/gh/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS)


## Descripción del proceso. 📋
_**Responsabilidades de los integrantes:**_
+ _Angi Jiménez. (Views)_
+ _Daniela Ruiz. (BD)_
+ _Edwin Rodríguez. (Back)_
+ _Henry Sánchez. (Back)_


### Descripción de la Metodología:
_Durante la realización del proyecto se trabajó mediante la metodología ágil de: **Scrum**. Se hicieron 3 sprints, de los cuales cada uno tuvo una duración de 2 semanas._


### Enlace a Taiga:
https://tree.taiga.io/project/angiedanielar-plataforma-banco-de-iniciativas-de-proyectos/backlog


### Release-burndown chart:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/backlogTotal.jpg) 


# SPRINTS. ⚙️
## Primer Sprint:
### Sprint-backlog:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/1erSprint.jpeg) 


### Descripción breve de los problemas encontrados y  mejoras realizadas al proceso:
_Nos quedó pendiente por realizar la asociación las palabras claves a la iniciativa porque aún no entendemos bien como realizarlo.
Y por problemas con el tiempo y el trabajo en equipo nos quedó pendiente por realizar las siguientes cosas de la historia de usuario número 5:_


![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/faltando1sprint.jpeg)


_Por esta razón se movió esta historia de usuario al segundo sprint y se creo aparte en el segundo sprint una historia de usuario nueva para el trabajo de las correcciones del primer sprint que nos indicó nuestro profesor._


### Sprint-burndown chart:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/backlog.jpeg) 


## Segundo Sprint:
### Sprint-backlog:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/2doSprint.jpg) 


### Descripción breve de los problemas encontrados y  mejoras realizadas al proceso:
_Nos quedó pendiente generar el reporte de las estadísticas en excel porque aún no entendemos bien como realizarlo.
Por problemas con el tiempo y el trabajo en equipo nos quedó pendiente por realizar de la historia de usuario 7 lo siguiente:_


![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/falta2sprint.jpg)


_Por esta razón se movió esta historia de usuario al tercer sprint y se creo aparte en el tercer sprint una historia de usuario nueva para el trabajo de las correcciones del segundo sprint que nos indicó nuestro profesor._


### Sprint-burndown chart:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/backlog2.jpg) 


## Tercer Sprint:
### Sprint-backlog:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/3Sprint.jpg) 


### Descripción breve de los problemas encontrados y  mejoras realizadas al proceso:
_Se cumplió con la totalidad de las historias de usuario y tareas asginadas._


### Sprint-burndown chart:
![alt text](https://raw.githubusercontent.com/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS/master/Imagenes/backlog3.jpg) 


## Reporte de pruebas y de cubrimiento. 📖
aca va el reporte (sólo la foto del reporte principal). ---> ponerlo al final 
Para la cobertura, pueden usar los plugins disponibles (EclEmma, Jacoco, etc.)


## Reporte de análisis estático de código. 🎁
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1a70a519783d4905a1dc5140955316dd)](https://app.codacy.com/manual/Edyesid/2020-1-PROYCVDS-ECI_AVENGERS?utm_source=github.com&utm_medium=referral&utm_content=Edyesid/2020-1-PROYCVDS-ECI_AVENGERS&utm_campaign=Badge_Grade_Dashboard)




