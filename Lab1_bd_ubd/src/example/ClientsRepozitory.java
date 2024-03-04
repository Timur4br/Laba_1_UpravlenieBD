package example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepozitory implements IRepo<Clients> {
    @Override
    public void insert(Clients client) throws SQLException {
        MastersRepozitory mastersRepozitory = new MastersRepozitory();
        mastersRepozitory.insert(client.getIdMasters());
        String str = String.format("INSERT INTO clients (id, surname, name, id_master) VALUES (%s, '%s', '%s', %s)",
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getIdMasters().getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void delete(Clients client) throws SQLException {
        String str = String.format("DELETE  FROM clients where id = %s" , client.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void update(Clients client, int id) throws SQLException {
        String str = String.format("UPDATE clients SET id = %s, surname = '%s', name = '%s', idStylists = %s WHERE id = %s" ,
                client.getId(),
                client.getSurname(),
                client.getName(),
                client.getIdMasters().getId(),
                id);
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public List<Clients> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet ms = stmt.executeQuery("SELECT cl.id,  cl.surname, cl.name, cl.id_master, st.id, st.name, st.surname FROM Clients AS cl JOIN Masters AS st ON cl.id_master = st.id");

        List<Clients> clients = new ArrayList<>();
        while(ms.next()) {
            clients.add(new Clients(ms.getInt("id"),
                    ms.getString("surname"),
                    ms.getString("name"),
                    new Masters(ms.getInt("Masters.id"), ms.getString("Masters.surname"), ms.getString("Masters.name"))));
        }
        this.closeConnection(stmt);

        return clients;
    }
    @Override
    public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:testbd", "root", "");
        if (conn==null) {
            System.out.println("Error with connection with DataBase!");
            System.exit(0);
        }
        return conn;
    }
    @Override
    public Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }
    public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
    }
}
