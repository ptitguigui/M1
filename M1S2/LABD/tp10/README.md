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
SELECT ?traduction ?definition WHERE
{
 {
	?x rdfs:comment ?definition
	 FILTER regex (?x, "shoesize")
}
UNION {
	?x rdfs:label ?traduction
	 FILTER regex (?x, "shoesize")
	 FILTER (lang (?traduction) = 'fr')
}
}

```

5.

La requête nous donnant les synonymes de "Personne"
```
SELECT ?synonyme WHERE
{
	?x rdfs:label ?synonyme
	 FILTER regex (?x, "Person")
	 FILTER (lang (?synonyme) = 'fr')
}

```


et les résultats obtenus sont:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='synonyme'/>
</head>
<results>
<result>
<binding name='synonyme'><literal xml:lang='fr'>homme</literal></binding>
</result>
<result>
<binding name='synonyme'><literal xml:lang='fr'>humain</literal></binding>
</result>
<result>
<binding name='synonyme'><literal xml:lang='fr'>personne</literal></binding>
</result>
<result>
<binding name='synonyme'><literal xml:lang='fr'>être humain</literal></binding>
</result>
</results>
</sparql>

```

6.
La requête nous donnant les différent sens en français des mots "size" et "homme" est la suivante:

```
SELECT distinct ?homonymeSize ?homonymeHomme WHERE
{
{
	?x rdfs:comment ?homonymeSize
	 FILTER regex (?x, "size")
	 FILTER (lang (?homonymeSize) = 'fr')
}UNION
{
?x ?y ?t
?x rdfs:comment ?homonymeHomme
FILTER regex (?t, "homme")
FILTER (lang (?homonymeHomme) = 'fr')
FILTER  (?homonymeHomme != ?t)
}

}
```

et les réponses obtenues sont:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='homonymeSize'/>
<variable name='homonymeHomme'/>
</head>
<results>
<result>
<binding name='homonymeSize'><literal xml:lang='fr'>dimensions approximatives des chemises portées par une personne.</literal></binding>
</result>
<result>
<binding name='homonymeSize'><literal xml:lang='fr'>taille, exprimée en points, des chaussures d'une personne.</literal></binding>
</result>
<result>
<binding name='homonymeSize'><literal xml:lang='fr'>dimensions approximatives des pantalons portés par une personne.</literal></binding>
</result>
<result>
<binding name='homonymeHomme'><literal xml:lang='fr'>mâle adulte de l'espèce humaine.</literal></binding>
</result>
<result>
<binding name='homonymeHomme'><literal xml:lang='fr'>un membre de l'espèce humaine.</literal></binding>
</result>
</results>
</sparql>
```
## Exercice 3

1.1 La requête qui donne les ressources Person est:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT ?x WHERE {
   ?x a humans:Person
}
```

le résultat de la requête est:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#David</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Eve</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#John</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Karl</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Mark</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#William</uri></binding>
</result>
</results>
</sparql>
```

La requête qui donne les ressources Animal est:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT ?x WHERE {
   ?x a humans:Animal
}
```
et Aucune ressources n'est Animal


1.2
Avec les requêtes suivantes:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x WHERE {
    ?x a humans:Animal

```
et

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x WHERE {
    ?x a humans:Person

```

Nous obtenons le même résultat:
```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Alice</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Catherine</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#David</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Eve</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Flora</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Gaston</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Harry</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Jack</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Jennifer</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#John</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Karl</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Lucas</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Mark</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Pierre</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Sophie</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#William</uri></binding>
</result>
</results>
</sparql>
```


Les résultats sont les mêmes car une personne est  un animal et cela est marqué dans le fichier .rdfs. Ce qui explique le fait qu'on ait les mêmes réponses


2.1 Le requête nous retournant les Mâles et leurs épouses est:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x ?y WHERE {
    ?x a humans:Male
    ?x humans:hasSpouse ?y
}
```

Les résultats obtenues sont:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
<variable name='y'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Harry</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Sophie</uri></binding>
</result>
</results>
</sparql>

```

Nous obtenons Harry et Sophie comme résultat car c'est seulement Harry qui est défini comme Man (Man étant un Male)et c'est le seul Man qui a une épouse

2.2 Les résultats obtenues sont:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
<variable name='y'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Harry</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Sophie</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Karl</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Catherine</uri></binding>
</result>
</results>
</sparql>
```
nous obtenons ces résultats car la propriété hasFather a comme type de Male, ce qui fait de Karl un Male


3.
La requête donnant les lecturer et leurs types:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x ?y WHERE {
    ?x a humans:Lecturer
    ?x rdf:type ?y
}
```

les résultats sont:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
<variable name='y'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Eve</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Lecturer</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Eve</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Person</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Animal</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Female</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Lecturer</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Person</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs#Researcher</uri></binding>
</result>
</results>
</sparql>
```

Eve est une lecturer donc c'est une Person

Laura est une lecturer donc c'est une Person
Laura est un Researcher
Laura est une Mother donc elle est une Female donc une Animal


La requête renvoyant les instances communes entre Person et Male est:

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x WHERE {
    ?x a humans:Person
    ?x a humans:Male
}
```

Le résultat obtenu est:

```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
<variable name='y'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Gaston</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Harry</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Jack</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#John</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Karl</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Lucas</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Pierre</uri></binding>
</result>
</results>
</sparql>

```

Pierre, Lucas, Gaston, Harry et Jack sont des Man donc des subClassOf de Person et Male

Karl et John qui sont définit comme des  personnes sont des Father ce qui fait d'eux des Male


4.
La requête nous renvoyant la relation hasAncestor

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT distinct ?x ?y WHERE {
    ?x humans:hasAncestor ?y
}
```

les résultats obtenues sont:


```
<?xml version="1.0" ?>
<sparql xmlns='http://www.w3.org/2005/sparql-results#'>
<head>
<variable name='x'/>
<variable name='y'/>
</head>
<results>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Catherine</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Laura</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#John</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Sophie</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Lucas</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Catherine</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Lucas</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Karl</uri></binding>
</result>
<result>
<binding name='x'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#Mark</uri></binding>
<binding name='y'><uri>http://www.inria.fr/2007/09/11/humans.rdfs-instances#John</uri></binding>
</result>
</results>
</sparql>
```

Les résultats obtenus peuvent être expliqués par le fait que la relation
hasFather et hasMother héritent de hasParent qui lui même hérite de hasAncestor.
