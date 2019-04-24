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
    SELECT distinct ?y WHERE {
    {
    ?x humans:hasParent ?y
    } UNION {
    ?y humans:hasChild ?t	
    }
    }
```

6.3

## Exercice 2


## Exercice 3
