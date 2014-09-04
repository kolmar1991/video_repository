package pl.edu.agh;

import com.hp.hpl.jena.rdf.model.Model;
import java.util.HashMap;
import java.util.Map;

public final class ConversionHandlerRegistry {
    
    private final Map<String, ConversionHandler> handlers = new HashMap<>();
    
    private final Model model;
    
    public ConversionHandlerRegistry(Model model) {
        this.model = model;
        buildHandlers();
    }
    
    public ConversionHandler get(String key) {
        return handlers.get(key);
    }
    
    private void buildHandlers() {
        
        handlers.put("frame", new ExtractAttributesHandler(model, "frame", "number"));
 
        handlers.put("objectlist", new ListHandler(model, "objectlist"));
        
        handlers.put("object", new ExtractAttributesHandler(model, "object", "id"));
        
        handlers.put("orientation", new RewriteHandler(model, "orientation"));
        
        handlers.put("appearance", new RewriteHandler(model, "appearance"));
        
        handlers.put("box", new ExtractAttributesHandler(model, "box", "h", "w", "xc", "yc"));
        
        handlers.put("hypothesislist", new ListHandler(model, "hypothesislist"));
        
        handlers.put("hypothesis", new ExtractAttributesHandler(model, "hypothesis", "evaluation", "id", "prev"));
  
        handlers.put("movement", new ExtractAttributesHandler2(model, "movement", "evaluation"));
        
        handlers.put("role", new ExtractAttributesHandler2(model, "role", "evaluation"));
        
        handlers.put("context", new ExtractAttributesHandler2(model, "context", "evaluation"));
        
        handlers.put("situation", new ExtractAttributesHandler2(model, "situation", "evaluation"));
        
        handlers.put("event", new ExtractAttributesHandler2(model, "event", "evaluation"));
        
        handlers.put("entities", new RewriteHandler(model, "entities"));
        
        handlers.put("scenario", new ExtractAttributesHandler2(model, "scenario", "evaluation"));
        
        handlers.put("grouplist", new ListHandler(model, "grouplist"));
        
        handlers.put("group", new ExtractAttributesHandler(model, "group", "id"));
        
        handlers.put("members", new RewriteHandler(model, "members"));
    }
}
