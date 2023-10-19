package pl.pjatk.assignment6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPServer2 {
    private DatagramSocket server;

    public UDPServer2() throws SocketException {
        initializeServer();
    }

    private void initializeServer() throws SocketException {
        server = new DatagramSocket(12345);
        System.out.println("Server listens on port: " + server.getLocalPort());
    }

    int n2 = 0;
    int nFact = 0;

    private void service() throws IOException {
        byte[] buff = new byte[UDP.MAX_DATAGRAM_SIZE];
        final DatagramPacket datagram = new DatagramPacket(buff, buff.length);

        byte[] buff2 = new byte[UDP.MAX_DATAGRAM_SIZE];
        final DatagramPacket datagram2 = new DatagramPacket(buff2, buff2.length);

        server.receive(datagram);

        server.receive(datagram2);

        new Thread(() -> {
            int n = Integer.parseInt(new String(datagram.getData(), 0, datagram.getLength()));

            int n2 = Integer.parseInt(new String(datagram2.getData(), 0, datagram2.getLength()));

            System.out.println("I've got " + n + " and " + n2);

            nFact = n + n2;

            byte[] respBuff = String.valueOf(nFact).getBytes();
            int clientPort = datagram.getPort();
            InetAddress clientAddress = datagram.getAddress();
            DatagramPacket resp = new DatagramPacket(respBuff, respBuff.length, clientAddress, clientPort);


            try {
                server.send(resp);
                System.out.println("I've sent " + nFact);
            } catch (IOException e) {
                // do nothing
            }
        }).start();
    }

    public void listen() {
        while (true) {
            try {
                service();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UDPServer2().listen();
        } catch (SocketException e) {
            System.out.println("Could not set up the server");
        }
    }

}
