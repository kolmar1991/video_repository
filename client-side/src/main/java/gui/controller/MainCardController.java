package gui.controller;

import gui.view.MainCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;

import gui.view.OptionsCardView;
import model.DateService;
import model.FileSearch;

public class MainCardController implements ActionListener {

	private MainCardView mainCardView;
    private OptionsCardView optionsCardView;

	public MainCardController(MainCardView mainCardView, OptionsCardView optionsCardView) {

		this.mainCardView = mainCardView;
        this.optionsCardView = optionsCardView;

		this.mainCardView.getMoviesDirectoryChooserButton().addActionListener(
				this);
		this.mainCardView.getXmlsDirectoryChooserButton().addActionListener(
				this);
		this.mainCardView.getStartButton().addActionListener(this);

        this.mainCardView.getSameDirectoryCheckBox().addActionListener(this);
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

            if(mainCardView.getSameDirectoryCheckBox().isSelected()){
                mainCardView.getXmlsDirectoryTextField().setText(mainCardView.getMoviesDirectoryTextField().getText());
            }

            FileSearch fileSearch = new FileSearch(mainCardView
					.getMoviesDirectoryTextField().getText(), mainCardView
					.getXmlsDirectoryTextField().getText(), optionsCardView.getMoviesExtension());

            DateService dateService= new DateService();

            Date date = dateService.getLastCommitDate();

            if(date != null){
            System.out.println("XXXX DATE: " + date.toString());
            }

			fileSearch.findMovies();
			fileSearch.findXmls();
			fileSearch.TestConsolePrint();
			fileSearch.compareLists();
			fileSearch.TestConsolePrint();
		}

        if(event.getSource() == mainCardView.getSameDirectoryCheckBox()){
            if(mainCardView.getSameDirectoryCheckBox().isSelected()){
                mainCardView.getXmlsDirectoryChooserButton().setEnabled(false);
                mainCardView.getXmlsDirectoryTextField().setEnabled(false);
            }
            else{
                mainCardView.getXmlsDirectoryChooserButton().setEnabled(true);
                mainCardView.getXmlsDirectoryTextField().setEnabled(true);
            }
        }
	}

}
