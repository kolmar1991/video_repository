package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.w3c.dom.Element;

public class ExtractAttributesHandler extends IdentifiableHandler {

    protected final String[] attributeNames;

    public ExtractAttributesHandler(Model model, String propertyName, String... attributeNames) {
        super(model, propertyName);
        this.attributeNames = attributeNames;
    }

    @Override
    public Resource handle(Element element, Resource parentResource) {
        Resource newResource = model.createResource("http://" + element.getTagName()
                + "/" + idSource++);

        for (String attributeName : attributeNames) {
            Property tmpProperty = model.createProperty(Converter.NAMESPACE, attributeName);
            newResource.addProperty(tmpProperty, element.getAttribute(attributeName));
        }

        parentResource.addProperty(property, newResource);
        return newResource;
    }

}
