package Mitra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;

public class Menu_RiwayatTransaksi extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu_RiwayatTransaksi.class.getName());
    private String currentUsername;

    public Menu_RiwayatTransaksi() {
        initComponents();
        loadData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenuRiwayatTransaksi = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        btnRiwayat = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnPaket = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateDari = new com.toedter.calendar.JDateChooser();
        jDateSampai = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnExportPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMenuRiwayatTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID", "Tanggal", "Nama", "Paket", "Layanan", "Qty", "Biaya Tamb", "Diskon", "Total", "Status Pembyrn", "Status", "Tgl Ambil", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblMenuRiwayatTransaksi);

        btnHapus.setBackground(new java.awt.Color(102, 204, 255));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(102, 204, 255));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        lblJudul.setFont(new java.awt.Font("Imprint MT Shadow", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setText("Menu  Riwayat Transaksi");

        btnRiwayat.setBackground(new java.awt.Color(204, 204, 204));
        btnRiwayat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRiwayat.setText("Riwayat");
        btnRiwayat.setBorder(null);
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });

        btnTransaksi.setBackground(new java.awt.Color(102, 204, 255));
        btnTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        btnTransaksi.setText("Transaksi");
        btnTransaksi.setBorder(null);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });

        btnPaket.setBackground(new java.awt.Color(102, 204, 255));
        btnPaket.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPaket.setForeground(new java.awt.Color(255, 255, 255));
        btnPaket.setText("Paket");
        btnPaket.setBorder(null);
        btnPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaketActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Member");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnHome.setBackground(new java.awt.Color(255, 0, 0));
        btnHome.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("Logout");
        btnHome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255), null));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnUser.setBackground(new java.awt.Color(102, 204, 255));
        btnUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setText("Akun");
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblJudul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRiwayat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPaket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setText("Dari Tanggal:");

        jLabel2.setText("Sampai Tanggal:");

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Filter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 255, 0));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnExportPdf.setBackground(new java.awt.Color(255, 0, 0));
        btnExportPdf.setText("Export PDF");
        btnExportPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateDari, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jDateSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExportPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(btnReset)
                        .addComponent(btnExportPdf))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addGap(122, 122, 122)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static Connection configDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_laundry";
        String user = "root";
        String pass = "";

        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Member");
        model.addColumn("Keterangan");
        model.addColumn("Biaya Tambahan");
        model.addColumn("Estimasi Selesai");
        model.addColumn("Tanggal Ambil");
        model.addColumn("Paket");
        model.addColumn("Quantity");
        model.addColumn("Diskon");
        model.addColumn("Total");
        model.addColumn("Status");
        model.addColumn("Status Pembayaran");
        model.addColumn("Layanan");

        String sql
                = "SELECT t.id_transaksi, t.tgl, m.nama AS member, t.keterangan, "
                + "       t.biaya_tambahan, t.estimasi_selesai, t.tgl_ambil, "
                + "       p.nama_paket AS paket, t.qty, t.diskon, "
                + "       (IFNULL(p.harga,0)*t.qty + t.biaya_tambahan - t.diskon) AS total, "
                + "       t.status, t.status_pembayaran, t.layanan "
                + "FROM tb_transaksi t "
                + "JOIN tb_member m ON t.id_member = m.id_member "
                + "LEFT JOIN tb_paket p ON t.id_paket = p.id_paket "
                + "ORDER BY t.id_transaksi DESC";

        try (Connection conn = configDB(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_transaksi"),
                    rs.getString("tgl"),
                    rs.getString("member"),
                    rs.getString("keterangan"),
                    rs.getInt("biaya_tambahan"),
                    rs.getString("estimasi_selesai"),
                    rs.getString("tgl_ambil"),
                    rs.getString("paket"),
                    rs.getDouble("qty"),
                    rs.getDouble("diskon"),
                    rs.getDouble("total"),
                    rs.getString("status"),
                    rs.getString("status_pembayaran"),
                    rs.getString("layanan")
                });
            }

            tblMenuRiwayatTransaksi.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data: " + e.getMessage());
        }
    }

    /* ========================= HAPUS DATA (BERDASARKAN ID) ========================= */
    private void hapusData() {
        int selectedRow = tblMenuRiwayatTransaksi.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris yang ingin dihapus terlebih dahulu!");
            return;
        }

        String idTransaksiStr = tblMenuRiwayatTransaksi
                .getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah yakin ingin menghapus transaksi ID " + idTransaksiStr + " ?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = configDB()) {
                String sql = "DELETE FROM tb_transaksi WHERE id_transaksi = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(idTransaksiStr));
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                loadData();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Gagal menghapus data: " + e.getMessage());
            }
        }
    }

    /* ========================= FILTER TANGGAL ========================= */
    private void filterTanggal() {
        java.util.Date dari = jDateDari.getDate();
        java.util.Date sampai = jDateSampai.getDate();

        if (dari == null || sampai == null) {
            JOptionPane.showMessageDialog(this,
                    "Pilih tanggal awal dan tanggal akhir terlebih dahulu!");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tglDari = sdf.format(dari);
        String tglSampai = sdf.format(sampai);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Member");
        model.addColumn("Keterangan");
        model.addColumn("Biaya Tambahan");
        model.addColumn("Estimasi Selesai");
        model.addColumn("Tanggal Ambil");
        model.addColumn("Paket");
        model.addColumn("Quantity");
        model.addColumn("Diskon");
        model.addColumn("Total");
        model.addColumn("Status");
        model.addColumn("Status Pembayaran");
        model.addColumn("Layanan");

        String sql
                = "SELECT t.id_transaksi, t.tgl, m.nama AS member, t.keterangan, "
                + "       t.biaya_tambahan, t.estimasi_selesai, t.tgl_ambil, "
                + "       p.nama_paket AS paket, t.qty, t.diskon, "
                + "       (IFNULL(p.harga,0)*t.qty + t.biaya_tambahan - t.diskon) AS total, "
                + "       t.status, t.status_pembayaran, t.layanan "
                + "FROM tb_transaksi t "
                + "JOIN tb_member m ON t.id_member = m.id_member "
                + "LEFT JOIN tb_paket p ON t.id_paket = p.id_paket "
                + "WHERE DATE(t.tgl) BETWEEN ? AND ? "
                + "ORDER BY t.id_transaksi DESC";

        try (Connection conn = configDB(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tglDari);
            pst.setString(2, tglSampai);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_transaksi"),
                    rs.getString("tgl"),
                    rs.getString("member"),
                    rs.getString("keterangan"),
                    rs.getInt("biaya_tambahan"),
                    rs.getString("estimasi_selesai"),
                    rs.getString("tgl_ambil"),
                    rs.getString("paket"),
                    rs.getDouble("qty"),
                    rs.getDouble("diskon"),
                    rs.getDouble("total"),
                    rs.getString("status"),
                    rs.getString("status_pembayaran"),
                    rs.getString("layanan")
                });
            }

            tblMenuRiwayatTransaksi.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memfilter data: " + e.getMessage());
        }
    }

    private void exportTabelKePdf() {
        // kalau tabel kosong, tidak usah export
        if (tblMenuRiwayatTransaksi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Tidak ada data di tabel untuk diexport.");
            return;
        }

        // dialog pilih lokasi file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Simpan Laporan PDF");
        chooser.setSelectedFile(new File("Laporan_Riwayat_Transaksi.pdf"));

        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return; // user cancel
        }

        File file = chooser.getSelectedFile();
        String outputPath = file.getAbsolutePath();
        // pastikan ekstensi .pdf
        if (!outputPath.toLowerCase().endsWith(".pdf")) {
            outputPath += ".pdf";
        }

        Document document = null;
        try {
            // A4 landscape + margin
            document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // font
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

            // judul
            Paragraph title = new Paragraph("LAPORAN RIWAYAT TRANSAKSI LAUNDRY", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // info tanggal cetak
            String tglCetak = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .format(new java.util.Date());
            Paragraph infoCetak = new Paragraph("Dicetak pada: " + tglCetak, normalFont);
            infoCetak.setAlignment(Element.ALIGN_RIGHT);
            infoCetak.setSpacingAfter(10f);
            document.add(infoCetak);

            // tabel PDF
            int colCount = tblMenuRiwayatTransaksi.getColumnCount();
            PdfPTable table = new PdfPTable(colCount);
            table.setWidthPercentage(100);

            // ID, Tanggal, Member, Keterangan, Biaya Tamb, Estimasi, Tgl Ambil,
            // Paket, Qty, Diskon, Total, Status, Status Bayar, Layanan
            float[] widths = new float[]{
                3f, 6f, 6f, 10f, 5f, 6f, 6f,
                6f, 4f, 4f, 5f, 5f, 6f, 5f
            };
            if (colCount == widths.length) {
                table.setWidths(widths);
            }

            // header tabel
            for (int i = 0; i < colCount; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(
                        tblMenuRiwayatTransaksi.getColumnName(i), headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(230, 230, 230));
                cell.setPadding(4f);
                table.addCell(cell);
            }
            table.setHeaderRows(1);

            // isi data
            int rowCount = tblMenuRiwayatTransaksi.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = tblMenuRiwayatTransaksi.getValueAt(row, col);
                    String text = (value == null) ? "" : value.toString();

                    PdfPCell cell = new PdfPCell(new Phrase(text, normalFont));
                    cell.setPadding(3f);
                    cell.setUseDescender(true);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setNoWrap(false); // biar bisa wrap

                    // zebra row
                    if (row % 2 == 1) {
                        cell.setBackgroundColor(new BaseColor(248, 248, 248));
                    }

                    // rata kanan untuk angka uang
                    if (col == 4 || col == 9 || col == 10) {
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    } else if (col == 0 || col == 8) {
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    } else {
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    }

                    table.addCell(cell);
                }
            }

            document.add(table);

            JOptionPane.showMessageDialog(this,
                    "Laporan PDF berhasil dibuat di:\n" + outputPath);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal membuat laporan PDF: " + ex.getMessage());
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
        }
    }


    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        jDateDari.setDate(null);
        jDateSampai.setDate(null);
        loadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusData();

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        int pilih = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (pilih == JOptionPane.YES_OPTION) {
            // Jika user pilih "YA"
            new Menu_Login().setVisible(true);
            this.dispose();   // tutup halaman sekarang
        } else {
            // Jika user pilih "TIDAK"
            // Tidak melakukan apa-apa → tetap di Menu_User
        }
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiwayatActionPerformed
        new Menu_RiwayatTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRiwayatActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        new Menu_Transaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaketActionPerformed
        new Menu_Paket().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPaketActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new Menu_Member().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        filterTanggal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        jDateDari.setDate(null);
        jDateSampai.setDate(null);
        loadData();  // tampilkan semua data lagi
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExportPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPdfActionPerformed
        // TODO add your handling code here:
        exportTabelKePdf();
    }//GEN-LAST:event_btnExportPdfActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        new Menu_User(currentUsername).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUserActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new Menu_RiwayatTransaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportPdf;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPaket;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateDari;
    private com.toedter.calendar.JDateChooser jDateSampai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JTable tblMenuRiwayatTransaksi;
    // End of variables declaration//GEN-END:variables

}
