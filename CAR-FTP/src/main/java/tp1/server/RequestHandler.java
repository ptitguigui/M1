package tp1.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import tp1.commands.Command;
import tp1.commands.CommandUnknown;
import tp1.commands.CommandPass;
import tp1.commands.CommandUser;
import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;
import tp1.utils.RequestMessage;

public class RequestHandler implements Runnable {

	private Socket client;
	private HashMap<String, Command> commands;
	private RequestMessage requestMessage;
	private BufferedReader in;
	private String currentDirectory;
	private boolean isConnected;
	private ConfigurationServer configServer;
	private ConfigurationClient configClient;
	private String login;

	RequestHandler(Socket client) throws Exception {
		this.client = client;
		initHashMap();
		this.requestMessage = new RequestMessage(new DataOutputStream(this.client.getOutputStream()));
		this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.currentDirectory = "/"; // System.GETproperty(user.dir)
		this.isConnected = true;
		this.configServer = new ConfigurationServer();
		this.configClient = new ConfigurationClient();
	}

	private void initHashMap() throws IOException {
		commands = new HashMap<>();
		DataOutputStream dataOutputStream = new DataOutputStream(this.client.getOutputStream());
		commands.put("UK", new CommandUnknown(dataOutputStream));
		commands.put("USER", new CommandUser(dataOutputStream));
		commands.put("PASS", new CommandPass(dataOutputStream));
	}

	@Override
	public void run() {
		try {
			this.requestMessage.sendMessage(RequestMessage.CODE_220);
			while (this.isConnected) {
				evaluateMessageFromClient();
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

	private void evaluateMessageFromClient() throws IOException {
		String clientMessage = this.in.readLine();
		System.out.println(clientMessage);
		Command command = this.commands.get(clientMessage.split(" ")[0]);
		if (command != null) {
			command.execute(clientMessage, this.configClient, this.configServer);
		} else {
			this.commands.get("UK").execute(clientMessage, configClient, configServer);
		}

		/*
		 * switch (command) { case "USER": processUSER(clientMessage); break; case
		 * "PASS": processPASS(clientMessage); break; case "PWD":
		 * this.requestMessage.sendMessage(RequestMessage.CODE_200.replace("DIRECTORY",
		 * this.currentDirectory)); break; case "QUIT": this.isConnected = false;
		 * this.requestMessage.sendMessage(RequestMessage.CODE_221); break; case "TYPE":
		 * this.requestMessage.sendMessage(RequestMessage.CODE_200.replace("DIRECTORY",
		 * this.currentDirectory)); default:
		 * this.requestMessage.sendMessage(RequestMessage.CODE_502); break; }
		 */
	}

}
