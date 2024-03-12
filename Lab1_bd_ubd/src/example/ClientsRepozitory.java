package example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepozitory implements IRepo<Clients> {
    @Override
    public int insert(Clients client) throws SQLException {
        String str = String.format("INSERT INTO clients (surname, name, id_master) VALUES ('%s', '%s', %s)",
                client.getName(),
                client.getSurname(),
                client.getIdMasters().getId());

        client.setDelete(false);
        this.executeRequest(str);
        try (ResultSet ms = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(id) FROM Clients")) {
            while (ms.next()) {
                return ms.getInt(1);
            }
            return -1;
        }
    }

    private void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }

    @Override
    public void delete(Clients client) throws SQLException {
        String str = String.format("UPDATE Clients SET isDelete = true WHERE id = %s" , client.getId());
        this.executeRequest(str);
    }
    @Override
    public void update(Clients client, int id) throws SQLException {
        String str = String.format("UPDATE clients SET id = %s, surname = '%s', name = '%s', id_master = %s WHERE id = %s" ,
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
        ResultSet rs = stmt.executeQuery("SELECT cl.id, cl.surname ,cl.name, cl.id_master, st.id, st.surname,st.name, st.isDelete, cl.isDelete FROM Clients  AS cl JOIN Masters AS st ON cl.id_master = st.ID");

        List<Clients> clients = new ArrayList<>();
        while(rs.next()) {
            clients.add(new Clients(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    new Masters(
                            rs.getInt("Masters.id"),
                            rs.getString("Masters.name"),
                            rs.getString("Masters.surname"),
                            rs.getBoolean("Masters.ISDELETE")),
                    rs.getBoolean("Clients.isDelete")));
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
