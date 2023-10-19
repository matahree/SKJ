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
import java.time.temporal.ChronoUnit;

public class TCPClient2 {
    public static void main(String args[]) {


        String host_address = "gaia.cs.umass.edu";
        String resource = "/wireshark-labs/HTTP-wireshark-file1.html";

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Instant time0 = null;


        try {
            // DNS
//            InetAddress addr = InetAddress.getByName("gaia.cs.umass.edu");
            InetAddress addr = InetAddress.getByName("www.pjwstk.edu.pl");
            System.out.println("Address: " + addr);
            InetSocketAddress sockaddr = new InetSocketAddress(addr,80);
            System.out.println("socket: " + sockaddr);

            socket = new Socket();
            time0 = Instant.now();
            // This timeout is only for the SYN handshake timeout
            socket.connect(sockaddr, 500);
//            socket = new Socket("gaia.cs.umass.edu", 80);
            System.out.println("socket connect [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
            socket.setSoTimeout(10000);
            // Here we will get 0
            System.out.println("socket timeout set to [ms]: " + socket.getSoTimeout());
//            System.exit(0);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.out.println("error after [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
            System.exit(-1);
        }


        try {
            time0 = Instant.now();
            String request_line = "GET / HTTP/1.1";
            String host_header = "Host: " + "www.pjwstk.edu.pl";

            out.println(request_line);
            out.println(host_header);
            out.println(); // blank line = end of request header

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("server response [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));

    } catch (IOException e) {
            System.out.println("Error during communication");
            System.out.println("error after [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
            System.exit(-1);
        }


        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }
    }
}
