package tp1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.util.HashMap;

/**
 * Class to handle the
 * 
 * @author irakoze & lepretre
 *
 */
public class ConfigurationServer {

	private File fileConf;
	private String currentDirectory;
	private HashMap<String, String> users;
	private ServerSocket transferServerSocket;

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
		this.currentDirectory = System.getProperty("user.dir") + "/myFTPDirectory";
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
		return currentDirectory;
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
	 * Getter of the ServerSocket used to handle file transfer
	 * 
	 * @return transferServerSocket
	 */
	public ServerSocket getTransferServerSocket() {
		return transferServerSocket;
	}
}
