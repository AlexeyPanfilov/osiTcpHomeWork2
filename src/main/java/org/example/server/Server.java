package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        String lastIncomingCity = null;
        String incomingCity;
        String replyToClient = "???";
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            // В цикле принимаем подключения
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    out.println(replyToClient);
                    incomingCity = in.readLine(); // Moscow // Welling
                    // Для того
                    if (replyToClient.equals("???")) { // true // false (Moscow)
                        lastIncomingCity = incomingCity; // "???" -> Moscow
                        replyToClient = "OK"; // send W to client
                    } else if (lastIncomingCity.toUpperCase().charAt(lastIncomingCity.length() - 1) == incomingCity.toUpperCase().charAt(0)) { // W = W
                        lastIncomingCity = incomingCity; // Moscow - > Welling
                        replyToClient = "OK";
                    } else {
                        replyToClient = "NOT OK";
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
