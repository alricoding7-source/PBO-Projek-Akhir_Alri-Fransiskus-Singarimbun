package com.perpustakaan.dao;

import com.perpustakaan.config.DatabaseConnection;
import com.perpustakaan.model.Pengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PenggunaDAO {

    public Pengguna login(String username, String password) {
        String sql = "SELECT id_pengguna, username, password, nama_lengkap, role "
                + "FROM pengguna WHERE username = ? AND password = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pengguna(
                            rs.getString("id_pengguna"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("nama_lengkap"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            return null;
        }

        return null;
    }
}