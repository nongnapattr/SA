package yote.workschedule.Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection connection;
    public Connection getConnection(){

        String dbName="database";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
