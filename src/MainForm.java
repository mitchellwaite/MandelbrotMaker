import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentAdapter;

import javax.swing.JToggleButton;

import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JCheckBox;




public class MainForm extends JFrame {

	private static final long serialVersionUID = -3461394810736300367L;
	private JPanel contentPane;
	private graphicsPanel imageCanvas;
	private boolean testPattern = false;
	private MandlebrotSettings imgSettings;
	JLabel savedlabel;
	
	/**
	 * @wbp.nonvisual location=41,399
	 */
	private final Timer timer = new Timer(10000, new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			savedlabel.setText("");
			timer.stop();
		}
	});
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.pai
	 */
	public MainForm() {
		setBackground(new Color(0.1F,0.1F,0.1F));
		setTitle("Mandelbrot App");
		imgSettings = new MandlebrotSettings(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 467);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		
		contentPane.setBackground(UIManager.getColor("Button.shadow"));
		
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				imgSettings.setImageDimensions(imageCanvas.getWidth(), imageCanvas.getHeight());
				drawGraphics();
			}
		});
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(UIManager.getColor("Button.foreground"));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		imageCanvas = new graphicsPanel();
		imageCanvas.setBackground(new Color(0,0,0));
		
		contentPane.add(imageCanvas, BorderLayout.CENTER);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener( event -> System.exit(0));
		
		JToggleButton testToggle = new JToggleButton("Test Pattern");
		testToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testPattern = testToggle.isSelected();
				drawGraphics();
			}
		});
		
		savedlabel = new JLabel("");
		savedlabel.setForeground(Color.WHITE);
		
		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = imageCanvas.saveImage();
				savedlabel.setText(s);
				timer.start();
			}
		});
		
		JButton btnImageSettings = new JButton("Image Settings");
		btnImageSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionDialog o = new OptionDialog(imgSettings);
				imgSettings = o.updSettings();
				drawGraphics();
			}
		});
		
		
		panel.add(savedlabel);
		panel.add(btnImageSettings);
		
		panel.add(btnSaveImage);
		panel.add(testToggle);
		panel.add(btnExit);
		
		JPanel boxpanel = new JPanel();
		boxpanel.setOpaque(false);
		contentPane.add(boxpanel, BorderLayout.NORTH);
		
		JCheckBox chckbxLightBackground = new JCheckBox("Light Background");
		chckbxLightBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox)e.getSource();
				boolean x = cb.isSelected();
				if(x)
				{
					chckbxLightBackground.setForeground(Color.BLACK);
					setBackground(new Color(0.9F,0.9F,0.9F));
				}
				else
				{
					chckbxLightBackground.setForeground(Color.WHITE);
					setBackground(new Color(0.1F,0.1F,0.1F));
				}
				imgSettings.setLightBack(x);
				drawGraphics();	
			}
		});
		
		chckbxLightBackground.setForeground(Color.WHITE);
		chckbxLightBackground.setOpaque(false);
		boxpanel.add(chckbxLightBackground);
		timer.setInitialDelay(5000);
		timer.setCoalesce(false);
		timer.setDelay(0);
	
		
	}
	
	private void drawGraphics()
	{
		if(!testPattern)
		{
			imageCanvas.drawImage(MandlebrotClass.getPlot(imgSettings));
		}
		else
		{
			imageCanvas.drawImage(MandlebrotClass.getTest(imgSettings));
		}
		contentPane.repaint();
	}
	
	private int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private class graphicsPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private BufferedImage buff;
		
		public graphicsPanel()
		{
			
		}
		
		private String saveImage()
		{
			if(buff != null)
			{
				String filename = String.valueOf(randInt(0,999999999)) + ".png";
				String path = System.getProperty("user.dir");
				String tpath = path + System.getProperty("file.separator")+ filename;
				File outStream = new File(tpath);
				try {
					ImageIO.write(buff, "png", outStream);
					return "Image saved to: " + tpath;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return "Could Not Save File.";
				}
				
			}
			return "No File To Save";
		}
		
		private void drawImage(BufferedImage img)
		{
			buff = img;
		}
		
		@Override
		public void paintComponent(Graphics g){
			Graphics2D grap = (Graphics2D)g;
			grap.drawImage(buff, null, 0,0);
		}
	}

}
