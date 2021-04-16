
package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private final int port;

    public Server(final int port) {
        this.port = port;
    }

    public void startListening() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server starts");
                    final ServerSocket ss = new ServerSocket(port);
                    final Socket remoteClient = ss.accept();
                    System.out.println("Client Connected: " + remoteClient.getRemoteSocketAddress());
                    final Scanner s = new Scanner(
                            new BufferedReader(new InputStreamReader(remoteClient.getInputStream())));
                    if (s.hasNext()) {
                        System.out.println("Massage from Client " + s.nextLine());
                    }
                    final PrintWriter pw = new PrintWriter(new OutputStreamWriter(remoteClient.getOutputStream()));
                    pw.println("Message angenommen");
                    pw.flush();

                    s.close();
                    remoteClient.close();
                    ss.close();
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
            }

        }).start();
    }

    public static void main(final String... strings) {
        final Server s = new Server(8000);
        s.startListening();
    }

}
