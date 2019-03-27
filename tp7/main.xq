import module namespace exp = "http://fil.univ-lille1.fr/expression"
at "expression.xq";


(:let $filename := 'expression2.xml'
    return exp:print($filename) :)

let $filename2 := 'expression2.xml'
    return exp:eval($filename2) 
 