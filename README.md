# 📚 Challenge Literatura - Alura Latam

Aplicación de consola desarrollada en Java con Spring Boot, que permite buscar, almacenar y consultar información sobre libros y autores obtenidos de la API Gutendex.

## 🚀 Características

Buscar libro en la web y guardarlo en base de datos.

- Listar todos los libros almacenados.

- Buscar un libro por título.

- Listar todos los autores registrados.

- Buscar autores vivos en un año determinado.

- Listar libros por idioma.

## 🛠️ Tecnologías utilizadas

Java 17+

Spring Boot 3+

Spring Data JPA

Hibernate

pgAdmin 4 (base de datos)

Gutendex API (fuente de datos)

## 📦 Instalación y ejecución

Clonar el repositorio

git clone https://github.com/usuario/challenge-literatura.git
cd challenge-literatura


Configurar la base de datos
En el archivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/challenge_literatura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update


Compilar y ejecutar

./mvnw spring-boot:run

## 📋 Uso de la aplicación

El menú principal ofrece las siguientes opciones:

1.- Buscar libro en la web  
2.- Listar todos los libros de la base  
3.- Buscar un libro por el título  
4.- Listar todos los autores registrados  
5.- Buscar autores vivos a partir de un año dado  
6.- Listar libros por idioma  
0.- Salir  


### Ejemplo de búsqueda:
- 1  
<img width="795" height="489" alt="image" src="https://github.com/user-attachments/assets/ddd27acd-5657-416e-b8fc-13d948eeb6f5" />  
- 2  
  <img width="369" height="505" alt="image" src="https://github.com/user-attachments/assets/f4244ca5-b4d1-4c3d-982b-2fa26a812538" />  
- 3  
  <img width="424" height="470" alt="image" src="https://github.com/user-attachments/assets/07a0da91-3e56-4d1e-919d-aec3452cd8f3" />  
- 4  
  <img width="276" height="436" alt="image" src="https://github.com/user-attachments/assets/16782e30-2975-4669-b2a8-55679ca19cb3" />  
- 5  
  <img width="451" height="393" alt="image" src="https://github.com/user-attachments/assets/c52a5540-43b4-454c-bf92-91fade83fb16" />  
- 6  
  <img width="422" height="513" alt="image" src="https://github.com/user-attachments/assets/27b9d3a2-1ecf-405e-bcf7-c4f35e333dd2" />  

## 📖 API Utilizada

Gutendex API
URL Base: https://gutendex.com/books/
Permite realizar búsquedas por título, idioma, autor, etc.

## ✍️ Autor

Proyecto desarrollado como parte del Challenge de Alura Latam.
