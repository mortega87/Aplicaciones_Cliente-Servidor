//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcesadorYodafy {
	
	private Random random;
        DatagramPacket paquete;
        byte[] Datos = new byte[256];
        DatagramSocket serverSocket = null;
	
	public ProcesadorYodafy(DatagramPacket paquete, DatagramSocket serverSocket) {
		this.paquete=paquete;
                this.serverSocket=serverSocket;
		random=new Random();
	}
	
	void procesa(){
            
            try {
                
                //Se obtiene el mensaje del datagrama para procesarlo
                String peticion = new String (paquete.getData(), 0, paquete.getLength());
                String respuesta=yodaDo(peticion);
                
                //Se crea un datagrama en respuesta y se envia al cliente
                Datos = respuesta.getBytes();
                DatagramPacket DatosAEnviar = new DatagramPacket(Datos, Datos.length, paquete.getAddress(), paquete.getPort());
                serverSocket.send(DatosAEnviar);
                
            } catch (IOException ex) {
                Logger.getLogger(ProcesadorYodafy.class.getName()).log(Level.SEVERE, null, ex);
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
