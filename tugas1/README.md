# Tugas 1 - Aplikasi Restoran Sederhana dengan Array

Aplikasi restoran sederhana yang menggunakan **array** untuk mengelola menu dan pesanan dengan sistem multiple transaksi.

## 📋 Deskripsi

Program ini adalah implementasi dasar aplikasi restoran yang menggunakan array untuk menyimpan data menu dan pesanan. Mendukung multiple transaksi dengan kemampuan menyimpan hingga 10 transaksi berbeda.

## ✨ Fitur Utama

### 1. Input Menu Baru
- Menambahkan menu makanan atau minuman baru
- Validasi kategori (Makanan/Minuman)
- Kapasitas: 8 menu makanan tambahan, 4 menu minuman tambahan
- Otomatis mengelompokkan berdasarkan kategori

### 2. Lihat Menu
- Menampilkan daftar menu lengkap
- Dikelompokkan berdasarkan kategori (Makanan dan Minuman)
- Format harga dengan pemisah ribuan (Rp 25.000)
- Penomoran otomatis

### 3. Pemesanan
- Input nama pemesan
- Maksimal 4 item menu per transaksi
- Input nomor menu dan jumlah pesanan
- Menyimpan hingga 10 transaksi berbeda
- Validasi nomor menu

### 4. Cetak Struk
- Menampilkan daftar semua pemesan yang sudah melakukan transaksi
- Pilih pemesan untuk mencetak struk
- Detail pesanan dengan subtotal per item
- Perhitungan otomatis:
  - **Subtotal** pesanan
  - **Biaya Pelayanan**: Rp 20.000
  - **Pajak**: 10% dari subtotal
  - **Diskon 10%**: jika total > Rp 100.000
  - **Promo Beli 1 Gratis 1 Minuman**: jika total > Rp 50.000 (gratis minuman termahal dari pesanan)

## 🎯 Konsep Pemrograman

- **Array**: Struktur data untuk menu dan pesanan
- **Array 2D**: Multiple transaksi (10 transaksi x 4 item)
- **Class & Object**: Class `Menu` dengan encapsulation
- **Method**: Fungsi-fungsi terpisah untuk setiap fitur
- **Conditional**: Validasi dan perhitungan diskon
- **Loop**: Iterasi untuk menampilkan menu dan pesanan
- **Format String**: `String.format()` untuk format Rupiah

## 🍽️ Menu Default

### Makanan (4 item)
- Nasi Padang - Rp 25.000
- Mie Goreng - Rp 20.000
- Ayam Bakar - Rp 30.000
- Sate Ayam - Rp 28.000

### Minuman (4 item)
- Es Teh - Rp 5.000
- Es Jeruk - Rp 8.000
- Jus Alpukat - Rp 15.000
- Kopi - Rp 10.000

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

```
=================================
   APLIKASI RESTORAN SEDERHANA
=================================
1. Input Menu
2. Lihat Menu
3. Pemesanan
4. Cetak Struk
5. Keluar
=================================
Pilih menu: 3

=================================
        PEMESANAN
=================================
Nama Pemesan: Budi

Masukkan pesanan Anda (maksimal 4 menu)
Format: Masukkan nomor menu (1-8), lalu jumlah pesanan
Ketik '0' pada nomor menu jika sudah selesai memesan

Nomor Menu Pesanan 1: 1
Jumlah: 2
Nomor Menu Pesanan 2: 5
Jumlah: 1
Nomor Menu Pesanan 3: 0

Pesanan berhasil disimpan!
```

## 📊 Struktur Program

```
Main.java
├── Class Menu (inner class)
│   ├── Fields: nama, harga, kategori
│   └── Methods: getNama(), getHarga(), getKategori()
└── Main Class
    ├── Static Arrays
    │   ├── menuItems[] (Menu array)
    │   ├── pesananIndex[], pesananJumlah[] (pesanan saat ini)
    │   └── daftarNamaPemesan[], daftarPesanan[][] (multiple transaksi)
    └── Methods
        ├── main()
        ├── tampilkanMenuUtama()
        ├── inisialisasiMenu()
        ├── inputMenuBaru()
        ├── tampilkanMenu()
        ├── inputPesanan()
        ├── cetakStruk()
        ├── prosesInput()
        ├── getMenuIndexFromNumber()
        └── formatRupiah()
```

## 📝 Catatan Penting

1. **Kapasitas Menu**: Maksimal 20 item (12 makanan + 8 minuman)
2. **Kapasitas Pesanan**: 4 item per transaksi, maksimal 10 transaksi
3. **Format Harga**: Otomatis dengan pemisah ribuan
4. **Validasi**: Kategori harus "Makanan" atau "Minuman" (case-insensitive)
5. **Promo Minuman**: Gratis minuman dengan harga tertinggi dari pesanan

## 🔄 Alur Program

1. User memilih menu dari menu utama
2. **Input Menu**: Tambah menu baru ke array
3. **Lihat Menu**: Loop array dan tampilkan menu berdasarkan kategori
4. **Pemesanan**: Simpan data pesanan ke array transaksi
5. **Cetak Struk**: Tampilkan daftar pemesan → pilih pemesan → cetak struk dengan perhitungan lengkap

## 🎓 Pembelajaran

Tugas ini mengajarkan:
- Penggunaan array untuk menyimpan data
- Pengelolaan multiple array untuk relasi data
- Implementasi class sederhana dengan encapsulation
- Pemrosesan data dengan loop dan conditional
- Format output yang user-friendly

## 👤 Author

**Budi Komarudin**

---

**Bagian dari:** [Desktop - Aplikasi Restoran](../README.md)
