# Usar una imagen base con Maven para construir la aplicación
FROM maven:4.0.0-openjdk-17-slim AS build

# Establecer el directorio de trabajo
WORKDIR /jobportal

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Ejecutar el comando para construir el archivo .jar
RUN mvn clean package -DskipTests

# Usar una imagen de Java más ligera para ejecutar la aplicación
FROM openjdk:17-jre-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /jobportal

# Copiar el archivo .jar construido desde la imagen anterior
COPY --from=build /jobportal/target/jobportal-0.0.1-SNAPSHOT.jar /jobportal/jobportal-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "/jobportal/jobportal-0.0.1-SNAPSHOT.jar"]
