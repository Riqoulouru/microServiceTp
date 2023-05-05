# TP Micro Service

Ce projet est une application Spring Boot qui inclut plusieurs microservices pour démontrer une architecture de système distribué.

## Docker

Un fichier Dockerfile est présent dans le répertoire courant du projet pour permettre de construire une image Docker qui installe tous les besoins nécessaires pour exécuter l'application Spring Boot avec Vue.js


1. Construisez l'image Docker en exécutant la commande suivante :

            docker build -t projet-microservice 
            
2. Démarrez l'image Docker en exécutant la commande suivante :

            docker run -p 8761:8761 -p 8080:8080 -p 9411:9411 project-microservice

Cela va démarrer l'application Spring Boot ainsi que les services Eureka, Zuul, Zipkin et Sleuth et JWT, et exposer les ports nécessaires pour y accéder depuis l'extérieur.

Notez que les ports exposés (8761, 8080 et 9411) sont les ports par défaut utilisés par les services Eureka, Zuul et Zipkin.

## Installation

Pour exécuter ce projet, vous avez besoin de Java 11 et d'un IDE (IntelliJ est recommandé).

1. Clonez le projet sur votre machine.

2. Ouvrez le projet dans votre IDE.

3. Démarrez l'application Eureka en exécutant la classe EurekaApplication qui se trouve dans le package LosGuerreros.Eureka.

4. Ensuite, démarrez l'application MicroservicesApplication qui se trouve dans le package LosGuerreros.MicroServices.

5. Démarrez chaque microservice individuellement en exécutant les classes correspondantes dans le package LosGuerreros.microservices. Nous avons quatre microservices, LosGuerreros.microservicesclients, LosGuerreros.microservicescommandes,LosGuerreros.microservicespaiements et LosGuerreros.microservicesproduits.

6. Enfin, pour lancer le frontend, accédez au dossier frontend et exécutez les commandes suivantes :

    
        npm install
        
        npm run serve
    
Ceci lancera l'application Vue.js qui communique avec les microservices.

Accédez à ``http://localhost:3000`` dans votre navigateur pour accéder à l'application.

## Configuration

Vous pouvez modifier la configuration des microservices dans le fichier application.properties dans chaque module. Par exemple, vous pouvez changer le port sur lequel s'exécute chaque microservice cependant le front end ne fonctionnera alors plus a moins d'être modifié également.
