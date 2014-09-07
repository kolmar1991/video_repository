package gui.controller;

import gui.view.MainCardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;

import javax.swing.JFileChooser;

import gui.view.OptionsCardView;
import model.*;

public class MainCardController implements ActionListener {

	private MainCardView mainCardView;
    private OptionsCardView optionsCardView;
    private DataSender dataSender;
    private boolean isSynchronizationRunning;
    private Timer timer;

	public MainCardController(MainCardView mainCardView, OptionsCardView optionsCardView) {

       // this.dataSender = new DataSender("jasiek","Trololo");


        isSynchronizationRunning = false;

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

    private void setFieldsEnabled(boolean areEnabled){

        mainCardView.getMoviesDirectoryChooserButton().setEnabled(areEnabled);
        mainCardView.getMoviesDirectoryTextField().setEnabled(areEnabled);
        mainCardView.getXmlsDirectoryChooserButton().setEnabled(areEnabled);
        mainCardView.getXmlsDirectoryTextField().setEnabled(areEnabled);
        mainCardView.getSameDirectoryCheckBox().setEnabled(areEnabled);
        optionsCardView.getMoviesExtension().setEnabled(areEnabled);

        if(areEnabled){
            mainCardView.getSameDirectoryCheckBox().setSelected(false);
        }


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

                // if same directory option choosed
               if(mainCardView.getSameDirectoryCheckBox().isSelected()){
                   mainCardView.getXmlsDirectoryTextField().setText(mainCardView.getMoviesDirectoryTextField().getText());
               }


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

            /*if(mainCardView.getSameDirectoryCheckBox().isSelected()){
                mainCardView.getXmlsDirectoryTextField().setText(mainCardView.getMoviesDirectoryTextField().getText());
            }*/

            if (!isSynchronizationRunning){
                isSynchronizationRunning = true;

                setFieldsEnabled(false);

                String moviesDirectory = mainCardView
                        .getMoviesDirectoryTextField().getText();
                String xmlsDirectory = mainCardView.getXmlsDirectoryTextField().getText();
                String moviesExtension = (String) optionsCardView.getMoviesExtension().getSelectedItem();
                int timeInterval = 1; // in minutes

                timer = new Timer();
                SynchronizationTimer synchronizationTimer = new SynchronizationTimer(moviesDirectory, xmlsDirectory, moviesExtension, "jasiek","Trololo", mainCardView);
                timer.scheduleAtFixedRate(synchronizationTimer, 500, 60 * 1000 * timeInterval);
            }
            else{
                isSynchronizationRunning = false;
                timer.cancel();

                setFieldsEnabled(true);
            }

            /*FileSearch fileSearch = new FileSearch(mainCardView
					.getMoviesDirectoryTextField().getText(), mainCardView
					.getXmlsDirectoryTextField().getText(), optionsCardView.getMoviesExtension());

            DateService dateService= new DateService();

            Date lastCommitDate = dateService.getLastCommitDate();

            if(lastCommitDate != null){
            System.out.println("Last commit date: " + lastCommitDate.toString());
            }

            Date now = new Date();


            List<MovieXmlPair> movieXmlPairs = fileSearch.findFilesToCommit(lastCommitDate, now);

            System.out.println("Sending started");

            for(int i = 0; i< movieXmlPairs.size(); i++){
                System.out.println("Sending " + i + " pair (" + movieXmlPairs.get(i).getXml().getName() + ")");
                //this.dataSender.sendToRestService(movieXmlPairs.get(i).getXml(), movieXmlPairs.get(i).getMovie());

                try{
                this.dataSender.sendFile(movieXmlPairs.get(i).getXml());
                this.dataSender.sendFile(movieXmlPairs.get(i).getMovie());
                }
                catch (Exception e){
                    System.out.println("error ocurred during file sending:\n" + e.toString());
                }
                System.out.println("Pair (" + movieXmlPairs.get(i).getXml().getName() + ") is sended");
            }

            System.out.println("Sending finished");

            dateService.setLastCommitDate(now);

            System.out.println("new date uploaded to server");*/
		}

        // if same direcotry option was changed
        if(event.getSource() == mainCardView.getSameDirectoryCheckBox()){
            if(mainCardView.getSameDirectoryCheckBox().isSelected()){
                mainCardView.getXmlsDirectoryChooserButton().setEnabled(false);
                mainCardView.getXmlsDirectoryTextField().setEnabled(false);
                mainCardView.getXmlsDirectoryTextField().setText(mainCardView.getMoviesDirectoryTextField().getText());
            }
            else{
                mainCardView.getXmlsDirectoryChooserButton().setEnabled(true);
                mainCardView.getXmlsDirectoryTextField().setEnabled(true);
            }
        }
	}

}
