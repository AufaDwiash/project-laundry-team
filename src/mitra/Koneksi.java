package Mitra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/db_laundry",
                    "root",
                    ""
            );
        }
        return conn;
    }
}