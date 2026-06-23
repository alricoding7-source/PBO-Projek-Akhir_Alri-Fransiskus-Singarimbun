package com.perpustakaan.model;

public class Pengguna {

    private String idPengguna;
    private String username;
    private String password;
    private String namaLengkap;
    private String role;

    public Pengguna() {
    }

    public Pengguna(String idPengguna, String username, String password, String namaLengkap, String role) {
        this.idPengguna = idPengguna;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
        this.role = role;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}