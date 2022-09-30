package org.example;

import org.example.clients.Client;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Client> players = List.of(new Client("Иван"), new Client("Алена"),
                new Client("Сергей"), new Client("Ольга"), new Client("Павел"));

        players.forEach(Client::everyTurn);
        System.out.println("Игра окончена, все молодцы");
    }
}
