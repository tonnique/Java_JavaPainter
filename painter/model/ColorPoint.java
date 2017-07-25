package painter.model;

import java.awt.Color;
import java.awt.Point;

public class ColorPoint extends Point {
	private Color color;
	/* this is important. here we define that this is the start point in the sequence.
	 * When user presses the mouse button and drags mouse we have a start point 
	 * and points that follow it in the sequence. When program redraws canvas, 
	 * we take all points in the ArayList and connect one point with another. 
	 * But if we have no such checking for start point then when user wants to start 
	 * drawing again, he/she presses mouse button but this the new point is not 
	 * separate from previous ones, but it is connected with the last point. Thus we have to set this 
	 * point as startPoint when we need it.*/
	private boolean startPoint = false;
	
	public ColorPoint(Color c, int x, int y) {
		super(x, y);
		this.color = c;		
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setStartPoint(boolean b) {
		startPoint = b;
	}	
	
	public boolean isStartPoint() {
		return startPoint;
	}	
}
