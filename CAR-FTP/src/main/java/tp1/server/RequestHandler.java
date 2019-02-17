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

/**
 * Class which handles the commands from the FTP client
 *
 * @author irakoze & lepretre
 */
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

    /**
     * Initiate Hashmap listing Commands as keys and the functions to be executed as values
     *
     * @throws IOException
     */
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
        commands.put("EPSV", new CommandExtendPassive(dataOutputStream));
        commands.put("PORT", new CommandPort(dataOutputStream));
        commands.put("LIST", new CommandList(dataOutputStream));
        commands.put("QUIT", new CommandQuit(dataOutputStream));
        commands.put("RETR", new CommandRetrieve(dataOutputStream));
        commands.put("STOR", new CommandStore(dataOutputStream));
        commands.put("DELE", new CommandDelete(dataOutputStream));
        commands.put("MKD", new CommandMkd(dataOutputStream));
        commands.put("RMD", new CommandRmd(dataOutputStream));
        commands.put("RNFR", new CommandRenameFrom(dataOutputStream));
        commands.put("RNTO", new CommandRenameTo(dataOutputStream));
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
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

    /**
     * Evaluate  the validity of the command from the client
     * And execute the Command
     *
     * @throws Exception
     */
    private void evaluateMessageFromClient() throws Exception {
        String clientMessage = this.in.readLine();
        if (clientMessage != null) {
            System.out.println(clientMessage);
            Command command = this.commands.get(clientMessage.split(" ")[0]);
            if (command != null) {
                command.execute(clientMessage, this.configClient, this.configServer);
            } else {
                this.commands.get("UK").execute(clientMessage, configClient, configServer);
            }
        } else {
            System.out.println("Client Disconnected");
            this.isConnected = false;
        }
    }

}
