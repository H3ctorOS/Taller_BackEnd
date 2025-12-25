package com.TallerWapo.dominio.utiles.XML;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

public class SqlXmlLoader {
    public static String load(String xmlPath, String sqlId) {
        try (InputStream is = SqlXmlLoader.class
                .getClassLoader()
                .getResourceAsStream(xmlPath)) {

            if (is == null) {
                throw new IllegalArgumentException("No se encontró el archivo: " + xmlPath);
            }

            XmlMapper mapper = new XmlMapper();
            QueriesXml queries = mapper.readValue(is, QueriesXml.class);

            return queries.query.stream()
                    .filter(q -> sqlId.equals(q.id))
                    .findFirst()
                    .map(q -> q.value.trim())
                    .orElseThrow(() ->
                            new IllegalArgumentException("SQL id no encontrado: " + sqlId));

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo SQL desde XML", e);
        }
    }
}
