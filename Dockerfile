# Usar una imagen base con Maven para construir la aplicación
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establecer el directorio de trabajo
WORKDIR /jobportal

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Ejecutar el comando para construir el archivo .jar
RUN mvn clean package -DskipTests

# Usar una imagen de Java más ligera para ejecutar la aplicación
FROM eclipse-temurin:17-jre

# Establecer el directorio de trabajo en el contenedor
WORKDIR /jobportal

# Copiar el archivo .jar construido desde la imagen anterior
COPY --from=build /jobportal/target/jobportal-0.0.1-SNAPSHOT.jar /jobportal/jobportal-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "/jobportal/jobportal-0.0.1-SNAPSHOT.jar"]
