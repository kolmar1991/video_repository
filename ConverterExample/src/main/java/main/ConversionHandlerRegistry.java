package main;

import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import static main.ConversionHandler.NS_URI;

public final class ConversionHandlerRegistry {
    
    private static final Map<String, ConversionHandler> HANDLERS = new HashMap<>();
    
    private ConversionHandlerRegistry() {}
    
    public static ConversionHandler getHandler(String key) {
        return HANDLERS.get(key);
    }
    
    public static boolean handlerExists(String key) {
        return HANDLERS.containsKey(key);
    }
    
    public static void reloadContext(Context context) {
        for(ConversionHandler handler : HANDLERS.values()) {
            handler.updateContext(context);
        }
    }
    
    public static void buildHandlersForContext(Context context) {
        
        HANDLERS.put("dataset", new ConversionHandler(context) {
            @Override
            public void handle() throws XMLStreamException {
                writer.writeStartElement(NS_URI, "Description");
                writer.writeAttribute(NS_URI, "about", "dataset/" + XMLUtils.findAttributeValueByName(reader, "name"));
            }
        });
        HANDLERS.put("frame", new ConversionHandler(context) {
            @Override
            public void handle() throws XMLStreamException {
                writer.writeStartElement(NS_URI, "Description");
                writer.writeAttribute(NS_URI, "about", "frame/" + XMLUtils.findAttributeValueByName(reader, "number"));
            }
        });
        HANDLERS.put("objectlist", new ConversionHandler(context) {
            @Override
            public void handle() throws XMLStreamException {
                writer.writeStartElement(NS_URI, "Description");
                writer.writeAttribute(NS_URI, "about", "objectlist");
            }
        });
        HANDLERS.put("object", new ConversionHandler(context) {
            @Override
            public void handle() throws XMLStreamException {
                writer.writeStartElement(NS_URI, "Description");
                writer.writeAttribute(NS_URI, "about", "object" + "/" + XMLUtils.findAttributeValueByName(reader, "id"));
            }
        });
    }
}
