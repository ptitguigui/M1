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
    <th>Qte</th>
    <xsl:apply-templates select="./COMMON[not(text() = following::COMMON/text())]">
        <xsl:sort select="." order="ascending" data-type="text"/>
    </xsl:apply-templates>
  </table>
</xsl:template>

<xsl:template match="./COMMON[not(text() = following::COMMON/text())]">
  <xsl:variable name="text" select=""/>
  <xsl:variable name="qte" select="count(document('panier_local.xml',.)//COMMON[text() = $text])"/>
  <tr>
    <td>
      <xsl:value-of select="text()"/>
    </td>
    <td>
      <xsl:value-of select= "$qte"/>
    </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
