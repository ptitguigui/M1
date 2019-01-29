package tp1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {

	private Socket client;

	public RequestHandler(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {

			DataOutputStream out;
			out = new DataOutputStream(this.client.getOutputStream());

			out.writeBytes("220 OK.\r\n");
			out.flush();

			while (true) {

				/*
				 * 
				 * DataOutputStream out; Quand on ne connait pas la requete du client out = new
				 * DataOutputStream(client.getOutputStream()); out.writeBytes("503 OK.\r\n");
				 * out.flush(); // Send off the data
				 */
				getMessageFromClient(this.client);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getMessageFromClient(Socket client) throws IOException {
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Client !");

		String dataRequest = in.readLine();
		System.out.println(dataRequest);
		if (dataRequest.startsWith("AUTH")) {
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			out.writeBytes("502 OK.\r\n");
			out.flush(); // Send off the data
		}

	}
}
