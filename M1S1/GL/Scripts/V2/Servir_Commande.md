**Nom du cas** : ***Commande servis par le serveur.***  
**But** : Prendre la commande du client.  
**Acteur Principal** : Le Serveur.  
**Date de creation** : 09/10/2018.  
**Nom des responsables** : Guillaume.  
**Version** : 1.0  
**Pre-condition** : La commande a été préparé .  
**Declenchement** :Le serveur est notifié de la commande.  
**Post-condition** : Les client sont servis.  
**Scenario nominal** :   
 1. Le serveur récupère la commande chez le préparateur
 2. Le client reçoit la commande servis par le serveur
 3. Le serveur passe la commande à l'état servis

**Scenario Alternatif** :   
  2.a Le client remarque qu'il manque quelque chose sur la commande  
  2.a.1 Le serveur envoie notifie le problème sur l'application.  
  2.a.2 Le préparateur corrige le problème.  
  2.a.3 Le préparateur notifie le Serveur
  2.a.3 retour a 2
