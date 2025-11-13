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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lila
 */
public class Menu_Transaksi extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu_Transaksi.class.getName());

    public Menu_Transaksi() {
        initComponents();
        connect();
        loadMember();
        loadPaket();
        loadTransaksi();

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
            java.util.Date tgl, java.util.Date batas, java.util.Date tglAmbil,
            int biayaTambahan, double diskon, String status, String statusPembayaran) {

        String sql = "INSERT INTO tb_transaksi "
                + "(id_member, id_paket, qty, keterangan, tgl, batas_waktu, tgl_ambil, biaya_tambahan, diskon, status, status_pembayaran) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idMember);
            pst.setInt(2, idPaket);
            pst.setDouble(3, qty);
            pst.setString(4, keterangan);
            pst.setTimestamp(5, new java.sql.Timestamp(tgl.getTime()));
            pst.setTimestamp(6, new java.sql.Timestamp(batas.getTime()));
            pst.setTimestamp(7, new java.sql.Timestamp(tglAmbil.getTime()));
            pst.setInt(8, biayaTambahan);
            pst.setDouble(9, diskon);
            pst.setString(10, status);
            pst.setString(11, statusPembayaran);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Data transaksi berhasil disimpan ke database!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Gagal menyimpan transaksi: " + e.getMessage());
        }
    }

    private void loadTransaksi() {
    DefaultTableModel model = (DefaultTableModel) tblTransaksi.getModel();
    model.setRowCount(0);

    String sql = "SELECT t.tgl, m.nama, t.keterangan, t.biaya_tambahan, t.batas_waktu, p.nama_paket, " +
                 "t.qty, t.diskon, t.status_pembayaran, t.tgl_ambil, t.status " +
                 "FROM tb_transaksi t " +
                 "JOIN tb_member m ON t.id_member = m.id_member " +
                 "JOIN tb_paket p ON t.id_paket = p.id_paket";

    try (PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("tgl"),
                rs.getString("nama"),
                rs.getString("keterangan"),
                rs.getInt("biaya_tambahan"),
                rs.getString("batas_waktu"),
                rs.getString("nama_paket"),
                rs.getDouble("qty"),
                rs.getDouble("diskon"),
                rs.getString("status_pembayaran"),
                rs.getString("tgl_ambil"),
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data transaksi: " + e.getMessage());
    }
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

        btnClear.setBackground(new java.awt.Color(204, 204, 255));
        btnClear.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnClear.setText("Bersihkan");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(204, 204, 255));
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
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Member", "Keterangan", "Biaya Tambahan", "Estimasi Selesai", "Paket", "Quantity", "Diskon", "Status Pembayaran", "Tanggal Ambil", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        lblJudul.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setText("Menu Transaksi");

        btnHome.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
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
                .addGap(226, 226, 226)
                .addComponent(lblJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblJudul)
                        .addContainerGap(8, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblKeterangan)
                        .addComponent(lblMember, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTanggal, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lblBiayaTambahan)
                    .addComponent(lblBiayaTambahan1)
                    .addComponent(lblBiayaTambahan2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2))
                        .addComponent(txtBiayaTambahan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbMember1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKeterangan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBatasWaktu)
                    .addComponent(lblDiskon)
                    .addComponent(lblQuantity)
                    .addComponent(lblBatasWaktu1)
                    .addComponent(lblPaket))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQuantity)
                            .addComponent(cmbDiskon, javax.swing.GroupLayout.Alignment.TRAILING, 0, 318, Short.MAX_VALUE))
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblBatasWaktu))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cmbDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTanggal)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMember)
                            .addComponent(lblBatasWaktu1)
                            .addComponent(cmbMember1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKeterangan)
                            .addComponent(lblPaket))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBiayaTambahan)
                            .addComponent(txtBiayaTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBiayaTambahan1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDiskon)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBiayaTambahan2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null); // 🔹 tambahkan: kosongkan tanggal ambil
        cmbMember1.setSelectedIndex(0);
        txtKeterangan.setText("");
        txtBiayaTambahan.setText(""); // 🔹 perbaiki: sebelumnya getText()
        cmbPaket.setSelectedIndex(0);
        txtQuantity.setText("");
        cmbDiskon.setSelectedIndex(0);
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (jDateChooser1.getDate() == null || jDateChooser2.getDate() == null || jDateChooser3.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Pastikan semua tanggal sudah dipilih!");
            return;
        }

        // Ambil nilai dari form
        java.util.Date tgl = jDateChooser1.getDate();
        java.util.Date batas = jDateChooser2.getDate();
        java.util.Date tglAmbil = jDateChooser3.getDate();

        String memberNama = cmbMember1.getSelectedItem().toString();
        String paketNama = cmbPaket.getSelectedItem().toString();
        String keterangan = txtKeterangan.getText();
        String biaya = txtBiayaTambahan.getText();
        String quantity = txtQuantity.getText();
        String diskon = cmbDiskon.getSelectedItem().toString();
        String statusPembayaran = jComboBox1.getSelectedItem().toString();
        String status = jComboBox2.getSelectedItem().toString();

        if (memberNama.equals("Pilih Member") || paketNama.equals("Pilih Paket") || keterangan.isEmpty() || biaya.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi semua data terlebih dahulu!");
            return;
        }

        try {
            // Cari ID member
            String sqlMember = "SELECT id_member FROM tb_member WHERE nama = ?";
            PreparedStatement pstMember = conn.prepareStatement(sqlMember);
            pstMember.setString(1, memberNama);
            ResultSet rsMember = pstMember.executeQuery();
            int idMember = 0;
            if (rsMember.next()) {
                idMember = rsMember.getInt("id_member");
            }
            rsMember.close();
            pstMember.close();

            // Cari ID paket
            String sqlPaket = "SELECT id_paket FROM tb_paket WHERE nama_paket = ?";
            PreparedStatement pstPaket = conn.prepareStatement(sqlPaket);
            pstPaket.setString(1, paketNama);
            ResultSet rsPaket = pstPaket.executeQuery();
            int idPaket = 0;
            if (rsPaket.next()) {
                idPaket = rsPaket.getInt("id_paket");
            }
            rsPaket.close();
            pstPaket.close();

            // Panggil fungsi simpan ke database
            simpanTransaksi(
                    idMember, idPaket, Double.parseDouble(quantity), keterangan,
                    tgl, batas, tglAmbil, Integer.parseInt(biaya), Double.parseDouble(diskon),
                    status, statusPembayaran
            );

            // Tambahkan juga ke tabel GUI
            DefaultTableModel model = (DefaultTableModel) tblTransaksi.getModel();
            model.insertRow(0, new Object[]{
                sdf.format(tgl), memberNama, keterangan, biaya,
                sdf.format(batas), paketNama, quantity, diskon,
                statusPembayaran, sdf.format(tglAmbil), status
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka salah di kolom biaya/quantity/diskon!");
        }
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
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbDiskon;
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
