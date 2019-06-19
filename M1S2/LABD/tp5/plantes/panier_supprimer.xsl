<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:param name="no_item"/>

<xsl:template match="/">
  <BASKET>
    <xsl:copy-of select="document('panier_local.xml', .)//BASKET/COMMON[position() != $no_item]"/>
  </BASKET>
</xsl:template>


</xsl:stylesheet>
