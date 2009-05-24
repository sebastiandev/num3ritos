package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NServer {
	
	protected static final String MSG_LOGIN = "LOGIN?";
	protected static final String MSG_DIGIT = "DIGIT?";
	protected static final String MSG_GUESS = "GUESS?";
	protected static final String MSG_RESULT = "RESULT?";
	protected static final String MSG_BIEN = "@";
	protected static final String MSG_MM = "@";
	protected static final String MSG_NADA = "#";
	
	protected ServerSocket server = null;
    protected Socket clientSocket = null;
    protected Controlador controlador = null;
    protected PrintWriter out; 
    protected BufferedReader in; 

	public NServer(Controlador controlador){
		this.controlador = controlador;
	}
	
	public void iniciar(int digitos) throws IOException{
		
        
        boolean conexion = false;
        
        server = new ServerSocket(4444);
        
        //while (!conexion){
                
        	clientSocket = server.accept();
        	conexion = true;
        //}
        
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        out.println(MSG_DIGIT+String.valueOf(digitos));
        listen();
	}
	
	public void sendGuess(String guess) throws IOException{
		out.println(MSG_GUESS+guess);
		listen();
	}
	
	public void sendResult(String resul){
		out.println(MSG_RESULT+resul);
	}
	
	private void listen() throws IOException{
		
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			
			String clave = inputLine.split("@")[0];
			String valor = inputLine.split("@")[1];
			if (clave == MSG_LOGIN){
				iniciarPantalla(valor);
			}
			if (clave == MSG_GUESS){
				checkGuess(valor);
			}			
			if (clave == MSG_RESULT){
				showResult(valor);
			}
		}
	}
	
	private void iniciarPantalla(String nombreJugador){
		
		this.controlador.mostrarPantalla(nombreJugador);
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
