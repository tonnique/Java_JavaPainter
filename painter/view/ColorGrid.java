package painter.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import painter.model.IPainter;

public class ColorGrid extends JPanel {

	MouseListener controller;
	
	public ColorGrid(MouseListener ml) {
		this.controller = ml;
		
		int length = IPainter.colors.length;
		setLayout(new GridLayout(2, length / 2 , 3, 3));
		
		for (int i = 0; i < length; i++) {
			JLabel color = new JLabel();
			color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			color.setPreferredSize(new Dimension(17, 17));
			color.setBackground(IPainter.colors[i]);	
			color.setOpaque(true);
			color.setName("colorLabel");
			color.addMouseListener(controller);
			color.setToolTipText(IPainter.colorsToolTips[i]);
			this.add(color);
		}	
	}
}
