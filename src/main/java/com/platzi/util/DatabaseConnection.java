package com.platzi.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

// Aplicando SINGLETON.
// Una unica instancia de conexion a la base de datos.
public class DatabaseConnection {

    // Credentials Database.
    private static final String url = System.getenv("URL_DATABASE");
    private static final String user = System.getenv("USER_DATABASE");
    private static final String password = System.getenv("PASSWORD_DATABASE");

    // Connection Object.
    private static Connection connection;

    // Connection Database Method.
    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
