package gui.view;


import javax.swing.JTabbedPane;

public class MainPanelView {

	private JTabbedPane mainPanel;

	private MainCardView mainCardView;
	private OptionsCardView optionsCardView;
	private InfoCardView infoCardView;
	
	public MainPanelView() {
		
		mainPanel = new JTabbedPane();
		
		// creating cards
		mainCardView = new MainCardView();		
		optionsCardView = new OptionsCardView();
		infoCardView = new InfoCardView();
		
		
		// adding cards to mainPanel
		mainPanel.add(MainCardView.CARDNAME, mainCardView.getCard());
		mainPanel.add(OptionsCardView.CARDNAME, optionsCardView.getCard());
		mainPanel.add(InfoCardView.CARDNAME, infoCardView.getCard());
		
		
	}

	public JTabbedPane getMainPanel() {
		return mainPanel;
	}
	
	public MainCardView getMainCardView(){
		return mainCardView;
	}

}
