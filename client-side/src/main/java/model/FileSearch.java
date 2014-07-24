package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

	public void findMovies() {
		File dir = new File(moviesDirectory);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(("." + moviesExtension))
					&& file.canRead()) {
				movies.add(file);
			}
		}

	}

	public void findXmls() {
		File dir = new File(xmlsDirectory);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".xml")) && file.canRead()) {
				xmls.add(file);
			}
		}

	}

	public void compareLists() {
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
