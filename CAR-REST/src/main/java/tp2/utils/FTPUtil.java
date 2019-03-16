package tp2.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FTPUtil {


    /**
     * Methode qui affiche le détail des fichiers
     *
     * @param files l'ensemble des fichiers
     * @return StringBuilder le detail des fichiers
     */
    public static StringBuilder getDetailsFiles(FTPFile[] files) {
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
     * Methode qui télécharge un fichier dans le FTP
     *
     * @param ftpClient
     * @param remoteFilePath chemin du fichier dans le serveur
     * @param savePath       chemin du dossier où se trouve le fichier
     * @return true si fichier télécharger, false sinon
     * @throws IOException
     */
    public static boolean downloadSingleFile(FTPClient ftpClient,
                                             String remoteFilePath, String savePath) throws IOException {
        File downloadFile = new File(savePath);

        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }

        try (OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(downloadFile))) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        }
    }

    /**
     * Télécharge un dossier complet dans le FTP
     *
     * @param ftpClient
     * @param parentDir  chemin du répertoire parent du répertoire téléchargé
     * @param currentDir chemin du répertoire courant où le fichier se télécharge
     * @param saveDir    chemin du répertoire où le dossier sera sauvegarder
     * @throws IOException
     */
    public static void downloadDirectory(FTPClient ftpClient, String parentDir,
                                         String currentDir, String saveDir) throws IOException {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        FTPFile[] subFiles = ftpClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                System.out.println(aFile.getName());
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = parentDir + "/" + currentDir + "/"
                        + currentFileName;
                if (currentDir.equals("")) {
                    filePath = parentDir + "/" + currentFileName;
                }

                String newDirPath = saveDir + parentDir + File.separator
                        + currentDir + File.separator + currentFileName;
                if (currentDir.equals("")) {
                    newDirPath = saveDir + parentDir + File.separator
                            + currentFileName;
                }

                if (aFile.isDirectory()) {
                    // create the directory in saveDir
                    File newDir = new File(newDirPath);
                    newDir.mkdirs();

                    // download the sub directory
                    downloadDirectory(ftpClient, dirToList, currentFileName, saveDir);
                } else {
                    // download the file
                    downloadSingleFile(ftpClient, filePath, newDirPath);

                }
            }
        }
    }
}
