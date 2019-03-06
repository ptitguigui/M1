<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>

  <xsl:template match="/">
    <table border="1">
      <tr><xsl:apply-templates select="//PLANT[1]" mode="titre"/></tr>
      <xsl:apply-templates select="//PLANT" mode="valeur"/>
    </table>
  </xsl:template>

  <xsl:template match="//PLANT[1]/*" mode="titre">
     <th><xsl:value-of select ="name()"/></th>
  </xsl:template>
  
  <xsl:template match="//PLANT" mode="valeur">
   <tr>
       <xsl:apply-templates select="*"/>
   </tr>
  </xsl:template>
  
  <xsl:template match="*" >
   <td>
       <xsl:apply-templates select="text()"/>
   </td>
  </xsl:template>

</xsl:stylesheet>
