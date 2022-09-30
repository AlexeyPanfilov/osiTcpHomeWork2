package org.example.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private static final int PORT = 8080;
    private static final String HOST = "localHost";

    protected static char lastChar = 0;
    protected String clientsName;

    public Client (String clientsName) {
        this.clientsName = clientsName;
    }

    public void everyTurn() {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {
            Scanner scanner = new Scanner(System.in);
            String replyFromServer = in.readLine();
//            System.out.println("Reply from server: " + replyFromServer);
            System.out.println("Играет " + clientsName);
            if (replyFromServer.equals("???")) { // true
                System.out.println("Первый ход, введите любой город");
                replyFromServer = scanner.nextLine(); // Moscow
                lastChar = replyFromServer.toUpperCase().charAt(replyFromServer.length() - 1);
                out.println(replyFromServer);
            } else if (replyFromServer.equals("OK")) {
                System.out.println("Ваш ход, введите город, начинающийся на букву " + lastChar);
                replyFromServer = scanner.nextLine();
                if (replyFromServer.toUpperCase().charAt(0) == lastChar)
                    lastChar = replyFromServer.toUpperCase().charAt(replyFromServer.length() - 1);
                out.println(replyFromServer);
            } else {
                System.out.println("Введите город на букву " + lastChar);
                replyFromServer = scanner.nextLine();
                if (replyFromServer.toUpperCase().charAt(0) == lastChar)
                    lastChar = replyFromServer.toUpperCase().charAt(replyFromServer.length() - 1);
                out.println(replyFromServer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
