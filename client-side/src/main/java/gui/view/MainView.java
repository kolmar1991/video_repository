package gui.view;

import javax.swing.JFrame;

public class MainView {

	private JFrame frame;
	
	private MainPanelView mainPanelView;
	
	static final String WINDOWTITLE = "Repozytorium video";
	
	public MainView() {
		frame = new JFrame();
		frame.setTitle(WINDOWTITLE);
		
		mainPanelView = new MainPanelView();
		
		
		
		frame.getContentPane().add(mainPanelView.getMainPanel());
	}
	
	public void run() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public JFrame getFrame(){
		return frame;
	}
	
	public MainPanelView getMainPanel(){
		return mainPanelView;
	}
	
}
