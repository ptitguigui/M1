TP2 : Passerelle REST
===================



----------
<p> Bienvenue sur notre passerelle REST. Mini projet réalisé dans le cadre de la Construction d'Application Repartie par Irakoze Franco Davy et Leprêtre Guillaume.</p>

# Composition du TP :

- Un projet `Maven` regroupant notre projet FTP
- Le code source se trouve dans `src/main`
- Les tests unitaire dans `src/test`
- Les diagammes de classe dans `/diagrams`
- Un fichier de config dans `fileConf.txt`
- Le dossier de par défault `/files`  permettant d'upload/télécharger des fichiers à travers ce dossier
- Ainsi que le `README.md` de ce tp

# Mise en place du TP :

- Télécharger l'archive à l'aide de la commande `git clone`
- Compiler le projet avec la commande `mvn clean compile"`
- Vous pouvez générer la javadoc avec la commande `mvn javadoc:javadoc`
- Pour lancer le programme, situé vous à la racine du module à savoir `CAR-REST`
- Exécuter la commande suivante `mvn exec:java`

# Disposition des classes :

Vous pouvez voir ci-dessous l'architecture de notre projet : 

![image](diagrams/tp2.png)

# Commandes REST 

Vous pouvez effectuer les commandes suivantes : 

| Commande   | Description   | Methode    | Commande curl |
|------------|------------|------------|------------|
| CONNECT  | Connexion au serveur  |  POST  | POST /myapp/ftp/connect HTTP/1.1 Host: localhost:8080 Content-Type: application/json {"username":"[user]","password":"[mdp]"} |
| LIST  |  liste le repertoire courant | GET | /myapp/ftp/list HTTP/1.1 Host: localhost:8080  |
| LIST  |  liste le repertoire choisis selon un chemin | POST | POST /myapp/ftp/list HTTP/1.1 Host: localhost:8080  Content-Type: application/json {"serverPath":"[path/to/directory]"}|
| CWD |  change de repertoire  |  GET  | GET /myapp/ftp/cwd/{folder} HTTP/1.1 Host: localhost:8080 |
| RETRIEVE | Télécharge un fichier du serveur |  GET  | GET /myapp/ftp/download/{filename} HTTP/1.1 Host: localhost:8080 |
| RETRIEVE | Télécharge un fichier du serveur en spécifiant plus de configurations |  POST  | POST /myapp/ftp/download HTTP/1.1 Host: localhost:8080 Content-Type: application/json { "serverPath":"[path/to/server]", "clientPath":"[path/to/server]", "filename": "[filename]"} |
| RETRIEVE | Télécharge un dossier complet avec ses sous dossiers depuis le FTP |  GET  | GET /myapp/ftp/downloadDirectory/{folder} HTTP/1.1 Host: localhost:8080 |
| STORE |  Upload un fichier vers le serveur  |  GET  |/myapp/ftp/upload/{folder} HTTP/1.1 Host: localhost:8080 |
| STORE |  Upload un fichier vers le serveur en spécifiant plus de configurations  |  POST  |POST /myapp/ftp/upload HTTP/1.1 Host: localhost:8080 Content-Type: application/json { "serverPath":"[path/to/server]", "clientPath":"[path/to/server]", "filename": "[filename]"} |
| STORE | Upload un dossier complet avec ses sous dossiers depuis le FTP  |  GET  |GET /myapp/ftp/uploadDirectory/{folder} HTTP/1.1 Host: localhost:8080 |
| RNTO  | Renomme un dossier ou un fichier |  PUT  | PUT /myapp/ftp/rename HTTP/1.1 Host: localhost:8080 Content-Type: application/json { "newFilename":"[newFilename]", "filename": [filename]" }|
| MKD | Crée un nouveau dossier sur le FTP |  POST  |POST /myapp/ftp/create HTTP/1.1 Host: localhost:8080 Content-Type: application/json { "filename":"[folder]" }|
| RMD | Supprime un dossier depuis le FTP |  DELETE  | DELETE /myapp/ftp/remove/{folder} HTTP/1.1 Host: localhost:8080 |
| DISCONNECT | Le client se déconnecte du serveur FTP |  GET  | GET /myapp/ftp/disconnect HTTP/1.1 Host: localhost:8080 |


# Tests :

Nous avons implémentés les tests dans le dossier `src/test/`

Pour lancer les tests, vous pouvez faire la commande suivante:
`mvn test`
