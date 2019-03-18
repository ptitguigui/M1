package tp2.data;

/**
 * @author lepretreg
 *
 */
public class User {

	String username;
	String password;

	/**
	 * Retourne le username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Initialise le username avec le paramètre
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retourne le mot de passe
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Initialise le mot de passe avec le paramètre
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
