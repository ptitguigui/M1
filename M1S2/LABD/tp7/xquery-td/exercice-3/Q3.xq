

declare variable $catalog := "plant_catalog.xml" ;
declare variable $families := "plant_families.xml" ;


declare function local:f() as empty-sequence() {
  ()
};

declare function local:insert-plantWithFamily($nodes as node()*)
{
 for $node in $nodes
 order by $node/COMMON
 return 
    typeswitch($node)
        case element(PLANT)
                return
                let $family := doc($families)//FAMILY/NAME[../SPECIES = $node/BOTANICAL/text()]/text()
                return 
                    element { name($node) } {
                        $node/@*, 
                        local:insert-plantWithFamily($node/node()),
                        element FAMILY { $family }
                    }
        case element(LIGHT)
            return
                local:f()
        case element()
            return 
                element { name($node) } {
                    $node/@*, 
                    local:insert-plantWithFamily($node/node())
                }
        default 
            return $node
};
        

<CATALOG>
    {for $light in  distinct-values(doc($catalog)//LIGHT)
    order by $light
    return
          <LIGHT>
            <EXPOSURE>{$light}</EXPOSURE>
            {local:insert-plantWithFamily(doc($catalog)//PLANT[./LIGHT/text() = $light])}
          </LIGHT>
    }
</CATALOG>
     

