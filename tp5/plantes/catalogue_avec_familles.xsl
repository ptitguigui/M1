<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='families_xml' select="//param[@name='families_xml']"/>
  <xsl:variable name='family' select="//param[@name='family']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="sort_key" select="//param[@name='sort_key']"/>
            <xsl:with-param name='families_xml' select="//param[@name='families_xml']"/>
            <xsl:with-param name="family" select="$family"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="CATALOG">
  <!--xsl:variable name="sort_key" select="//param[@name='sort_key']"/-->
  <xsl:param name="sort_key"/>
  <xsl:param name="families_xml"/>
  <xsl:param name="family"/>
  <table border="1">
      <xsl:variable name="species" select="document($families_xml)//SPECIES[../NAME=$family]/text()"/>
      <xsl:variable name="plantes" select="//PLANT[BOTANICAL/text()=$species]"/>
      <tr>
        <xsl:apply-templates select="//PLANT[1]/*" mode="titre"/>
        <th><a href=" ?sort_key=FAMILY">FAMILY</a></th>
      </tr>
      <xsl:choose>
        <xsl:when test="count($plantes) = 0">
          <xsl:for-each select="//PLANT">
              <xsl:sort select="./*[name(.) = $sort_key]"/>
              <tr>
                  <xsl:apply-templates select="*" mode="valeur"/>
                  <xsl:variable name="name" select = "BOTANICAL/text()"/>
                  <xsl:variable name="famille" select="document($families_xml)//SPECIES[text() = $name]/../NAME/text()"/>
                  <td>
                    <a href=" ?family={$famille}"><xsl:value-of select ="$famille"/></a>
                  </td>
                  <!-- <xsl:apply-templates select="document($catalog_xml)//CATALOG/SPECIES" mode="valeur"/>       -->
              </tr>
          </xsl:for-each>
        </xsl:when>
        <xsl:otherwise>
          <xsl:for-each select="$plantes">
              <xsl:sort select="./*[name(.) = $sort_key]"/>
              <tr>
                  <xsl:apply-templates select="*" mode="valeur"/>
                  <xsl:variable name="name" select = "BOTANICAL/text()"/>
                  <xsl:variable name="famille" select="document($families_xml)//SPECIES[text() = $name]/../NAME/text()"/>
                  <td>
                    <a href=" ?family={$famille}"><xsl:value-of select ="$famille"/></a>
                  </td>
                  <!-- <xsl:apply-templates select="document($catalog_xml)//CATALOG/SPECIES" mode="valeur"/>       -->
              </tr>
          </xsl:for-each>
        </xsl:otherwise>
      </xsl:choose>

    </table>
</xsl:template>

<xsl:template match="*" mode="titre">
     <th><a href=" ?sort_key={name()}"><xsl:value-of select ="name()"/></a></th>
</xsl:template>


<xsl:template match="*" mode="valeur" >
  <td>
    <xsl:apply-templates select="text()"/>
  </td>

</xsl:template>

</xsl:stylesheet>
