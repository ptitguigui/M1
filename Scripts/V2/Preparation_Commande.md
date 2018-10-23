**Nom du cas** : ***Préparation de la commande.***  
**But** : Réaliser toutes les préparations d'une commande.  
**Acteur Principal** : Les préparateurs.  
**Date de creation** : 09/10/2018.  
**Nom des responsables** : Emeline et Thomas.  
**Version** : 1  
**Pre-condition** : Les préparateurs ont une commande à réaliser.  
**Declenchement** : Le serveur notifie les préparateurs qu'une commande a été prise.  
**Post-condition** : Le serveur vient chercher la commande.  
**Scenario nominal** :   
 1. Un préparateur est notifié d'une nouvelle commande.  
 2. Un préparateur réalise la commande.    
 3. Le préparateur notifie le serveur que la commande est prête.

**Scenario Alternatif** :   
 2. a Retard sur la commande  
 2 .a.1 : Le préparateur s'aperçoit qu'il y aura du retard sur la commande.  
 2 .a.2 : Le préparateur notifie le serveur de ce retard.  
 2 .a.3 : Apres ce temps d'attente, retour au 2

 3. a Changement de dernière minute
 3 .a.1 : Le préparateur reçoit une notification qui indique un changement sur une commande.  
 3 .a.2 : Le préparateur prends en compte le changement de commande.  
 3 .a.3 : Retour en 2.
