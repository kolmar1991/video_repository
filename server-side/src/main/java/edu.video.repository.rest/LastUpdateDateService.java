package edu.video.repository.rest;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.*;

@Path("/date")
public class LastUpdateDateService {

    @POST
    @Path("getupdatedate")
    public String getUpdateDate(@FormParam("username") String username) {
        String date = null;
        try {
            date = loadDate(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date;
    }

    @POST
    @Path("setupdatedate")
    public String setUpdateDate(@FormParam("date") String date, @FormParam("username") String username) {
        try {
            saveDate(date, username);
        } catch (FileNotFoundException e) {
            return "User probably doesn't exist";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ALL correct!";
    }

    private void saveDate(String date, String username) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(RestConstants.MAIN_DIR + username + "//date.txt");
        file.getParentFile().mkdirs();
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.println(date);
        writer.close();
    }

    private String loadDate(String username) throws IOException {
        File file = new File(RestConstants.MAIN_DIR + username + "//date.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return "";
        }
        String date = br.readLine();
        System.out.println(date);
        return date;
    }


}
