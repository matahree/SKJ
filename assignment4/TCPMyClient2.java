package pl.pjatk.assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Instant;

public class TCPMyClient2 {
    public static void main(String args[]) {


        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Instant time0 = null;

        int count = 0;
        for (int i = 33973; i <= 34542; i++) {

            try {
                InetAddress addr = InetAddress.getByName("172.21.48.148");
                InetSocketAddress sockaddr = new InetSocketAddress(addr, i);
                System.out.println("socket: " + sockaddr);

                socket = new Socket();
                time0 = Instant.now();
                // This timeout is only for the SYN handshake timeout
                socket.connect(sockaddr, 200);
                count++;
                System.out.println("Number of running servers: " + count);

                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socket.setSoTimeout(10000);

            } catch (UnknownHostException e) {
                System.out.println("Unknown host");
                System.exit(-1);
            } catch (IOException e) {
                continue;
            }

            try {
                out.println("239074");

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                continue;
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }
    }
}
