<?php  header("Content-type: text/xml");?>
<?xml-stylesheet type="text/xsl" href="catalogue_par_famille.xsl"?>
<?php
  echo "<page>
          <params>
            <param name='family'>".$_REQUEST['family']."</param>
            <param name='sort_key'>".$_REQUEST['sort_key']."</param>
            <param name='catalog_xml'>plant_catalog.xml</param>
            <param name='families_xml'>plant_families.xml</param>
          </params>
        </page>"
  ?>
