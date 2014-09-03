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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Karol on 19.08.14.
 */
public class DateService {

    private Date lastCommitDate;
    private DateFormat dateForamt;


    public DateService(){
        this.lastCommitDate = null;
        dateForamt = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    }

    public String sendDateToServer(Date date) {
        //String date = new Date().toString();

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/date/setupdatedate").build());
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("date", dateForamt.format(date));
        formData.add("username", "jasiek");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);

        String responseMsg =  response.getEntity(String.class);

        System.out.println("Response " + responseMsg);

        return responseMsg;
    }

    public Date getDateFromServer() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/videorepository/webservices/date/getupdatedate").build());
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("username", "jasiek");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);

        String responseMsg =  response.getEntity(String.class);

        System.out.println("Response " + responseMsg);



        if(responseMsg.equals("")){
            return null;
        }

        Date date = null;


        //DateFormat df =  new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        try {
            date = dateForamt.parse(responseMsg);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public Date getLastCommitDate(){
        if(lastCommitDate != null){
            return lastCommitDate;
        }

        Date dateFromServer = getDateFromServer();

        if(dateFromServer != null){
            return dateFromServer;
        }

        return null;
    }


    public void setLastCommitDate(Date date){
        this.lastCommitDate = date;
        this.sendDateToServer(date);
    }
}
