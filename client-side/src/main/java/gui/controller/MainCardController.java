package gui.controller;

import gui.view.MainCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import gui.view.OptionsCardView;
import model.DataSender;
import model.DateService;
import model.FileSearch;
import model.MovieXmlPair;

public class MainCardController implements ActionListener {

	private MainCardView mainCardView;
    private OptionsCardView optionsCardView;
    private DataSender dataSender;

	public MainCardController(MainCardView mainCardView, OptionsCardView optionsCardView) {

        this.dataSender = new DataSender("jasiek","Trololo");

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

            Date lastCommitDate = dateService.getLastCommitDate();

            if(lastCommitDate != null){
            System.out.println("XXXX DATE: " + lastCommitDate.toString());
            }

            Date now = new Date();


            List<MovieXmlPair> movieXmlPairs = fileSearch.findFilesToCommit(lastCommitDate, now);

            System.out.println("Sending started");

            for(int i = 0; i< movieXmlPairs.size(); i++){
                System.out.println("Sending " + i + " pair (" + movieXmlPairs.get(i).getXml().getName() + ")");
                this.dataSender.sendToRestService(movieXmlPairs.get(i).getXml(), movieXmlPairs.get(i).getMovie());
                System.out.println("Pair (" + movieXmlPairs.get(i).getXml().getName() +") is sended");
            }

            System.out.println("Sending finished");

            dateService.setLastCommitDate(now);

            System.out.println("new date uploaded to server");
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
