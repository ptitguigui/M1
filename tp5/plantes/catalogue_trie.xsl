<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>

  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($catalog_xml)//CATALOG">
            <xsl:with-param name="cle_tri" select="//param[@name='cle_tri']"/>
          </xsl:apply-templates>
      </div>
    </body>
  </html>
</xsl:template>

<xsl:template match="CATALOG">
  <xsl:param name="cle_tri"/>
  <table border="1">
      <tr><xsl:apply-templates select="//PLANT[1]/*" mode="titre"/></tr>
      <xsl:apply-templates select="//PLANT"> <xsl:sort select="./*[name(.) = $cle_tri]"/> </xsl:apply-templates>
    </table>
</xsl:template>

<xsl:template match="*" mode="titre">  
     <th><a href=" ?cle_tri={name()}"><xsl:value-of select ="name()"/></a></th>
</xsl:template>
  
<xsl:template match="//PLANT">
  <tr>
       <xsl:apply-templates select="*" mode="valeur"/>
  </tr>
</xsl:template>
  
<xsl:template match="*" mode="valeur">
  <td>
    <xsl:apply-templates select="text()"/>
   </td>
</xsl:template>

</xsl:stylesheet>
