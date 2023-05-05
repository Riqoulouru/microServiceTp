# TP Micro Service

Ce projet est une application Spring Boot qui inclut plusieurs microservices pour démontrer une architecture de système distribué.

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

##Configuration

Vous pouvez modifier la configuration des microservices dans le fichier application.properties dans chaque module. Par exemple, vous pouvez changer le port sur lequel s'exécute chaque microservice cependant le front end ne fonctionnera alors plus a moins d'être modifié également.
