# Aplikasi CRUD Data Buku Perpustakaan (Java Maven + Swing + JDBC + MySQL)

Project ini adalah contoh aplikasi desktop sederhana untuk pemula menggunakan:

- Java
- Java with Maven
- Java Swing (`JFrame`)
- JDBC
- MySQL

Fitur yang tersedia:

- Login pengguna berbasis database
- Dashboard menu utama berbasis menubar setelah login
- Tambah data buku
- Edit data buku
- Hapus data buku
- Tampilkan data buku di `JTable`
- Cari data buku berdasarkan judul
- Refresh tabel data buku
- Reset form
- Validasi input kosong
- Klik baris `JTable` menampilkan data ke form
- Pesan sukses/gagal menggunakan `JOptionPane`
- ID Buku otomatis (format: B001, B002, dst)

## Struktur Class

1. `com.perpustakaan.config.DatabaseConnection`
- Tugas: membuat koneksi JDBC ke MySQL.
- Konfigurasi dibaca dari file `src/main/resources/db.properties`.

2. `com.perpustakaan.model.Buku`
- Tugas: class model/entitas data buku.
- Memiliki field: `idBuku`, `judulBuku`, `penulis`, `stok`.

3. `com.perpustakaan.dao.BukuDAO`
- Tugas: class DAO untuk operasi CRUD database.
- Method utama:
  - `insert(Buku buku)`
  - `update(Buku buku)`
  - `delete(String idBuku)`
  - `getAll()`
  - `searchByJudul(String keyword)`
  - `getNextId()` untuk generate ID buku otomatis.

4. `com.perpustakaan.model.Pengguna`
- Tugas: class model/entitas data pengguna.
- Memiliki field: `idPengguna`, `username`, `password`, `namaLengkap`, `role`.

5. `com.perpustakaan.dao.PenggunaDAO`
- Tugas: class DAO untuk proses login pengguna.
- Method utama:
  - `login(String username, String password)`.

6. `com.perpustakaan.view.LoginForm`
- Tugas: tampilan login awal aplikasi Swing (`JFrame`).
- Validasi username dan password.
- Jika login berhasil, akan membuka `DashboardForm`.

7. `com.perpustakaan.view.DashboardForm`
- Tugas: menu dashboard utama aplikasi (`JFrame`) yang bisa didesain via NetBeans.
- Menggunakan `JMenuBar` sebagai navigasi utama.
- Membuka form fitur sebagai child form di area `JDesktopPane`.
- Menyediakan menu `Data Buku`, `Logout`, dan `Keluar`.

8. `com.perpustakaan.view.BukuForm`
- Tugas: tampilan data buku Swing sebagai child form (`JInternalFrame`).
- Berisi form input, tombol CRUD, pencarian, dan tabel data.
- Semua event tombol ada di class ini.
- ID Buku dibuat otomatis saat form reset.
- Tombol `Refresh` untuk memuat ulang seluruh data tabel.
- Struktur class mengikuti pola NetBeans GUI Builder (`initComponents`, `GEN-BEGIN`, `GEN-END`).

9. `com.perpustakaan.App`
- Tugas: `main class` untuk menjalankan aplikasi.
- Saat ini aplikasi dimulai dari `LoginForm`.

File Form Designer untuk NetBeans:

- `src/main/java/com/perpustakaan/view/LoginForm.form`
- `src/main/java/com/perpustakaan/view/DashboardForm.form`
- `src/main/java/com/perpustakaan/view/BukuForm.form`

## Komponen Swing yang Digunakan

- `JFrame`
- `JPanel`
- `JLabel`
- `JTextField`
- `JButton`
- `JTable`
- `JScrollPane`
- `JOptionPane`

Tombol pada form:

- `Simpan`
- `Edit`
- `Hapus`
- `Reset`
- `Cari`
- `Refresh`

## File Konfigurasi Database

Edit file `src/main/resources/db.properties` sesuai MySQL Anda:

```properties
db.url=jdbc:mysql://localhost:3306/perpustakaan_db?useSSL=false&serverTimezone=Asia/Jakarta
db.username=root
db.password=
```

## SQL Database

Jalankan file SQL berikut di MySQL:

- `database/library_maven.sql`

SQL ini akan:

- Membuat database `perpustakaan_db`
- Membuat tabel `buku`
- Membuat tabel `pengguna`
- Menambahkan data contoh

Kredensial default login:

- Username: `admin`
- Password: `admin123`

## Cara Menjalankan di NetBeans (Java with Maven)

1. Buka NetBeans.
2. Pilih menu **File > Open Project**.
3. Pilih folder project ini: `Library-Maven`.
4. Pastikan MySQL sudah aktif.
5. Jalankan SQL `database/library_maven.sql` di MySQL.
6. Sesuaikan `db.properties` jika username/password MySQL berbeda.
7. Klik kanan project > **Run**.
8. Aplikasi `CRUD Data Buku Perpustakaan` akan tampil.

## Catatan untuk NetBeans JFrame Form (Drag and Drop)

Agar bisa edit UI dengan drag and drop di Form Designer:

1. Buat package `com.perpustakaan.view` (jika belum ada).
2. Buat file baru: **New > Swing GUI Forms > JFrame Form** dengan nama `BukuForm`.
3. NetBeans akan membuat pasangan file `.java` + `.form`.
4. Anda bisa memindahkan logika event dari class `BukuForm` pada project ini ke form hasil drag and drop.

Project ini juga sudah menyertakan file Form Designer:

- `src/main/java/com/perpustakaan/view/BukuForm.form`

Class `BukuForm.java` juga sudah disiapkan dengan pola generated NetBeans sehingga lebih mudah dipelihara saat edit drag and drop.

Jika NetBeans menyesuaikan isi `.form` saat pertama kali dibuka, itu normal.

Tujuan contoh ini adalah latihan dasar CRUD, jadi kode dibuat sederhana dan mudah dibaca.
