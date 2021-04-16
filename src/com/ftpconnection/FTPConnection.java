
package com.ftpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FTPConnection {

    public static void main(final String[] args) {
        try {
            final URL url = new URL("ftp://willi:passwort@test.de/verzeichnis/");
            show(url);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static void show(final URL url) throws IOException {
        final InputStream in = url.openStream();
        final BufferedReader buff = new BufferedReader(new InputStreamReader(in));
        String s;
        while ((s = buff.readLine()) != null) {
            System.out.println(s);
        }
    }
}
