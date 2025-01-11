package com.platzi.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

// Aplicando SINGLETON.
// Una unica instancia de conexion a la base de datos.
public class DatabaseConnection {

    // Credentials Database.
    private static final String url = System.getenv("URL_DATABASE");
    private static final String user = System.getenv("USER_DATABASE");
    private static final String password = System.getenv("PASSWORD_DATABASE");

    // Connection Object.
    // Implementando un pool de conexiones.
    private static BasicDataSource poolConnection;

    // Connection Database Method.
    public static BasicDataSource getInstance() throws SQLException {
        if (poolConnection == null) {
            poolConnection = new BasicDataSource();
            poolConnection.setUrl(url);
            poolConnection.setUsername(user);
            poolConnection.setPassword(password);

            // Configuracion del pool de conexiones.
            poolConnection.setInitialSize(3); // Tamano inicial del pool de conexiones
            poolConnection.setMinIdle(3); // Minimo de conexiones inactivas
            poolConnection.setMaxIdle(10); // Maximo conexiones inactivas
            poolConnection.setMaxTotal(10); // Maximo conexiones activas simultaneamente
        }
        return poolConnection;
    }
     // Este metodo obtiene 1 conexion del pool.
    public static Connection getConecction() throws SQLException {
        return getInstance().getConnection();
    }
}
