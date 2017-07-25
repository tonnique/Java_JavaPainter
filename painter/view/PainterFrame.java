package painter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import painter.controller.PainterController;
import painter.model.IPainrerToolBar;
import painter.model.PainterModel;

public class PainterFrame extends JFrame implements IPainrerToolBar {

	private static final long serialVersionUID = 1L;

	private PainterCanvas canvas = new PainterCanvas();	
	
	private PainterController controller = new PainterController();	
	
	private PainterModel model;
	
	private JScrollPane sc = new JScrollPane(canvas);
	
	JMenu mnuFile;
	
	JToolBar tb;
	private ColorLabel colorLeft = new ColorLabel ("Цвет 1", Color.BLACK);
	private ColorLabel colorRight = new ColorLabel ("Цвет 2", Color.WHITE);
	private ColorGrid cg;
	private JLabel chooser; 
	
	public PainterFrame() {
		super("Java Painter");
		
		model = new PainterModel(canvas, this);
		controller.setModel(model);
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		
		canvas.setName("canvas");
		canvas.addMouseMotionListener(controller);
		canvas.addMouseListener(controller);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sc, BorderLayout.CENTER);
		
		// setting menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		 
		mnuFile = new JMenu("File");
	
		addMenuItemToFileMenu("Очистить", "mnuNew", controller);
		mnuFile.addSeparator();
		addMenuItemToFileMenu("Сохранить", "mnuSave", controller);
		addMenuItemToFileMenu("Загрузить", "mnuLoad", controller);
		addMenuItemToFileMenu("Закрыть", "mnuExit", controller);
		
		menuBar.add(mnuFile);
		
		// --------------------------------------
		colorLeft.setName("1");
		colorLeft.setSelected(true);
		colorLeft.addMouseListener(controller);
		
		colorRight.setName("2");
		colorRight.addMouseListener(controller);
		
		cg = new ColorGrid(controller);
				
		chooser = new JLabel();		
		URL imgURL = ClassLoader.getSystemResource("res/image.png");
		
		if (imgURL != null) { 
			ImageIcon img = new ImageIcon(imgURL);
			chooser.setIcon(img);
		}
		
		tb = new JToolBar();
		tb.setLayout(new BorderLayout());		
		setupToolBar();
		add(tb, BorderLayout.NORTH);
	}
	
	private void setupToolBar() {
		JPanel panel = new JPanel();
		
		panel.add(colorLeft);
		panel.add(colorRight);
		panel.add(cg);
		
		JPanel colorChooserPanel = new JPanel();
		colorChooserPanel.add(chooser);		
		colorChooserPanel.setName("chooser");
		colorChooserPanel.addMouseListener(controller);
		panel.add(colorChooserPanel);
		
		tb.add(panel, BorderLayout.WEST);
	}
	
	/**
	 * Adds menu item to File Menu
	 * @param miStr - a string with text for menu
	 * @param ac - action command text
	 * @param al - ActionCommand Listener
	 */
	private void addMenuItemToFileMenu(String miStr, String ac, ActionListener al) {
		
		JMenuItem mnuINew = new JMenuItem(miStr);
		mnuINew.setActionCommand(ac);
		mnuINew.addActionListener(al);		
		mnuFile.add(mnuINew);		
	}
	
	public ColorLabel getColorLabel(int n) {
		if (n < 0 || n > 2) return null;
		if (n == 1) return colorLeft;
		else return colorRight;		
	}	
	
	
	public static void main(String[] args) {
		JFrame frame = new PainterFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);		
	}
}
