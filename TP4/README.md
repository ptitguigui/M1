# TP4  

Dans ce TP vous trouverez la résolution d'un problème avec des methodes d'heuristique

## Contenue du TP:

Dans ce TP vous trouverez :

- Le code source du TP dans le dossier `/src`
- La synthese de ce TP à travers le fichier `README.md` 
- Le dossier de jeux de `/donnees`

## Utilisation du TP

Lancer la classe `Heuristique`, suivez les instructions afin d'iniatiliser le problème.  
Cette classe vous permettra selon un fichier donnée, la résolution du problème selon plusieurs méthodes : 
- Version globale de façon itérative
- Version exhaustive 
- Version methode locale

## Synthese des experimentations

###  Version globale de façon itérative

Cette version correspond à la partie 1.1 (l'algorithme de l'heuristique globale en construction itérative).  
Vous pouvez voir le code dans la méthode `heuristiqueGlobaleIterative()`. Cette version reprends l'algorithme expliqué dans l'énoncé. Mais, cet algorithme test tous les sommet de départ possible et garde uniquement la permutation donnant la distance la plus courte. 

En expérimentant avec les jeux de données, nous remarquons que cet algorithme donne une valeur très approximative puisqu'il donne la meilleur solution uniquement pour `exe5.atsp`. Les autres sont des réponses "rapproché" comme : 
- exe7.atsp -->  104 avec 3 5 4 6 0 1 2 
- exe7b.atsp -->  82 avec 3 5 4 6 0 2 1 
- br17.atsp -->  87 avec 0 1 2 9 11 10 13 12 7 5 3 6 4 14 8 15 16
- .. etc

### Version exhaustive 

Cette version reprend le TP3 qui calcule l'ensemble des permutations possible et récupère la meilleur solution. Par conséquent cet algorithme donne forcément la meilleur solution tant que les jeux de données ne sont pas très grand.
Par conséquent il trouve uniquement les jeux de données suivants : 
- exe5.atsp --> 22 avec 1 3 2 0 4
- exe7.atsp -->  36 avec  3 4 5 1 2 0 6 
- exe7b.atsp -->  36 avec 1 2 0 5 3 4 6 
- br17.atsp  et autres -->  données trop grande


### Version locale : Hill Climbing 

#TODO