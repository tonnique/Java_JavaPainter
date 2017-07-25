package painter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import painter.model.ColorPoint;


public class PainterCanvas extends JPanel {
	
	private int lastX, lastY;
	private ArrayList<ColorPoint> points = new ArrayList<ColorPoint>();
	private Image image;
	
	public PainterCanvas() {		
		setBackground(Color.WHITE);		
		setPreferredSize(new Dimension(800, 600));
	}

	public void addPoint(ColorPoint cp) {
		points.add(cp);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.repaint();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if (image != null) {			
			g.drawImage(image, 0, 0, null);			
		} 
		
		for (ColorPoint p : points) {
			g.setColor(p.getColor());
			if (p.isStartPoint()) {
				g.fillOval(p.x, p.y, 2, 2);
				lastX = p.x;
				lastY = p.y;
			}
			else {
				g.drawLine(lastX, lastY, p.x, p.y);
				lastX = p.x;
				lastY = p.y;
			}			
		}
	}
		
	
	public void cleanCanvas() {
		points.clear();
		image = null;
		repaint();
	}
	
	public void setImage(Image img) {
		image = img;
		repaint();
	}
}
