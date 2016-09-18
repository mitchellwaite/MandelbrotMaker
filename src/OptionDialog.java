import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


public class OptionDialog extends JDialog {
	private int width, height, hueAdj;
	private double xmin, xmax, ymin, ymax;
	private int ixmin, ixmax, iymin, iymax;
	private MandlebrotSettings tempSettings;
	
	private boolean updatedSettings = false;
	
	private static final long serialVersionUID = -8067603919702664043L;
	private JTextField xmintb;
	private JTextField xmaxtb;
	private JTextField ymintb;
	private JTextField ymaxtb;
	private JTextField hueadjtb;

	/**
	 * Create the dialog.
	 */
	public OptionDialog(MandlebrotSettings s) {
		tempSettings = s;
		width = s.getWidth();
		height = s.getHeight();
		xmin = s.getXmin();
		xmax = s.getXmax();
		ymin = s.getYmin();
		ymax = s.getYmax();
		ixmin = (int)(xmin * 100);
		ixmax = (int)(xmax * 100);
		iymin = (int)(ymin * 100);
		iymax = (int)(ymax * 100);
		hueAdj = s.getHueAdjustmentFactor();
		
		setResizable(false);
		setBounds(100, 100, 563, 425);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setActionCommand("OK");
		panel.add(btnOk);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblXMinimum = new JLabel("X Minimum");
		lblXMinimum.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblXMinimum);
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		
		JSlider xminslider = new JSlider(-224,225,ixmin);	
		xminslider.setValue(0);
		panel_1.add(xminslider);
		
		
		xmintb = new JTextField();
		xmintb.setEditable(false);
		xmintb.setText("0");
		panel_1.add(xmintb);
		xmintb.setColumns(10);
		
		JLabel lblXMaximum = new JLabel("X Maximum");
		lblXMaximum.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblXMaximum);
		
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1);
		
		JSlider xmaxslider = new JSlider(-223,226,ixmax);
		xmaxslider.setValue(0);
		panel_1.add(xmaxslider);
		
		
		
		xmaxtb = new JTextField();
		xmaxtb.setEditable(false);
		xmaxtb.setText("0");
		xmaxtb.setColumns(10);
		panel_1.add(xmaxtb);
		
		JLabel lblYMinimum = new JLabel("Y Minimum");
		lblYMinimum.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblYMinimum);
		
		JLabel label_2 = new JLabel("");
		panel_1.add(label_2);
		
		JSlider yminslider = new JSlider(-224,225,iymin);
		panel_1.add(yminslider);
		yminslider.setValue(0);
		
		
		
		ymintb = new JTextField();
		ymintb.setEditable(false);
		ymintb.setText("0");
		ymintb.setColumns(10);
		panel_1.add(ymintb);
		
		JLabel lblYMaximum = new JLabel("Y Maximum");
		lblYMaximum.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblYMaximum);
		
		JLabel label_3 = new JLabel("");
		panel_1.add(label_3);
		
		JSlider ymaxslider = new JSlider(-223,226,iymax);
		ymaxslider.setValue(0);
		panel_1.add(ymaxslider);
		
		
		
		ymaxtb = new JTextField();
		ymaxtb.setEditable(false);
		ymaxtb.setText("0");
		ymaxtb.setColumns(10);
		panel_1.add(ymaxtb);
		
		JLabel lblHueAdjustment = new JLabel("Hue Adjustment");
		lblHueAdjustment.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblHueAdjustment);
		
		JLabel lblMandelbrotSettings = new JLabel("Mandelbrot Settings");
		lblMandelbrotSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblMandelbrotSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblMandelbrotSettings, BorderLayout.NORTH);
		
		//actions
		yminslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider src = (JSlider) arg0.getSource();
				if(!src.getValueIsAdjusting())
				{
					iymin = (int)src.getValue();
					if(iymin > iymax)
					{
						ymaxslider.setValue(iymin + 1);
					}
					ymintb.setText(String.valueOf(iymin/100.0));
				}
			}
		});
		
		
		ymaxslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider src = (JSlider) arg0.getSource();
				if(!src.getValueIsAdjusting())
				{
					iymax = (int)src.getValue();
					if(iymax < iymin)
					{
						yminslider.setValue(iymax-1);
					}
					ymaxtb.setText(String.valueOf(iymax/100.0));
				}
			}
		});
		
		
		xmaxslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider src = (JSlider) arg0.getSource();
				if(!src.getValueIsAdjusting())
				{
					ixmax = (int)src.getValue();
					if(ixmax < ixmin)
					{
						xminslider.setValue(ixmax-1);
					}
					xmaxtb.setText(String.valueOf(ixmax/100.0));
				}
			}
		});
		
		
		xminslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JSlider src = (JSlider) arg0.getSource();
				if(!src.getValueIsAdjusting())
				{
					ixmin = (int)src.getValue();
					if(ixmin > ixmax)
					{
						xmaxslider.setValue(ixmin + 1);
					}
					xmintb.setText(String.valueOf(ixmin/100.0));
				}
			}
		});
		
		//change values here
		xminslider.setValue(ixmin);
		xmaxslider.setValue(ixmax);
		ymaxslider.setValue(iymax);
		yminslider.setValue(iymin);
		
		JLabel label_4 = new JLabel("");
		panel_1.add(label_4);
		
		hueadjtb = new JTextField();
		hueadjtb.setEditable(false);
		hueadjtb.setText("0");
		hueadjtb.setColumns(10);
		
		
		JSlider hueadjslider = new JSlider();
		
			hueadjslider.setMinimum(-100);
			panel_1.add(hueadjslider);
			
			
			hueadjslider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					JSlider src = (JSlider) arg0.getSource();
					if(!src.getValueIsAdjusting())
					{
						hueAdj = (int)src.getValue();
						hueadjtb.setText(String.valueOf(hueAdj));
					}
				}
			});
			hueadjslider.setValue(hueAdj);
		
			panel_1.add(hueadjtb);
		
		JButton btnNewButton = new JButton("Reset All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hueadjslider.setValue(0);
				xmaxslider.setValue(xmaxslider.getMaximum());
				xminslider.setValue(xminslider.getMinimum());
				ymaxslider.setValue(ymaxslider.getMaximum());
				yminslider.setValue(yminslider.getMinimum());
			}
		});
		panel_1.add(btnNewButton);
		
		//add listeners here
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatedSettings = true;
				setVisible(false);
				dispose();
			}
		});
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		
		
		
		
	}

	public MandlebrotSettings updSettings()
	{
		this.setVisible(true);
		if(updatedSettings)
		{
			xmin = ixmin / 100.0;
			xmax = ixmax / 100.0;
			ymin = iymin / 100.0;
			ymax = iymax / 100.0;
				
			return new MandlebrotSettings(width,height,xmin,xmax,ymin,ymax, hueAdj,tempSettings.getLightBack());
		}
		else
		{
			return tempSettings;
		}
	}
}
