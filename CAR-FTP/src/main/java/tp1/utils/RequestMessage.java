package tp1.utils;

import java.io.DataOutputStream;
import java.net.InetAddress;

/**
 * Class which associate messages to send with the request from the FTP server to the client.
 *
 * @author irakoze & lepretre
 */
public class RequestMessage {

    public static final String CODE_125 = "125 Data connection already open; transfer starting.\r\n";
    public static final String CODE_150 = "150 File status okay; about to open data connection.\r\n";
    public static final String CODE_200 = "200 OK \r\n";
    public static final String CODE_215 = "215 Unix system type.\r\n";
    public static final String CODE_220 = "220 Service ready for new user.\r\n";
    public static final String CODE_221 = "221 Service closing control connection.\r\n";
    public static final String CODE_226 = "226 Closing data connection.\r\n";
    public static final String CODE_227 = "227 Entering Passive Mode (127,0,0,1,PORT_DIVIDED,PORT_MODULO).\r\n";
    public static final String CODE_230 = "230 User logged in, proceed.\r\n";
    public static final String CODE_250 = "250 Requested file action okay, completed. \r\n";
    public static final String CODE_257 = "257 \"DIRECTORY\"\r\n";
    public static final String CODE_229 = "229 Entering Extended Passive Mode (|||PORT|)\r\n";
    public static final String CODE_331 = "331 User name okay, need password.\r\n";
    public static final String CODE_332 = "332 Need account for login.\r\n";
    public static final String CODE_350 = "350 ready to rename.\r\n";
    public static final String CODE_421 = "421 Home directory not available - aborting \r\n";
    public static final String CODE_450 = "450 Requested file action not taken.\r\n";
    public static final String CODE_500 = "500 Syntax error, command unrecognized.\r\n";
    public static final String CODE_501 = "501 Syntax error in parameters or argument.\r\n";
    public static final String CODE_502 = "502 Command not implemented.\r\n";
    public static final String CODE_530 = "530 Not logged in.\r\n";
    public static final String CODE_550 = "550 No such file or directory.\r\n";

    private DataOutputStream dataOutputStream;

    /**
     * RequestMessage builder. It initializes the attribute dataOutputStream
     *
     * @param dataOutputStream the dataOutputStream used to write message.
     */
    public RequestMessage(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    /**
     * Method used by the FTP server to send message to the client.
     *
     * @param message the message to sent
     */
    public void sendMessage(String message) {
        try {
        	if(message.contains("227")) {
        		String hoString = InetAddress.getLocalHost().getHostAddress().replace(".", ",");
        		message = "227 Entering Passive Mode ("+hoString+ message.substring(36);
        	}
        	System.out.println("Send the message : " + message);
            this.dataOutputStream.writeBytes(message);
            this.dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
