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

## Priorisation des CU
|          CU       | Importance (MOA)| Complexité (MOE)| Argumentation |
|-------------------|-----------------|-----------------|---------------|  
| Installer         | 3  | 1 | Concret pour le client|               
| Afficher Menu | 5 | 2 | Demande de la MOA, sert de base a l'application|               
| Saisir commande | 4 | 3 | Operation principale |            
| Valider commande | 3 | 4 | Suite directe de la saisie, interactions fortes |            
| Reception commande| 2 | 2 | / |            
| Preparation | 1 | 1 | Simple changement d'etat |            
| Envoi | 2 | 1 | / |            
| Servir commande | 2 | 1 | / |            
| Donner l'addition | 3 | 2 | / |            
| Encaisser | 1 | 5 | Risque technologique, securité |            
| Liberer table | 2 | 1 | / |  

## Reflexion de notre CU :

<p>
Avant de réaliser notre CU, nous avons réaliser un premier tableau grâce à nos [scénarios](../Scripts/)
</p>

|SUJET |TYPE |CONCEPT |
|--------|------|-------------|  
|Serveur |Acteur |Serveur |
|Installer |Action |CU |
|Famille |Acteur |Utlisateur / Client |
|Table |Concept -> Classe | / |
|Libre |Donnée de la Table | / |
|Prendre commande |Action |CU |
|Commande |Concept -> Classe | / |
|Boissons |Concept & donnée de la Commande | / |
|Barman |Acteur |Barman |
|Notification |Objet | / |
|Préparer commande |Action |CU |
|Cuisinier |Acteur |Cuisinier |
|Servir les plats |Action |CU |
|Glacier |Acteur |Glacier |
|Donner l'addition |Action |CU |
|Encaisser |Action |CU |
|Libérer une table |Action |CU |
|Addition |Objet | / |
