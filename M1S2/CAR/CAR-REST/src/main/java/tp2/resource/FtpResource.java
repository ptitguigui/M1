package tp2.resource;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import tp2.data.File;
import tp2.data.User;
import tp2.utils.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("ftp")
public class FtpResource {

    private static String DEFAULT_DIRECTORY;
    private ClientConnector clientConnector;

    /**
     * Constructeur Ftpresource qui initialise la Configuration Client
     */
    public FtpResource() {
        ConfigClient configClient = new ConfigClient();
        clientConnector = ClientConnector.getClientConnector();
        DEFAULT_DIRECTORY = configClient.getDirectory();
    }

    /**
     * Methode de connection au serveur FTP à travers la passerelle
     *
     * @param user
     * @return Réponse du serveur
     * @throws IOException
     */
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

    /**
     * Methode de déconnection sur le serveur FTP à travers la passerelle
     *
     * @return un message
     * @throws IOException
     */
    @GET
    @Path("disconnect")
    public Response disconnect() throws IOException {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        ftp.disconnect();
        return Response.ok("Client déconnecté").build();
    }

    /**
     * Permet de lancer la commande LIST du serveur FTP
     *
     * @return Réponse du serveur
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
            StringBuilder filesDisplay = FTPUtil.getDetailsFiles(files);

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
     * @return Réponse du serveur
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
            StringBuilder filesDisplay = FTPUtil.getDetailsFiles(files);

            return Response.ok(filesDisplay.toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Méthode qui permet de changer de répertoire sur le serveur FTP
     *
     * @param directory
     * @return Réponse du serveur
     */
    @GET
    @Path("cwd/{directory}")
    public Response doCWD(@PathParam("directory") String directory) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            int res = ftp.cwd(directory);
            if (res == 200 || res == 250) {
                return Response.ok("Déplacer vers " + directory).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du déplacement du répertoire " + directory).build();
    }

