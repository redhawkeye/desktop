# Tugas 2 - Aplikasi Restoran dengan For-Each Loop

Aplikasi restoran dengan fitur CRUD (Create, Read, Update, Delete) lengkap dan implementasi **for-each loop** untuk iterasi data.

## 📋 Deskripsi

Program ini adalah pengembangan aplikasi restoran yang memisahkan antara menu pelanggan dan menu pengelolaan. Menggunakan **for-each loop** untuk iterasi array dan nested class untuk struktur data yang lebih terorganisir.

## ✨ Fitur Utama

### 1. Menu Pelanggan (Pemesanan)
- Input pesanan tanpa batas jumlah item
- Input nama menu dan jumlah
- Ketik "selesai" untuk mengakhiri pesanan
- Pencarian menu otomatis (case-insensitive)
- Cetak struk otomatis setelah pemesanan
- Perhitungan promo dan diskon lengkap

### 2. Menu Pengelolaan Restoran

#### A. Tambah Menu
- Tambah menu makanan atau minuman baru
- Input: nama, harga, kategori
- Validasi kategori (makanan/minuman)
- Kapasitas: 50 menu

#### B. Ubah Harga Menu
- Tampilkan daftar menu
- Cari menu berdasarkan nama
- Konfirmasi sebelum mengubah harga
- Update harga menu yang dipilih

#### C. Hapus Menu
- Tampilkan daftar menu
- Cari menu berdasarkan nama
- Konfirmasi sebelum menghapus
- Shift array untuk menghapus item

### 3. Cetak Struk
- Detail pesanan dengan subtotal per item
- Perhitungan otomatis:
  - **Subtotal** pesanan
  - **Pajak**: 10% dari subtotal
  - **Biaya Pelayanan**: Rp 20.000
  - **Diskon 10%**: jika total > Rp 100.000
  - **Promo Beli 1 Gratis 1**: jika total > Rp 50.000 dan beli minimal 2 minuman (gratis minuman termurah dari pesanan)

## 🎯 Konsep Pemrograman

- **For-Each Loop**: Iterasi array dengan sintaks yang lebih ringkas
- **Nested Class**: Class `Menu` sebagai inner static class
- **Array**: Struktur data untuk menu dan pesanan (kapasitas 50)
- **Input Validation**: Validasi input dengan try-catch
- **Method Overloading**: Fungsi helper `safeInt()` untuk input aman
- **String Manipulation**: `equalsIgnoreCase()` untuk pencarian
- **Array Shifting**: Hapus element dari array dengan shift

## 🍽️ Menu Default

### Makanan (4 item)
- Nasi Goreng - Rp 25.000
- Ayam Bakar - Rp 30.000
- Mie Ayam - Rp 20.000
- Soto Ayam - Rp 22.000

### Minuman (4 item)
- Es Teh - Rp 7.000
- Es Jeruk - Rp 9.000
- Kopi Hitam - Rp 12.000
- Susu Coklat - Rp 15.000

## 🚀 Cara Menjalankan

### Prasyarat
- JDK 8 atau lebih tinggi

### Kompilasi
```bash
javac Main.java
```

### Jalankan
```bash
java Main
```

## 💡 Contoh Penggunaan

### Pemesanan
```
======= APLIKASI RESTORAN =======
1. Menu Pelanggan (Pemesanan)
2. Menu Pengelolaan Restoran
3. Keluar
Pilih menu: 1

===== MENU PELANGGAN =====

------ DAFTAR MENU ------
-- MAKANAN --
Nasi Goreng - Rp 25000
Ayam Bakar - Rp 30000
...

Masukkan nama menu (atau 'selesai'): Nasi Goreng
Jumlah: 2
Pesanan ditambahkan!

Masukkan nama menu (atau 'selesai'): Es Teh
Jumlah: 1
Pesanan ditambahkan!

Masukkan nama menu (atau 'selesai'): selesai
```

### Ubah Harga Menu
```
===== MENU PENGELOLAAN RESTORAN =====
1. Tambah Menu
2. Ubah Harga Menu
3. Hapus Menu
4. Kembali
Pilih: 2

------ DAFTAR MENU ------
...
Masukkan nama menu yang ingin diubah: Nasi Goreng
Yakin ingin mengubah harga? (Ya/Tidak): Ya
Harga baru: 27000
Harga berhasil diubah!
```

## 📊 Struktur Program

```
Main.java
├── Class Menu (static nested class)
│   └── Fields: nama, harga, kategori
└── Main Class
    ├── Static Arrays
    │   ├── menuList[] (array Menu)
    │   ├── pesananNama[] (nama menu pesanan)
    │   └── pesananJumlah[] (jumlah per pesanan)
    └── Methods
        ├── main()
        ├── tambahMenuDefault()
        ├── menuPelanggan()
        ├── menuPengelolaan()
        ├── tambahMenu()
        ├── ubahHargaMenu()
        ├── hapusMenu()
        ├── cetakStruk()
        ├── hargaMinumanTermurahDariPesanan()
        ├── tampilkanMenu() → for-each loop
        ├── cariMenu() → for loop with index
        └── safeInt()
```

## 📝 Catatan Penting

1. **For-Each Loop**: Digunakan di `tampilkanMenu()` untuk iterasi menu
2. **For Loop Biasa**: Digunakan di `cariMenu()` karena butuh index
3. **Input Validation**: `safeInt()` menangani input non-numerik
4. **Case-Insensitive**: Pencarian menu tidak case-sensitive
5. **Konfirmasi**: Ubah dan hapus menu memerlukan konfirmasi "Ya"
6. **Promo Minuman**: Gratis minuman termurah dari pesanan (bukan dari semua menu)

## 🔄 Alur Program

### Menu Pelanggan
1. Tampilkan menu dengan for-each loop
2. Input nama menu dan jumlah (loop sampai "selesai")
3. Cari menu di array
4. Simpan ke array pesanan
5. Cetak struk dengan perhitungan lengkap

### Menu Pengelolaan
1. Pilih operasi CRUD
2. **Tambah**: Tambah ke akhir array
3. **Ubah**: Cari index → konfirmasi → update harga
4. **Hapus**: Cari index → konfirmasi → shift array

## 🎓 Pembelajaran

Tugas ini mengajarkan:
- Penggunaan for-each loop untuk iterasi
- Perbedaan for-each dengan for biasa
- Nested class untuk organisasi kode
- CRUD operations pada array
- Input validation dan error handling
- Konfirmasi sebelum operasi destructive
- Array shifting untuk delete operation

## 🆚 Perbedaan dengan Tugas 1

| Aspek | Tugas 1 | Tugas 2 |
|-------|---------|---------|
| Loop | For biasa | For-each |
| Class | Inner class | Static nested class |
| Pesanan | Maksimal 4 item | Unlimited |
| CRUD | Terbatas | Full CRUD |
| Konfirmasi | Tidak ada | Ada (ubah & hapus) |
| Input | Nomor menu | Nama menu |
| Validasi | Basic | Exception handling |

## 👤 Author

**Budi Komarudin**

---

**Bagian dari:** [Desktop - Aplikasi Restoran](../README.md)
