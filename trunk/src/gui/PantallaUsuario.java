package gui;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

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
public class PantallaUsuario extends JInternalFrame {
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JLabel labelNombreOp;
	private JLabel labelOponente;
	private JTextArea logger;
	private JButton btSetNumero;
	private JButton btGuess;
	private JSeparator jSeparator1;
	private JTextField guess;
	private JLabel labelGuess;
	private JTextField numero;
	private JLabel labelNumero;
	private PantallaControladora controlGui;
	
	public PantallaUsuario(PantallaControladora pc){
		controlGui = pc;
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(385, 318));
				this.setBounds(0, 0, 385, 318);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.CENTER);
					jPanel1.setLayout(null);
					{
						labelNumero = new JLabel();
						jPanel1.add(labelNumero);
						labelNumero.setText("Numero Propio");
						labelNumero.setBounds(20, 43, 112, 15);
					}
					{
						numero = new JTextField();
						jPanel1.add(numero);
						numero.setBounds(19, 71, 64, 22);
					}
					{
						labelGuess = new JLabel();
						jPanel1.add(labelGuess);
						labelGuess.setText("Guess");
						labelGuess.setBounds(19, 116, 35, 15);
					}
					{
						guess = new JTextField();
						guess.setEnabled(false);
						jPanel1.add(guess);
						guess.setText(" ");
						guess.setBounds(19, 141, 64, 22);
					}
					{
						jSeparator1 = new JSeparator();
						jPanel1.add(jSeparator1);
						jSeparator1.setBounds(144, 43, 10, 227);
						jSeparator1.setOrientation(SwingConstants.VERTICAL);
					}
					{
						btGuess = new JButton();
						btGuess.setEnabled(false);
						jPanel1.add(btGuess);
						btGuess.setText("Guess");
						btGuess.setBounds(19, 248, 106, 22);
						btGuess.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								System.out.println("Clickeo en guess..");
								controlGui.guess(guess.getText());
								mostrarGuess(guess.getText());
							}
						});
					}
					{
						btSetNumero = new JButton();
						jPanel1.add(btSetNumero);
						btSetNumero.setText("set");
						btSetNumero.setBounds(95, 71, 31, 22);
						btSetNumero.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								System.out.println("Clickeo en setear..");
								controlGui.setNumero(numero.getText());
								btGuess.setEnabled(true);
								guess.setEditable(true);
								btSetNumero.setEnabled(false);
							}
						});
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1);
						jScrollPane1.setBounds(165, 71, 199, 199);
						jScrollPane1.setAutoscrolls(true);
						{
							logger = new JTextArea();
							jScrollPane1.setViewportView(logger);
							logger.setEditable(false);
						}
					}
					{
						labelOponente = new JLabel();
						jPanel1.add(labelOponente);
						labelOponente.setText("Oponente");
						labelOponente.setBounds(166, 43, 72, 15);
					}
					{
						labelNombreOp = new JLabel();
						jPanel1.add(labelNombreOp);
						labelNombreOp.setText(" -");
						labelNombreOp.setBounds(244, 43, 120, 15);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarGuess(String numero){
		
		logger.append("[");
		for (int i=0; i<numero.length(); i++)
			logger.append(numero.charAt(i)+" ");
	
		logger.append("] :: ");
	}
	
	public void mostrarResultado(String resul){
		
		logger.append(resul+"\n");
	}
	
	public void mostrar(String oponente){
		labelNombreOp.setText(oponente);
		this.setVisible(true);
	}

}
