package painter.view;

import java.awt.*;
import java.awt.image.MemoryImageSource;

import javax.swing.*;

import painter.model.IPainter;

/* 
 *   Этот класс представляет собой контролл (это не Java Bean), 
 *   совмещающий в одном 
 *   JLabel c изображением выбранного (активного) цвета и
  *  JLabel с текстом
  *  
  *  Выбранный (активный) контрол подсвечивается цветом
  *       
 */
public class ColorLabel extends JPanel {
		
	private JLabel lblText;
	private JLabel lblColor;	
	private Color color;
	private final int IMAGE_HEIGHT;
	private final int IMAGE_WIDTH;
	private final Color selectedBackground = IPainter.labelColorSelectedBackground;
	private final Color defaultBackground = getBackground();
	
	public ColorLabel(String s, Color c) {
		color = c;
		
		lblText = new JLabel(s);		
		lblColor = new JLabel();
		lblColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		IMAGE_HEIGHT = IMAGE_WIDTH = 20;
		setPreferredSize(new Dimension(43, 50));		
		setColor(color);
		
		add(lblColor);
		add(lblText);
	}
	
	public ColorLabel(String s) {
		this(s, Color.BLACK);
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void setSelected(boolean b) {
		if (b == true) {
			setBackground(selectedBackground); 
		} else {
			setBackground(defaultBackground);	
		}
	}
		
	
	public void setColor(Color c) {		
		setColorLabel(c);
		lblColor.revalidate();
	}
	
	/* #Интересно
	 * Здесь в этом методе я динамически создаю "иконку" с выбранным цветом и устанавливаю её для 
	 * компонента JLabel.
	 * 
	 * В этом методе, при создании иконки нужно преобразовать объект Color в значение int
	 * 
	 * */
	/**
	 * Этот метод создает иконку для выбранного цвета 
	 * и устанавливает её в объекте lblColor 
	 * @param c - Color
	 */
	private void setColorLabel(Color c) {
		color = c; 
		int[] pixs = new int[IMAGE_HEIGHT*IMAGE_WIDTH];		
		
/*		Проверки
 * 		String RedHex = Integer.toHexString(c.getRed());
		String GreenHex = Integer.toHexString(c.getGreen());
		String BlueHex = Integer.toHexString(c.getBlue());
		
		String hex = Integer.toHexString((c.getRed() << 16) | (c.getGreen() << 8) | c.getBlue());
		System.out.println("Red: " + RedHex + " Green: " + GreenHex + " Blue: " + BlueHex);		
		System.out.println(hex);*/		
			
		for (int i = 0; i < pixs.length; i++) {
			pixs[i] = 0xFF000000 | ((color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue());
		}
				
		lblColor.setIcon(
			new ImageIcon(
			createImage(new MemoryImageSource(IMAGE_WIDTH, IMAGE_HEIGHT, pixs, 0, IMAGE_WIDTH))
			)
		);		
		
	}
	
	public Color getColor() {
		return color;
	}
	
}
