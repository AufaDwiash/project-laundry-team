package Mitra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class Menu_Transaksi extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu_Transaksi.class.getName());

    public Menu_Transaksi() {

        initComponents();
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", // 0
                    "Tanggal", // 1
                    "Member", // 2
                    "Keterangan", // 3
                    "Biaya Tambahan", // 4
                    "Estimasi Selesai",// 5
                    "Paket", // 6
                    "Quantity", // 7
                    "Diskon", // 8
                    "Status Pembayaran",// 9
                    "Tanggal Ambil", // 10
                    "Status", // 11
                    "Layanan", // 12
                    "Total" // 13 
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int col
            ) {
                return false; // tabel read-only
            }
        };
        tblTransaksi.setModel(model);

        // (opsional) sembunyikan kolom ID biar nggak kelihatan
        tblTransaksi.getColumnModel()
                .getColumn(0).setMinWidth(0);
        tblTransaksi.getColumnModel().getColumn(0).setMaxWidth(0);
        tblTransaksi.getColumnModel().getColumn(0).setWidth(0);
        connect();
        loadMember();
        loadPaket();
        loadTransaksi();
        jDateChooser1.setDate(new java.util.Date());

        // Event klik baris pada tabel
        tblTransaksi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblTransaksi.getSelectedRow() != -1) {
                int row = tblTransaksi.getSelectedRow();
                System.out.println("Row selected: " + row);

                // id_transaksi ada di kolom 0 (kalau mau dipakai nanti)
                // int idTransaksi = Integer.parseInt(tblTransaksi.getValueAt(row, 0).toString());
                String tanggal = tblTransaksi.getValueAt(row, 1).toString();
                String member = tblTransaksi.getValueAt(row, 2).toString();
                String keterangan = tblTransaksi.getValueAt(row, 3).toString();
                String biayaTambahan = tblTransaksi.getValueAt(row, 4).toString();
                String batas = tblTransaksi.getValueAt(row, 5).toString();
                String paket = tblTransaksi.getValueAt(row, 6).toString();
                String qty = tblTransaksi.getValueAt(row, 7).toString();
                String diskon = tblTransaksi.getValueAt(row, 8).toString();
                String statusPembayaran = tblTransaksi.getValueAt(row, 9).toString();
                Object tglAmbilObj = tblTransaksi.getValueAt(row, 10);
                String tglAmbil = (tglAmbilObj == null) ? "" : tglAmbilObj.toString();
                String status = tblTransaksi.getValueAt(row, 11).toString();
                String layanan = tblTransaksi.getValueAt(row, 12).toString();

                // Format tanggal (lebih aman kalau ada jam)
                try {
                    java.text.SimpleDateFormat fmtDateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.text.SimpleDateFormat fmtDateOnly = new java.text.SimpleDateFormat("yyyy-MM-dd");

                    java.util.Date tgl = (tanggal.contains(":") ? fmtDateTime : fmtDateOnly).parse(tanggal);
                    java.util.Date batasTgl = (batas.contains(":") ? fmtDateTime : fmtDateOnly).parse(batas);

                    if (!tglAmbil.isEmpty()) {
                        java.util.Date ambilTgl = (tglAmbil.contains(":") ? fmtDateTime : fmtDateOnly).parse(tglAmbil);
                        jDateChooser3.setDate(ambilTgl);
                    } else {
                        jDateChooser3.setDate(null);
                    }

                    jDateChooser1.setDate(tgl);
                    jDateChooser2.setDate(batasTgl);
                    jDateChooser3.setDate(ambilTgl);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Set nilai ke form
                cmbMember1.setSelectedItem(member);
                txtKeterangan.setText(keterangan);
                txtBiayaTambahan.setText(biayaTambahan);
                cmbPaket.setSelectedItem(paket);
                txtQuantity.setText(qty);
                cmbDiskon.setSelectedItem(diskon);
                jComboBox1.setSelectedItem(statusPembayaran);
                jComboBox2.setSelectedItem(status);
                cmbLayanan.setSelectedItem(layanan);
                btnClear1.setEnabled(true);
                btnDelete.setEnabled(true);
            }
        });
        btnClear1.setEnabled(false);  // Perbarui
        btnDelete.setEnabled(false);  // Hapus
    }

    private Connection conn;

    private void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_laundry"; // ganti sesuai port DB kamu
            String user = "root"; // username MySQL kamu    
            String pass = "";     // password MySQL kamu
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi ke database berhasil.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi database gagal: " + e.getMessage());
        }
    }

    private void loadMember() {
        try {
            String sql = "SELECT nama FROM tb_member";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            cmbMember1.removeAllItems();
            cmbMember1.addItem("Pilih Member");

            while (rs.next()) {
                cmbMember1.addItem(rs.getString("nama"));
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data member: " + e.getMessage());
        }
    }

    private void loadPaket() {
        try {
            String sql = "SELECT nama_paket FROM tb_paket";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            cmbPaket.removeAllItems();
            cmbPaket.addItem("Pilih Paket");

            while (rs.next()) {
                cmbPaket.addItem(rs.getString("nama_paket"));
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data paket: " + e.getMessage());
        }
    }

    private void simpanTransaksi(
            int idMember, int idPaket, double qty, String keterangan,
            java.util.Date tgl, java.util.Date estimasi, java.util.Date tglAmbil,
            int biayaTambahan, double diskon, String status, String statusPembayaran,
            String layanan) {

        String sql = "INSERT INTO tb_transaksi "
                + "(id_member, id_paket, qty, keterangan, tgl, estimasi_selesai, "
                + "tgl_ambil, biaya_tambahan, diskon, status, status_pembayaran, layanan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idMember);
            pst.setInt(2, idPaket);
            pst.setDouble(3, qty);
            pst.setString(4, keterangan);
            pst.setTimestamp(5, new java.sql.Timestamp(tgl.getTime()));
            pst.setTimestamp(6, new java.sql.Timestamp(estimasi.getTime()));
            pst.setTimestamp(7, new java.sql.Timestamp(tglAmbil.getTime()));
            pst.setInt(8, biayaTambahan);
            pst.setDouble(9, diskon);
            pst.setString(10, status);
            pst.setString(11, statusPembayaran);
            pst.setString(12, layanan);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Data transaksi berhasil disimpan ke database!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Gagal menyimpan transaksi: " + e.getMessage());
        }
    }

    private void loadTransaksi() {
        DefaultTableModel model = (DefaultTableModel) tblTransaksi.getModel();
        model.setRowCount(0);

        String sql
                = "SELECT t.id_transaksi, t.tgl, m.nama AS nama_member, "
                + "       t.keterangan, t.biaya_tambahan, t.estimasi_selesai, "
                + "       p.nama_paket, p.harga, t.qty, t.diskon, t.status_pembayaran, "
                + "       t.tgl_ambil, t.status, t.layanan, "
                + // 💰 hitung total:
                // versi diskon = nilai langsung (rupiah)
                "       ( (p.harga * t.qty) + t.biaya_tambahan - t.diskon ) AS total "
                + "FROM tb_transaksi t "
                + "JOIN tb_member m ON t.id_member = m.id_member "
                + "LEFT JOIN tb_paket p ON t.id_paket = p.id_paket";

        try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_transaksi"), // 0
                    rs.getString("tgl"), // 1
                    rs.getString("nama_member"), // 2
                    rs.getString("keterangan"), // 3
                    rs.getInt("biaya_tambahan"), // 4
                    rs.getString("estimasi_selesai"), // 5
                    rs.getString("nama_paket"), // 6
                    rs.getDouble("qty"), // 7
                    rs.getDouble("diskon"), // 8
                    rs.getString("status_pembayaran"),// 9
                    rs.getString("tgl_ambil"), // 10
                    rs.getString("status"), // 11
                    rs.getString("layanan"), // 12
                    rs.getDouble("total") // 13 ✅
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Gagal memuat data transaksi: " + e.getMessage());
        }
    }
