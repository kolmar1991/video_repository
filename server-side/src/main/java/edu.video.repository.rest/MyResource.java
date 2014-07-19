
package edu.video.repository.rest;

import com.hp.hpl.jena.query.DatasetAccessor;
import com.hp.hpl.jena.query.DatasetAccessorFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import javax.ws.rs.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }

    @POST
    @Path("rdfupload")
    public String uploadRDF(@FormParam("rdf") String rdf, @FormParam("username") String username,
                            @FormParam("password") String password, @FormParam("xmlFileName") String xmlFileName) {
        System.out.println("serwis wysylana rdf!");
        System.out.println(rdf + "   " + username);
        InputStream decodedInput = new ByteArrayInputStream(rdf.getBytes());
        Model model = ModelFactory.createDefaultModel();
        model.read(decodedInput, null);
        DatasetAccessor datasetAccessor = DatasetAccessorFactory.createHTTP("http://localhost:3030/videoRepo/data");
        String path = username + "_" + xmlFileName;
        datasetAccessor.putModel(path, model);
        return "ALL correct!";
    }
}
