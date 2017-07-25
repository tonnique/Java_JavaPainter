package painter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import painter.model.PainterModel;
import painter.view.ColorLabel;
import painter.view.PainterCanvas;


public class PainterController implements MouseListener, MouseMotionListener, ActionListener {
	

	private PainterModel model;
	
	public void setModel(PainterModel m) {model = m;}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("mnuExit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("mnuNew")) {
			model.cleanCanvas();
		}	
		
		if (e.getActionCommand().equals("mnuSave")) {
			if (model == null ) return;
			model.saveImage();			
		}
		
		if (e.getActionCommand().equals("mnuLoad")) {
			if (model == null ) return;
			model.loadImage();	
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() instanceof ColorLabel ) {			
			ColorLabel clrLabel = (ColorLabel) e.getSource();			
			model.setColorLabelControlActive(clrLabel);
		}
		if (e.getSource() instanceof JLabel) {
			JLabel srcLabel = (JLabel) e.getSource();			
			model.getActiveColorLabel().setColor(srcLabel.getBackground());
		}
		if (e.getSource() instanceof JPanel) {
			JPanel srcPanel = (JPanel) e.getSource();
			
			if (srcPanel.getName().equals("chooser")) {
				model.chooseAndSetColor();		
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof PainterCanvas) {
			// 1 for left mouse Button; 2 for right muse Button
			int buttonNumber = 1; //left mouse button by default
			
			/* here we define which mouse button is pressed
			 * by experiment (using system.out.println) i learned that
			 * e.getModifiers() == 16 for left mouse button and 
			 * e.getModifiers() == 4 for right mouse button 
			 */
			if (e.getModifiers() == 16) buttonNumber = 1;
			else if (e.getModifiers() == 4) buttonNumber = 2;			
			
			model.addPoint(buttonNumber, e.getPoint(), true);
		}		
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// 1 for left mouse Button; 2 for right muse Button
		int buttonNumber = 1; //left mouse button by default
		
		//Here we define which mouse button is pressed
		if (e.getModifiers() == 16) buttonNumber = 1;
		else if (e.getModifiers() == 4) buttonNumber = 2;
		
		model.addPoint(buttonNumber, e.getPoint(), false);		
	}
	
	// Unused methods 
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {	}

	@Override
	public void mouseExited(MouseEvent e) {}
}
