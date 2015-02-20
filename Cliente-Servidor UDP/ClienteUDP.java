//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class YodafyClienteUDP {
    
    public static void main(String[] args) {
        
        String host="localhost";
        String datos;
        int port = 8989;
        InetAddress direccion;
        byte[] Datos = new byte[256];
        byte[] DatosRecibidos = new byte[256];
        DatagramSocket socket = null;
        
        try {
            
            datos = "Al monte del volcán debes ir sin demora";
            System.out.println(datos);
            
            socket = new DatagramSocket ();
            direccion = InetAddress.getByName(host);
            
            //Se crea y envia el paquete al servidor
            Datos = datos.getBytes();
            DatagramPacket PaqueteAEnviar = new DatagramPacket (Datos, Datos.length, direccion, port);
            socket.send(PaqueteAEnviar);
            
            //Se crea el datagrama que contendra la respuesta del servidor
            DatagramPacket PaqueteRecibido = new DatagramPacket (DatosRecibidos, DatosRecibidos.length);
            
            //Se espera la respuesta del servidor y se muestra
            socket.receive (PaqueteRecibido);
            datos = new String (PaqueteRecibido.getData(), 0, PaqueteRecibido.getLength());
            System.out.println (datos );
            
        } catch (SocketException ex) {
            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        socket.close();
        
    }
}
