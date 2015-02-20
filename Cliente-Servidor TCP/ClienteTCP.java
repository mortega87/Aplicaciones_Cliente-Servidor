//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		String buferEnvio;
		String host="localhost";
		int port=8989;
		Socket socketServicio=null;
                PrintWriter out = null;
                BufferedReader in = null;
		
		try {
                        //Se inicializan los flujos de E/S y el socket
			socketServicio= new Socket(host, port);		
			out = new PrintWriter(socketServicio.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
                        
			buferEnvio="Al monte del volcán debes ir sin demora";  

                        //Enviamos la frase al servidor y la muestro por pantalla
                        out.println(buferEnvio);
                        System.out.println(buferEnvio);
                        out.flush();

                        //Recogemos el mensaje del servidor y lo mostramos por pantalla
                        String resultado = in.readLine();
                        System.out.println(resultado);
                        
                        //Se cierran todos los flujos de E/S y el socket
                        in.close();
                        out.close();
			socketServicio.close();

		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
