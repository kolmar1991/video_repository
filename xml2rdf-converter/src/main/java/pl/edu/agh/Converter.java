package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Converter implements Runnable {
    
    public static final String NAMESPACE = "http://www.example.org/2014/f-rdf/1.0#"; 
    
    private final ConversionHandlerRegistry registry;
    
    private final Model model;
    
    private final String filePath;
    
    private final Writer writer;
    
    public Converter(String filePath) {
        this(filePath, new StringWriter());
    }
    
    // for testing purposes
    public Converter(String filePath, Writer writer) {
        this.filePath = filePath;
        this.model = ModelFactory.createDefaultModel();
        model.setNsPrefix("f", NAMESPACE);
        this.registry = new ConversionHandlerRegistry(model);
        this.writer = writer;
    }
    
    // for testing purposes
    @Override
    public void run() {
        try {
            System.out.println("processing file: " + filePath);
            convertToWriter();
            System.out.println("processing file: " + filePath + " ended");
        } catch (Throwable ex) {
            System.err.println("ERROR WHILE PROCESSING: " + filePath);
            throw new RuntimeException(ex);
        }
    }
    
    public String convert() throws Exception {
        Document doc = buildDocument();

        Element root = doc.getDocumentElement();
        processRoot(root);

        model.write(writer);
        return writer.toString();
    }
    
    // for testing purposes
    public void convertToWriter() throws Exception {
        Document doc = buildDocument();

        Element root = doc.getDocumentElement();
        processRoot(root);

        model.write(writer);
    }
       
    private void processRoot(Element root) {
        Resource rootResource = model.createResource("http://" + root.getNodeName());
        NodeList nList = root.getChildNodes();
        for (int i = 0; i < nList.getLength(); ++i) {
            Node node = nList.item(i);
            processNode(node, rootResource);
        }
    }
    
    private void processNode(Node node, Resource parentResource) {
        // do sth
//        System.out.println("processing node: " + node.getNodeName());
        Resource newParentResource = null;
        if (isNodeValid(node)) {
            ConversionHandler handler = registry.get(node.getNodeName());
            if(handler != null) {
                newParentResource = handler.handle((Element)node, parentResource);
            } 
//            else {
//                System.out.println("handler for \"" + node.getNodeName() + "\" not found");
//            }
        } 
//        else {
//            System.out.println("node \"" + node.getNodeName() + "\" is not valid for processing");
//        }
        // --
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); ++i) {
            Node n = nList.item(i);
            if(newParentResource != null) {
                processNode(n, newParentResource);
            } else {
                processNode(n, parentResource);
            }
        }
    }
 
    private static Boolean isNodeValid(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE
                && (node.getChildNodes().getLength() != 0
                    || node.getAttributes().getLength() != 0);
    }
    
    private Document buildDocument() throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filePath);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);
	doc.getDocumentElement().normalize();
        
        return doc;
    }
    
}
