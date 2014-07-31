package main;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public abstract class ConversionHandler {

    protected XMLStreamReader reader;

    protected XMLStreamWriter writer;

    protected static final String NS_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    public ConversionHandler(Context context) {
        updateContext(context);
    }

    public final void updateContext(Context context) {
        this.reader = context.getReader();
        this.writer = context.getWriter();
    }
    
    public abstract void handle() throws XMLStreamException;

}
