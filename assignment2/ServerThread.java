package pl.pjatk.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String lineOut1 = "22222";
            String lineOut2 = "623466";
            out.println(lineOut1);
            out.println(lineOut2);

            String lineIn1 = in.readLine();
            System.out.println(lineIn1);
            String lineIn2 = in.readLine();
            System.out.println(lineIn2);

            out.println(lineIn1 + lineIn2);

        } catch (IOException e1) {
            System.out.println("No I/O");
            // do nothing
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("No I/O");
            // do nothing
        }
    }

}
