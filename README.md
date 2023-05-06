# TP Micro Service
``` Barbary Théo, Thiry Basile, Cogo Clément ```

Ce projet est une application Spring Boot qui inclut plusieurs microservices pour démontrer une architecture de système distribué.

## Docker

Un fichier Dockerfile est présent dans le répertoire courant du projet pour permettre de construire une image Docker qui installe tous les besoins nécessaires pour exécuter l'application Spring Boot avec Vue.js


1. Construisez l'image Docker en exécutant la commande suivante :

            docker build -t projet-microservice 
            
2. Démarrez l'image Docker en exécutant la commande suivante :

            docker run -p 8761:8761 -p 8080:8080 -p 9411:9411 project-microservice

Cela va démarrer l'application Spring Boot ainsi que les services Eureka, gateway, Zipkin, et exposer les ports nécessaires pour y accéder depuis l'extérieur.

Accédez à ``http://localhost:3000`` dans votre navigateur pour accéder à l'application.

