package Mitra;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class TRANSAKSI extends JFrame {

    // --- Variabel Komponen UI ---
    private JDateChooser dateTgl, dateEstimasi, dateAmbil;
    private JComboBox<String> cmbMember, cmbPaket, cmbDiskon, cmbStatusBayar, cmbStatus, cmbLayanan;
    private JTextField txtKeterangan, txtBiayaTambahan, txtQuantity;
    private JButton btnTambah, btnClear, btnUpdate, btnHome;
    private JTable tblTransaksi;
    private DefaultTableModel tableModel;
    private Connection conn;

    // --- Warna & Font Tema ---
    private final Color colorPrimary = new Color(52, 152, 219);   // Biru Laut
    private final Color colorSecondary = new Color(41, 128, 185); // Biru Gelap
    private final Color colorSuccess = new Color(46, 204, 113);   // Hijau
    private final Color colorWarning = new Color(241, 196, 15);   // Kuning/Oranye
    private final Color colorBg = new Color(236, 240, 241);       // Abu-abu muda
    private final Font fontHeader = new Font("Segoe UI", Font.BOLD, 24);
    private final Font fontLabel = new Font("Segoe UI", Font.BOLD, 12);
    private final Font fontInput = new Font("Segoe UI", Font.PLAIN, 13);

    public TRANSAKSI() {
        initUI();
        connect();
        loadMember();
        loadPaket();
        loadTransaksi(); // Load data awal ke tabel
        setupTableListener();
    }

    // =================================================================
    // BAGIAN 1: SETUP TAMPILAN (UI)
    // =================================================================
    private void initUI() {
        setTitle("Menu Transaksi Laundry");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorBg);

        // 1. HEADER
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(colorPrimary);
        pnlHeader.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblTitle = new JLabel("TRANSAKSI LAUNDRY");
        lblTitle.setFont(fontHeader);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setIcon(UIManager.getIcon("FileView.floppyDriveIcon")); // Ikon bawaan swing sbg contoh

        btnHome = createStyledButton("Home", new Color(255, 255, 255));
        btnHome.setForeground(colorPrimary); // Text biru
        btnHome.setPreferredSize(new Dimension(80, 30));

        pnlHeader.add(lblTitle, BorderLayout.WEST);
        pnlHeader.add(btnHome, BorderLayout.EAST);

        // 2. FORM INPUT (Bagian Tengah - Atas)
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 20, 10, 20),
                new LineBorder(new Color(200, 200, 200), 1)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // Margin antar elemen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Kolom Kiri ---
        // Baris 1
        addLabel(pnlForm, "Tanggal Masuk", gbc, 0, 0);
        dateTgl = new JDateChooser(); styleDateChooser(dateTgl);
        addComponent(pnlForm, dateTgl, gbc, 1, 0);

        addLabel(pnlForm, "Member", gbc, 2, 0);
        cmbMember = new JComboBox<>(); styleComboBox(cmbMember);
        addComponent(pnlForm, cmbMember, gbc, 3, 0);

        // Baris 2
        addLabel(pnlForm, "Estimasi Selesai", gbc, 0, 1);
        dateEstimasi = new JDateChooser(); styleDateChooser(dateEstimasi);
        addComponent(pnlForm, dateEstimasi, gbc, 1, 1);

        addLabel(pnlForm, "Paket Laundry", gbc, 2, 1);
        cmbPaket = new JComboBox<>(); styleComboBox(cmbPaket);
        addComponent(pnlForm, cmbPaket, gbc, 3, 1);

        // Baris 3
        addLabel(pnlForm, "Tanggal Ambil", gbc, 0, 2);
        dateAmbil = new JDateChooser(); styleDateChooser(dateAmbil);
        addComponent(pnlForm, dateAmbil, gbc, 1, 2);

        addLabel(pnlForm, "Berat / Qty", gbc, 2, 2);
        txtQuantity = new JTextField(); styleTextField(txtQuantity);
        addComponent(pnlForm, txtQuantity, gbc, 3, 2);

        // Baris 4
        addLabel(pnlForm, "Layanan", gbc, 0, 3);
        String[] layanans = {"Reguler", "Express"};
        cmbLayanan = new JComboBox<>(layanans); styleComboBox(cmbLayanan);
        addComponent(pnlForm, cmbLayanan, gbc, 1, 3);

        addLabel(pnlForm, "Diskon (%)", gbc, 2, 3);
        String[] diskons = {"0", "5", "10", "15", "20", "25"};
        cmbDiskon = new JComboBox<>(diskons); styleComboBox(cmbDiskon);
        addComponent(pnlForm, cmbDiskon, gbc, 3, 3);

        // Baris 5
        addLabel(pnlForm, "Biaya Tambahan", gbc, 0, 4);
        txtBiayaTambahan = new JTextField(); styleTextField(txtBiayaTambahan);
        addComponent(pnlForm, txtBiayaTambahan, gbc, 1, 4);

        addLabel(pnlForm, "Keterangan", gbc, 2, 4);
        txtKeterangan = new JTextField(); styleTextField(txtKeterangan);
        addComponent(pnlForm, txtKeterangan, gbc, 3, 4);

        // Baris 6 (Status)
        addLabel(pnlForm, "Status Pembayaran", gbc, 0, 5);
        String[] stBayar = {"belum dibayar", "dibayar"};
        cmbStatusBayar = new JComboBox<>(stBayar); styleComboBox(cmbStatusBayar);
        addComponent(pnlForm, cmbStatusBayar, gbc, 1, 5);

        addLabel(pnlForm, "Status Laundry", gbc, 2, 5);
        String[] stLaundry = {"baru", "proses", "selesai", "diambil"};
        cmbStatus = new JComboBox<>(stLaundry); styleComboBox(cmbStatus);
        addComponent(pnlForm, cmbStatus, gbc, 3, 5);

        // 3. PANEL TOMBOL
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButton.setBackground(Color.WHITE);
        pnlButton.setBorder(new EmptyBorder(0, 20, 20, 20));

        btnTambah = createStyledButton("Simpan Transaksi", colorSuccess);
        btnUpdate = createStyledButton("Perbarui Data", colorSecondary);
        btnClear = createStyledButton("Reset Form", colorWarning);

        pnlButton.add(btnTambah);
        pnlButton.add(btnUpdate);
        pnlButton.add(btnClear);

        // Gabungkan Form dan Tombol dalam satu panel Container atas
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlForm, BorderLayout.CENTER);
        pnlTop.add(pnlButton, BorderLayout.SOUTH);

        // 4. TABEL DATA (Bagian Bawah)
        String[] columns = {"ID", "Tgl", "Member", "Paket", "Qty", "Diskon", "Biaya+", "Total", "Status", "Bayar"};
        tableModel = new DefaultTableModel(columns, 0);
        tblTransaksi = new JTable(tableModel);
        styleTable(tblTransaksi);

        JScrollPane scrollPane = new JScrollPane(tblTransaksi);
        scrollPane.setBorder(new EmptyBorder(10, 20, 20, 20));
        scrollPane.getViewport().setBackground(Color.WHITE);

        // 5. MENYUSUN SEMUA KE FRAME
        add(pnlHeader, BorderLayout.NORTH);
        add(pnlTop, BorderLayout.CENTER); // Form di tengah
        add(scrollPane, BorderLayout.SOUTH); // Tabel di bawah
        // Agar tabel proporsional tingginya
        scrollPane.setPreferredSize(new Dimension(getWidth(), 250));

        // --- EVENTS ---
        btnHome.addActionListener(e -> {
            // Logika tombol home
             JOptionPane.showMessageDialog(this, "Kembali ke Menu Utama...");
             this.dispose();
             // new Menu_Utama().setVisible(true);
        });

        btnClear.addActionListener(e -> clearForm());
        
        btnTambah.addActionListener(e -> aksiSimpan());
        
        btnUpdate.addActionListener(e -> {
             // Tambahkan logika update ID transaksi jika diperlukan
             JOptionPane.showMessageDialog(this, "Fitur Update belum diset ID-nya");
        });
    }

    // --- Helper Styling Methods ---

    private void addLabel(JPanel p, String text, GridBagConstraints gbc, int x, int y) {
        JLabel l = new JLabel(text);
        l.setFont(fontLabel);
        l.setForeground(Color.DARK_GRAY);
        gbc.gridx = x; gbc.gridy = y;
        gbc.weightx = 0.1;
        p.add(l, gbc);
    }

    private void addComponent(JPanel p, JComponent c, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x; gbc.gridy = y;
        gbc.weightx = 0.9;
        p.add(c, gbc);
    }

    private void styleTextField(JTextField txt) {
        txt.setFont(fontInput);
        txt.setPreferredSize(new Dimension(100, 30));
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 5, 5, 5)));
    }

    private void styleComboBox(JComboBox box) {
        box.setFont(fontInput);
        box.setBackground(Color.WHITE);
        box.setPreferredSize(new Dimension(100, 30));
    }
    
    private void styleDateChooser(JDateChooser date) {
        date.setFont(fontInput);
        date.setPreferredSize(new Dimension(100, 30));
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover Effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

  private void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        // --- PENGATURAN WARNA HEADER ---
        
        // 1. Ganti warna latar belakang header (Misal: Kuning)
        header.setBackground(new Color(255, 204, 0)); 
        
        // 2. Ganti warna TEKS nama kolom (Misal: Hitam)
        header.setForeground(Color.BLACK); 
        
        // -------------------------------
        
        header.setBorder(null);
        
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setBorder(new EmptyBorder(0, 10, 0, 10));
    }

    // =================================================================
    // BAGIAN 2: LOGIC & DATABASE (Dari Kode Lama Anda)
    // =================================================================

    private void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_laundry"; 
            String user = "root"; 
            String pass = "";     
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi Gagal: " + e.getMessage());
        }
    }

    private void loadMember() {
        try {
            cmbMember.removeAllItems();
            cmbMember.addItem("- Pilih Member -");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nama FROM tb_member");
            while (rs.next()) {
                cmbMember.addItem(rs.getString("nama"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadPaket() {
        try {
            cmbPaket.removeAllItems();
            cmbPaket.addItem("- Pilih Paket -");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nama_paket FROM tb_paket");
            while (rs.next()) {
                cmbPaket.addItem(rs.getString("nama_paket"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadTransaksi() {
        tableModel.setRowCount(0);
        try {
            String sql = "SELECT t.id_transaksi, t.tgl, m.nama, p.nama_paket, t.qty, t.diskon, t.biaya_tambahan, t.status, t.status_pembayaran " +
                         "FROM tb_transaksi t " +
                         "JOIN tb_member m ON t.id_member = m.id_member " +
                         "LEFT JOIN tb_paket p ON t.id_paket = p.id_paket ORDER BY t.tgl DESC";
            
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                // Hitung total sederhana untuk display (logic harga asli ada di database/controller)
                tableModel.addRow(new Object[]{
                    rs.getString("id_transaksi"),
                    rs.getString("tgl"),
                    rs.getString("nama"),
                    rs.getString("nama_paket"),
                    rs.getString("qty"),
                    rs.getString("diskon") + "%",
                    rs.getString("biaya_tambahan"),
                    "Hitung Total..", // Placeholder
                    rs.getString("status"),
                    rs.getString("status_pembayaran")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void aksiSimpan() {
        try {
            if (cmbMember.getSelectedIndex() == 0 || cmbPaket.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Pilih Member dan Paket!");
                return;
            }

            // 1. Ambil ID Member
            String namaMember = cmbMember.getSelectedItem().toString();
            int idMember = getID("tb_member", "id_member", "nama", namaMember);

            // 2. Ambil ID Paket
            String namaPaket = cmbPaket.getSelectedItem().toString();
            int idPaket = getID("tb_paket", "id_paket", "nama_paket", namaPaket);

            // 3. Parse Data Lain
            double qty = parseDoubleSafe(txtQuantity.getText());
            int biayaPlus = parseIntSafe(txtBiayaTambahan.getText());
            double diskon = Double.parseDouble(cmbDiskon.getSelectedItem().toString());
            
            String ket = txtKeterangan.getText();
            String status = cmbStatus.getSelectedItem().toString();
            String bayar = cmbStatusBayar.getSelectedItem().toString();
            String layanan = cmbLayanan.getSelectedItem().toString();

            java.util.Date tgl = dateTgl.getDate();
            java.util.Date est = dateEstimasi.getDate();
            java.util.Date ambil = dateAmbil.getDate();
            
            if(tgl == null) tgl = new java.util.Date(); // Default hari ini
            if(est == null) est = new java.util.Date();
            if(ambil == null) ambil = new java.util.Date();

            // 4. Query Insert
            String sql = "INSERT INTO tb_transaksi (id_member, id_paket, qty, keterangan, tgl, estimasi_selesai, tgl_ambil, biaya_tambahan, diskon, status, status_pembayaran, layanan) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idMember);
            pst.setInt(2, idPaket);
            pst.setDouble(3, qty);
            pst.setString(4, ket);
            pst.setTimestamp(5, new java.sql.Timestamp(tgl.getTime()));
            pst.setTimestamp(6, new java.sql.Timestamp(est.getTime()));
            pst.setTimestamp(7, new java.sql.Timestamp(ambil.getTime()));
            pst.setInt(8, biayaPlus);
            pst.setDouble(9, diskon);
            pst.setString(10, status);
            pst.setString(11, bayar);
            pst.setString(12, layanan);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!");
            loadTransaksi();
            clearForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Helper untuk mengambil ID dari nama
    private int getID(String table, String colID, String colName, String value) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("SELECT " + colID + " FROM " + table + " WHERE " + colName + " = ?");
        pst.setString(1, value);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return 0;
    }

    private void setupTableListener() {
        tblTransaksi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblTransaksi.getSelectedRow() != -1) {
                int row = tblTransaksi.getSelectedRow();
                // Ambil data dasar saja sebagai contoh
                cmbMember.setSelectedItem(tblTransaksi.getValueAt(row, 2).toString());
                cmbPaket.setSelectedItem(tblTransaksi.getValueAt(row, 3).toString());
                txtQuantity.setText(tblTransaksi.getValueAt(row, 4).toString());
                // Sisanya bisa Anda lengkapi ambil dari database by ID jika kolom tabel tidak lengkap
            }
        });
    }

    private void clearForm() {
        dateTgl.setDate(null);
        dateEstimasi.setDate(null);
        dateAmbil.setDate(null);
        cmbMember.setSelectedIndex(0);
        cmbPaket.setSelectedIndex(0);
        txtKeterangan.setText("");
        txtBiayaTambahan.setText("");
        txtQuantity.setText("");
        cmbDiskon.setSelectedIndex(0);
        tblTransaksi.clearSelection();
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s.replaceAll("[^0-9.]", ""));
        } catch (Exception e) { return 0; }
    }
    
    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
        } catch (Exception e) { return 0; }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        java.awt.EventQueue.invokeLater(() -> new TRANSAKSI().setVisible(true));
    }
}