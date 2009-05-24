package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NCliente {
	
	protected Socket socket = null;
	protected PrintWriter out = null;
	protected BufferedReader in = null;
	protected Controlador controlador = null;
	protected String nombre;
	protected String ip;
	
	public NCliente(Controlador controlador, String ip, String nombre){
		this.controlador = controlador;
		this.ip = ip;
		this.nombre = nombre;
	}
	
	public void iniciar() throws IOException{
		
		System.out.println("conecta a host: "+ip);
		socket = new Socket(ip, 4444);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   	
		
    	listen();
	}
	
	private void listen() throws IOException{
		
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			
			String clave = inputLine.split("@")[0];
			String valor = inputLine.split("@")[1];
			
			if (clave == NServer.MSG_DIGIT){
				controlador.setDigitos(Integer.parseInt(valor));
				login();
			}
			
			if (clave == NServer.MSG_GUESS){
				checkGuess(valor);
			}
			
			if (clave == NServer.MSG_RESULT){
				showResult(valor);
			}
		}
	}
	
	public void sendGuess(String guess) throws IOException{
		out.println(NServer.MSG_GUESS+guess);
		listen();
	}
	
	public void sendResult(String resul){
		out.println(NServer.MSG_RESULT+resul);
	}
	
	private void login(){
		out.println(NServer.MSG_LOGIN+nombre);
	}
	
	private void checkGuess(String valor){
		
		String resul = this.controlador.validar(valor);
		sendResult(resul);
	}
	
	private void showResult(String valor) throws IOException{
		
		this.controlador.mostrarResultado(valor);
		listen();
	}
	
}
