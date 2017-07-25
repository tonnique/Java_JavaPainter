package painter.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import painter.view.ColorLabel;
import painter.view.PainterCanvas;


public class PainterModel {
	
	private PainterCanvas canvas;
	private IPainrerToolBar toolbar;
	
	private ColorLabel colorLeft;
	private ColorLabel colorRight;
	private ColorLabel selectedRLColor;
	
	public PainterModel(PainterCanvas c, IPainrerToolBar t) {
		canvas = c;
		toolbar = t;
		
		colorLeft = toolbar.getColorLabel(1);
		colorRight = toolbar.getColorLabel(2);
		
		if (colorLeft == null || colorRight == null) {
			JOptionPane.showMessageDialog(null, "Не удалось получить ссылку(и)\n"
					+ "на объекты ColorLabel в классе PainterModel", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
		
		selectedRLColor = colorLeft;		
	}
	
		
	public void cleanCanvas() {
		canvas.cleanCanvas();
	}
	
	public void saveImage() {
		if (canvas == null) return;
		
		JFileChooser fchooser = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif");
		fchooser.setFileFilter(filter);					
		fchooser.setAcceptAllFileFilterUsed(false);
					
		int result = fchooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			
		BufferedImage image =(BufferedImage) canvas.createImage(canvas.getWidth(), canvas.getHeight());
			Graphics2D g2 = image.createGraphics();
			canvas.paint(g2);
			g2.dispose();
			
			String filename =  fchooser.getSelectedFile().getName();
			String fileExt = filename.substring(filename.length()-3, filename.length());

			if (fileExt.equals("gif") || fileExt.equals("png") || fileExt.equals("jpg")) {
				
				try {
		            ImageIO.write(image, fileExt, fchooser.getSelectedFile());
		        }
		        catch(IOException io) { io.printStackTrace(); }	       
			}
		}	
	}
	
	public void loadImage() {
		if (canvas == null) return;
		
		JFileChooser fchooser = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif");
		fchooser.setFileFilter(filter);					
		fchooser.setAcceptAllFileFilterUsed(false);
					
		int result = fchooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			
			try {					
				Image image = ImageIO.read(fchooser.getSelectedFile());					
				canvas.setImage(image);					
			} catch(IOException io) { io.printStackTrace(); }			
			
		}	
	}
	
	public void chooseAndSetColor() {		
		Color c = JColorChooser.showDialog(null, "Выбор цвета", selectedRLColor.getColor());
		if (c != null) {
			selectedRLColor.setColor(c);			
		}
	}
	
	/* Переключает активное положение контролов-мониторов цвета для левой и правой кнопки мыши
	 * этот метод получает управлени из контроллера (слушателя событий)	*/
	public void setColorLabelControlActive(ColorLabel cl) {
		if (cl != selectedRLColor)
			
			if (cl.getName().equals("1")) { 
				switchColorLabel(colorLeft, selectedRLColor); 
			} else {
				switchColorLabel(colorRight, selectedRLColor);
			}
		}

	// Переключает активное положение контролов-мониторов цвета для левой и правой кнопки мыши  
		private void switchColorLabel(ColorLabel cl1, ColorLabel cl2) {					
			cl1.setSelected(true);
			cl2.setSelected(false);
			selectedRLColor = cl1;
		}

		
		// Возвращет активную (выбранную) компоненту ColorLabel
		public ColorLabel getActiveColorLabel() {
			return selectedRLColor;
		}
		
		/**
		 * 
		 * @param button the int value for mouse button 1 - for Left Button; 2 for Right Button
		 * @param point - is mouse coordinates
		 * @param start - this param defines if this point is start point for drawing. 
		 * Start point takes place every time when user presses button and then drugs mouse. 
		 * After user released button new start point should take place when user press mouse button again. */
		public void addPoint(int button, Point point, boolean start) {
			// 1 for left mouse Button
			// 2 for right muse Button
			Color color = (button == 1) ? colorLeft.getColor() : colorRight.getColor();
			ColorPoint cp = new ColorPoint(color, point.x, point.y);
			
			if (start) {
				
			/* this is important. here we define that this is the start point in the sequence. 
			 * when user presses the mouse button and drags mouse we have a start point 
			 * and points following it in the sequence. */
				cp.setStartPoint(true);
			}
						
			canvas.addPoint(cp);
		}
}
