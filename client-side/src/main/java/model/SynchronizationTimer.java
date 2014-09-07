package model;

import gui.view.MainCardView;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Karol on 07.09.14.
 */
public class SynchronizationTimer extends TimerTask {

    private FileSearch fileSearch;
    private DateService dateService;
    private DataSender dataSender;
    MainCardView mainCardView;

    public SynchronizationTimer(String moviesDirecotry, String xmlsDirectory, String moviesExtension, String userName, String password, MainCardView mainCardView){
        this.fileSearch = new FileSearch(moviesDirecotry, xmlsDirectory, moviesExtension);
        this.dateService = new DateService();
        this.dataSender = new DataSender(userName,password);

        this.mainCardView = mainCardView;
        this.mainCardView.setCurrentAction(2);
    }

    @Override
    public void run() {

        this.mainCardView.setCurrentAction(0);
        Date lastCommitDate = dateService.getLastCommitDate();

        if(lastCommitDate != null){
            System.out.println("Last commit date: " + lastCommitDate.toString());
        }

        Date now = new Date();

        System.out.println("Searching for files to upload");
        List<MovieXmlPair> movieXmlPairs = fileSearch.findFilesToCommit(lastCommitDate, now);

        System.out.println("Sending started");

        this.mainCardView.setCurrentAction(1);
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

        System.out.println("new date uploaded to server");
        this.mainCardView.setCurrentAction(2);
    }
}
