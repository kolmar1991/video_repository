package gui.controller;

import gui.view.TranferRowView;
import gui.view.popup.PopUpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class TransferRowController implements ActionListener {
	private TranferRowView transferRow;
	private JFrame mainFrame;
	private PopUpFrame popUpFrame;

	public TransferRowController(TranferRowView transferRow, JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.transferRow = transferRow;

		transferRow.getDirectoryChooserButton().addActionListener(this);
		transferRow.getStartButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		// choose directory
		if (event.getSource() == transferRow.getDirectoryChooserButton()) {
			int returnVal = transferRow.getFileChooser().showOpenDialog(
					transferRow.getPanel());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = transferRow.getFileChooser().getSelectedFile();

				transferRow.getDirectoryTextField().setText(
						file.getAbsolutePath());

			}

		}

		// start button
		if (event.getSource() == transferRow.getStartButton()) {
			popUpFrame = new PopUpFrame();

			popUpFrame.getCancelButton().addActionListener(this);

			//mainFrame.setVisible(false);
			mainFrame.setEnabled(false);
		}

		// cancel button
		if (popUpFrame != null) {
			if (event.getSource() == popUpFrame.getCancelButton()) {

				popUpFrame.getFrame().dispose();
				//popUpFrame.getFrame().setEnabled(false);
				
				//mainFrame.setVisible(true);
				mainFrame.setEnabled(true);
				mainFrame.toFront();
			}
		}

	}
}
