package gui.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsCardView {
	JPanel panel;
	final static String CARDNAME = "Opcje";

	public OptionsCardView() {
		panel = new JPanel();

		panel.add(new JButton("test2"));
		panel.add(new JButton("test3"));
	}

	public JPanel getCard() {
		return panel;
	}

}
