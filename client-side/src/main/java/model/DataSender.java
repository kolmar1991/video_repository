package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.io.File;

/**
 * Created by Karol on 20.08.14.
 */
public class DataSender {

    String user;
    String pass;

    public DataSender(String user, String pass){
        this.user = user;
        this.pass = pass;
    }


    public void sendToRestService(File rdf, File clip) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/myresource/rdfupload").build());
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("rdf", rdf.getAbsolutePath());
        formData.add("username", this.user);
        formData.add("password", this.pass);
        formData.add("xmlFileName", "asd");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        System.out.println("Response " + response.getEntity(String.class));
    }


//    private void sendToRestService(String user, String pass, File rdf, File fileName) {
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/myresource/rdfupload").build());
//        MultivaluedMap formData = new MultivaluedMapImpl();
//        formData.add("rdf", rdf);
//        formData.add("username", user);
//        formData.add("password", pass);
//        formData.add("xmlFileName", fileName);
//        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
//        System.out.println("Response " + response.getEntity(String.class));
//    }
//
//    private void sendToRestService(String rdf, String user, String pass, String fileName) {
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/myresource/rdfupload").build());
//        MultivaluedMap formData = new MultivaluedMapImpl();
//        formData.add("rdf", rdf);
//        formData.add("username", user);
//        formData.add("password", pass);
//        formData.add("xmlFileName", fileName);
//        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
//        System.out.println("Response " + response.getEntity(String.class));
//    }
}
