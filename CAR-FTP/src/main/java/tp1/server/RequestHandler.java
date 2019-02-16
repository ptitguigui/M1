package tp1.server;

import tp1.commands.*;
import tp1.utils.ConfigurationClient;
import tp1.utils.ConfigurationServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RequestHandler implements Runnable {

    private Socket client;
    private HashMap<String, Command> commands;
    private BufferedReader in;
    private boolean isConnected;
    private ConfigurationServer configServer;
    private ConfigurationClient configClient;

    RequestHandler(Socket client, ServerSocket transferServerSocket) throws Exception {
        this.client = client;
        initHashMap();
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.isConnected = true;
        this.configServer = new ConfigurationServer(transferServerSocket);
        this.configClient = new ConfigurationClient();
    }

    private void initHashMap() throws IOException {
        commands = new HashMap<>();
        DataOutputStream dataOutputStream = new DataOutputStream(this.client.getOutputStream());
        commands.put("UK", new CommandUnknown(dataOutputStream));
        commands.put("READY", new CommandReady(dataOutputStream));
        commands.put("USER", new CommandUser(dataOutputStream));
        commands.put("PASS", new CommandPass(dataOutputStream));
        commands.put("SYST", new CommandSyst(dataOutputStream));
        commands.put("PWD", new CommandPwd(dataOutputStream));
        commands.put("CWD", new CommandCwd(dataOutputStream));
        commands.put("TYPE", new CommandType(dataOutputStream));
        commands.put("PASV", new CommandPassive(dataOutputStream));
        commands.put("PORT", new CommandPort(dataOutputStream));
        commands.put("LIST", new CommandList(dataOutputStream));
        commands.put("QUIT", new CommandQuit(dataOutputStream));
        commands.put("RETR", new CommandRetrieve(dataOutputStream));
        commands.put("STOR", new CommandStore(dataOutputStream));
        commands.put("DELE", new CommandDelete(dataOutputStream));
    }

    @Override
    public void run() {
        try {
            this.commands.get("READY").execute("", this.configClient, this.configServer);

            while (this.isConnected) {
                evaluateMessageFromClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void evaluateMessageFromClient() throws Exception {
        String clientMessage = this.in.readLine();
        System.out.println(clientMessage);
        Command command = this.commands.get(clientMessage.split(" ")[0]);
        if (command != null) {
            command.execute(clientMessage, this.configClient, this.configServer);
        } else {
            this.commands.get("UK").execute(clientMessage, configClient, configServer);
        }
    }

}
