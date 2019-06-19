**Nom du cas** : ***Installation du Client.***  
**But** : Trouver une table et y installer le client.  
**Acteur Principal** : Le serveur.  
**Date de creation** : 09/10/2018.  
**Nom des responsables** : Fabien et Florian.  
**Version** : 1  
**Pre-condition** : Le serveur a accès à l'application.  
**Declenchement** : Un client (seul ou en groupe) se presente.  
**Post-condition** : Le client (seul ou en groupe) est installé a une table.  
**Scenario nominal** :   
 1. Le serveur acceuille le client.  
 2. Le serveur consulte l'application pour savoir si une table est libre.    
 3. Le serveur installe les clients sur la table.
 4. Le serveur indique a l'application que la table est occupée.

**Scenario Alternatif** :   
 2. a Aucune table libre  
 2 .a.1 : Le serveur s'apperçoit que toute les tables sont occupées.  
 2 .a.2 : Le serveur indique aux clients un temps d'attente estimé par l'application.  
 2 .a.3 : Apres ce temps d'attente, retour a 3
