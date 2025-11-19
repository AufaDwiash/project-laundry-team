package Mitra;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LOGIN extends JFrame {

    // Komponen UI
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JPanel mainPanel;

    // Warna Tema (Palette Modern)
    private final Color colorPrimary = new Color(74, 144, 226); // Biru Cerah
    private final Color colorHover = new Color(53, 122, 189);   // Biru Gelap (Hover)
    private final Color colorBgStart = new Color(102, 126, 234); // Gradasi Awal
    private final Color colorBgEnd = new Color(118, 75, 162);    // Gradasi Akhir

    public LOGIN() {
        initUI();
        initEvent();
    }

    private void initUI() {
        setTitle("Login System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Tengah layar

        // 1. Setup Main Panel dengan Background Gradient
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                // Membuat gradasi diagonal
                GradientPaint gp = new GradientPaint(0, 0, colorBgStart, w, h, colorBgEnd);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // Untuk menengahkan Login Box
        add(mainPanel);

        // 2. Login Box (Kartu Putih di tengah)
        JPanel loginCard = new JPanel();
        loginCard.setBackground(Color.WHITE);
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBorder(new EmptyBorder(40, 50, 40, 50)); // Padding dalam kartu
        // Efek bayangan sederhana dengan border
        loginCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1),
                new EmptyBorder(40, 50, 40, 50)
        ));
        
        // Menambahkan shadow effect (opsional/simulasi) bisa rumit di swing murni, 
        // jadi kita pakai panel putih bersih saja.

        // 3. Elemen - Judul
        JLabel lblTitle = new JLabel("WELCOME");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(new Color(50, 50, 50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Please login to continue");
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Elemen - Input Fields
        JLabel lblUser = new JLabel("Username");
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        txtUsername = new JTextField(15);
        styleTextField(txtUsername);
        txtUsername.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblPass = new JLabel("Password");
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 12));

        txtPassword = new JPasswordField(15);
        styleTextField(txtPassword);
        txtPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 5. Elemen - Tombol Login
        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(colorPrimary);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Membuat tombol lebih besar
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 
        
        // Menambahkan padding antar elemen (Spacer)
        loginCard.add(lblTitle);
        loginCard.add(Box.createVerticalStrut(5));
        loginCard.add(lblSubtitle);
        loginCard.add(Box.createVerticalStrut(30));
        loginCard.add(lblUser);
        loginCard.add(Box.createVerticalStrut(5));
        loginCard.add(txtUsername);
        loginCard.add(Box.createVerticalStrut(15));
        loginCard.add(lblPass);
        loginCard.add(Box.createVerticalStrut(5));
        loginCard.add(txtPassword);
        loginCard.add(Box.createVerticalStrut(30));
        loginCard.add(btnLogin);

        mainPanel.add(loginCard);
        
        // Set Default Enter Button
        getRootPane().setDefaultButton(btnLogin);
    }

    // Helper untuk mempercantik TextField
    private void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        // Hanya garis bawah (Material Design style)
        field.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, new Color(200, 200, 200)), 
                new EmptyBorder(5, 0, 5, 0)));
        field.setBackground(Color.WHITE);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        // Ubah warna garis saat diklik (Focus Listener)
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, colorPrimary), 
                new EmptyBorder(5, 0, 5, 0)));
            }
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 2, 0, new Color(200, 200, 200)), 
                new EmptyBorder(5, 0, 5, 0)));
            }
        });
    }

    private void initEvent() {
        // Efek Hover pada Tombol
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(colorPrimary);
            }
        });

        // Action Listener untuk Login Logic
        btnLogin.addActionListener(evt -> performLogin());
    }

    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Menggunakan Koneksi class yang sudah ada di project Anda
            Connection conn = Koneksi.getConnection(); 
            String sql = "SELECT * FROM tb_user WHERE username=? AND password=?";
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                String role = rs.getString("role");

                JOptionPane.showMessageDialog(this, "Selamat Datang, " + nama + "\nRole: " + role, "Login Sukses", JOptionPane.INFORMATION_MESSAGE);

                // Membuka Menu Utama
                Menu_Utama mu = new Menu_Utama();
                mu.setVisible(true);
                this.dispose(); // Tutup form login
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
            
            rs.close();
            pst.close();
            // conn.close(); // Opsional, tergantung strategi koneksi Anda (singleton atau open-close)

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Set Look and Feel ke System default agar font rendering lebih halus
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new LOGIN().setVisible(true);
        });
    }
}