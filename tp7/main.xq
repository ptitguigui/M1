import module namespace exp = "http://fil.univ-lille1.fr/expression"
at "expression.xq";

declare variable $variables  := "variables.xml" ;

(:~ Q1 ~:)
(:let $filename := 'expression2.xml'
    return exp:print($filename) :)

(:~ Q2 ~:)
(:~ let $filename2 := 'expression2.xml'
    return exp:eval($filename2)  ~:)

(:~ Q3 ~:)
(:let $filename1 := 'expression1.xml'
    return exp:eval-var($filename1, $variables) :)

(:~ Q4 ~:)
(:~ let $filename1 := 'expression1.xml'
    return exp:simplifie($filename1, $variables) ~:)
