# TP2 

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

Dans de ce TP, nous avons choisi d'utiliser deux tableaux de booleen : 
- Un pour les pions blancs
- L'autre pour les pions noirs

Nous avons implémenté deux fonctions play une pour le joueur noir et l'autre pour le joueur blanc.

Pour lancer le jeu nous appelons la fonction playblanc vu que c'est le joueur blanc qui doit commencer le jeu.

Les fonctions play (playblanc et playnoir) auront la même fonctionnalité  c'est à dire pour des pions de même couleur, elles vont faire jouer chaque pions et calculer le nombre de coups optimal jusqu'à la fin du jeu pour chaque pion; tout en choisissant le nombre de coup optimal. La fonction retourne le nombre de coup minimal parmi tous les cas gagnants initiés par les différents pions, si non elle retourne le nombre de coup du pion ayant résisté le plus.


Pour faire jouer un pion , nous avons implémentés deux fonctions l'une pour le pion blanc et l'autre pour le pion noir. Encore une fois ces fonctions ont le même comportement c'est à dire qu'elles vont faire jouer le pion en testant les trois choix qui lui sont donnés(attaque à gauche, avancer, attaque à droite) et en calculant à chaque fois le nombre de coup optimale.

 
