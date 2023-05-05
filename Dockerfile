FROM openjdk:11-jdk-slim

# Installation de Node.js et npm
RUN apt-get update && \
    apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt-get install -y nodejs && \
    apt-get clean

# Installation de maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Copie des fichiers de configuration
COPY . /app
WORKDIR /app

# Installation des dépendances et compilation
RUN mvn package

# Exposition des ports
EXPOSE 8761 8080 9411

# Lancement de l'application
CMD ["java", "-jar", "target/my-app.jar"]
