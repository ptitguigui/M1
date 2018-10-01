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
Lorsque nous stockons les différents points dans une liste, nous devons nous arrêter lorsque nous trouvons une ordonnée plus petite que celle du premier point ajouté.  

