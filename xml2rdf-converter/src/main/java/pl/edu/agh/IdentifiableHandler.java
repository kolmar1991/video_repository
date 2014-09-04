package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;

public abstract class IdentifiableHandler extends ConversionHandler {
    
    protected long idSource = 0L;

    public IdentifiableHandler(Model model, String propertyName) {
        super(model, propertyName);
    }
}
