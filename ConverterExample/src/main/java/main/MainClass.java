package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class MainClass {
    
    public static String convert() throws FileNotFoundException, XMLStreamException, IOException {
        final String nsURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        XMLStreamReader reader = StreamFactory.getReader("wk1gt.xml");
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter writer = StreamFactory.getWriter(stringWriter);
        writer.setPrefix("rdf", nsURI);
        writer.writeStartElement("rdf", "RDF", nsURI);
        ConversionHandlerRegistry.buildHandlersForContext(new Context(reader, writer));
        while (reader.hasNext()) {
            int elementType = reader.next();
            switch (elementType) {
                case XMLStreamConstants.START_ELEMENT:
                    ConversionHandler handler = ConversionHandlerRegistry.getHandler(reader.getLocalName());
                    System.out.println("START: " + reader.getLocalName());
                    if (handler != null) {
                        handler.handle();
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("END: " + reader.getLocalName());
                    if (ConversionHandlerRegistry.handlerExists(reader.getLocalName())) {
                        writer.writeEndElement();
                    }
                    break;
            }
        }
        writer.writeEndElement();
        writer.flush();
        writer.close();
        
        return stringWriter.toString();
    }
}
