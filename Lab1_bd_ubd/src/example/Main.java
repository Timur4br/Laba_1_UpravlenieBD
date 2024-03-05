package example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        IRepo<Clients> clientsRepozitory = new ClientsRepozitory();
        IRepo<Masters> mastersRepozitory = new MastersRepozitory();


        List<Clients> clients = clientsRepozitory.getList();
        List<Masters> masters = mastersRepozitory.getList();

        //mastersRepozitory.insert(new Masters(4,"Тестов", "Тест"));
        //mastersRepozitory.insert(new Masters(5,"Магов", "Игорь"));
        //clientsRepozitory.insert(new Clients(7,"Ромбов", "Антон", new Masters(6,"Уэскер", "Игорь")));
        //clientsRepozitory.insert(new Clients(8,"Кид", "Александр", new Masters(7,"Золотой", "Глеб")));

        //mastersRepozitory.update(new Masters(2,"Романов", "Артем"), 2);
        //clientsRepozitory.update(new Clients(6, "Петров", "Никита", new Masters(4,"Тестов", "Тест")), 6);


        System.out.println("---------Список Клиентов---------");
        for (Clients client : clients) {
            BDWork.printClient(client); // Print information about clients
        }

        System.out.println("---------Список Мастеров---------");
        for (Masters masters1 : masters) {
            BDWork.printMasters(masters1); // Print information about stylists
        }

        System.out.println("------Клиенты и их мастера-------");
        for (Clients client : clients) {
            BDWork.printClientMasters(client); // Print information about client-stylist
        }




    }
}