module namespace exp='http://fil.univ-lille1.fr/expression';
declare default element namespace "http://www.expression.org";
declare namespace saxon="http://saxon.sf.net/";
declare option saxon:output "indent=yes";



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


declare function exp:useNodeEvalWithVar($el as node(), $variables as xs:string) as xs:double{
    typeswitch($el)
            case element(op)
                return
                    number(exp:evalOpWithVar($el, $variables))
            case element(cons)
                return
                    number(exp:printText($el))
            case element(var)
                return
                    number(exp:printVar($el, $variables))
            default 
                return
                    error()
};

declare function exp:evalOpWithVar($op as node(), $variables as xs:string){
    let $op1 := $op/*[1]
    let $op2 := $op/*[2]
        return 
                switch($op/@val)
                    case "+"
                         return
                            exp:useNodeEvalWithVar($op1, $variables) + exp:useNodeEvalWithVar($op2, $variables)
                    case "/" 
                        return
                            exp:useNodeEvalWithVar($op1, $variables) idiv exp:useNodeEvalWithVar($op2, $variables)
                    case "*" 
                        return
                            exp:useNodeEvalWithVar($op1, $variables) * exp:useNodeEvalWithVar($op2, $variables)
                    case "-" 
                        return
                            exp:useNodeEvalWithVar($op1, $variables) - exp:useNodeEvalWithVar($op2, $variables)
                     default
                        return
                            error()
};

declare function exp:useNode($el as node()){
    typeswitch($el)
            case element(op)
                return
                    exp:printOp($el)
            default 
                return
                    exp:printText($el)
};



declare function exp:printOp($op as node()){
    let $op1 := $op/*[1]
    let $op2 := $op/*[2]
        return concat(" ( ", exp:useNode($op1), " ", string($op/@val), " " , exp:useNode($op2)," ) ")
};

declare function exp:printText($el as node()){
    $el/text()
};

declare function exp:printVar($el as node(), $variables as xs:string) as xs:double{
    let $var := doc($variables)//$el/text()
    return 
        if (empty($var)) then 
            (:~  tout le temp vide.. ~:)
            error() 
        else 
            $var
        
};

declare function exp:print($name as xs:string){
    let $el := doc($name)/expr/*[1]
        return exp:useNode($el)
};


declare function exp:eval($name as xs:string) as xs:double{
    let $el := doc($name)/expr/*[1]
        return exp:useNodeEval($el)
};

declare function exp:eval-var($name as xs:string, $variables as xs:string) as xs:double{
    let $el := doc($name)/expr/*[1]
        return exp:useNodeEvalWithVar($el, $variables)
};

(:~ declare function exp:simplifie($name as xs:string , $variables as xs:string) as element(){
    (:~ To do  ~:)
};~:)



