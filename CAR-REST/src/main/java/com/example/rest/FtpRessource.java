package com.example.rest;

import org.apache.commons.net.ftp.FTPClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Root ftp (exposed at "ftpRessource" path)
 */
@Path("ftp")
public class FtpRessource {
    private FTPClient ftp;

    public FtpRessource() throws Exception {
        String address = "127.0.1.1";
        int port = 1025;
        String username = "guillaume";
        String password = "mdp123";
        Client client = new Client(address, port, username, password);
        client.connect();
        this.ftp = client.getClient();
    }

    @GET
    @Path("syst")
    /**
     * Permet de lancer la commande SYST du server ftp
     */
    public String doSyst() throws IOException {
        try {
            this.ftp.syst();
        } catch (Exception e) {
            this.ftp.disconnect();
            e.printStackTrace();
        }
        return this.ftp.getReplyString();
    }


    @GET
    @Produces("application/octet-stream")
    @Path("download/{filename :.*}")
    /**
     * Permet de télécharger le fichier sélectionner par l'utilisateur
     */
    public String getFile(@PathParam("filename") String filename) throws Exception {
        try {
            String directory = System.getProperty("user.dir")+"/files/";
            FileOutputStream fos = new FileOutputStream(directory+ filename);
            ftp.enterLocalPassiveMode();
            ftp.retrieveFile(filename, fos);
            if (fos != null) {
                fos.close();
                return "fichier téléchargé avec succès";
            }else{
                return "Erreur lors du téléchargement du fichier :" + filename+ "Reponse du server :"+ftp.getReplyString();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }finally {
            ftp.disconnect();
        }
        return null;
    }


    @GET
    @Path("quit")
    /**
     * Permet de lancer la commande QUIT du server ftp
     */
    public String doQuit() throws Exception {
        try {
            this.ftp.quit();
            return this.ftp.getReplyString();
        } catch (Exception e) {
            this.ftp.disconnect();
            e.printStackTrace();
        } finally {
            ftp.disconnect();
        }
        return null;
    }
}
