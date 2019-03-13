<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:param name="plant"/>
<xsl:variable name='panier_local_xml' select="//param[@name='panier_local_xml']"/>

<xsl:template match="/">
      <BASKET>
       <xsl:copy-of select="document($panier_local_xml)//BASKET/*"/>
        <COMMON>
          <xsl:value-of select="$plant"/>
        </COMMON>
      </BASKET>
</xsl:template>


</xsl:stylesheet>
