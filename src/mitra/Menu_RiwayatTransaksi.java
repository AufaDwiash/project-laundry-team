package Mitra;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Menu_RiwayatTransaksi extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu_RiwayatTransaksi.class.getName());

    public Menu_RiwayatTransaksi() {
        initComponents();
        loadData();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMenuRiwayatTransaksi = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenuRiwayatTransaksi = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMenuRiwayatTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMenuRiwayatTransaksi.setText("Menu Riwayat Transaksi");

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        tblMenuRiwayatTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Member", "Keterangan", "Biaya Tambahan", "Tanggal Ambil", "Paket", "Quantity", "Diskon"
            }
        ));
        jScrollPane1.setViewportView(tblMenuRiwayatTransaksi);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMenuRiwayatTransaksi)
                        .addGap(136, 136, 136)
                        .addComponent(btnHome)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMenuRiwayatTransaksi)
                    .addComponent(btnHome))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus)
                    .addComponent(btnRefresh))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static Connection configDB() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/db_laundry";
        String user = "root";
        String pass = "";

        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    
    private void loadData(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tanggal");
        model.addColumn("Member");
        model.addColumn("Keterangan");
        model.addColumn("Biaya Tambahan");
        model.addColumn("Estimasi Selesai");
        model.addColumn("Tanggal Ambil");
        model.addColumn("Paket");
        model.addColumn("Quantity");
        model.addColumn("Diskon");
        
        try (Connection conn = configDB();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(
                "SELECT t.tgl, id_member, t.keterangan, t.biaya_tambahan, " +
                "t.estimasi_selesai, t.tgl_ambil, p.nama_paket AS paket, t.qty, t.diskon " +
                "FROM tb_transaksi t " +
                "LEFT JOIN tb_paket p ON t.id_paket = p.id_paket"
             )) {
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("tgl"),
                    rs.getString("id_member"),
                    rs.getString("keterangan"),
                    rs.getString("biaya_tambahan"),
                    rs.getString("estimasi_selesai"),
                    rs.getString("tgl_ambil"),
                    rs.getString("paket"),
                    rs.getString("qty"),
                    rs.getString("diskon")
                });
            }

            tblMenuRiwayatTransaksi.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data: " + e.getMessage());
        }  
    }
    
    private void hapusData(){
        int selectedRow = tblMenuRiwayatTransaksi.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris yang ingin dihapus terlebih dahulu!");
            return;
        }

        String tanggal = tblMenuRiwayatTransaksi
                .getValueAt(selectedRow, 0)
                .toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah yakin ingin menghapus data dengan tanggal " + tanggal + " ?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = configDB()) {
                String sql = "DELETE FROM tb_transaksi WHERE tgl = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, tanggal);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                loadData(); 

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Gagal menghapus data: " + e.getMessage());
            }
        }
    }
    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        JOptionPane.showMessageDialog(this, "Kembali ke menu utama!");
        new Menu_Utama().setVisible(true);
            this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusData();
                
    }//GEN-LAST:event_btnHapusActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new Menu_RiwayatTransaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMenuRiwayatTransaksi;
    private javax.swing.JTable tblMenuRiwayatTransaksi;
    // End of variables declaration//GEN-END:variables

    }
