
declare variable $order := "plant_order.xml" ;
declare variable $catalog := "plant_catalog.xml" ;

declare function local:getPrice ($plants) { 
  for $plant in $plants
    return $plant//QUANTITY * xs:double(substring(doc($catalog)//PLANT[COMMON = $plant/COMMON]/PRICE, 2))
};


let $plants := doc($order)//PLANT
    return
        <PRICE>{round-half-to-even(sum(local:getPrice($plants)),2)}</PRICE>


     

