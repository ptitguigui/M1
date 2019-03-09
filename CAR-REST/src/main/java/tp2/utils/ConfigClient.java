package tp2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConfigClient {

    private String address;
    private int port;
    private String username;
    private String password;
    private String directory;
    private File fileConf;

    public ConfigClient() {
        this.fileConf = new File(System.getProperty("user.dir") + "/fileConf.txt");
        this.getInformationFromFile();
    }

    /**
     * This method get all informations from file configuration And initialize
     * login/mdp
     */
    private void getInformationFromFile() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileConf));
            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(":");
                switch (data[0].trim()) {
                    case "server":
                        this.address = data[1].trim();
                        this.port = Integer.parseInt(data[2].trim());
                        break;
                    case "username":
                        this.username = data[1].trim();
                        break;
                    case "password":
                        this.password = data[1].trim();
                        break;

                    case "directory":
                        this.directory = System.getProperty("user.dir") + data[1].trim();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter of directory
     *
     * @return directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Getter of address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter of port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter of username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

}
