package tp2.resource;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import tp2.utils.Client;
import tp2.utils.ConfigClient;
import tp2.utils.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("ftp")
public class FtpResource {

    private static String DEFAULT_DIRECTORY;
    private FTPClient ftp;

    public FtpResource() throws Exception {
        ConfigClient configClient = new ConfigClient();
        Client client = new Client(configClient.getAddress(), configClient.getPort(), configClient.getUsername(), configClient.getPassword());
        DEFAULT_DIRECTORY = configClient.getDirectory();
        client.connect();
        this.ftp = client.getClient();
    }

    /**
     * Permet de lancer la commande LIST du serveur FTP
     *
     * @param file permet de spécifier le répertoir voulu
     * @return Response du server
     * @throws IOException
     */
    @POST
    @Path("list")
    public Response listFiles(File file) throws IOException {
        try {
            this.ftp.enterLocalPassiveMode();
            FTPFile[] files = this.ftp.listFiles(file.getServerPath());
            StringBuilder filesDisplay = getDetailsFiles(files);

            return Response.ok(filesDisplay.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }

    /**
     * Methode qui affiche le détail des fichiers
     *
     * @param files l'ensemble des fichiers
     * @return StringBuilder le detail des fichiers
     */
    private StringBuilder getDetailsFiles(FTPFile[] files) {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder filesDisplay = new StringBuilder();
        for (FTPFile file : files) {
            String details = file.getName();
            if (file.isDirectory()) {
                details = "[" + details + "]";
            }
            details += "\t\t" + file.getSize();
            details += "\t\t" + dateFormatter.format(file.getTimestamp().getTime());
            filesDisplay.append(details).append("\n");
        }
        return filesDisplay;
    }

    /**
     * Permet de télécharger un fichier vers le serveur FTP
     *
     * @param file
     * @return Response du serveur
     * @throws Exception
     */
    @POST
    @Path("download")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFile(File file) throws Exception {
        try {
            String clientPath = getClientPath(file);
            FileOutputStream fos = new FileOutputStream(clientPath);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.retrieveFile(file.getServerPath() + file.getFilename(), fos);
            if (fos != null) {
                fos.close();
                return Response.ok("Fichier transféré avec succès").build();
            }
            return Response.serverError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }

    /**
     * Permet d'upload un fichier vers le serveur FTP
     * Il faut spécifier le
     *
     * @param file
     * @return Response du serveur
     * @throws Exception
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upload(File file) throws Exception {
        try {
            String clientPath = getClientPath(file);
            FileInputStream fileUpload = new FileInputStream(clientPath);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (this.ftp.storeFile(file.getServerPath() + file.getFilename(), fileUpload)) {
                fileUpload.close();
                return Response.ok("Fichier upload avec succès").build();
            }
            return Response.serverError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }

    /**
     * Methode qui donne le chemin du fichier du client
     * Si rien n'est spécifier, alors on définis le repertoir par défaut
     *
     * @param file
     * @return pathClient
     */
    private String getClientPath(File file) {
        String pathClient;
        if (file.getClientPath() == null) {
            pathClient = file.getClientPath() + file.getFilename();
        } else {
            pathClient = DEFAULT_DIRECTORY + file.getFilename();
        }
        return pathClient;
    }
}
