
package edu.video.repository.rest;

import com.hp.hpl.jena.query.DatasetAccessor;
import com.hp.hpl.jena.query.DatasetAccessorFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.mail.internet.MimeMultipart;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

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

    @POST
    @Path("/upfile")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile2(final MimeMultipart file) {
        if (file == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Must supply a valid file").build();

        try {
            for (int i = 0; i < file.getCount(); i++) {
                System.out.println("Body Part: " + file.getBodyPart(i));
                savefile("zapisz", file.getBodyPart(i).getInputStream());
            }
            return Response.ok("Done").build();
        } catch (final Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e)
                    .build();
        }
    }

    public static void savefile(String FileName, InputStream is) throws IOException {
        System.out.print(1);
        File f = new File("files/" + FileName);
        System.out.print(2);
        FileOutputStream fos = new FileOutputStream(f);
        System.out.print(3);
        byte[] buf = new byte[4096];

        int bytesRead;
        System.out.print(4);
        while ((bytesRead = is.read(buf)) != -1) {
            System.out.print("petla");
            fos.write(buf, 0, bytesRead);
        }
        fos.close();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("upload")                                         //probably works for file uploaded through html form
    private String uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String uploadedFileLocation = "c://uploadedFiles/" + fileDetail.getFileName();
        saveToFile(uploadedInputStream, uploadedFileLocation);


        return "uploaded";
    }

    private void saveToFile(InputStream uploadedInputStream,
                            String uploadedFileLocation) {

        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
