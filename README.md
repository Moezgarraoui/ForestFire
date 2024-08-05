# Simulation de la Propagation d’un Feu de Forêt

## Description

Ce projet implémente une simulation de la propagation d’un feu de forêt. La forêt est représentée par une grille de dimension h x l, où chaque cellule peut être dans l'un des états suivants : arbre (T), en feu (F), ou cendre (A). La simulation progresse étape par étape et s'arrête lorsqu'il n'y a plus aucune case en feu.

### Fonctionnement de la Simulation

1. **État Initial** : Une ou plusieurs cases sont initialement en feu.
2. **Propagation du Feu** : À chaque étape, si une case est en feu, elle s'éteint et devient cendre à l'étape suivante. Il y a une probabilité `p` que le feu se propage aux cases adjacentes (haut, bas, gauche, droite).
3. **Arrêt de la Simulation** : La simulation continue jusqu'à ce qu'il n'y ait plus de cases en feu.

Les dimensions de la grille, les positions initiales des feux, et la probabilité de propagation sont des paramètres configurables via un fichier de configuration.

## Fonctionnalités

- Initialisation de la grille avec des dimensions personnalisables.
- Définition des positions initiales des feux.
- Propagation du feu avec une probabilité définie.
- Affichage de l'état de la forêt à chaque étape de la simulation.
- Possibilité de démarrer, arrêter et reprendre la simulation.

## Prérequis

- Java 17 ou plus récent
- Maven 3.6.0 ou plus récent
- Un navigateur web moderne (Chrome, Firefox, etc.)

## Installation

### Backend (Spring Boot)

1. Clonez le dépôt

    ```sh
    git clone https://github.com/Moezgarraoui/ForestFire.git
    cd Simulation-forest-fire
    ```

2. Compilez et démarrez l'application Spring Boot

    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

### Frontend (JavaScript)

1. Allez dans le répertoire du frontend

    ```sh
    cd src/main/resources/static
    ```
## Choix Technologique : Spring Boot pour le Backend

### Productivité accrue
Spring Boot permet un démarrage rapide avec une configuration par défaut intelligente et réduit le boilerplate, ce qui permet de se concentrer sur la logique métier.

### Écosystème riche et mature
Avec son intégration facile avec diverses bibliothèques et outils, ainsi que son vaste support communautaire, Spring Boot assure un développement fluide et efficace.

### Gestion des dépendances
Les Spring Boot Starters simplifient la gestion des dépendances et évitent les conflits de version, assurant une configuration optimale et sans erreurs.

### Testabilité
Spring Boot propose des outils et annotations pour faciliter les tests unitaires et d'intégration, garantissant la fiabilité de l'application.

### Performance et efficacité
Les applications Spring Boot sont légères et démarrent rapidement, cruciales pour les environnements de développement agiles et les déploiements fréquents.

### Déploiement simplifié
Avec des applications autonomes intégrant un serveur, Spring Boot simplifie le déploiement et réduit les problèmes de compatibilité liés aux serveurs externes.

Spring Boot est donc un choix idéal pour le backend de notre projet de simulation de la propagation d’un feu de forêt, offrant productivité, robustesse et scalabilité.

## Utilisation

1. Ouvrez votre navigateur web et accédez à `http://localhost:8080/index.html`.
2. Cliquez sur "Démarrer la simulation" pour lancer la simulation.
3. Utilisez le bouton "Stop" pour arrêter la simulation et "Continuer" pour la reprendre.
4. La grille affichera l'état actuel de la forêt avec des cases colorées représentant les arbres, les feux et les cendres.

## Tests

### Tests Unitaires du Service FireSimulatorService

Les tests unitaires de la classe `FireSimulatorService` vérifient le bon fonctionnement de la logique de simulation du feu de forêt. Les principaux tests sont :

- **testRunSimulation** : Vérifie que la simulation fonctionne correctement avec des paramètres valides.
- **testRunSimulationWithInvalidFireCount** : Vérifie que l'exception `InvalidFireCountException` est levée lorsque le nombre initial de feux est invalide (par exemple, `0`).

### Tests d'Intégration du Contrôleur ForestFireController

Les tests d'intégration de la classe `ForestFireController` vérifient l'interaction entre le contrôleur et les services. Les principaux tests sont :

- **testSimulate** : Vérifie que l'API `/simulate` renvoie correctement les étapes de la simulation.
- **testSimulateApiConnectionException** : Vérifie que l'API renvoie un statut `503 Service Unavailable` lorsque la connexion à l'API échoue.
- **testSimulateInvalidFireCountException** : Vérifie que l'API renvoie un statut `400 Bad Request` lorsque le nombre initial de feux est invalide.

#### Exécution des Tests Unitaires et d'Intégration

Pour exécuter les tests unitaires et d'intégration, utilisez la commande suivante :

```sh
mvn test

## Configuration

Le fichier `application.properties` contient les paramètres de configuration suivants :

```properties
grid.height=6
grid.width=6
fire.probability=0.8
initial.fire.count=1
