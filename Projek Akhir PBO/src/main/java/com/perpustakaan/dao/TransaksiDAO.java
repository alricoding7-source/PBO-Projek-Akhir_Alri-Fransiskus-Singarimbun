package com.perpustakaan.dao;

import com.perpustakaan.config.DatabaseConnection;
import com.perpustakaan.model.Transaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public boolean insert(Transaksi t) {
        String sql = "INSERT INTO transaksi (tanggal, id_person, id_jenis, jumlah, keterangan) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(t.getTanggal().getTime()));
            if (t.getIdPerson() == null || t.getIdPerson() == 0) {
                stmt.setNull(2, Types.INTEGER);
            } else {
                stmt.setInt(2, t.getIdPerson());
            }
            stmt.setInt(3, t.getIdJenis());
            stmt.setDouble(4, t.getJumlah());
            stmt.setString(5, t.getKeterangan());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(Transaksi t) {
        String sql = "UPDATE transaksi SET id_person = ?, id_jenis = ?, jumlah = ?, keterangan = ? WHERE id_transaksi = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (t.getIdPerson() == null || t.getIdPerson() == 0) {
                stmt.setNull(1, Types.INTEGER);
            } else {
                stmt.setInt(1, t.getIdPerson());
            }
            stmt.setInt(2, t.getIdJenis());
            stmt.setDouble(3, t.getJumlah());
            stmt.setString(4, t.getKeterangan());
            stmt.setInt(5, t.getIdTransaksi());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTransaksi);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Transaksi> getAll() {
        String sql = "SELECT t.*, COALESCE(p.nama, 'Umum / Tanpa Person') AS nama_person, j.nama_jenis " +
                     "FROM transaksi t " +
                     "LEFT JOIN person p ON t.id_person = p.id_person " +
                     "JOIN jenis_transaksi j ON t.id_jenis = j.id_jenis " +
                     "ORDER BY t.id_transaksi DESC";
        List<Transaksi> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            return list;
        }
        return list;
    }

    public List<Transaksi> search(String keyword) {
        String sql = "SELECT t.*, COALESCE(p.nama, 'Umum / Tanpa Person') AS nama_person, j.nama_jenis " +
                     "FROM transaksi t " +
                     "LEFT JOIN person p ON t.id_person = p.id_person " +
                     "JOIN jenis_transaksi j ON t.id_jenis = j.id_jenis " +
                     "WHERE t.keterangan LIKE ? OR p.nama LIKE ? OR j.nama_jenis LIKE ? " +
                     "ORDER BY t.id_transaksi DESC";
        List<Transaksi> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            stmt.setString(1, key);
            stmt.setString(2, key);
            stmt.setString(3, key);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            return list;
        }
        return list;
    }

    public String getNextId() {
        String sql = "SELECT id_transaksi FROM transaksi ORDER BY id_transaksi DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return String.valueOf(rs.getInt("id_transaksi") + 1);
            }
            return "1";
        } catch (Exception e) {
            return "1";
        }
    }

    private Transaksi mapResultSet(ResultSet rs) throws SQLException {
        Transaksi t = new Transaksi();
        t.setIdTransaksi(rs.getInt("id_transaksi"));
        t.setTanggal(rs.getTimestamp("tanggal"));
        t.setIdPerson(rs.getInt("id_person"));
        if (rs.wasNull()) {
            t.setIdPerson(null);
        }
        t.setIdJenis(rs.getInt("id_jenis"));
        t.setJumlah(rs.getDouble("jumlah"));
        t.setKeterangan(rs.getString("keterangan"));
        t.setNamaPerson(rs.getString("nama_person"));
        t.setNamaJenis(rs.getString("nama_jenis"));
        return t;
    }
}