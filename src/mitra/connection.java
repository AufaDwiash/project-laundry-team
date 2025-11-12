import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    public static void main(String[] args) {
        // Ganti sesuai konfigurasi database kamu
        String url = "jdbc:mysql://localhost:3306/db_laundry"; // nama database
        String user = "root";
        String password = ""; // kosong kalau default XAMPP

        try {
            // Coba koneksi ke database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database BERHASIL!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Koneksi GAGAL!");
            e.printStackTrace();
        }
    }
}
