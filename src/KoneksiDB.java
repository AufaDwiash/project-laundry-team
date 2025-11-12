import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_laundry";
        String user = "root";
        String pass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Koneksi ke database berhasil!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Koneksi gagal: " + e.getMessage());
        }
    }
}
