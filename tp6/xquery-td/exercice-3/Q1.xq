

declare variable $catalog := "plant_catalog.xml" ;
declare variable $families := "plant_families.xml" ;

declare function local:insert-family($nodes as node()*)
{
 for $node in $nodes
 return 
    typeswitch($node)
        case element(PLANT)
            return
               let $family := doc($families)//FAMILY/NAME[../SPECIES = $nodes/BOTANICAL/text()]/text()
               return 
                element { name($node) } {
                    $node/@*, 
                    local:insert-family($node/node()),
                    element FAMILY { $family }
                }
        case element()
            return 
                element { name($node) } {
                    $node/@*, 
                    local:insert-family($node/node())
                }
        default 
            return $node
};
let $plant := doc($catalog)//PLANT 
        return local:insert-family($plant) 

