package com.example.rest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class Client {

    private String address;
    private int port;
    private String username;
    private String password;
    private FTPClient client;

    Client(String address, int port, String username, String password) {
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.client = new FTPClient();
    }

    void connect() throws IOException {
        this.client.connect(this.address, this.port);
        int reply = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.close();
            throw new IOException("Exception in connecting to FTP Server");
        }
        client.login(this.username, this.password);
    }

    void close() throws IOException {
        client.disconnect();
    }

    public FTPClient getClient() {
        return client;
    }
}
