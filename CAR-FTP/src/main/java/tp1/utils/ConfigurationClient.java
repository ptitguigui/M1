package tp1.utils;

public class ConfigurationClient {

    private String login;
    private boolean loggedIn;

    public ConfigurationClient() {
        this.loggedIn = false;
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
