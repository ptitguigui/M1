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

### Qu'est ce qu'une propriété NP?

#### Question 1 :

Le certificat de notre problème est de faire une permutation des villes où la distance est inférieur à l. Etant donnée que nous avons n villes, la taille des certificats sera : `n log(n)`. Le taille de tous les certificats sera `n!` et donc polynomiale.
L'algorithme de vérification associé est le suivant:

- Pour un certificat donné
- Calculer la distance entre les villes
- Retourner la distance < K (Retourne True si distance est inférieur à K et False si non).

L'algorithme de vérification correspondant à la fonction "verifierCertificat" dans la classe NP

#### Question 2 : 


<p>Q.1 L'algorithme proposer corresponds à la fonction "certificatAleatoire" dans la classe NP </p> 
<p>Q.2 Le schéma est le suivant : On choisis un certificat de façon aléatoire, nous calculons sa distance totale puis nous vérifions si il est valide, à savoir, si "distance total < k". </p> 


 
#### Question 3 :

<p>Q.1 Pour une instance donnée le nombre de certficats est égal à n! </p>

<p>Q.2 L'order d'énumération proposé est:
Pour une liste contenant les numéros de villes de 0 à n-1, l'ordre d'énumération corresponds à la position des différentes villes dans la liste.
L'algorithme nous procurrant toutes les configurations est "generatePerm" dans la classe NP</p>

<p>Q.3 L'algorithme pour verifier que le problème a une solution consiste à calculer le distance totale pour chaque certificat généré et de le comparer avec k. La compléxité de cet algorithme sera au plus n*n!. 
L'algorithme correspondant est "vericiationAllPossibilities" dans ma classe NP </p>
 
 
## Réductions polynomiales


#### Question 1 : Réduction Hamilton Cycle vers TSP


Hamilton Cycle.

Cycle hamiltonien ham: [0 .. n-1] -> [0 .. n-1]
- D(ham(v), ham(v+1))= True, pour tout 0 <= v <= n-2
- D(ham(v-1), ham(0))= True
- Non, si non


##### Reduction

Q1. L'objectif est de réduire un problème cycle Hamiltonien vers un problème TSP

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

##### Réduction correcte

La réduction du cycle hamiltonien vers TSP donne une matrice d' entiers n*n tel que la distance entre deux villes quand elles sont liées soit = 0 et la longueur obtenue suite à la résolution du problème TSP sera égal à 0, la valeur fixée pour la longueur totale.

Q2. (Voir code : initValHAM)

Q3. Etant donnée que Halmitone-Cycle est NP-Complet et, en considérant le faite que Halmitone-Cycle se réduis en TSP, TSP est donc au pire NP-Complet. 
    
Q4. Oui il se réduit. En effet, tous les deux sont NP-Complet. Par conséquent ils se réduisent polynomialement l'un,l'autre .  


#### Question 2 : Reduction Hamilton Path vers Hamilton Cycle

Hamiton Path.

Path hamiltonien ham: [0 .. n-1] -> [0 .. n-1]
- D(ham(v), ham(v+1))= True, pour tout 0 <= v <= n-1
- Non, si non


##### Reduction

Q1. L'objectif est de réduire un problème path Hamiltonien vers un problème cycle hamiltonien.

Dans la suite nous aurons:

- P la matrice Hamilton Path 
- G le graphe de Hamilton Cycle
- E le graphe de Hamilton Path
- N le nombre de sommets sur le graphe G
- n le nombre de sommets sur E
- D la matrice Hamilton Cycle qui sera générée


La réduction sera faite de la manière suivante:

- N le nombre de sommets sur G sera: N = n+1
- La construction de la matrice D se fera de la manière suivante:
Pour tout sommets s1, s2 ayant un numéro compris entre 0 et n-1
- si P(s1, s2) = True -> D(s1, s2) = True
- si P(s1, s2) = False -> D(s1, s2) = False

On ajoutera un sommet z dans Hamilton Cycle tel que:

pour tout i compris entre 0 et n-1, on ait:

D(z,si) = true;


Ensuite le résolution du problème Hamilton cycle sera faite par une réduction au probème TSP que l'on sait déjà faire.


L'algorithme de réduction est polynomiale, le passage de la matrice P à D, necessite un parcours d'un tableau à deux entrées et un ajout d'un sommet.Ce qui nous conduits à une construction en theta(n²)

##### Réduction correcte

La réduction de Hamilton Path donne une matrice de taille (n+1)* (n+1) avec un nouveau sommet qui est rélié avec tous les autres sommets. Nous aurons bien un cycle vu que ce nouveau sommet sera lié au premier sommet et au dernier sommet du path provenant de Hamilton Path, nous aurons donc une solution de Hamilton cycle.

#### Question 3 : Réduction Hamilton Path vers TSP
  
Etant donné que Hamilton Path se réduit polynomialement en Hamilton Cycle(déjà prouvé) et que Hamilton Cycle se réduit polynomialement en TSP (déjà prouvé), on en déduit qu'une réduction polynomialement de Hamilton path se réduit en TSP.

#### Question 5

### Objectif:  Prouver que Si TSPOpt1 est P alors TSP est aussi P

Si TSPOpt1 est P alors TSP l'est aussi car TSPOpt1 se réduit polynialement en TSP Donc TSP sera au pire P.

En effet le réduction TSPOpt1 en TSP se fait par une affectation qui correspond à la construction de la matrice de TSP.
Avec TS la matrice de TSPOpt1 et t la matrice TSP qui sera générée on aura:
TS =  T
la longueur minimale L de TSP sera:

L = une longueur quelconque suffisamment grande.

TSPOpt1 aura comme solution la longueur minimale parmis les longueurs des certificats valides. Si TSP n'a pas de solution TSPOpt1 n'en aura pas aussi.



#### Question 6

### Objectif:  Prouver que Si TSP est P alors TSPOpt1 est aussi P

TSPOpt1 sera P si TSP est P. En effet TSP se réduit polyniomalement en TSPOpt1 correspondant à l'affectation TS = T, 
Avec TS la matrice de TSPOpt1 qui sera générée et T la matrice TSP. 
Donc si TSP est P, alors TSPOpt1 sera (au pire) P (le but de la réduction étant de passer à un problème plus facile à résoudre).

Si TSPOpt1 a  comme solution une distance l, il faudra prendra cette distance comme L la longueur en entré dans TSP. Et TSP aura une solution. Si TSPOpt1 n'a pas de solution alors TSP avec une longueur L = l n'aura pas de solution car il n'existera pas une tournée tel que la longueur soit <= l.


#### Question 7


TSPOpt2 sera P si TSP est P. En effet TSP se réduit polyniomalement en TSPOpt2 correspondant à l'affectation TS = T, 
Avec TS la matrice de TSPOpt2 qui sera générée et T la matrice TSP. 
Donc si TSP est P, alors TSPOpt2 sera (au pire) P (le but de la réduction étant de passer à un problème plus facile à résoudre).

Si TSPOpt2 a  comme solution une tournée avec une distance l minimale, il faudra prendra cette distance comme L la longueur en entré dans TSP. Et TSP aura une solution. Si TSPOpt2 n'a pas de solution alors TSP avec une longueur L = l n'aura pas de solution car il n'existera pas une tournée tel que la longueur soit <= l.





