# TP4

## Exercice 1


## Exercice 2

Voir les fichiers `/labd-4/examen-2.xsd` et `/labd-4/examen-2.xsd`.  

## Exercice 3

Voir les fichier `/labd-4/a.xsd` et `/labd-4/b.xsd`.  

## Exercice 4

### Question 1

Voir le fichier `/labd-4/personne.xsd`

### Question 2

On remarque que nous ne trouvons pas de résultat lorsque nous faisons la requête : `//nom`. En effet, nous déclarons aucun namespace dans cette requete. Il faut donc faire la commande suivante `declare namespace abd="http://www.fil.univ-lille1.fr/labd"; //abd:nom`.

### Question 3

date de naissance des personnes de sexe masculin : `declare namespace abd="http://www.fil.univ-lille1.fr/labd";
for $p in //abd:personne
 return $p[abd:sexe="M"]/abd:naissance/abd:date`

## Exercice 5
