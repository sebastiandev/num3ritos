package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Controlador {

	protected NServer server = null;
	protected NCliente cliente = null;
	protected int digitos = 0;
	protected String numero;
	protected LinkedList<ObservadorControlador> observadores;
	
	public Controlador(){
		 observadores = new LinkedList<ObservadorControlador>();
	}

	public void addObservador(ObservadorControlador ob){
		observadores.add(ob);
	}
	
	public void setDigitos(int digitos){
		this.digitos = digitos;
	}
	public void setNumero(String numero){
		this.numero = numero;
	}
	
	public void iniciarServer(int digitos){
		
		server = new NServer(this);
		
		this.digitos = digitos;
		try {
			server.iniciar(digitos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void iniciarCliente(String ip, String nombre){
		
		cliente = new NCliente(this, ip, nombre);
		
		try {
			cliente.iniciar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarPantalla(String nombre){
		
		observadores.getFirst().mostrarPantalla(nombre);
	}
	
	public void enviarGuess(String valor){
		try {
			this.server.sendGuess(valor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String validar(String valor) {
		
		int bien = 0;
		int mm = 0;
		ArrayList<Character> aciertos = new ArrayList<Character>();
		
		for (int i=0; i<numero.length(); i++){
			
			for (int j=0; j<numero.length(); j++){
			
				if (numero.charAt(i) == valor.charAt(j)){
					
					if (i == j){
						bien++;
						aciertos.add(valor.charAt(j));
					}
					else if (!aciertos.contains(valor.charAt(j))){
						mm++;
						aciertos.add(valor.charAt(j));
					}
				}
			}	
		}
		
		String resul = null;
		if (bien>0 || mm>0){
			if (bien>0){
				resul.concat(String.valueOf(bien)+server.MSG_BIEN);
			}
			if (mm>0){
				resul.concat(String.valueOf(mm)+server.MSG_MM);
			}
			return resul;
		}
		else
			return server.MSG_NADA;
	}

	public void mostrarResultado(String valor) {
		// TODO Auto-generated method stub
		
	}
	
	
        

}
