package gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainCardView {

    final static String CARDNAME = "Transfer";

    final static String DIRECTORYBUTTONDESC = "dodaj katalog";
    final static String SERVERCONNECTIONOK = "Połączenie z serwerem: OK";
    final static String SERVERCONNECTIONERROR = "Połączenie z serwerem: Błąd";
    final static String REMOVEBUTTONTEXT = "X";
    private String DIRECTORY_AT_START = "Ścieżka do katalogu";
    final static String DIRECTORYCHOOSERTITLE = "Wybierz katalog";

    final static String CONVERTINGACTION = " Konwertowanie ";
    final static String SENDINGACTION = " Wysyłanie na serwer ";
    final static String WAITINGACTION = " Oczekiwanie ";
    final static String ERROR = "Błąd!";

    private JPanel panel;

    private JButton startButton;
    private JTextField moviesDirectoryTextField;
    private JButton moviesDirectoryChooserButton;
    private JTextField xmlsDirectoryTextField;
    private JButton xmlsDirectoryChooserButton;
    private JFileChooser fileChooser;
    private JTextField currentActionTextField;
    private JCheckBox sameDirectoryCheckBox;

    private JTextField serverConnectionStatus;

    public MainCardView() {
        panel = new JPanel();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridBagLayout());

        // start button
        startButton = new JButton(/* REMOVEBUTTONTEXT */);
        try {
            Image img = ImageIO.read(new File("client-side/drawings/syncIcon.png"));
            startButton.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            startButton.setText(REMOVEBUTTONTEXT);
            System.out
                    .println("Error while loading syncIncon.png in TranferRowView constructor - text: \""
                            + REMOVEBUTTONTEXT + "\" will be displayed instead");
        }
        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        startButtonConstraints.gridx = 4;
        startButtonConstraints.gridy = 4;

        // directoryTextField
        moviesDirectoryTextField = new JTextField(30);
        moviesDirectoryTextField.setEditable(false);
        moviesDirectoryTextField.setText(DIRECTORY_AT_START);
        GridBagConstraints moviesDirectoryTextFieldConstraints = new GridBagConstraints();
        moviesDirectoryTextFieldConstraints.gridx = 1;
        moviesDirectoryTextFieldConstraints.gridy = 0;

        // fileChooserButton
        moviesDirectoryChooserButton = new JButton(DIRECTORYCHOOSERTITLE);
        moviesDirectoryChooserButton.setPreferredSize(new Dimension(120, 25));
        GridBagConstraints moviesDirectoryChooserButtonConstraints = new GridBagConstraints();
        moviesDirectoryChooserButtonConstraints.gridx = 2;
        moviesDirectoryChooserButtonConstraints.gridy = 0;

        // directoryTextField
        xmlsDirectoryTextField = new JTextField(30);
        xmlsDirectoryTextField.setEditable(false);
        xmlsDirectoryTextField.setText(DIRECTORY_AT_START);
        GridBagConstraints xmlsDirectoryTextFieldConstraints = new GridBagConstraints();
        xmlsDirectoryTextFieldConstraints.gridx = 1;
        xmlsDirectoryTextFieldConstraints.gridy = 1;

        // fileChooserButton
        xmlsDirectoryChooserButton = new JButton(DIRECTORYCHOOSERTITLE);
        xmlsDirectoryChooserButton.setPreferredSize(new Dimension(120, 25));
        GridBagConstraints xmlsDirectoryChooserButtonConstraints = new GridBagConstraints();
        xmlsDirectoryChooserButtonConstraints.gridx = 2;
        xmlsDirectoryChooserButtonConstraints.gridy = 1;

        // fileChooser
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle(DIRECTORYCHOOSERTITLE);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // internetStatusDesc
        serverConnectionStatus = new JTextField();
        serverConnectionStatus.setEditable(false);
        GridBagConstraints serverConnectionStatusConstraints = new GridBagConstraints();
        serverConnectionStatusConstraints.gridx = 1;
        serverConnectionStatusConstraints.gridy = 2;
        serverConnectionStatusConstraints.anchor = GridBagConstraints.WEST;
        setServerConnectionStatus(false);

        // currentActionTextField
        currentActionTextField = new JTextField();
        currentActionTextField.setEditable(false);
        setCurrentAction(2); // waiting action
        GridBagConstraints currentActionTextFieldConstraints = new GridBagConstraints();
        currentActionTextFieldConstraints.gridx = 1;
        currentActionTextFieldConstraints.gridy = 3;
        currentActionTextFieldConstraints.anchor = GridBagConstraints.WEST;

        sameDirectoryCheckBox = new JCheckBox("ta sama ścieżka");
        sameDirectoryCheckBox.setSelected(false);
        GridBagConstraints sameDirectoryCheckBoxConstraints = new GridBagConstraints();
        sameDirectoryCheckBoxConstraints.gridx = 3;
        sameDirectoryCheckBoxConstraints.gridy = 1;


        // adding elements to card

        // panel.add(addDirectoryButton, addDirectoryButtonConstraints);
        panel.add(startButton, startButtonConstraints);
        panel.add(moviesDirectoryTextField, moviesDirectoryTextFieldConstraints);
        panel.add(moviesDirectoryChooserButton, moviesDirectoryChooserButtonConstraints);
        panel.add(xmlsDirectoryTextField, xmlsDirectoryTextFieldConstraints);
        panel.add(xmlsDirectoryChooserButton, xmlsDirectoryChooserButtonConstraints);
        panel.add(serverConnectionStatus, serverConnectionStatusConstraints);
        panel.add(currentActionTextField, currentActionTextFieldConstraints);
        panel.add(sameDirectoryCheckBox, sameDirectoryCheckBoxConstraints);

    }

    public JPanel getCard() {
        return panel;
    }

    public JButton getStartButton(){
        return startButton;
    }

    public JTextField getMoviesDirectoryTextField(){
        return moviesDirectoryTextField;
    }

    public JButton getMoviesDirectoryChooserButton(){
        return moviesDirectoryChooserButton;
    }

    public JTextField getXmlsDirectoryTextField(){
        return xmlsDirectoryTextField;
    }

    public JButton getXmlsDirectoryChooserButton(){
        return xmlsDirectoryChooserButton;
    }

    public JFileChooser getFileChooser(){
        return fileChooser;
    }

    public JCheckBox getSameDirectoryCheckBox(){
        return sameDirectoryCheckBox;
    }

    public void setServerConnectionStatus(boolean isConnected) {
        if (isConnected) {
            serverConnectionStatus.setText(SERVERCONNECTIONOK);
            serverConnectionStatus.setForeground(Color.GREEN);
        } else {
            serverConnectionStatus.setText(SERVERCONNECTIONERROR);
            serverConnectionStatus.setForeground(Color.RED);
        }
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
}
