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

    RequestHandler(Socket client) throws Exception {
        this.client = client;
        this.requestMessage = new RequestMessage(new DataOutputStream(this.client.getOutputStream()));
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    }

    @Override
    public void run() {
        try {
            this.requestMessage.sendMessage(RequestMessage.CODE_220);
            evaluateMessageFromClient();
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
        String command = this.in.readLine();
        System.out.println(command);
        switch (command) {
            case "USER":
                this.requestMessage.sendMessage(RequestMessage.CODE_230);
                break;
            case "PASS":
                this.requestMessage.sendMessage(RequestMessage.CODE_230);
                break;
            default:
                this.requestMessage.sendMessage(RequestMessage.CODE_502);
                break;
        }
    }
}
