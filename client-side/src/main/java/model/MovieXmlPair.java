package model;

import java.io.File;

/**
 * Created by Karol on 20.08.14.
 */
public class MovieXmlPair {

    private File movie;
    private File xml;

    public MovieXmlPair(File movie, File xml){
        this.movie = movie;
        this.xml = xml;
    }

    public File getMovie(){
        return movie;
    }

    public File getXml(){
        return xml;
    }
}
