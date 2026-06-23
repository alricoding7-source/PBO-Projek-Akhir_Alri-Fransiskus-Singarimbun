package com.perpustakaan.dao;

import com.perpustakaan.config.DatabaseConnection;
import com.perpustakaan.model.RekapKas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RekapKasDAO {

    // Method untuk mengambil semua data rekap kas dari database
    public List<RekapKas> getAll() {
        List<List<RekapKas>> list = new ArrayList<>(); // Digunakan jika ingin mengembalikan list data
        List<RekapKas> rekapList = new ArrayList<>();
        
        // QUERY UTAMA: Menggabungkan dan menghitung langsung dari tabel transaksi
        // id_jenis = 1 (Kas Masuk), id_jenis = 2 (Kas Keluar) sesuai isi db_kas.sql
        String sql = "SELECT "
                   + "  DATE(tanggal) AS tgl, "
                   + "  SUM(CASE WHEN id_jenis = 1 THEN jumlah ELSE 0 END) AS total_masuk, "
                   + "  SUM(CASE WHEN id_jenis = 2 THEN jumlah ELSE 0 END) AS total_keluar, "
                   + "  (SUM(CASE WHEN id_jenis = 1 THEN jumlah ELSE 0 END) - SUM(CASE WHEN id_jenis = 2 THEN jumlah ELSE 0 END)) AS saldo "
                   + "FROM transaksi "
                   + "GROUP BY DATE(tanggal) "
                   + "ORDER BY tgl DESC";

        try (Connection conn = DatabaseConnection.getConnection(); // Sesuaikan dengan kelas Koneksi Anda
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RekapKas r = new RekapKas();
                r.setTanggal(rs.getDate("tgl"));
                r.setTotalMasuk(rs.getDouble("total_masuk"));
                r.setTotalKeluar(rs.getDouble("total_keluar"));
                r.setSaldoAkhir(rs.getDouble("saldo"));
                
                rekapList.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rekapList;
    }
}