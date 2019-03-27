module namespace exp='http://fil.univ-lille1.fr/expression';
declare default element namespace "http://www.expression.org";


declare function exp:useNode($el as node()){
    typeswitch($el)
            case element(op)
                return
                    exp:printOp($el)
            default 
                return
                    exp:printText($el)
};


declare function exp:evalOp($op as node()){
    let $op1 := $op/*[1]
    let $op2 := $op/*[2]
        return 
                switch($op/@val)
                    case "+"
                         return
                            exp:useNodeEval($op1) + exp:useNodeEval($op2)
                    case "/" 
                        return
                            exp:useNodeEval($op1) idiv exp:useNodeEval($op2)
                    case "*" 
                        return
                            exp:useNodeEval($op1) * exp:useNodeEval($op2)
                    case "-" 
                        return
                            exp:useNodeEval($op1) - exp:useNodeEval($op2)
                     default
                        return
                            error()
};


declare function exp:useNodeEval($el as node()){
    typeswitch($el)
            case element(op)
                return
                    exp:evalOp($el)
            case element(cons)
                return
                    exp:printText($el)
            default 
                return
                    error()
};

declare function exp:printOp($op as node()){
    let $op1 := $op/*[1]
    let $op2 := $op/*[2]
        return concat(" ( ", exp:useNode($op1), " ", string($op/@val), " " , exp:useNode($op2)," ) ")
};

declare function exp:printText($var as node()){
    $var/text()
};

declare function exp:print($name as xs:string){
    let $el := doc($name)/expr/*[1]
        return exp:useNode($el)
};


declare function exp:eval($name as xs:string) as xs:double{
    let $el := doc($name)/expr/*[1]
        return exp:useNodeEval($el)
};




