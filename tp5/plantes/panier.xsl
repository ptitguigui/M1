<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:include href="html_wrapper.xsl"/>

<xsl:template match="/">
  <xsl:variable name='catalog_xml' select="//param[@name='catalog_xml']"/>
  <xsl:variable name='panier_xml' select="//param[@name='panier_xml']"/>
  <html>
    <xsl:call-template name="header"/>
    <body>
      <xsl:call-template name="menu"/>
      <div id="content">
          <xsl:apply-templates select="document($panier_xml)/BASKET"/>
      </div>
    </body>
  </html>

</xsl:template>

<xsl:template match="BASKET">
  <h1> Panier : </h1>
  <table border="1">
    <th>COMMON</th>
    <tr>
      <xsl:apply-templates select="./COMMON"/>
    </tr>
  </table>
</xsl:template>

<xsl:template match="COMMON">
  <td>
    <xsl:value-of select="text()"/>
  </td>
</xsl:template>


</xsl:stylesheet>
