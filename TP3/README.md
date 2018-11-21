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

## Qu'est ce qu'une propriété NP?

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
Pour une liste contenant les numéros de villes de 0 à n-1, l'ordre d'énumération corresponds à la position des différentes villes dans la liste.
L'algorithme nous procurrant toutes les configurations est "generatePerm" dans la classe NP</p>

<p>Q.3 L'algorithme pour verifier que le problème a une solution consiste à calculer le distance totale pour chaque certificat généré et de le comparer avec k. La compléxité de cet algorithme sera au plus n*n!. 
L'algorithme correspondant est "vericiationAllPossibilities" dans ma classe NP </p>
 
 
## Réductions polynomiales


### Question 1 :


Q.1 HamiltonCycle -> Villes.Cycle hamiltonien ham: [0 .. n-1] -> [0 .. n-1] - Permutation de n villes tel que:
- D(ham(v), ham(v+1))= True, pour tout 0 <= v <= n-2
- D(ham(v-1), ham(0))= True
- Non, si non

#### Reduction

L'objectif est de réduire un problème cycle Hamiltonien vers un problème TSP

Dans la suite nous aurons:

- T la matrice TSP qui sera générée
- n le nombre de villes dans TSP
- G le graphe du cycle d'hamilton
- N le nombre de sommets sur le graphe G
- D la matrice correspondant au cycle hamiltonien
- l la longueur en entrée dans un problème TSP

La réduction sera faite de la manière suivante:

- n sera à égal à N : une ville correspondra à un sommet
- Construction de la matrcie T de TSP se fera de la manière suivante:
- Pour toutes villes v1 et v2 compris dans [0 .. n-1]
- si D(v1, v2) = True -> T(v1 , v2) = 0
- sinon T(v1, v2) = 1 
- l prendra la valeur 0

Ensuite le résolution du problème TSP sera faite, toutes les données ayant été générées. La fonction éffectuant la réduction est initValHAM

l'algorithme de réduction est polynomiale , En effet la réduction sera faite par une lecture de la matrice correspondant au cycle d'hamilton, ce qui revient à lire un tableau à deux entrées.Donc on aura une construction en theta(n²)).

#### Réduction correcte

Un cycle hamiltonien donne une matrice d' entiers n*n tel que la distance entre deux villes quand elles sont liées soit = 0 et la longueur obtenue suite à la résolution du problème TSP sera égal à 0, la valeur fixée pour la longueur totale.





