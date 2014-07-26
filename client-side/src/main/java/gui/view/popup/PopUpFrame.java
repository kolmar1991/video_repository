package gui.view.popup;

import java.awt.Component;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PopUpFrame {
	private JFrame frame;
	private JButton cancelButton;
	
	
	public static final String FRAME_TITLE = "Parametry po³¹czenia";
	
	
	
	public PopUpFrame (){
		
		frame = new JFrame(FRAME_TITLE);
		frame.setResizable(false); 
		removeMinMaxClose(frame);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		cancelButton = new JButton("Anuluj");
		
		
		frame.add(cancelButton);
		frame.setSize(300, 100);
	    frame.setVisible(true);
	}
	
	public void removeMinMaxClose(Component comp)  
	  {  
	    if(comp instanceof AbstractButton)  
	    {  
	      comp.getParent().remove(comp);  
	    }  
	    if (comp instanceof Container)  
	    {  
	      Component[] comps = ((Container)comp).getComponents();  
	      for(int x = 0, y = comps.length; x < y; x++)  
	      {  
	        removeMinMaxClose(comps[x]);  
	      }  
	    }  
	  }
	
	public JButton getCancelButton(){
		return cancelButton;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
