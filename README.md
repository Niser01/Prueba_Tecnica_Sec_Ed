# Conversor de hora a texto - API REST con Spring Boot

Este proyecto es una aplicación **Spring Boot** que expone un servicio REST para conversión de archivos (ejemplo: subir un archivo y procesarlo).  
Actualmente está configurado para ejecutarse fácilmente desde **VSCode** usando la extensión *Spring Boot Dashboard*.

## 📋 Requisitos previos

Asegúrate de tener instalado en tu equipo:

- [Java 17+]
- [Maven 3.8+]
- [Git]
- [Visual Studio Code]
  - Extensión **Spring Boot Tools** (Spring Boot Dashboard)  
  - Extensión **Java Extension Pack**  

## 📂 Clonar el repositorio

Clona este proyecto en tu máquina local con:

```bash
git clone https://github.com/Niser01/Prueba_Tecnica_Sec_Ed
cd backend
```

## ⚙️ Instalación

Compila e instala las dependencias del proyecto con Maven:

```bash
mvn clean install
```

## ▶️ Ejecución

### 🔹 Opción 1: Desde VSCode con Spring Boot Dashboard

1. Abre la carpeta **backend** en **VSCode**.  
2. Ve a la pestaña **Spring Boot Dashboard** (en la barra lateral izquierda).  
3. Selecciona el proyecto que aparece listado (VSCode detectará automáticamente la clase principal BackendApplication).
4. Haz clic en **Run** para iniciar la aplicación.  

La aplicación quedará levantada en:

```
http://localhost:8080
```

### 🔹 Opción 2: Desde línea de comandos

Ejecuta:

```bash
mvn spring-boot:run
```

o bien:

```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## 📡 Endpoints principales

Por defecto los endpoints expuestos son:

- **Subir archivo**  
  ```
  POST /upload
  Content-Type: multipart/form-data
  ```
  Parámetros:
  - `file`: archivo a subir.

Ejemplo usando **cURL**:

```bash
curl -X POST http://localhost:8080/upload \
  -F "file=@ejemplo.txt"

```

## 🧪 Pruebas

Puedes probar los endpoints con:

- [Insomnia](https://insomnia.rest/) 
- [Postman](https://www.postman.com/)  
- [cURL](https://curl.se/)  


## 🚀 Despliegue futuro

Se puede empaquetar como `jar` o `war` y desplegar en cualquier servidor o nube compatible con Java (Ej: AWS, Heroku, Azure, Docker).  


