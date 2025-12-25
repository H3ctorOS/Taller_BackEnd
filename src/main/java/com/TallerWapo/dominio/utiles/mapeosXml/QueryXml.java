package com.TallerWapo.dominio.utiles.mapeosXml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class QueryXml {
    @JacksonXmlProperty(isAttribute = true)
    public String id;

    @JacksonXmlText
    public String value;
}
