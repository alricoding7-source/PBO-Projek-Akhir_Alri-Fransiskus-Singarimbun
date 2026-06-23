package com.perpustakaan.model;

import java.util.Date;

public class Transaksi {

    private int idTransaksi;
    private Date tanggal;
    private Integer idPerson; // Menggunakan Integer agar bisa bernilai null (Transaksi Umum)
    private int idJenis;
    private double jumlah;
    private String keterangan;

    // Properti Relasional Tambahan untuk mempermudah visualisasi di JTable
    private String namaPerson;
    private String namaJenis;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, Date tanggal, Integer idPerson, int idJenis, double jumlah, String keterangan) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.idPerson = idPerson;
        this.idJenis = idJenis;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(int idJenis) {
        this.idJenis = idJenis;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPerson() {
        return namaPerson;
    }

    public void setNamaPerson(String namaPerson) {
        this.namaPerson = namaPerson;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }
}