package example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MastersRepozitory implements IRepo<Masters> {
    @Override
    public void insert(Masters masters) throws SQLException {
        String str = String.format("INSERT INTO stylists (id, surname, name) VALUES (%s, '%s', '%s')",
                masters.getId(),
                masters.getSurname(),
                masters.getName());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void delete(Masters masters) throws SQLException {
        String str = String.format("DELETE  FROM stylists where id = %s" , masters.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void update(Masters masters, int id) throws SQLException {
        String str = String.format("UPDATE stylists SET id = %s, surname = '%s', name = '%s' WHERE id = %s" ,
                masters.getId(),
                masters.getSurname(),
                masters.getName(),
                masters.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();

    }

    @Override
    public List<Masters> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet ms = stmt.executeQuery("SELECT ms.id, ms.surname, ms.name FROM Masters AS ms");

        List<Masters> masters = new ArrayList<>();
        while(ms.next()) {
            Masters tmpStylist = new Masters
                    (ms.getInt("Masters.id"), ms.getString("Masters.surname"), ms.getString("Masters.name"));
            masters.add(tmpStylist);
        }
        this.closeConnection(stmt);

        return masters;
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
