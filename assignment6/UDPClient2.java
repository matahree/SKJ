package pl.pjatk.assignment6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient2 {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        //  InetAddress address = InetAddress.getByName("172.23.129.10");
        int port = 12345;

        int n = 15;

        byte[] queryBuff = String.valueOf(n).getBytes();
        DatagramPacket query = new DatagramPacket(queryBuff, queryBuff.length, address, port);

        DatagramSocket socket = new DatagramSocket();

        socket.send(query);
        System.out.println("Number sent: "  + n);

        byte[] buff = new byte[UDP.MAX_DATAGRAM_SIZE];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);

        int n2 = 20;

        byte[] queryBuff2 = String.valueOf(n2).getBytes();
        DatagramPacket query2 = new DatagramPacket(queryBuff2, queryBuff2.length, address, port);

        DatagramSocket socket2 = new DatagramSocket();
        socket2.send(query2);
        System.out.println("Number sent: "  + n2);

        System.out.println("Waiting for server response...");
        socket.setSoTimeout(5000);
        socket.receive(packet);


        String str = new String(packet.getData(), 0, packet.getLength()).trim();

        System.out.println("Received from server: "  + str);

        socket.close();
    }
}