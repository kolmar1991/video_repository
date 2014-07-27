package main;

import javax.xml.stream.XMLStreamReader;

public class XMLUtils {
    
    public static String findAttributeValueByName(XMLStreamReader reader, String attributeName) {
        for(int i = 0; i < reader.getAttributeCount(); ++i) {
            if(reader.getAttributeLocalName(i).equals(attributeName)) {
                return reader.getAttributeValue(i);
            }
        }
        
        return null;
    }
}
