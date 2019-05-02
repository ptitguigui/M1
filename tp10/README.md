# TP10

## Exercice 1

1. Cette requête affiche tous les types déclarer dans le RDF : l'instance x et son type t.
   Il y a 33 résultats en tout :

   ```
   SELECT  (count(?x) as ?nb) WHERE
    {
         ?x rdf:type ?t
    }   
    ```
    John est à le type Person :
    ```
    SELECT ?x ?t WHERE
    {
        ?x rdf:type ?t
        FILTER regex (?x, "John")
    }
    ```
2. La requête donne les couples mariés. Il y a 6 résultats :

```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT (count(*) as ?nb) WHERE {
        ?x humans:hasSpouse ?y
    }
```
3. La propriété est l'âge, voici la requête :

```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x ?y WHERE {
        ?x  humans:age ?y
        FILTER ( xsd:integer(?y) > 20)
    }
```

4. La propriété est shoesize.  
4.1

```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x ?y WHERE {
        ?x  humans:shoesize ?y
    }
```
4.2
```
        PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT ?x ?y WHERE {
            ?x a humans:Person
        OPTIONAL {?x humans:shoesize ?y}
        }
```
4.3

```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x ?y WHERE {
        ?x a humans:Person
    OPTIONAL {
	    ?x humans:shoesize ?y
	    FILTER ( xsd:integer(?y) >8)
    }
    }
```
4.4
```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x ?y ?z WHERE {
        ?x  humans:shoesize ?y
        ?x  humans:shirtsize ?z
        FILTER (xsd:integer(?y) > 8 || xsd:integer(?z) > 12)

    }
```

5.

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT ?x ?y ?z WHERE {
    ?x  humans:shoesize ?y
    ?x  humans:shirtsize?z
    FILTER (xsd:integer(?y) > 8 || xsd:integer(?z) > 12)
    FILTER regex (?x, "John")        
}
```

5.1

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT ?t ?y WHERE {
    ?x ?t ?y
    FILTER regex (?x, "John")        
}
```  

6.1

```
   PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT * WHERE {
    {
    ?x humans:hasParent ?y
    } UNION {
    ?x humans:hasChild ?y
    }
    }
```

6.2


```
   PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?y WHERE {
    {
    ?x humans:hasParent ?y
    } UNION {
    ?y humans:hasChild ?t
    }
    }
```


6.3

Nous optenons 6 réponses dont un doublon.

6.4

```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT distinct ?y WHERE {
        {
        ?x humans:hasParent ?y
        } UNION {
        ?y humans:hasChild ?t	
        }
        }
```
Nous optenons 5 réponses.

6.5

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x WHERE {
        ?x  a humans:Man
         OPTIONAL { ?x humans:hasChild ?y } .
        FILTER ( !bound(?y))
    }
```

6.6

```
 PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?x ?y WHERE {
        ?x a humans:Woman
        ?x humans:hasSpouse ?t
    OPTIONAL {
	    ?x humans:hasChild ?y
    }
    }
```

7.

```
   PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT distinct ?x ?y WHERE {
    ?x humans:hasSpouse ?y
	?x humans:shirtsize ?z
	?y humans:shirtsize ?t
	FILTER (?z =?t )
    }
```

8.

```
   PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT distinct ?x ?y WHERE {
    ?x humans:hasFriend ?y
    }
```
9.

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT distinct ?y WHERE {
    {
     ?x a humans:Man
     ?x humans:hasSpouse ?y}
UNION {
     ?d humans:hasMother ?y}
 UNION {
     ?y a humans:Woman
    }
UNION {
     ?x a humans:Man
     ?y humans:hasSpouse ?x
}
}
```


## Exercice 2

1.

L'espace de nom associé à l'antologie décrite est :

```
   xmlns:rdf ="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
```
Property -> domain

- hasAncestor, hasPerent, hasChild, hasFather, hasMother, hasBrother, hasSister, name, age ont comme domaine Animal

- shoesize, shirtsize, trouserssize, hasSpouse ont comme domaine Person


La propriété "age" a comme domain Animal

2.

```
SELECT ?x WHERE
{
 ?x rdf:type ?t
 FILTER regex (?t, "Class")
}
```
3.

```
SELECT ?x ?t WHERE
{
 ?x ?y ?t
 FILTER regex (?y, "subClassOf")
}
```

4.

```

```
## Exercice 3
