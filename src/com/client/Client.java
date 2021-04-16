
package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final InetSocketAddress address;

    public Client(final String hostName, final int port) {
        this.address = new InetSocketAddress(hostName, port);
    }

    public void sendMessage(final String message) {
        final Socket s = new Socket();
        try {
            s.connect(address, 10000);
            final PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            pw.println(message);
            pw.flush();
            final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(s.getInputStream())));
            if (scanner.hasNext()) {
                System.out.println("Antwort vom Server " + scanner.nextLine());
            }

            pw.close();
            scanner.close();
            s.close();
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(final String... strings) {
        final Client client = new Client("127.0.0.1", 8000);
        client.sendMessage("Guten Tag Server");
    }

}
