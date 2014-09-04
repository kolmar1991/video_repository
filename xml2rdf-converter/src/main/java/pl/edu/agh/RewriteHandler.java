package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import org.w3c.dom.Element;

public class RewriteHandler extends ConversionHandler {

    public RewriteHandler(Model model, String propertyName) {
        super(model, propertyName);
    }

    @Override
    public Resource handle(Element element, Resource parentResource) {
        parentResource.addProperty(property, element.getTextContent());

        return parentResource;
    }

}