// Mengubah string angka menjadi double (mengganti koma jadi titik, hapus karakter non-digit kecuali -, .)

    private double parseDoubleSafe(String s) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }
        s = s.trim();
        // Replace comma with dot (user mungkin pakai 1,5)
        s = s.replace(",", ".");
        // Hapus karakter selain digit, minus, dan titik
        s = s.replaceAll("[^0-9.\\-]", "");
        if (s.isEmpty()) {
            throw new NumberFormatException("Kosong");
        }
        return Double.parseDouble(s);
    }

    private void clearForm() {
        jDateChooser1.setDate(null);   // tanggal
        jDateChooser2.setDate(null);   // estimasi selesai
        jDateChooser3.setDate(null);   // tanggal ambil

        cmbMember1.setSelectedIndex(0);
        cmbPaket.setSelectedIndex(0);
        cmbLayanan.setSelectedIndex(0);

        txtKeterangan.setText("");
        txtBiayaTambahan.setText("");
        txtQuantity.setText("");
        cmbDiskon.setSelectedIndex(0);

        jComboBox1.setSelectedIndex(0); // status pembayaran
        jComboBox2.setSelectedIndex(0); // status
    }

// Versi untuk int (buang desimal jika ada)
    private int parseIntSafe(String s) throws NumberFormatException {
        double d = parseDoubleSafe(s);
        return (int) Math.round(d); // atau (int) d jika kamu ingin floor/truncate
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jCalendar3 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        lblTanggal = new javax.swing.JLabel();
        lblMember = new javax.swing.JLabel();
        lblKeterangan = new javax.swing.JLabel();
        lblBiayaTambahan = new javax.swing.JLabel();
        txtBiayaTambahan = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        lblBatasWaktu = new javax.swing.JLabel();
        lblPaket = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        lblDiskon = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        cmbPaket = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        cmbDiskon = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        lblBatasWaktu1 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        lblBiayaTambahan1 = new javax.swing.JLabel();
        cmbMember1 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblBiayaTambahan2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnClear1 = new javax.swing.JButton();
        cmbLayanan = new javax.swing.JComboBox<>();
        lblDiskon1 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTanggal.setText("Tanggal");

        lblMember.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMember.setText("Member");

        lblKeterangan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblKeterangan.setText("Keterangan");

        lblBiayaTambahan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBiayaTambahan.setText("Biaya Tambahan");

        txtKeterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeteranganActionPerformed(evt);
            }
        });

        lblBatasWaktu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBatasWaktu.setText("Estimasi Selesai");

        lblPaket.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPaket.setText("Paket");

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblQuantity.setText("Quantity");

        lblDiskon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiskon.setText("Diskon");

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        cmbPaket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih paket" }));
        cmbPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaketActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 153));
        btnClear.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnClear.setText("Bersihkan");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(51, 255, 0));
        btnTambah.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        cmbDiskon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", " " }));

        tblTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Member", "Keterangan", "Biaya Tambahan", "Estimasi Selesai", "Paket", "Quantity", "Diskon", "Status Pembayaran", "Tanggal Ambil", "Status", "Layanan", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        lblJudul.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(51, 0, 51));
        lblJudul.setText("Menu TransaksI");

        btnHome.setBackground(new java.awt.Color(0, 0, 0));
        btnHome.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblJudul))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBatasWaktu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBatasWaktu1.setText("Tanggal Ambil");

        lblBiayaTambahan1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBiayaTambahan1.setText("Status Pembayaran");

        cmbMember1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Member " }));
        cmbMember1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMember1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dibayar", "belum dibayar" }));

        lblBiayaTambahan2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBiayaTambahan2.setText("Status");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "proses", "selesai", "diambil" }));

        btnClear1.setBackground(new java.awt.Color(153, 153, 255));
        btnClear1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnClear1.setText("Perbarui");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        cmbLayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "reguler", "express" }));
        cmbLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLayananActionPerformed(evt);
            }
        });

        lblDiskon1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiskon1.setText("Layanan");

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKeterangan)
                    .addComponent(lblMember)
                    .addComponent(lblTanggal)
                    .addComponent(lblBiayaTambahan)
                    .addComponent(lblBiayaTambahan1)
                    .addComponent(lblBiayaTambahan2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMember1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKeterangan)
                    .addComponent(txtBiayaTambahan)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBatasWaktu)
                    .addComponent(lblBatasWaktu1)
                    .addComponent(lblPaket)
                    .addComponent(lblDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity)
                    .addComponent(lblDiskon1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPaket, 0, 502, Short.MAX_VALUE)
                    .addComponent(txtQuantity)
                    .addComponent(cmbDiskon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbLayanan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbMember1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBatasWaktu1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTanggal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKeterangan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBiayaTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBiayaTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBiayaTambahan1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBiayaTambahan2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPaket))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblQuantity))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDiskon)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(lblBatasWaktu)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDiskon1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnClear)
                    .addComponent(btnClear1)
                    .addComponent(btnDelete))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        jDateChooser1.setDate(new java.util.Date());;
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null); // 🔹 tambahkan: kosongkan tanggal ambil
        cmbMember1.setSelectedIndex(0);
        txtKeterangan.setText("");
        txtBiayaTambahan.setText(""); // 🔹 perbaiki: sebelumnya getText()
        cmbPaket.setSelectedIndex(0);
        txtQuantity.setText("");
        cmbDiskon.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        cmbLayanan.setSelectedIndex(0);
        // 👉 user lagi di mode "tambah", jadi disable update & delete
        btnClear1.setEnabled(false);
        btnDelete.setEnabled(false);

    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        try {
            // Validasi pilihan
            if (cmbMember1.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Pilih member terlebih dahulu.");
                return;
            }
            if (cmbPaket.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Pilih paket terlebih dahulu.");
                return;
            }

            // 1️⃣ Ambil MEMBER (nama → id_member)
            String memberNama = cmbMember1.getSelectedItem().toString();
            int idMember = 0;
            String sqlMember = "SELECT id_member FROM tb_member WHERE nama = ?";
            try (PreparedStatement pst = conn.prepareStatement(sqlMember)) {
                pst.setString(1, memberNama);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        idMember = rs.getInt("id_member");
                    } else {
                        JOptionPane.showMessageDialog(this, "Member tidak ditemukan di database.");
                        return;
                    }
                }
            }

            // 2️⃣ Ambil PAKET (nama → id_paket)
            String paketNama = cmbPaket.getSelectedItem().toString();
            int idPaket = 0;
            String sqlPaket = "SELECT id_paket FROM tb_paket WHERE nama_paket = ?";
            try (PreparedStatement pst = conn.prepareStatement(sqlPaket)) {
                pst.setString(1, paketNama);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        idPaket = rs.getInt("id_paket");
                    } else {
                        JOptionPane.showMessageDialog(this, "Paket tidak ditemukan di database.");
                        return;
                    }
                }
            }

            // 3️⃣ Ambil LAYANAN dari combo
            String layanan = cmbLayanan.getSelectedItem().toString();

            // 4️⃣ Ambil nilai input form
            double qty = Double.parseDouble(txtQuantity.getText());
            String keterangan = txtKeterangan.getText();
            int biayaTambahan = Integer.parseInt(txtBiayaTambahan.getText());
            double diskon = Double.parseDouble(cmbDiskon.getSelectedItem().toString());

            java.util.Date tgl = jDateChooser1.getDate();
            java.util.Date estimasi = jDateChooser2.getDate();
            java.util.Date tglAmbil = jDateChooser3.getDate();

            if (tgl == null || estimasi == null) {
                JOptionPane.showMessageDialog(this, "Tanggal dan Estimasi Selesai tidak boleh kosong.");
                return;
            }

            String status = jComboBox2.getSelectedItem().toString();
            String statusPembayaran = jComboBox1.getSelectedItem().toString();

            // 5️⃣ INSERT TRANSAKSI
            String sqlInsert = "INSERT INTO tb_transaksi "
                    + "(id_member, id_paket, qty, keterangan, tgl, estimasi_selesai, tgl_ambil, biaya_tambahan, diskon, status, status_pembayaran, layanan) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pst = conn.prepareStatement(sqlInsert)) {
                pst.setInt(1, idMember);
                pst.setInt(2, idPaket);
                pst.setDouble(3, qty);
                pst.setString(4, keterangan);
                pst.setTimestamp(5, new java.sql.Timestamp(tgl.getTime()));
                pst.setTimestamp(6, new java.sql.Timestamp(estimasi.getTime()));

                if (tglAmbil != null) {
                    pst.setTimestamp(7, new java.sql.Timestamp(tglAmbil.getTime()));
                } else {
                    pst.setNull(7, java.sql.Types.TIMESTAMP);
                }
                pst.setTimestamp(7, new java.sql.Timestamp(tglAmbil.getTime()));
                pst.setInt(8, biayaTambahan);
                pst.setDouble(9, diskon);
                pst.setString(10, status);
                pst.setString(11, statusPembayaran);
                pst.setString(12, layanan);

                pst.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "✅ Transaksi berhasil disimpan!");
            loadTransaksi();
            btnClearActionPerformed(null); // bersihkan form

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pastikan angka (Qty / Biaya / Diskon) diisi dengan benar.\n" + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error database: " + e.getMessage());
        }
        JOptionPane.showMessageDialog(this, "✅ Transaksi berhasil disimpan!");
        loadTransaksi();
        btnClearActionPerformed(null); // bersihkan form
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        JOptionPane.showMessageDialog(this, "Kembali ke menu utama!");
        new Menu_Utama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void cmbPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaketActionPerformed
        // TODO add your handling code here:
        String selectedPaket = (String) cmbPaket.getSelectedItem();
        if (selectedPaket == null || selectedPaket.equals("Pilih Paket")) {
            return;
        }

        try {
            String sql = "SELECT * FROM tb_paket WHERE nama_paket = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, selectedPaket);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String jenis = rs.getString("jenis");
                int harga = rs.getInt("harga");
                JOptionPane.showMessageDialog(this,
                        "Paket: " + selectedPaket
                        + "\nJenis: " + jenis
                        + "\nHarga: Rp " + harga);
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data paket: " + e.getMessage());
        }
    }//GEN-LAST:event_cmbPaketActionPerformed

    private void txtKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeteranganActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void cmbMember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMember1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMember1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        int selectedRow = tblTransaksi.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel yang ingin diperbarui terlebih dahulu!");
            return;
        }

        try {
            // Ambil input dari form
            if (cmbMember1.getSelectedIndex() <= 0 || cmbPaket.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Pilih Member dan Paket yang valid.");
                return;
            }

            String memberNama = cmbMember1.getSelectedItem().toString();
            String paketNama = cmbPaket.getSelectedItem().toString();
            String keterangan = txtKeterangan.getText();
            int biayaTambahan = Integer.parseInt(txtBiayaTambahan.getText());
            double qty = Double.parseDouble(txtQuantity.getText());
            double diskon = Double.parseDouble(cmbDiskon.getSelectedItem().toString());
            String statusPembayaran = jComboBox1.getSelectedItem().toString();
            String status = jComboBox2.getSelectedItem().toString();
            String layanan = cmbLayanan.getSelectedItem().toString();

            java.util.Date tgl = jDateChooser1.getDate();
            java.util.Date estimasi = jDateChooser2.getDate();
            java.util.Date tglAmbil = jDateChooser3.getDate();

            if (tgl == null || estimasi == null || tglAmbil == null) {
                JOptionPane.showMessageDialog(this, "Tanggal, Estimasi dan Tanggal Ambil tidak boleh kosong.");
                return;
            }

            // Ambil ID member
            int idMember = 0;
            String sqlMember = "SELECT id_member FROM tb_member WHERE nama = ?";
            try (PreparedStatement pstMember = conn.prepareStatement(sqlMember)) {
                pstMember.setString(1, memberNama);
                try (ResultSet rs = pstMember.executeQuery()) {
                    if (rs.next()) {
                        idMember = rs.getInt("id_member");
                    } else {
                        JOptionPane.showMessageDialog(this, "Member tidak ditemukan di database.");
                        return;
                    }
                }
            }

            // Ambil ID paket
            int idPaket = 0;
            String sqlPaket = "SELECT id_paket FROM tb_paket WHERE nama_paket = ?";
            try (PreparedStatement pstPaket = conn.prepareStatement(sqlPaket)) {
                pstPaket.setString(1, paketNama);
                try (ResultSet rs = pstPaket.executeQuery()) {
                    if (rs.next()) {
                        idPaket = rs.getInt("id_paket");
                    } else {
                        JOptionPane.showMessageDialog(this, "Paket tidak ditemukan di database.");
                        return;
                    }
                }
            }

            // Ambil id_transaksi dari tabel (kolom 0)
            int idTransaksi = Integer.parseInt(tblTransaksi.getValueAt(selectedRow, 0).toString());

            // Query update transaksi (sekaligus update layanan)
            String sqlUpdate = "UPDATE tb_transaksi "
                    + "SET id_member=?, id_paket=?, qty=?, keterangan=?, "
                    + "tgl=?, estimasi_selesai=?, tgl_ambil=?, biaya_tambahan=?, "
                    + "diskon=?, status=?, status_pembayaran=?, layanan=? "
                    + "WHERE id_transaksi=?";

            try (PreparedStatement pst = conn.prepareStatement(sqlUpdate)) {
                pst.setInt(1, idMember);
                pst.setInt(2, idPaket);
                pst.setDouble(3, qty);
                pst.setString(4, keterangan);
                pst.setTimestamp(5, new java.sql.Timestamp(tgl.getTime()));
                pst.setTimestamp(6, new java.sql.Timestamp(estimasi.getTime()));
                pst.setTimestamp(7, new java.sql.Timestamp(tglAmbil.getTime()));
                pst.setInt(8, biayaTambahan);
                pst.setDouble(9, diskon);
                pst.setString(10, status);
                pst.setString(11, statusPembayaran);
                pst.setString(12, layanan);
                pst.setInt(13, idTransaksi);

                int updated = pst.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "✅ Data transaksi berhasil diperbarui!");
                    loadTransaksi();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Data transaksi tidak ditemukan atau gagal diperbarui!");
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Pastikan angka diisi dengan benar! (" + ex.getMessage() + ")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui transaksi: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void cmbLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLayananActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblTransaksi.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data transaksi yang ingin dihapus terlebih dahulu!");
            return;
        }

        // Ambil id_transaksi dari kolom 0 (yang disembunyikan)
        int idTransaksi = Integer.parseInt(tblTransaksi.getValueAt(selectedRow, 0).toString());

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus transaksi dengan ID: " + idTransaksi + " ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi != JOptionPane.YES_OPTION) {
            return;
        }

        String sqlDelete = "DELETE FROM tb_transaksi WHERE id_transaksi = ?";

        try (PreparedStatement pst = conn.prepareStatement(sqlDelete)) {
            pst.setInt(1, idTransaksi);
            int deleted = pst.executeUpdate();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(this, "✅ Transaksi berhasil dihapus!");
                loadTransaksi();
                btnClearActionPerformed(null); // bersihkan form & disable tombol
            } else {
                JOptionPane.showMessageDialog(this, "❌ Gagal menghapus transaksi (data tidak ditemukan).");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error menghapus transaksi: " + e.getMessage());
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Menu_Transaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbDiskon;
    private javax.swing.JComboBox<String> cmbLayanan;
    private javax.swing.JComboBox<String> cmbMember1;
    private javax.swing.JComboBox<String> cmbPaket;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private com.toedter.calendar.JCalendar jCalendar3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBatasWaktu;
    private javax.swing.JLabel lblBatasWaktu1;
    private javax.swing.JLabel lblBiayaTambahan;
    private javax.swing.JLabel lblBiayaTambahan1;
    private javax.swing.JLabel lblBiayaTambahan2;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblDiskon1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblMember;
    private javax.swing.JLabel lblPaket;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtBiayaTambahan;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
