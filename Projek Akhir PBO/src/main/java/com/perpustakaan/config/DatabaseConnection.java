package com.perpustakaan.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final Properties PROPERTIES = loadProperties();

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        String url = PROPERTIES.getProperty("db.url");
        String username = PROPERTIES.getProperty("db.username");
        String password = PROPERTIES.getProperty("db.password");
        return DriverManager.getConnection(url, username, password);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException("File db.properties tidak ditemukan di resources.");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Gagal membaca konfigurasi database.", e);
        }
    }
}
