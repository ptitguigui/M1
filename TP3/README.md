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
L'algorithme de vérification associé est le suivant:

- Pour un certificat donné
- Calculer la distance entre les villes
- Retourner la distance < K (Retourne True si distance est inférieur à K et False si non).

L'algorithme de vérification correspondant à la fonction "verifierCertificat" dans la classe NP

### Question 2 : 


<p>Q.1 L'algorithme proposer corresponds à la fonction "certificatAleatoire" dans la classe NP </p> 
<p>Q.2 Le schéma est le suivant : On choisis un certificat de façon aléatoire, nous calculons sa distance totale puis nous vérifions si il est valide, à savoir, si "distance total < k". </p> 


 
### Question 3 :

<p>Q.1 Pour une instance donnée le nombre de certficats est égal à n! </p>

<p>Q.2 L'order d'énumération proposé est:
Pour une liste contenant les éléments de 0 à n-1, l'ordre d'énumération corresponds à la position des différent élment dans la liste.
L'algorithme nous procurrant toutes les configurations est "generatePerm" dans la classe NP</p>

<p>Q.3 L'algorithme pour verifier que le problème a une solution consiste à calculer le distance totale pour chaque certificat généré et de le comparer avec k. La compléxité de cet algorithme sera au plus n*n!. 
L'algorithme correspondant est "vericiationAllPossibilities" dans ma classe NP </p>
 