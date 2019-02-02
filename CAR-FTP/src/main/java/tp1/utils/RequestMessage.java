package tp1.utils;

import java.io.DataOutputStream;

public class RequestMessage {

    public static final String CODE_125 = "125 Data connection already open; transfer starting.\r\n";
    public static final String CODE_150 = "150 File status okay; about to open data connection.\r\n";
    public static final String CODE_200 = "200 Directory changed to \"DIRECTORY\"\r\n";
    public static final String CODE_215 = "215 Unix system type.\r\n";
    public static final String CODE_220 = "220 Service ready for new user.\r\n";
    public static final String CODE_221 = "221 Service closing control connection.\r\n";
    public static final String CODE_226 = "226 Closing data connection.\r\n";
    public static final String CODE_227 = "227 Entering Passive Mode (127,0,0,1,PORT_DIVIDED,PORT_MODULO).\r\n";
    public static final String CODE_230 = "230 User logged in, proceed.\r\n";
    public static final String CODE_257 = "257 \"DIRECTORY\"\r\n";
    public static final String CODE_229 = "229 Entering Extended Passive Mode (|||PORT|)\r\n";
    public static final String CODE_331 = "331 User name okay, need password.\r\n";
    public static final String CODE_332 = "332 Need account for login.\r\n";
    public static final String CODE_502 = "502 Command not implemented.\r\n";
    public static final String CODE_530 = "530 Not logged in.\r\n";

    private DataOutputStream dataOutputStream;

    public RequestMessage(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void sendMessage(String message) {
        System.out.println("Send the message : " + message);
        try {
            this.dataOutputStream.writeBytes(message);
            this.dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
