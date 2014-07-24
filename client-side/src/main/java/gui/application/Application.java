package gui.application;

import gui.controller.MainCardController;
import gui.controller.TransferRowController;
import gui.view.MainView;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;




public class Application {
	
	

	private static void createAndShowGui() {
		 try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		 
		final MainView view = new MainView();

		MainCardController mainCardController = new MainCardController(view.getMainPanel().getMainCardView());
		
		//TransferRowController transfeRowController0 = new TransferRowController(view.getMainPanel().getMainCardView().getTranferRowView(0), view.getFrame());

		
		
		view.run();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}
	
}
