package com.perpustakaan.dao;

import com.perpustakaan.config.DatabaseConnection;
import com.perpustakaan.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public boolean insert(Person person) {
        String sql = "INSERT INTO person (nama, alamat, telepon, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, person.getNama());
            stmt.setString(2, person.getAlamat());
            stmt.setString(3, person.getTelepon());
            stmt.setString(4, person.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(Person person) {
        String sql = "UPDATE person SET nama = ?, alamat = ?, telepon = ?, email = ? WHERE id_person = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, person.getNama());
            stmt.setString(2, person.getAlamat());
            stmt.setString(3, person.getTelepon());
            stmt.setString(4, person.getEmail());
            stmt.setInt(5, person.getIdPerson());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int idPerson) {
        String sql = "DELETE FROM person WHERE id_person = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPerson);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Person> getAll() {
        String sql = "SELECT id_person, nama, alamat, telepon, email FROM person ORDER BY id_person";
        List<Person> list = new ArrayList<>();

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

    public List<Person> searchByNama(String keyword) {
        String sql = "SELECT id_person, nama, alamat, telepon, email FROM person WHERE nama LIKE ? ORDER BY id_person";
        List<Person> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

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
        String sql = "SELECT id_person FROM person ORDER BY id_person DESC LIMIT 1";
        String defaultId = "1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.next()) {
                return defaultId;
            }

            int lastId = rs.getInt("id_person");
            return String.valueOf(lastId + 1);
        } catch (Exception e) {
            return defaultId;
        }
    }

    private Person mapResultSet(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt("id_person"),
                rs.getString("nama"),
                rs.getString("alamat"),
                rs.getString("telepon"),
                rs.getString("email")
        );
    }
}