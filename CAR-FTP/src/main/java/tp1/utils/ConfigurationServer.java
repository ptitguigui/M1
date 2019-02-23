package tp1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Class to handle the informations on the server like (users end passwords)
 *
 * @author irakoze & lepretre
 */
public class ConfigurationServer {

    private File fileConf;
    private String rootDirectory;
    private String currentDirectory;
    private HashMap<String, String> users;
    private ServerSocket transferServerSocket;
    private Socket socketTransfer;

    /**
     * ConfigurationServer builder. Initialize the attributes of the
     * ConfigurationServer
     *
     * @param transfertServerSocket ServerSocket created to handle files transfer
     *                              between the server and the client
     */
    public ConfigurationServer(ServerSocket transfertServerSocket) {
        this.transferServerSocket = transfertServerSocket;
        this.fileConf = new File(System.getProperty("user.dir") + "/fileConf.txt");
        String directory = System.getProperty("user.dir") + "/myFTPDirectory";
        this.rootDirectory = directory;
        this.currentDirectory = directory;
        users = new HashMap<>();
        getInformationFromFile();
    }

    /**
     * This method get all informations from file configuration And initialize
     * login/mdp
     */
    private void getInformationFromFile() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileConf));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(" ");
                this.users.put(tmp[0], tmp[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return Hashmap all the users and their password known by the server
     *
     * @return users Hashmap
     */
    public HashMap<String, String> getUsers() {
        return users;
    }

    /**
     * Getter of the current directory
     *
     * @return currentDirectory the current directory
     */
    public String getCurrentDirectory() {
        return this.currentDirectory;
    }

    /**
     * Set the current directory
     *
     * @param currentDirectory a string to specify the current directory
     */
    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    /**
     * Getter of the root directory
     *
     * @return rootDirectory the root directory
     */
    public String getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Set a new transfer socketTransfer when is actif
     * @param socketTransfer the new socket
     */
    public void setSocketTransfer(Socket socketTransfer) {
        this.socketTransfer = socketTransfer;
    }

    /**
     * Getter of the socketTransfer used to handle file transfer when active
     * @return
     */
    public Socket getSocketTransfer() {
        return socketTransfer;
    }

    /**
     * Getter of the ServerSocket used to handle file transfer when passive
     *
     * @return transferServerSocket
     */
    public ServerSocket getTransferServerSocket() {
        return transferServerSocket;
    }
}
