package com.TallerWapo.dominio.utiles;

import com.TallerWapo.dominio.utiles.mapeosXml.QueriesXml;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

public class XmlUtil {
    public static String loadSql(String xmlPath, String sqlId) {
        try (InputStream is = XmlUtil.class
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
