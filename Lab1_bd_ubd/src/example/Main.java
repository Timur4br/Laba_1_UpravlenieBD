package example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        IRepo<Clients> clientsRepozitory = new ClientsRepozitory();
        IRepo<Masters> mastersRepozitory = new MastersRepozitory();


        List<Clients> clients = clientsRepozitory.getList();
        List<Masters> masters = mastersRepozitory.getList();

        //mastersRepozitory.insert(new Masters(null,"Башкиров", "Альберт", null));
        //mastersRepozitory.insert(new Masters(null,"Русов", "Ящер", null));
        //mastersRepozitory.insert(new Masters(null,"Пивной", "Богдан", null));
        //clientsRepozitory.insert(new Clients(null,"Ромбов", "Антон", masters.get(1), null));
        //clientsRepozitory.insert(new Clients(null,"Кубов", "Виталий", masters.get(2), null));

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