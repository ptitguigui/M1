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
    public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, String savePath) throws IOException {
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
    public static boolean downloadDirectory(FTPClient ftpClient, String parentDir, String currentDir, String saveDir) {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        try {
            FTPFile[] subFiles = ftpClient.listFiles(dirToList);

            if (subFiles != null && subFiles.length > 0) {
                for (FTPFile aFile : subFiles) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Upload un fichier vers le serverur FTP
     *
     * @param ftpClient
     * @param localFilePath  le chemin du fichier local
     * @param remoteFilePath le chemin du fichier à upload sur le serveur
     * @return true si upload false sinon
     * @throws IOException
     */
    public static boolean uploadSingleFile(FTPClient ftpClient,
                                           String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);
        if (!localFile.exists()) {
        	return false;
        }

        try (InputStream inputStream = new FileInputStream(localFile)) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFilePath, inputStream);
        }
    }

    /**
     * Upload un répertoire y comprit ses sous répertoires sur le serveur FTP
     *
     * @param ftpClient
     * @param remoteDirPath   le chemin du repertoire distant
     * @param localParentDir  le chemin du repertoire à upload
     * @param remoteParentDir chemin des potentiels sous-repertoire à upload
     * @throws IOException
     */
    public static boolean uploadDirectory(FTPClient ftpClient, String remoteDirPath, String localParentDir, String remoteParentDir) throws IOException {

        try {
            File localDir = new File(localParentDir);
            File[] subFiles = localDir.listFiles();
            if (subFiles != null && subFiles.length > 0) {
            	ftpClient.makeDirectory(remoteDirPath);
                for (File item : subFiles) {
                    String remoteFilePath = remoteDirPath + "/" + remoteParentDir
                            + "/" + item.getName();
                    if (remoteParentDir.equals("")) {
                        remoteFilePath = remoteDirPath + "/" + item.getName();
                    }

                    if (item.isFile()) {
                        // upload the file
                        String localFilePath = item.getAbsolutePath();
                        uploadSingleFile(ftpClient, localFilePath, remoteFilePath);
                    } else {
                        // create directory on the server
                        ftpClient.makeDirectory(remoteFilePath);

                        // upload the sub directory
                        String parent = remoteParentDir + "/" + item.getName();
                        if (remoteParentDir.equals("")) {
                            parent = item.getName();
                        }
                        localParentDir = item.getAbsolutePath();
                        uploadDirectory(ftpClient, remoteDirPath, localParentDir, parent);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
