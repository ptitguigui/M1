<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:param name="plant"/>

<xsl:template match="/">
      <BASKET>
       <xsl:copy-of select="document('panier_local.xml', .)//BASKET/*"/>
        <COMMON>
          <xsl:value-of select="$plant"/>
        </COMMON>
      </BASKET>
</xsl:template>


</xsl:stylesheet>
