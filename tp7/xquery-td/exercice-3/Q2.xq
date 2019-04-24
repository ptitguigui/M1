

declare variable $catalog := "plant_catalog.xml" ;
declare variable $families := "plant_families.xml" ;

declare function local:f() as empty-sequence() {
  ()
};

declare function local:insert-plant($nodes as node()*)
{
 for $node in $nodes
 return 
    typeswitch($node)
        case element(LIGHT)
            return
                local:f()
        case element()
            return 
                element { name($node) } {
                    $node/@*, 
                    local:insert-plant($node/node())
                }
        default 
            return $node
};
        

<CATALOG>
    {for $light in  distinct-values(doc($catalog)//LIGHT)
        return
          <LIGHT>
            <EXPOSURE>{$light}</EXPOSURE>
            { local:insert-plant(doc($catalog)//PLANT[./LIGHT/text() = $light]) }
          </LIGHT>
    }
</CATALOG>
     

