package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException{
        try{
            Class.forName("org.h2.Driver").newInstance();}
        catch(Exception e)
        {System.err.println("Unable to load driver:"+ e);}
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:testbd", "root", "");
        if (conn==null){System.out.println("Нет соединения с БД!");
            System.exit(0);}
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM CLI_MAS JOIN CLIENTS ON CLI_MAS .ID_CLIENT=CLIENTS.id JOIN Masters ON CLI_MAS .ID_MASTER=Masters.id");
        while(rs.next()){
        System.out.println(rs.getRow() + ". " +
                "КЛИЕНТ - " +rs.getString("clients.surname") +" " +rs.getString("clients.name") + "\t\t\t"+
                "ЕГО МАСТЕР - " +rs.getString("masters.surname") + " " + rs.getString("masters.name"));}
        stmt.close();}}

