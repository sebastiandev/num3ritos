package gui;

import java.util.LinkedList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import controlador.Controlador;
import controlador.ObservadorControlador;
import controlador.ObservadorPantalla;

public class PantallaControladora extends JFrame implements ObservadorControlador{

	protected static final short MODO_SERVER = 0;
	protected static final short MODO_CLIENTE = 1;
	
	private PantallaPrincipal principal;
	private PantallaUsuario usuario;
	private Controlador controlador;
	protected LinkedList<ObservadorPantalla> observadores;
	
	public static void main(String[] args){
		
		Controlador control = new Controlador();
		PantallaControladora numeritos = new PantallaControladora(control);
		control.addObservador(numeritos);
		numeritos.show();
	}
	
	public PantallaControladora(Controlador control){
		
		observadores = new LinkedList<ObservadorPantalla>();
		
		principal = new PantallaPrincipal(this);
		usuario = new PantallaUsuario(this);
		controlador = control;
		principal.setVisible(true);
		principal.setLocation(200, 200);
		usuario.setVisible(false);
		
		initGui();
	}
	
	private void initGui(){
		
		this.setSize(800, 600);
		this.setLocation(100, 100);
		
		JDesktopPane panel = new JDesktopPane();
		setContentPane(panel);
		panel.add(principal);
		panel.add(usuario);
	}
	
	public void iniciar(short modo, String nombre, String ip, int digitos){
		
		if (modo == MODO_SERVER){
			controlador.iniciarServer(digitos);
		}
		else if (modo == MODO_CLIENTE){
			controlador.iniciarCliente(ip, nombre);
		}
	}
	
	public void guess(String text) {
		this.controlador.enviarGuess(text);
		
	}

	public void setNumero(String text) {
		
		this.controlador.setNumero(text);		
	}

	public void mostrarPantalla(String nombre) {
		
		this.usuario.mostrar(nombre);
		this.principal.setVisible(false);
	}

	public void mostrarResultado(String valor) {
		
		this.usuario.mostrarResultado(valor);
	}

}
