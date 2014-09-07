package gui.view;

import javax.swing.*;

public class OptionsCardView {
	JPanel panel;
	final static String CARDNAME = "Opcje";

    JComboBox moviesExtensions;

	public OptionsCardView() {
		panel = new JPanel();

        String[] moviesExtensionsStrings = { "avi", "mpeg", "mov"};

        moviesExtensions = new JComboBox(moviesExtensionsStrings);

		panel.add(moviesExtensions);
	}

	public JPanel getCard() {
		return panel;
	}

    public JComboBox getMoviesExtension(){
        return  moviesExtensions;
    }

}
