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
				
ham: [0 .. n-1] -> [0 .. n-1] où ville = ham(v)= un sommet tel que:
- ham(v1), ham(v2) ne sont pas liées
- ssi D(ham(v1), ham(v2)) = False


#### Réduction correcte

Un cycle hamiltonien qui donne une matrice d' entiers n*n tel que la distance(random(n)) entre deux villes quand elles sont liées soit <= n et la longueur obtenue suite à la résolution du problème TSP soit inférieur à n*n .


