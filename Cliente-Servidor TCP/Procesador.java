//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;


public class ProcesadorYodafy {

	private Socket socketServicio;
	
	private Random random;
	
	public ProcesadorYodafy(Socket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	void procesa(){
            
		try {
                        //Se inicializan los flujos de E/S
                        PrintWriter out = new PrintWriter(socketServicio.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
                        
                        //Recogemos la frase del cliente, la procesamos y se envía el mensaje manipulado al cliente
                        String peticion = in.readLine();
			String respuesta=yodaDo(peticion);
                        out.println(respuesta);
			
			
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	private String yodaDo(String peticion) {
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
