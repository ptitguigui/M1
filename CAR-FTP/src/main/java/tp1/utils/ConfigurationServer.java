package tp1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class ConfigurationServer {

	private File fileConf ;
	private HashMap <String, String> users;

	public ConfigurationServer() {
		this.fileConf = new File("src/../fileConf.txt");
		users = new HashMap<String, String>();
		getInformationsFromFile();
	}
	
	/**
	 * This method get all informations from file configuration
	 * And initialize login/mdp
	 */
	private void getInformationsFromFile() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileConf));
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				String[] tmp = line.split(" ");
				System.out.println(tmp[0]+" "+tmp[1]);
				this.users.put(tmp[0], tmp[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getUsers() {
		return users;
	}
	
	
}
