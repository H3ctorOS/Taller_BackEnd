package com.TallerWapo.dominio.utiles.XML;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class QueryXml {
    @JacksonXmlProperty(isAttribute = true)
    public String id;

    @JacksonXmlProperty
    public String value;
}
