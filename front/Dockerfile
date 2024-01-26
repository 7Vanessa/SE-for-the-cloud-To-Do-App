# Utiliser une image Node.js comme base
FROM node:14 as builder

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier package.json et package-lock.json
COPY front/package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste des fichiers de l'application
COPY ./front .

# Construire l'application React
RUN npm run build

# Utiliser une image Nginx pour servir l'application construite
FROM nginx:1.21-alpine

# Copier les fichiers construits depuis l'image Node.js
COPY --from=builder /app/build /usr/share/nginx/html

# Exposer le port 80 pour Nginx (par défaut)
EXPOSE 80

# La commande par défaut pour démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]
