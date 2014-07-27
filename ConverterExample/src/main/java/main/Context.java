package main;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class Context {

    private final XMLStreamReader reader;

    private final XMLStreamWriter writer;

    public Context(XMLStreamReader reader, XMLStreamWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public XMLStreamWriter getWriter() {
        return writer;
    }
}
