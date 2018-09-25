Diagrammes des cas d'utilisation
==========
---
## Cas d'utilisation :

![](./Diagrammes/CU_V1.png)

Description :
=============

<p>Dans ce diagramme de cas d'utilisation, nous pouvons voir le déroulement complet d'une commande, depuis l'installation des clients jusqu'à la libération de la table</p>

### Commander :
<p>
Le client est capable de consulter le menu sur une tablette à sa disposition.  
Ensuite, soit il commande seul, à l'aide de cette même tablette, soit le serveur la prends pour lui (i.e. : saisir la commande puis la valider.).  
</p>

### Suivi commande :
<p>
Une fois la commande envoyée, celle est réceptionnée par un préparateur qui peut être un barman, un cuisinier ou un glacier.
Celui-ci en commence la préparation, puis lorsque celle-ci est
terminée, en informe le serveur qui peut alors la récupérer.
La réception d'une commande mène a une notification envoyée au préparateur concerné. De même que le fait de terminer la préparation envoie une notification au serveur afin que celui ci vienne la récupérer.
</p>

### Facturation :
<p>
La facturation est constituée de deux étapes.
La première consiste en l'établissement d'une addition, répertoriant l'ensemble des consommations, leurs prix respectifs, ainsi que la somme de ces prix.  
Ensuite, l'encaissement consiste a collecter la somme requise par l'addition, que ce soit par carte bancaire, espèce ou encore ticket restaurant, etc ...
</p>
