**Nom du cas** : Encaissement des clients.  
**But** : Faire payer les clients pour les services demandés.  
**Acteur Principal** : Le serveur.  
**Date de creation** : 09/10/2018.  
**Nom des responsables** : Emeline et Thomas.  
**Version** : 1  
**Pre-condition** : Les clients ont fini de consommer et s'apprêtent à partir.  
**Declenchement** : Le serveur donne l'addition.  
**Post-condition** : Les clients partent du restaurant.   
**Scenario nominal** :   
 1. Le serveur donne l'addition aux clients.  
 2. Les clients paient l'addition .    
 3. Les clients partent du restaurant.

**Scenario Alternatif** :   
 2. a Ristourne  
 2 .a.1 : Le serveur applique la ristourne sur l'addition.  
 2 .a.2 : Le serveur donne la nouvelle addition.  
 2 .a.3 : Retour au 2

 2. a Le client ne peut pas payer  
 2 .a.1 : Le serveur fait signer au client une reconnaissance de dettes.  
 2 .a.2 : Retour en 3
