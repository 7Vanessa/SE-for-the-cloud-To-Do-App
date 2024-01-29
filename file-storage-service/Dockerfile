# Utiliser une image Maven pour construire l'application
FROM maven:latest AS BUILD

# Créer le répertoire d'application
WORKDIR /app

# Copier le fichier pom.xml pour télécharger les dépendances
COPY pom.xml .

# Télécharger les dépendances Maven
RUN mvn dependency:go-offline

# Copier les sources de l'application
COPY src src

# Copier le fichier de configuration
COPY src/main/resources/application.properties src/main/resources/

# Compiler les sources et créer le jar exécutable
RUN mvn package -DskipTests

# Utiliser une image OpenJDK comme base
FROM openjdk:17

# Copier le jar exécutable de l'étape précédente
COPY --from=build /app/target/file-storage-service-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel votre application fonctionne
EXPOSE 8082

# Commande pour exécuter les tests lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]
