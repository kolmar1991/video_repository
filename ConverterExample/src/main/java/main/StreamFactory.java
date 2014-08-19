package main;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class StreamFactory {
    
    private static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
    
    private static final XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();
    
    static {
        OUTPUT_FACTORY.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
    }
    
    public static XMLStreamReader getReader(String inputFilePath) throws FileNotFoundException, XMLStreamException {
        return INPUT_FACTORY.createXMLStreamReader(new FileReader(inputFilePath));
    }
    
    public static XMLStreamWriter getWriter(String outputFilePath) throws FileNotFoundException, XMLStreamException, IOException {
        return new IndentingXMLStreamWriter(OUTPUT_FACTORY.createXMLStreamWriter(new FileWriter(outputFilePath)));
    }
    
    public static XMLStreamWriter getWriter(Writer writer) throws FileNotFoundException, XMLStreamException, IOException {
        return new IndentingXMLStreamWriter(OUTPUT_FACTORY.createXMLStreamWriter(writer));
    }
}
