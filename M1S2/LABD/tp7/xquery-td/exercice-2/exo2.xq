declare function local:getSurface ($m) { 
   sum($m//*[not(ancestor::*[@surface-m2])]/@surface-m2)
};

declare variable $ms := "maisons.xml" ;

<html> 
   <header></header>
   <body>
      <table border="1">
         <tr>
         <th>Maisons</th><th>Surfaces (m2)</th></tr>
       
         {
         for $m in doc($ms)//maison 
         return 
            <tr><td>Maison { data($m/@id) }</td><td>  {local:getSurface($m)}   </td></tr>   
         } 
      </table>
   </body>
</html>
