# TP1 

Dans ce TP vous trouverez un problème d'algorithmique et de complexité :   
`Soit la région rectangulaire du plan déterminée par le rectangle (0, 0)(0, h)(l, h)(l, 0), h et
l éant deux entiers positifs et soient n points à coordonnées entières à l’intérieur de cette région.
On souhaite dessiner un rectangle dont la base est sur l’axe des x, dont l’intérieur ne contienne aucun des
n points et qui soit de surface maximale`

## Contenue du TP:

Dans ce TP vous trouverez :

- Le code source du TP dans le dossier `/src`
- La synthese de ce TP à travers le fichier `README.md` 

## Explications des réalisations

### Réalisation naïve

Nous avons deux versions permettant de résoudre le problème. Tous les deux ont la même idée de conception mais leur complexité sont différentes. 
En effet, nous avons le premier algorithme se trouvant dans la méthode : `grandRectangle_n3` qui a une compléxité de n3 et le deuxième qui à une complexité de n2 se trouvant dans la méthode : `grandRectangle_n2`.  

Pour que l'algorithme puisse fonctionner, il faut que les coordonnées soit triées par ordre croissant pour les abscisses. Le principe est de parcourir cette liste de points et de trouver la combinaison de deux points réalisant le plus grand rectangle possible sans qu'aucun autre points soit dans ce rectangle. On suposera que l'axe des abscisses fait toujours partir d'un des cotés de ce rectangle.
Ainsi nous parcourant deux fois la liste pour trouver cette combinaison. Puis, pour chaque combinaison, on cherche la plus petite ordonnée faisant partie des deux points.  

Ainsi nous pouvons calculer l'aire avec la formule suivante :  `A = (x2 - x1) * h` avec A l'aire du rectangle, x1 et x2 l'abscisse des deux points et h la plus petite ordonnée avec son abscisse compris entre x1 et x2.  

La plus grande aire est stocké et retournée par la méthode.  


### Réalisation avec le paradigme "diviser pour régner"

Pour pouvoir réaliser ce problème avec le paradigme "diviser pour régner", il faut deviner a quel moment nous pouvons diviser la liste.
Le bon moment est de diviser sur la plus petite ordonnée des points afin de ne pas trouver un plus grand rectangle entre les deux listes.  
Puis sur ces deux listes, nous pouvons trouver l'aire maximum par le biais de notre algorithme précédent.  
Grâce à ce procédé, le temps d'execution est diviser par 2.

### Réalisation de façon linéaire 

Le but est de trouver le plus grand rectangle en parcourant une seule fois la liste. Pour ce faire, il faut les moments ou l'on peux construire notre rectangle sans qu'un autre point soit à l'intérieur.  
Nous avons une expérimentation qui fonctionne avec les données du TP que vous pouvez voir sur la méthode `grandRectangle_n`.

Cette méthode fonctionne de la façon suivante : 

- Parcourt la liste et stocke le premier point p1
- Si un point p2 possède une ordonnée plus petit que le premier
    + Une ordonnée maximum maxOrdonnée dans l'intervalle p1 et p2 est stocké
    + Alors calculer l'air du rectangle `(p2.x-p1.x) * maxOrdonnée` 
    + stocker p2 dans p1
- Si un point p2 possède une ordonnée plus grande et qu'on à déja passé un points
    + Récupérer le point p3 juste avant p2
    + Une ordonnée maximum maxOrdonnée dans l'intervalle p1 et p3 est stocké
    + Alors calculer l'air du rectangle `(p3.x-p1.x) * maxOrdonnée` 
    + stocker p3 dans p1
- Sinon ne rien faire et stocker la maximum ordonnée

