# TP1 

Dans ce TP vous trouverez le problème d'algorithmique et de complexité sur le jeu `Hexaspawn`

## Contenue du TP:

Dans ce TP vous trouverez :

- Le code source du TP dans le dossier `/src`
- La synthese de ce TP à travers le fichier `README.md` 

## Explications des réalisations

### Formule mathématique de la valeur de la configuration selons ses successeurs

Il y a deux cas possible pour la valeur de la configuration : 

- soit les successeurs sont tous positifs
 	+ alors `configuration = inverse(max(successeur) + 1)`
- soit les successeurs possède des négatifs
	+ alors `configuration =  inverse(max(successeur_negatif)) + 1`
	
### Description de notre algorithme

Lors de ce TP, nous avons choisis d'utiliser deux tableaux de booleen : 
- Un pour les pions blancs
- L'autre pour les pions noirs

Puis, nous réalisons toutes les actions possible jusqu'a ce qu'elle se terminent.  
Lorsque qu'une partie est terminé, nous stockons dans une liste le nombre de coups.  
Lorsque  le blanc gagne, nous stokons un nombre positif et lorsque le noir gagne, nous stockons un nombre negatif.   
Enfin, nous choisissons le nombre le plus proche de zero qu'il soit négatif ou positif.  
Celui-ci représentera notre configuration.  
La plupart des jeux de données fonctionnent mais des cas particulier ne sont pas gérés...

