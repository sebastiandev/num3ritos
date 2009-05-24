package gui;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PantallaPrincipal extends JInternalFrame {
	private JPanel jPanel1;
	private JTextField nombre;
	private JComboBox jCombo;
	private JLabel labelDigitos;
	private JButton btIniciar;
	private JTextField ip;
	private JLabel labelServer;
	private JLabel labelNombre;
	private JRadioButton radioCliente;
	private JRadioButton radioServer;
	private ButtonGroup grupo;
	private JSeparator jSeparator2;
	private PantallaControladora controlGui = null;
	
	public PantallaPrincipal(PantallaControladora pc){
		controlGui = pc;
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(406, 214));
				this.setBounds(0, 0, 406, 214);
				getContentPane().setLayout(null);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, "Center");
					jPanel1.setLayout(null);
					jPanel1.setPreferredSize(new java.awt.Dimension(394, 183));
					jPanel1.setLayout(null);
					jPanel1.setBounds(0, 0, 402, 185);
					{
						radioServer = new JRadioButton();
						jPanel1.add(radioServer);
						radioServer.setText("Server");
						radioServer.setBounds(119, 37, 73, 19);
						radioServer.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent arg0) {
								System.out.println("Cambio de estado radio server");
								if (radioServer.isSelected()){
									ip.setEnabled(false);
									jCombo.setEnabled(true);
								}
								else {
									ip.setEnabled(true);
									jCombo.setEnabled(false);
								}
							}
						});
					}
					{
						radioCliente = new JRadioButton();
						jPanel1.add(radioCliente);
						radioCliente.setText("Cliente");
						radioCliente.setBounds(212, 37, 82, 19);
					}
					
					grupo = new ButtonGroup();
					grupo.add(radioServer);
					grupo.add(radioCliente);
					
					{
						labelNombre = new JLabel();
						jPanel1.add(labelNombre);
						labelNombre.setText("Nombre");
						labelNombre.setBounds(39, 83, 59, 15);
					}
					{
						nombre = new JTextField();
						jPanel1.add(nombre);
						nombre.setText(" ");
						nombre.setBounds(98, 80, 94, 22);
					}
					{
						labelServer = new JLabel();
						jPanel1.add(labelServer);
						labelServer.setText("IP");
						labelServer.setBounds(39, 115, 36, 10);
					}
					{
						ip = new JTextField();
						ip.setEnabled(false);
						jPanel1.add(ip);
						ip.setBounds(98, 110, 94, 22);
					}
					{
						btIniciar = new JButton();
						jPanel1.add(btIniciar);
						btIniciar.setText("Iniciar");
						btIniciar.setBounds(319, 156, 63, 22);
						btIniciar.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						btIniciar.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								System.out.println("Clickeo en iniciar..");
								short modo = 0;
								if (radioServer.isSelected()){
									modo = controlGui.MODO_SERVER;
								}
								else{
									modo = controlGui.MODO_CLIENTE;
								}
								controlGui.iniciar(modo, 
										           nombre.getText(), 
										           ip.getText(), 
										           ((Integer)jCombo.getSelectedItem()).intValue());
							}
						});
					}
					{
						labelDigitos = new JLabel();
						jPanel1.add(labelDigitos);
						labelDigitos.setText("Nro Digitos");
						labelDigitos.setBounds(233, 83, 86, 15);
					}
					{
						ComboBoxModel jComboModel = 
							new DefaultComboBoxModel(
									new Integer[] { 4, 5, 6 });
						jCombo = new JComboBox();
						jCombo.setEnabled(false);
						jPanel1.add(jCombo);
						jCombo.setModel(jComboModel);
						jCombo.setBounds(233, 109, 67, 22);
					}
					{
						jSeparator2 = new JSeparator();
						jPanel1.add(jSeparator2);
						jSeparator2.setOrientation(SwingConstants.VERTICAL);
						jSeparator2.setBounds(212, 80, 9, 59);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
