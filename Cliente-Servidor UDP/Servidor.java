import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class YodafyServidor {
    public static void main(java.lang.String[] args) {
        
        int port = 8989;
        byte[] Datos = new byte[256];
        DatagramSocket serverSocket = null;
           
        try {
            
            serverSocket = new DatagramSocket (port);
            
            //Creamos el paquete que contendra el mensaje
            DatagramPacket PaqueteRecibido = new DatagramPacket(Datos, Datos.length);

            //Espera la llegada del datagrama
            serverSocket.receive(PaqueteRecibido);
            
            //Una vez obtenido el mensaje lo envia a procesar
            ProcesadorYodafy procesador=new ProcesadorYodafy(PaqueteRecibido, serverSocket);
            procesador.procesa();

        } catch (SocketException ex) {
            Logger.getLogger(YodafyServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YodafyServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}
