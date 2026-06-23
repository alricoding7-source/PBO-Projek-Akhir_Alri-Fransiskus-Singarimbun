package com.perpustakaan.model;

import java.util.Date;

public class RekapKas {
    private int idRekap;
    private Date tanggal; // Menggunakan nama tanggal sesuai catatan Anda
    private double totalMasuk;
    private double totalKeluar;
    private double saldoAkhir;

    // Constructor Kosong
    public RekapKas() {
    }

    // Constructor Lengkap
    public RekapKas(int idRekap, Date tanggal, double totalMasuk, double totalKeluar, double saldoAkhir) {
        this.idRekap = idRekap;
        this.tanggal = tanggal;
        this.totalMasuk = totalMasuk;
        this.totalKeluar = totalKeluar;
        this.saldoAkhir = saldoAkhir;
    }

    // Getter dan Setter
    public int getIdRekap() {
        return idRekap;
    }

    public void setIdRekap(int idRekap) {
        this.idRekap = idRekap;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotalMasuk() {
        return totalMasuk;
    }

    public void setTotalMasuk(double totalMasuk) {
        this.totalMasuk = totalMasuk;
    }

    public double getTotalKeluar() {
        return totalKeluar;
    }

    public void setTotalKeluar(double totalKeluar) {
        this.totalKeluar = totalKeluar;
    }

    public double getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(double saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }
}