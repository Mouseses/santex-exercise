# ============================
# Stage 1: Build the application
# ============================
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copiar wrapper y archivos de configuración de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Asegurar permisos de ejecución ANTES de usar gradlew
RUN chmod +x ./gradlew

# Descargar dependencias (usa el wrapper con permisos)
RUN ./gradlew dependencies --no-daemon || true

# Copiar código fuente
COPY src src

# Compilar la aplicación
RUN ./gradlew bootJar --no-daemon

# ============================
# Stage 2: Run the application
# ============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el jar generado desde el build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
