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

# Clone le référentiel GitHub
RUN git clone https://github.com/Riqoulouru/microServiceTp.git

# Définit le répertoire de travail à l'intérieur du conteneur
WORKDIR /microServiceTp

# Compile le frontend Vue.js
WORKDIR /app/microServiceTp/frontend
RUN npm install
RUN npm run build

# Lancement de l'application
CMD ["java", "-version", "20", "-cp", "Eureka", "LosGuerreros.Eureka.EurekaApplication"]
CMD ["java", "-version", "20", "-cp", "MicroServices", "LosGuerreros.APIGateway.APIGatewayApplication"]
CMD ["java", "-version", "20", "-cp", "APIGateway", "LosGuerreros.MicroServices.MicroServicesApplication"]
CMD ["java", "-version", "20", "-cp", "microserviceclients", "LosGuerreros.microserviceclients.MicroServiceclientsApplication"]
CMD ["java", "-version", "20", "-cp", "microservicecommandes", "LosGuerreros.microservicecommandes.MicroServicecommandesApplication"]
CMD ["java", "-version", "20", "-cp", "microservicepaiements", "LosGuerreros.microservicepaiements.MicroServicepaiementsApplication"]
CMD ["java", "-version", "20", "-cp", "microserviceproduits", "LosGuerreros.microserviceproduits.MicroServiceproduitsApplication"]

# Compile et exécute l'application
RUN ./mvnw package
CMD ["java", "-jar", "target/microServices-0.0.1-SNAPSHOT.jar"]

