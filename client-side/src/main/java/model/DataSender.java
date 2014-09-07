package model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.mail.MessagingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;

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

    public void sendFile(File file) throws IOException, MessagingException {
        String url = "http://localhost:8080/videorepository/webservices/fileupload";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        FileBody fileContent = new FileBody(file);
        StringBody comment = new StringBody("Filename: " + file.getName());
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("file", fileContent);
        reqEntity.addPart("username", new StringBody("jasiek"));
        httppost.setEntity(reqEntity);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();
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
