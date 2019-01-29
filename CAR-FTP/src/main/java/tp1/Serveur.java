package tp1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	private ServerSocket socket;

	public Serveur(int port) throws IOException {
		this.socket = new ServerSocket(port);
	}

	public void connexion(String path) throws IOException {
		try {
			System.out.println("En attente d'un client... ");
			while (true) {
				Socket client = this.socket.accept();
								
				System.out.println("Un client est entrée dans le serveur");
				RequestHandler request = new RequestHandler(client);
				request.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.socket.close();
		}
	}


	public static void main(String[] args) {
		try {
			Serveur server = new Serveur(1025);
			server.connexion("../../../myFTPDirectory/");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
