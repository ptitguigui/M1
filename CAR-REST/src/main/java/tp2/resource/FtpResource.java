package tp2.resource;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import tp2.utils.ClientConnector;
import tp2.utils.ConfigClient;
import tp2.utils.File;
import tp2.utils.User;

import javax.ws.rs.*;
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
    private ClientConnector clientConnector;

    public FtpResource() {
        ConfigClient configClient = new ConfigClient();
        clientConnector = ClientConnector.getClientConnector();
        DEFAULT_DIRECTORY = configClient.getDirectory();
    }

    @POST
    @Path("connect")
    public Response connect(User user) throws IOException {
        FTPClient ftp = clientConnector.getFTPClient();
        clientConnector.connect(user.getUsername(), user.getPassword());

        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.clientConnector.close();
            return Response.ok("Mauvais login/mdp").build();
        }
        return Response.ok("Client connecté").build();
    }

    @GET
    @Path("disconnect")
    public Response disconnect() throws IOException {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        ftp.quit();
        return Response.ok("Client déconnecté").build();
    }

    /**
     * Permet de lancer la commande LIST du serveur FTP
     */
    @GET
    @Path("list")
    public Response listFiles() {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        try {
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            StringBuilder filesDisplay = getDetailsFiles(files);

            return Response.ok(filesDisplay.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Permet de lancer la commande LIST du serveur FTP
     *
     * @param file permet de spécifier le répertoir voulu
     * @return Response du server
     */
    @POST
    @Path("list")
    public Response listFiles(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        try {
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles(file.getServerPath());
            StringBuilder filesDisplay = getDetailsFiles(files);

            return Response.ok(filesDisplay.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("cwd/{directory}")
    public Response doCWD(@PathParam("directory") String directory) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            if (ftp.cwd(directory) == 200) {
                return Response.ok("Déplacer vers " + directory).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du déplacement du répertoire " + directory).build();
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
     * Permet de télécharger un fichier se trouvant dans le repertoire courant du FTP
     *
     * @param filename
     * @return
     */
    @GET
    @Path("download/{filename}")
    public Response getFile(@PathParam("filename") String filename) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            FileOutputStream fos = new FileOutputStream(DEFAULT_DIRECTORY + filename);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.retrieveFile(filename, fos);
            if (fos != null) {
                fos.close();
                return Response.ok("Fichier transféré avec succès").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du téléchargement du fichier").build();
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
    public Response getFile(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

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
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du téléchargement du fichier").build();
    }

    /**
     * Upload un fichier du serveur se trouvant dans le repertoire par défaut
     * vers le repertoire courant du serveur FTP
     *
     * @param filename
     * @return
     */
    @GET
    @Path("upload/{filename}")
    public Response upload(@PathParam("filename") String filename) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            FileInputStream fileUpload = new FileInputStream(DEFAULT_DIRECTORY + filename);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (ftp.storeFile(filename, fileUpload)) {
                fileUpload.close();
                return Response.ok("Fichier upload avec succès").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du téléchargement du fichier").build();
    }

    /**
     * Permet d'upload un fichier vers le serveur FTP Il faut spécifier le
     *
     * @param file
     * @return Response du serveur
     * @throws Exception
     */
    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upload(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            String clientPath = getClientPath(file);
            FileInputStream fileUpload = new FileInputStream(clientPath);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (ftp.storeFile(file.getServerPath() + file.getFilename(), fileUpload)) {
                fileUpload.close();
                return Response.ok("Fichier upload avec succès").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors de l'upload du fichier").build();
    }

    /**
     * Methode qui donne le chemin du fichier du client Si rien n'est spécifier,
     * alors on définis le repertoir par défaut
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
