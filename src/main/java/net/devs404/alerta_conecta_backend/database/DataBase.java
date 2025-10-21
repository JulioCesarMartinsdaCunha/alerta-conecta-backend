package net.devs404.alerta_conecta_backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase
{
    private static final int port = 3306;
    private static final String database_name = "alertacon";

    private static final String URL = "jdbc:mysql://localhost:" + port + "/"+database_name;
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect()
    {
        Connection connect;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return connect;
    }
}
