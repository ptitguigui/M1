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

