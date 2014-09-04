package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import org.w3c.dom.Element;

public class ListHandler extends IdentifiableHandler {

    public ListHandler(Model model, String propertyName) {
        super(model, propertyName);
    }

    @Override
    public Resource handle(Element element, Resource parentResource) {
        Resource newResource = model.createResource("http://" + element.getTagName()
                + "/" + idSource++);
        parentResource.addProperty(property, newResource);

        return newResource;
    }

}
