<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <xsl:variable name='family' select="//param[@name='family']"/>
  <xsl:variable name="sort_key" select="//param[@name='sort_key']"/>
  <!-- attention, il ne sait pas gerer la transmission des arbres XML -->

  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($families_xml)/CLASSIFICATION"
            mode="generate-select">
              <xsl:with-param name='default' select="$family"/>
          </xsl:apply-templates>

          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="sort_key" select="$sort_key"/>
            <xsl:with-param name="family" select="$family"/>
            <xsl:with-param name="families_xml" select="$families_xml"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CLASSIFICATION" mode="generate-select">
  <xsl:param name="default"/>
  <form>
    <b>Filtrer par Famille</b> : <select name="family" onChange="javascript:submit()">
      <option value="">Aucune</option>
      <xsl:apply-templates select="FAMILY" mode="generate-select">
        <xsl:with-param name="default" select="$default"/>
      </xsl:apply-templates>
    </select>
  </form>
</xsl:template>

<xsl:template match="FAMILY" mode="generate-select">
  <xsl:param name="default"/>
  <option value="{NAME/text()}"><xsl:apply-templates select="NAME/text()"/></option>
</xsl:template>

<xsl:template match="CATALOG">
  <xsl:param name="sort_key"/>
  <xsl:param name="family"/>
  <xsl:param name="families_xml"/>
  <table border="1">
      <tr><xsl:apply-templates select="//PLANT[1]/*" mode="titre"/></tr>
      <xsl:apply-templates select="//PLANT">
       <xsl:sort select="./*[name(.) = $sort_key]"/>   
       <xsl:param name="family"/>
     </xsl:apply-templates>
    </table>
</xsl:template>

<xsl:template match="*" mode="titre">  
     <th><a href=" ?sort_key={name()}"><xsl:value-of select ="name()"/></a></th>
</xsl:template>
  
<xsl:template match="//PLANT" >
  <xsl:param name="family"/>
  <tr>
  <!-- <td><xsl:value-of select="$family"/></td> -->
  <!-- document($catalog_xml)//CATALOG/SPECIES[Name=$family] -->
    <!-- <xsl:if test = "./BOTANICAL/text() = //SPECIES[../NAME=$family]/text()"> -->

      <xsl:apply-templates select="*" mode="valeur"/>
    <!-- </xsl:if> -->
  </tr>
</xsl:template>
  
<xsl:template match="*" mode="valeur" >
  <td>
    <xsl:apply-templates select="text()"/>
   </td>
</xsl:template>

</xsl:stylesheet>
