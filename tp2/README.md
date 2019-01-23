# TP2

## Exercice 1

### Question 1

L'expression `//livre[titre="edition"]` nous donne les livres dont le titre à comme contenu "edition". Tandis que l'expression `//livre[titre=edition]` nous donne les livres dont les élements titre et edition ont au moins un même contenu.

Dans cet exemple, le résultat est identique pour ces deux requêtes:

```
<livre>
  <titre>edition</titre>
  <edition>edition</edition>
</livre>
```

### Question 2
Les expresions représentent :
1. Les livres parmis les items dont l'attribut titre est "labd" et sont positionnés en dernier.
2. Le livre positionné en dernier parmis ceux qui ont l'attribut titre "labd".
3. Les derniers livres des items ayant comme attribut titre "labd"

Les expressions `1` et `3` donnent le même résultat.

Voici un exemple :

```
<item>  
  <livre>machin</livre>  
  <livre titre="labd">truc</livre>
</item>
<item>
  <livre titre="labd">chose</livre>
  <livre>bidule</livre>
</item>
```

Pour les expresions `1` et `3` on aura comme résultat `<livre>truc</livre>`.   
Pour l'expression `2`, on aura comme résultat `<livre>chose</livre>`.  

### Question 3

L'expression `/descendant::livre[1]` correspond au premier livre dans l'arbre. Tandis que l'expression `//livre[1]` correspond a tous les premier fils livre.

Pour l'exemple suivant :

```
<item>  
  <livre>machin</livre>  
  <livre titre="labd">truc</livre>
</item>
<item>
  <livre titre="labd">chose</livre>
  <livre>bidule</livre>
</item>
```

L'expresion `/descendant::livre[1]` donne comme résultat `<livre>machin</livre>`. Tandis que pour l'expression `//livre[1]`, nous aurons comme résultat `<livre titre="labd">chose</livre>` et `<livre>machin</livre>`.

## Exercice 2

1. //fruit/producteur
2. //legume[origine="Espagne"]
3. //fruit[bio and @type="clementine" and @calibre="1"]
4. //origine[@region="Bretagne"]/preceding-sibling::producteur

## Exercice 3

### Recette 1 :

1. `//recette/titre`
2. `//ingredient/nom_ing`
3. `/descendant::recette[2]/titre`
4. `//etape[last()]`
5. `count(//recette)`
6. `//recette[count(.//ingredient) < 7]`
7. `//recette[count(.//ingredient) < 7]/titre`
8. `//recette[contains(.//nom_ing,"farine")]`
9. `//recette[contains(.//categorie,"entrée")]`

### Recette 2 :

1. `//recette/titre`
2. `//ingredient/@nom`
3. `/descendant::recette[2]/titre`
4. `//etape[last()]`
5. `count(//recette)`
6. `//recette[count(.//ing-recette) < 7]`
7. `//recette[count(.//ing-recette) < 7]/titre`
8. `//recette[.//ing-recette/@ingredient="farine"]`
9. `//recette[contains(@categ, "entree")]`

## Exercice 4
