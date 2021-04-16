
package com.hostreachability;

import java.io.IOException;
import java.net.InetAddress;

public class HostReachability {

    public static void main(final String... strings) {
        final String host = "hostname";

        try {
            final InetAddress iAdr = InetAddress.getByName(host);
            if (iAdr.isReachable(5000)) {
                System.out.println("Host: '" + host + "' ist erreichbar hat die IP: " + iAdr.getHostAddress());
            } else {
                System.out.println("Host '" + host + "' ist nicht erreichbar");
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

}
