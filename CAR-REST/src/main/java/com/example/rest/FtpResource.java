package com.example.rest;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Root ftp (exposed at "ftpRessource" path)
 */
@Path("ftp")
public class FtpResource {
    final private static String DIRECTORY = System.getProperty("user.dir") + "/files/";
    private FTPClient ftp;

    public FtpResource() throws Exception {
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
    public Response doSyst() throws IOException {
        try {
            this.ftp.syst();
            return Response.ok(ftp.getReplyString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }


    @GET
    @Produces("application/octet-stream")
    @Path("download/{filename :.*}")
    /**
     * Permet de télécharger le fichier sélectionner par l'utilisateur
     */
    public Response getFile(@PathParam("filename") String filename) throws Exception {
        try {
            FileOutputStream fos = new FileOutputStream(DIRECTORY + filename);
            ftp.enterLocalPassiveMode();
            ftp.retrieveFile(filename, fos);
            if (fos != null) {
                fos.close();
                return Response.ok("Fichier transférer avec succès").build();
            }
            return Response.serverError().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }

    @GET
    @Path("upload/{filename :.*}")
    public Response upload(@PathParam("filename") String filename) throws IOException {
        try {
            FileInputStream file = new FileInputStream(DIRECTORY + filename);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (this.ftp.storeFile(filename, file)) {
                file.close();
                return Response.ok("Fichier upload avec succès").build();
            }
            return Response.serverError().build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }


    @GET
    @Path("quit")
    /**
     * Permet de lancer la commande QUIT du server ftp
     */
    public Response doQuit() throws Exception {
        try {
            this.ftp.quit();
            return Response.ok(ftp.getReplyString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        } finally {
            ftp.disconnect();
        }
    }
}
