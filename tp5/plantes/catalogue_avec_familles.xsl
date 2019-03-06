<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="sort_key" select="//param[@name='sort_key']"/>
            <xsl:with-param name='families_xml' select="//param[@name='families_xml']"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CATALOG">
  <!--xsl:variable name="sort_key" select="//param[@name='sort_key']"/-->
  <xsl:param name="sort_key"/>
  <xsl:param name="families_xml"/>
  <table border="1">
      <tr>
        <xsl:apply-templates select="//PLANT[1]/*" mode="titre"/>      
        <th><a href=" ?sort_key=Family">Family</a></th> 
      </tr>
      <xsl:apply-templates select="//PLANT">
        <xsl:sort select="./*[name(.) = $sort_key]"/>   
      </xsl:apply-templates>
    </table>
</xsl:template>
<xsl:template match="*" mode="titre">  
     <th><a href=" ?sort_key={name()}"><xsl:value-of select ="name()"/></a></th>
</xsl:template>
  
<xsl:template match="//PLANT" >
  <xsl:param name="family"/>
  <tr>
      <xsl:apply-templates select="*" mode="valeur"/>
      <!-- <xsl:apply-templates select="document($catalog_xml)//CATALOG/SPECIES" mode="valeur"/>       -->
  </tr>
</xsl:template>
  
<xsl:template match="*" mode="valeur" >
  <td>
    <xsl:apply-templates select="text()"/>
  </td>

</xsl:template>

</xsl:stylesheet>
