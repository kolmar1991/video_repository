package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.w3c.dom.Element;

public class ExtractAttributesHandler2 extends ExtractAttributesHandler {

    public ExtractAttributesHandler2(Model model, String propertyName, String... attributeNames) {
        super(model, propertyName, attributeNames);
    }

    @Override
    public Resource handle(Element element, Resource parentResource) {
        Resource newResource = model.createResource("http://" + element.getTagName()
                + "/" + idSource++);

        for (String attributeName : attributeNames) {
            Property tmpProperty = model.createProperty(Converter.NAMESPACE, attributeName);
            newResource.addProperty(tmpProperty, element.getAttribute(attributeName));
        }

        if(!element.getTextContent().isEmpty()) {
            newResource.addProperty(property, element.getTextContent());
        }
        parentResource.addProperty(property, newResource);
        return newResource;
    }
    
}
