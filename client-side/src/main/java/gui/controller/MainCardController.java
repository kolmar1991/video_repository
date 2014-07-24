package gui.controller;

import gui.view.MainCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.FileSearch;

public class MainCardController implements ActionListener {

	private MainCardView mainCardView;

	public MainCardController(MainCardView mainCardView) {

		this.mainCardView = mainCardView;

		this.mainCardView.getMoviesDirectoryChooserButton().addActionListener(
				this);
		this.mainCardView.getXmlsDirectoryChooserButton().addActionListener(
				this);
		this.mainCardView.getStartButton().addActionListener(this);
		// transferRow.getDirectoryChooserButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		// choose movie directory
		if (event.getSource() == mainCardView.getMoviesDirectoryChooserButton()) {
			int returnVal = mainCardView.getFileChooser().showOpenDialog(
					mainCardView.getCard());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = mainCardView.getFileChooser().getSelectedFile();

				mainCardView.getMoviesDirectoryTextField().setText(
						file.getAbsolutePath());

			}
		}

		// choose xml directory
		if (event.getSource() == mainCardView.getXmlsDirectoryChooserButton()) {
			int returnVal = mainCardView.getFileChooser().showOpenDialog(
					mainCardView.getCard());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = mainCardView.getFileChooser().getSelectedFile();

				mainCardView.getXmlsDirectoryTextField().setText(
						file.getAbsolutePath());

			}
		}

		if (event.getSource() == mainCardView.getStartButton()) {
			FileSearch fileSearch = new FileSearch(mainCardView
					.getMoviesDirectoryTextField().getText(), mainCardView
					.getXmlsDirectoryTextField().getText(), "avi");
			
			fileSearch.findMovies();
			fileSearch.findXmls();
			fileSearch.TestConsolePrint();
			fileSearch.compareLists();
			fileSearch.TestConsolePrint();
		}
	}

}
