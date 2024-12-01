# ===== Etapa de Build =====
FROM openjdk:20-bullseye AS build

# Atualiza os repositórios e instala o Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o pom.xml para otimizar o cache do Docker
COPY pom.xml .

# Baixa as dependências do Maven sem compilar o projeto
RUN mvn dependency:go-offline -B

# Copia o código-fonte para o contêiner
COPY src ./src

# Compila o projeto e empacota o JAR, ignorando os testes para acelerar o build
RUN mvn clean package -DskipTests

# ===== Etapa de Runtime =====
FROM openjdk:20-slim

# Define o diretório de trabalho para a aplicação
WORKDIR /app

# Exponha a porta que sua aplicação usará
EXPOSE 8080

# Copia o JAR construído da etapa de build
COPY --from=build /app/target/apuracaoBr-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
