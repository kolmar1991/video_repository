package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.w3c.dom.Element;

public abstract class ConversionHandler {

    protected final Model model;
    
    protected final Property property;
    
    public ConversionHandler(Model model, String propertyName) {
        this.model = model;
        this.property = model.createProperty(Converter.NAMESPACE, propertyName);
    }
    
    public abstract Resource handle(Element element, Resource parentResource);

}
