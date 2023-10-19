package pl.pjatk.assignment2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void listenSocket() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(4567); // ServerSocket class - welcome socket
        } catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }

        System.out.println("Server listens on port: " + server.getLocalPort());

        while (true) {
            try {
                client = server.accept();  // "accept" method is blocking, waiting for request
                // "client" is a new communication socket
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }

            // Serve client in a separate thread, non-blocking
            (new ServerThread(client)).start(); // does not wait for thread completion
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listenSocket();
    }
}


