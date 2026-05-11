# Usar imagem base do OpenJDK 21
FROM eclipse-temurin:21-jdk

# Criar diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven
COPY target/therapy-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
