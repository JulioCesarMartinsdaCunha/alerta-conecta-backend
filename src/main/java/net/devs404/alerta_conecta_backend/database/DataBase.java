package net.devs404.alerta_conecta_backend.database;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase
{
    @Value("${spring.datasource.url}")
    private static String URL;

    // O nome de usuário resolvido
    @Value("${spring.datasource.username}")
    private static String USER;

    // A senha resolvida
    @Value("${spring.datasource.password}")
    private static String PASS;

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
