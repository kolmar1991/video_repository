package jena.example.rdf;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.io.ByteArrayOutputStream;

public class ClientExamples {

    public static void main(String[] s) {
        new ClientExamples().sendRdf();

    }

    public void sendRdf() {
        Model model = getRdfExample();
        String rdf = getStringFromModel(model);
        sendToRestService(rdf, "jasiek", "aaa", "jeden.xml");

    }

    private void sendToRestService(String rdf, String user, String pass, String fileName) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/myresource/rdfupload").build());
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("rdf", rdf);
        formData.add("username", user);
        formData.add("password", pass);
        formData.add("xmlFileName", fileName);
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        System.out.println("Response " + response.getEntity(String.class));
    }

    private String getStringFromModel(Model model) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        model.write(outputStream);
        return outputStream.toString();
    }


    private Model getRdfExample() {
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N,
                        model.createResource()
                                .addProperty(VCARD.Given, givenName)
                                .addProperty(VCARD.Family, familyName));
        return model;
    }
}
