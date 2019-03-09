package tp2.utils;


public class File {
    private String serverPath;
    private String clientPath;
    private String filename;

    public File() {
    }

    /**
     * Getter clientPath of file object
     *
     * @return clientPath
     */
    public String getClientPath() {
        return clientPath;
    }

    /**
     * Getter serverPath of file object
     *
     * @return serverPath
     */
    public String getServerPath() {
        return serverPath;
    }

    /**
     * Getter filename of file object
     *
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter serverPath of file object
     *
     * @param serverPath
     */
    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    /**
     * Setter clientPath of file object
     *
     * @param clientPath
     */
    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    /**
     * Setter filename of file object
     *
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