    /**
     * Méthode qui permet de se deéplacer sur le répertoire parent du le serveur FTP
     *
     * @return Réponse du serveur
     */
    @GET
    @Path("cwdParent")
    public Response doCWD() {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            if (ftp.changeToParentDirectory()) {
                return Response.ok("Déplacement effectué avec succès").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors du déplacement du répertoire parent").build();
    }

    /**
     * Permet de télécharger un fichier se trouvant dans le repertoire courant du
     * FTP
     *
     * @param filename
     * @return Réponse du serveur
     */
    @GET
    @Path("download/{filename}")
    public Response download(@PathParam("filename") String filename) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            if (FTPUtil.downloadSingleFile(ftp, filename, DEFAULT_DIRECTORY + "/" + filename)) {
                return Response.ok("Fichier téléchargé avec succès. ").build();
            } else {
                return Response.ok("Erreur lors du téléchargement du fichier. ").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
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
    public Response download(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            String filename = file.getFilename();
            String clientPath = getClientPath(file);
            ftp.enterLocalPassiveMode();

            if (FTPUtil.downloadSingleFile(ftp, file.getServerPath() + filename, clientPath)) {
                return Response.ok("Fichier téléchargé avec succès. ").build();
            } else {
                return Response.ok("Erreur lors du téléchargement du fichier. ").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Upload un fichier du serveur se trouvant dans le repertoire par défaut vers
     * le repertoire courant du serveur FTP
     *
     * @param filename
     * @return Réponse du serveur
     */
    @GET
    @Path("upload/{filename}")
    public Response upload(@PathParam("filename") String filename) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            if (FTPUtil.uploadSingleFile(ftp, DEFAULT_DIRECTORY + "/" + filename, filename)) {
                return Response.ok("Fichier upload avec succès. ").build();
            } else {
                return Response.ok("Erreur lors de l'upload du fichier. ").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Permet d'upload un fichier vers le serveur FTP Il faut spécifier le
     *
     * @param file
     * @return Réponse du serveur
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
            String filename = file.getFilename();
            String clientPath = getClientPath(file);

            ftp.enterLocalPassiveMode();
            if (FTPUtil.uploadSingleFile(ftp, clientPath, file.getServerPath() + filename)) {
                return Response.ok("Fichier upload avec succès. ").build();
            } else {
                return Response.ok("Erreur lors de l'upload du fichier. ").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
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
        if (file.getClientPath() != null && !file.getClientPath().equals("")) {
            pathClient = file.getClientPath() + file.getFilename();
        } else {
            pathClient = DEFAULT_DIRECTORY + file.getFilename();
        }
        System.out.println(pathClient);
        return pathClient;
    }

    /**
     * Méthode qui permet de renommer un fichier sur le serveur FTP à travers la
     * passerelle
     *
     * @param file
     * @return Réponse du serveur
     */
    @PUT
    @Path("rename")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rename(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }

        try {
            ftp.enterLocalPassiveMode();
            if (ftp.rename(file.getFilename(), file.getNewFilename())) {
                return Response.ok("Modification effectué avec succés").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors de la modification").build();
    }

    /**
     * Méthode qui crée un répertoire
     *
     * @param file: le nom du répertoire
     * @return Réponse du serveur
     */
    @POST
    @Path("create")
    public Response createDirectory(File file) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        String directory = file.getFilename();
        try {
            ftp.enterLocalPassiveMode();
            if (ftp.makeDirectory(directory)) {
                return Response.ok("Creation du dossier " + directory + " effectué avec succés").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors de la creation du dossier " + directory).build();
    }

    /**
     * Méthode qui permet de supprimer un fichier sur le serveur FTP
     *
     * @param file: le fichier à supprimer
     * @return Réponse du serveur
     */
    @DELETE
    @Path("removeFile/{filename}")
    public Response removeDirectory(@PathParam("filename") String filename) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        try {
            ftp.enterLocalPassiveMode();
            if (ftp.deleteFile(filename)) {
                return Response.ok("Le Fichier " + filename + " a été supprimé avec succés").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors de la suppression du fichier " + filename).build();
    }

    /**
     * Méthode qui permet de supprimer un répertoire sur le serveur FTP à travers la
     * passerelle
     *
     * @param directory: le dossier à supprimer
     * @return Réponse du serveur
     */
    @DELETE
    @Path("remove/{directory}")
    public Response removeFile(@PathParam("directory") String directory) {
        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        try {
            ftp.enterLocalPassiveMode();
            if (ftp.removeDirectory(directory)) {
                return Response.ok("Le dossier " + directory + " a été supprimé avec succés").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok("Erreur lors de la suppression du dossier " + directory).build();
    }

    /**
     * Télécharge le dossier complet du serveur se trouvant dans le répertoire
     * courant
     *
     * @param directory: le dossier à télécharger
     * @return Réponse du serveur
     */
    @GET
    @Path("downloadDirectory/{directory}")
    public Response downloadDirectory(@PathParam("directory") String directory) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        try {
            ftp.enterLocalPassiveMode();
            if (FTPUtil.downloadDirectory(ftp, directory, "", DEFAULT_DIRECTORY)) {
                return Response.ok("Le dossier a été télécharger avec succès").build();
            } else {
                return Response.ok("Erreur lors du téléchargement du dossier " + directory).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Télécharge le dossier complet du serveur se trouvant dans le répertoire
     * courant
     *
     * @param directory: le dossier à envoyer
     * @return Réponse du serveur
     */
    @GET
    @Path("uploadDirectory/{directory}")
    public Response uploadDirectory(@PathParam("directory") String directory) {

        FTPClient ftp = clientConnector.getFTPClient();
        if (!ftp.isConnected()) {
            return Response.ok("Le client n'est pas connecté").build();
        }
        String localParentDir = DEFAULT_DIRECTORY + directory;
        try {
            ftp.enterLocalPassiveMode();
            if (FTPUtil.uploadDirectory(ftp, directory, localParentDir, "")) {
                return Response.ok("Le dossier a été upload avec succès").build();
            } else {
                return Response.ok("Erreur lors de l'upload du dossier " + directory).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
