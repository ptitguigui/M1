package tp1.utils;

/**
 * Class to handle the logged status (logged in or not) of the client.
 * It contains also information about the mode used in the
 * connection Client - Server
 * 
 * @author irakoze & lepretre
 *
 */
public class ConfigurationClient {

	private String login;
	private boolean loggedIn;
	private boolean passiveMode;

	/**
	 * ConfigurationClient builder. Initialize attributes of ConfigurationClient.
	 */
	public ConfigurationClient() {
		this.loggedIn = false;
		this.passiveMode = true;
	}

	/**
	 * Return boolean.True if the current mode of the connection (client - server)
	 * is passive and False if not.
	 * 
	 * @return passiveMode boolean
	 */
	public boolean isPassiveMode() {
		return passiveMode;
	}

	/**
	 * Set the connection mode of the client to the server
	 * 
	 * @param passiveMode boolean to specify if the connection is in passive mode or
	 *                    not
	 */
	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	/**
	 * Getter of the name used as login
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the value of the login
	 * 
	 * @param login the name used as login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Return a boolean. True if the client is logged in and False if not.
	 * 
	 * @return loggedIn the client logged status
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Set the client logged status to True or False
	 * 
	 * @param loggedIn the current logged status of the client
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
