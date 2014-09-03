package model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileSearch {

	String moviesDirectory;
	String xmlsDirectory;
	String moviesExtension;

	List<File> movies;
	List<File> xmls;


	public FileSearch(String moviesDirectory, String xmlsDirectory,
			String moviesExtension) {
		this.moviesDirectory = moviesDirectory;
		this.xmlsDirectory = xmlsDirectory;
		this.moviesExtension = moviesExtension;

		movies = new ArrayList<File>();
		xmls = new ArrayList<File>();
	}


    public List<MovieXmlPair> findFilesToCommit(Date lastUpload, Date now){
        this.findMovies(lastUpload, now);
        this.findXmls(lastUpload, now);

        this.compareLists();

        this.sortLists();

        this.TestConsolePrint();

        return this.createParis();
    }

	private void findMovies(Date lastUpload, Date now) {
        movies = new ArrayList<File>();

		File dir = new File(moviesDirectory);
		for (File file : dir.listFiles()) {

          //  Date fileModifiedDate = new Date(file.lastModified() * 1000);
            Date fileModifiedDate = new Date(file.lastModified());

            if(lastUpload != null){
                if(lastUpload.after(fileModifiedDate)){
                    continue;
                }
            }

			if (file.getName().endsWith(("." + moviesExtension))
					&& file.canRead()) {

                DateFormat dateForamt = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                String fileDate = dateForamt.format(fileModifiedDate);
                String nowDate = dateForamt.format(now);

                System.out.println("File: " + fileDate);
                System.out.println("Now: " + nowDate);

                if(fileModifiedDate.before(now))
             {
				movies.add(file);
			}
            }
		}

	}

	private void findXmls(Date lastUpload, Date now) {
        xmls = new ArrayList<File>();

		File dir = new File(xmlsDirectory);
		for (File file : dir.listFiles()) {

           // Date fileModifiedDate = new Date(file.lastModified() * 1000);
            Date fileModifiedDate = new Date(file.lastModified());


            if(lastUpload != null){
                if(lastUpload.after(fileModifiedDate)){
                    continue;
                }
            }

			if (file.getName().endsWith((".xml")) && file.canRead() && fileModifiedDate.before(now)) {
				xmls.add(file);
			}
		}

	}

	private void compareLists() {
		List<File> moviesTmp = new ArrayList<File>();
		List<File> xmlsTmp = new ArrayList<File>();		
		
		for (int i = 0; i < movies.size(); i++) {
			String movieTitle = movies.get(i).getName();
			movieTitle = movieTitle.substring(0, movieTitle.lastIndexOf('.'));
			boolean matchFound = false;

			for (int j = 0; j < xmls.size(); j++) {
				String xmlTitle = xmls.get(j).getName();
				xmlTitle = xmlTitle.substring(0, xmlTitle.lastIndexOf('.'));
				if (movieTitle.equals(xmlTitle)) {
					moviesTmp.add(movies.get(i));
					matchFound = true;
					break;
				}
			}

			if (matchFound) {
				break;
			}
		}
		
		for (int i = 0; i < xmls.size(); i++) {
			String xmlTitle = xmls.get(i).getName();
			xmlTitle = xmlTitle.substring(0, xmlTitle.lastIndexOf('.'));
			boolean matchFound = false;

			for (int j = 0; j < movies.size(); j++) {
				String movieTitle = movies.get(j).getName();
				movieTitle = movieTitle.substring(0, movieTitle.lastIndexOf('.'));
				if (xmlTitle.equals(movieTitle)) {
					xmlsTmp.add(xmls.get(i));
					matchFound = true;
					break;
				}
			}

			if (matchFound) {
				break;
			}
		}

		movies = moviesTmp;
		xmls = xmlsTmp;

	}

    private void sortLists(){

        Collections.sort(movies, new Comparator<File>(){
            public int compare(File f1, File f2){
                String name1 = f1.getName();
                String name2 = f2.getName();
                return name1.compareTo(name2);
            }
        });

        Collections.sort(xmls, new Comparator<File>(){
            public int compare(File f1, File f2){
                String name1 = f1.getName();
                String name2 = f2.getName();
                return name1.compareTo(name2);
            }
        });

    }

    private List<MovieXmlPair> createParis(){
        List<MovieXmlPair> pairs = new ArrayList<MovieXmlPair>();

        int size = movies.size();

        if (size != xmls.size()){
            return null;
        }

        for(int i = 0; i < size; i++){
            pairs.add(new MovieXmlPair(movies.get(i), xmls.get(i)));
        }

        return pairs;
    }

	public void TestConsolePrint() {
		System.out.println("Number of movies = " + movies.size());

		for (int i = 0; i < movies.size(); i++) {
			System.out.println("Title " + i + ": " + movies.get(i).getName());
		}
		System.out.println("Number of xmls = " + xmls.size());

		for (int i = 0; i < xmls.size(); i++) {
			System.out.println("Title " + i + ": " + xmls.get(i).getName());
		}

		System.out.println();
		System.out.println();

	}
}
