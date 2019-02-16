package tp1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.util.HashMap;

public class ConfigurationServer {

    private File fileConf;
    private String currentDirectory;
    private HashMap<String, String> users;
    private ServerSocket transferServerSocket;

    public ConfigurationServer(ServerSocket transfertServerSocket) {
        this.transferServerSocket = transfertServerSocket;
        this.fileConf = new File(System.getProperty("user.dir")+"/fileConf.txt");
        this.currentDirectory = System.getProperty("user.dir")+"/myFTPDirectory";
        users = new HashMap<>();
        getInformationsFromFile();
    }

    /**
     * This method get all informations from file configuration And initialize
     * login/mdp
     */
    private void getInformationsFromFile() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileConf));
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
                String[] tmp = line.split(" ");
                this.users.put(tmp[0], tmp[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public ServerSocket getTransferServerSocket() {
        return transferServerSocket;
    }
}
