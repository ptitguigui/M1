TP1 : serveur FTP
===================



----------
<p> Bienvenue sur notre serveur FTP. Mini projet réalisé dans le cadre de la Construction d'Application Repartie par Irakoze Franco Davy et Leprêtre Guillaume. Dans ce tp, vous trouverez un serveur FTP</p>

# Composition du TP :

- Un projet `Maven` regroupant notre projet FTP
- Le code source se trouve dans `src/main`
- Les tests unitaire dans `src/test`
- Les diagammes de classe dans `/diagrams`
- Un fichier de config dans `fileConf.txt`
- Le dossier d'accès par le client `/myFTPDirectory` permettant de stocker/transférer ce qu'il veut
- Ainsi que le `README.md` de ce tp

# Mise en place du TP :

- Télécharger l'archive à l'aide de la commande `git clone`
- Compiler les différents modules avec la commande `./serverRun "mvn exec:java" "mvn package"`
	Nous avons utilisé un script bash (lance le serveur et lance la comilation) à défaut de trouver 
	une meilleure solution par rapport au fait que nous avions besoin de faire tourner le serveur 
	pour lancer les Tests. En effet pour tester les différentes commandes sur le  Serveur FTP nous avons 
	utilié un classe  `org.apache.commons.net.ftp.FTPClient`.
- Vous pouvez générer la javadoc avec la commande `mvn javadoc:javadoc`
- Pour lancer le programme, situé vous à la racine du module à savoir `CAR-FTP`
- Exécuter la commande suivante `java -jar target/CAR-FTP-1.0-SNAPSHOT.jar repository`

# Disposition des classes :

Vous pouvez voir ci-dessous l'architecture de notre projet : 

![image](diagrams/server.png)

## Code Samples:

### Fonctionnement du server FTP  
Comme vous avez pu le voir, la classe principale  est `Server`. Elle est composé de deux objets `ServerSocket`, une pour les commandes et une pour le transfert de fichiers.  
Cette classe hérite de `Runnable`, permettant de gérer plusieurs connexions.  Lorsqu'une connexion est établie, la classe `RequestHandler` s'occupe de capturer les commandes envoyés par le client et de les traiter si celui-ci la reconnait.  
On y trouve deux configurations, une pour le `client`, une pour le `serveur` permettant de passer les données à travers les différentes commandes.

### Les commandes
Chaque commandes sont définis par une classe qui implémente la classe abstraite `Command` et sont stockés dans une Hashmap, présent dans `RequestHandler`.  
Ainsi, vous pouvez voir l'ensemble des commandes mis en place ci-dessous :  

![image](diagrams/commands.png)
 
 Ces commandes ont chacune une seule méthode `execute` qui traite la requête du client et qui renvoie les informations nécessaire à son fonctionnement.  
 Pour cela, nous avons utilisé une classe permettant de gérer les réponses possible à envoyer au client : `RequestMessage`. Elle y répertorie, les différents codes à envoyer au client, pour que celui-ci puisse comprendre ce que nous avons réalisé.
   

# Tests :

Nous avons implémentés les tes dans le dossier `src/test/java/testCommand`

Pour lancer les tests, vous pouvez faire la commande suivante:
`./serverRun "mvn exec:java" "mvn package"`
