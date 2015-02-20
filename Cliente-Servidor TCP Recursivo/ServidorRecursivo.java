import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {
    
    public static class TCPThread extends Thread{

        Socket socketServicio;
        
        public TCPThread(Socket Cliente){
            socketServicio = Cliente;
            this.start();
        }

        public void run(){
            ProcesadorYodafy procesador=new ProcesadorYodafy(this.socketServicio);
            procesador.procesa();
        }

    }

	public static void main(String[] args) throws IOException {
	
		int port=8989;
		ServerSocket serverSocket=null;
		Socket socketServicio = null;
		
		try {
			serverSocket= new ServerSocket(port);
			
			do {
				//Se aceptan todos los servicios TCP y se les asigna a cada uno una hebra
				socketServicio= serverSocket.accept();
				TCPThread miThread = new TCPThread(socketServicio);
                                
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
                        socketServicio.close();
		}

	}

    
}
