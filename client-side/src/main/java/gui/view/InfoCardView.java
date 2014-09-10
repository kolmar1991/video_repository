package gui.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoCardView {

    private JPanel panel;

    final static String CARDNAME = "Info";

    final static String INFOTEXT = "jakieś brednie o projekcie";

    JTextField infoTextField;
    ImageIcon aghLogo;

    public InfoCardView() {
        panel = new JPanel();

        // infoTextField
        infoTextField = new JTextField();
        infoTextField.setEditable(false);
        infoTextField.setText(INFOTEXT);

        // aghLogo
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("client-side/drawings/AGH.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JLabel label = null;
        if (img != null) {
            ImageIcon icon = new ImageIcon(img);
            label = new JLabel(icon);
        } else {
            label = new JLabel();
        }

        label = new JLabel();

        // adding elements to panel
        panel.add(label);
        panel.add(infoTextField);

    }

    public JPanel getCard() {
        return panel;
    }

}
