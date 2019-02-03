package tp1.server;

import tp1.utils.RequestMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket client;
    private RequestMessage requestMessage;
    private BufferedReader in;
    private String currentDirectory;
    private boolean isConnected;

    RequestHandler(Socket client) throws Exception {
        this.client = client;
        this.requestMessage = new RequestMessage(new DataOutputStream(this.client.getOutputStream()));
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.currentDirectory = "/";
        this.isConnected = true;
    }

    @Override
    public void run() {
        try {
            this.requestMessage.sendMessage(RequestMessage.CODE_220);
            while (this.isConnected) {
                evaluateMessageFromClient();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void evaluateMessageFromClient() throws IOException {
        String line = this.in.readLine();
        System.out.println(line);
        String command = line.split(" ")[0];
        switch (command) {
            case "USER":
                this.requestMessage.sendMessage(RequestMessage.CODE_230);
                break;
            case "PWD":
                this.requestMessage.sendMessage((RequestMessage.CODE_257.replace("DIRECTORY", this.currentDirectory)));
                break;
            case "QUIT":
                this.isConnected = false;
                this.requestMessage.sendMessage(RequestMessage.CODE_221);
                break;
            case "TYPE":
                this.requestMessage.sendMessage(RequestMessage.CODE_200.replace("DIRECTORY", this.currentDirectory));
            default:
                this.requestMessage.sendMessage(RequestMessage.CODE_502);
                break;
        }
    }
}
