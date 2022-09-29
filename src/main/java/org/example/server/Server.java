package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            // В цикле принимаем подключения
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String lastIncomingCity = "???";
                    out.println("???");
                    char lastChar = 0;
                    while (true) {
                        String incomingCity = in.readLine(); // Moscow // Welling
                        //char currentChar = incomingCity.toUpperCase().charAt(incomingCity.length()-1); // W // G
                        if (lastIncomingCity.equals("???")) { // true // false (Moscow)
                            lastIncomingCity = incomingCity; // "???" -> Moscow
                            out.println("OK"); // send W to client
                        } else if (lastIncomingCity.toUpperCase().charAt(lastIncomingCity.length()-1) == incomingCity.toUpperCase().charAt(0)){ // W = W
                            lastIncomingCity = incomingCity; // Moscow - > Welling
                            out.println("OK");
                        } else {
                            out.println("NOT OK");
                        }
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
