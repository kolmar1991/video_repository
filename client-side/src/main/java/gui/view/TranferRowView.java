package gui.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TranferRowView {
    private JPanel panel;

    final static String DIRECTORYCHOOSERTITLE = "Wybierz katalog";
    final static String REMOVEBUTTONTEXT = "X";

    final static String CONVERTINGACTION = " Konwertowanie ";
    final static String SENDINGACTION = " Wysy�anie na serwer ";
    final static String WAITINGACTION = " Oczekiwanie ";
    final static String ERROR = "B��d!";

    private JButton startButton;
    private JTextField directoryTextField;
    private JButton directoryChooserButton;
    private JFileChooser fileChooser;
    private JTextField currentActionTextField;

    private String DIRECTORY_AT_START = "sciezka do katalogu";

    public TranferRowView() {
        panel = new JPanel(new FlowLayout());

        // removeButton
        startButton = new JButton(/*REMOVEBUTTONTEXT*/);
        try {
            Image img = ImageIO.read(new File("drawings/syncIcon.png"));
            startButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            startButton.setText(REMOVEBUTTONTEXT);
            System.out.println("Error while loading syncIncon.png in TranferRowView constructor - text: \"" + REMOVEBUTTONTEXT + "\" will be displayed instead");
        }

        // directoryTextField
        directoryTextField = new JTextField(30);
        directoryTextField.setEditable(false);
        directoryTextField.setText(DIRECTORY_AT_START);

        // fileChooserButton
        directoryChooserButton = new JButton(DIRECTORYCHOOSERTITLE);
        directoryChooserButton.setPreferredSize(new Dimension(120, 25));

        // fileChooser
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle(DIRECTORYCHOOSERTITLE);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // currentActionTextField
        currentActionTextField = new JTextField();
        currentActionTextField.setEditable(false);
        setCurrentAction(2); // waiting action

        // adding components to panel (TranferRowView)
        panel.add(startButton);
        panel.add(directoryTextField);
        panel.add(directoryChooserButton);
        panel.add(currentActionTextField);
        // panel.add(fileChooser);

    }

	/*
     * public void actionPerformed(ActionEvent e) { //Handle open button action.
	 * if (e.getSource() == fileChooserButton) { int returnVal =
	 * fileChooser.showOpenDialog(panel);
	 * 
	 * if (returnVal == JFileChooser.APPROVE_OPTION) { File file =
	 * fileChooser.getSelectedFile(); //This is where a real application would
	 * open the file. log.append("Opening: " + file.getName() + "." + newline);
	 * } else { log.append("Open command cancelled by user." + newline); } } }
	 */

    public JPanel getPanel() {
        return panel;
    }

    public void setCurrentAction(int actionType) { // 0 - converting, 1 -
        // sending, 2 - waiting,
        // other - error
        switch (actionType) {
            case 0:
                currentActionTextField.setText(CONVERTINGACTION);
                break;
            case 1:
                currentActionTextField.setText(SENDINGACTION);
                break;
            case 2:
                currentActionTextField.setText(WAITINGACTION);
                break;
            default:
                currentActionTextField.setText(ERROR);
                break;
        }
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JButton getDirectoryChooserButton() {
        return directoryChooserButton;
    }

    public JTextField getDirectoryTextField() {
        return directoryTextField;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
