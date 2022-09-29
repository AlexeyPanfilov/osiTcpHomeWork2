package org.example.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client2 {

    private static final int PORT = 8080;
    private static final String HOST = "localHost";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {
            String city;
            char lastChar = 0;

            while (true) {
                city = in.readLine();
                System.out.println("Reply from server: " + city);
                if (city.equals("???")) { // true
                    System.out.println("Первый ход, введите любой город");
                    city = scanner.nextLine(); // Moscow
                    lastChar = city.toUpperCase().charAt(city.length() - 1);
                    out.println(city);
                } else if (city.equals("OK")) {
                    System.out.println("Ваш ход, введите город, начинающийся на букву " + lastChar);
                    city = scanner.nextLine();
                    if (city.toUpperCase().charAt(0) == lastChar) lastChar = city.toUpperCase().charAt(city.length() - 1);
                    out.println(city);
                } else {
                    System.out.println("Надо ввести город на букву " + lastChar);
                    city = scanner.nextLine();
                    if (city.toUpperCase().charAt(0) == lastChar) lastChar = city.toUpperCase().charAt(city.length() - 1);
                    System.out.println("LastChar = " + lastChar);
                    out.println(city);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
