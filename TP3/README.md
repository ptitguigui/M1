# TP3  

Dans ce TP vous trouverez le problème NP

## Contenue du TP:

Dans ce TP vous trouverez :

- Le code source du TP dans le dossier `/src`
- La synthese de ce TP à travers le fichier `README.md` 
- Le dossier de jeux de `/donnees`

## Utilisation du TP

Lancer la classe NP, suivez les instructions afin d'iniatiliser le problème.  
Cette classe vous permettra selon un fichier donnée, la résolution du problème selon trois modes : 
- Version manuelle
- Version aléatoire
- Version exploration exhaustive

## Réponse aux question

### Question 1 :

Le certificat de notre problème est de faire une permutation des villes où la distance est inférieur à l. Etant donnée que nous avons n villes, la taille des certificats sera : `n log(n)`. Le taille de tous les certificats sera `n!` et donc polynomiale.

### Question 2 : 

<p>Q.1 Le schéma est le suivant : On choisis un certificat de façon aléatoire, nous calculons sa distance totale puis nous vérifions si il est valide, à savoir, si "distance total < k" </p> 
 
