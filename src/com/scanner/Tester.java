
package com.scanner;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Tester {

    private static String latestArticle;

    public static void main(final String... strings) {
        while (true) {
            try {
                final URL url = new URL("https://debukkitsblog.blogspot.com/");
                final Scanner s = new Scanner(url.openStream(), "UTF-8");
                while (s.hasNextLine()) {
                    final String line = s.nextLine();
                    if (line.contains("JAVA Grundkurs #")) {
                        if (!line.equals(latestArticle)) {
                            filter(line);
                            latestArticle = line;
                        }
                        break;
                    }

                }
                s.close();
            } catch (final IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("\nSeite wurde untersucht");
            try {
                Thread.sleep(5000);
            } catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void filter(final String line) {
        System.out.println("Etwas neues...");
        final String output = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
        System.out.println(output);

    }

}
