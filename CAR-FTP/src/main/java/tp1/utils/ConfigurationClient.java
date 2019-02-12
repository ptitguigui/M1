package tp1.utils;

public class ConfigurationClient {

    private String login;
    private boolean loggedIn;
    private boolean passiveMode;

    public ConfigurationClient() {
        this.loggedIn = false;
        this.passiveMode = true;
    }

    public boolean isPassiveMode() {
		return passiveMode;
	}

	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
