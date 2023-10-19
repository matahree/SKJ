package pl.pjatk.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) throws IOException {
        String server_host_address = "172.21.48.109"; // IP: 127.0.0.1   loop back interface
        int port_on_server = 20006;

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(server_host_address, port_on_server);
            out = new PrintWriter(socket.getOutputStream(), true);

            String lineOut1 = "22222";
            String lineOut2 = "172.23.129.77:4567";
            out.println(lineOut1);
            out.println(lineOut2);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(-1);
        }

        // Close TCP connection
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }

    }
}
