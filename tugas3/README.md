# Tugas 3 - Aplikasi Restoran dengan OOP dan File I/O

Aplikasi restoran lengkap dengan implementasi **Object-Oriented Programming (OOP)** penuh, **file persistence**, dan **exception handling**.

## 📋 Deskripsi

Program ini adalah implementasi aplikasi restoran dengan konsep OOP yang lengkap, termasuk abstract class, inheritance, polymorphism, custom exception, dan file I/O untuk menyimpan data secara permanen.

## ✨ Fitur Utama

### 1. Tambah Item Menu
Mendukung 3 jenis item dengan atribut spesifik:
- **Makanan**: nama, harga, porsi (kecil/sedang/besar), kalori
- **Minuman**: nama, harga, ukuran (small/medium/large), suhu (panas/dingin/normal)
- **Diskon**: nama, persentase diskon (0.1 = 10%)

### 2. Tampilkan Menu
- Menampilkan semua item menu dengan detail spesifik
- Format berbeda untuk setiap tipe (Makanan, Minuman, Diskon)
- Implementasi polymorphism melalui method `tampilMenu()`

### 3. Pesan Menu
- Input nama menu (bukan nomor)
- Pencarian menu dengan exception handling
- Unlimited item pesanan
- Ketik "selesai" untuk mengakhiri
- Cetak struk otomatis ke konsol dan file

### 4. Simpan Menu ke File
- Save semua menu ke file `menu.txt`
- Format CSV dengan delimiter `;`
- Menyimpan semua atribut spesifik per tipe

### 5. Muat Menu dari File
- Load menu dari file `menu.txt`
- Parsing otomatis berdasarkan tipe
- Recreate object sesuai class (Makanan/Minuman/Diskon)

### 6. Cetak Struk
- Detail pesanan lengkap dengan subtotal
- Format Rupiah dengan pemisah ribuan
- Timestamp menggunakan `LocalDateTime`
- Perhitungan otomatis:
  - **Subtotal** pesanan
  - **Pajak**: 10% dari subtotal
  - **Biaya Pelayanan**: Rp 20.000
  - **Diskon**: Ambil diskon tertinggi dari pesanan
- Output ke konsol dan file `struk_YYYYMMDD_HHmmss.txt`

## 🎯 Konsep Pemrograman

### OOP Concepts
- **Abstract Class**: `MenuItem` sebagai base class
- **Inheritance**: `Makanan`, `Minuman`, `Diskon` extends `MenuItem`
- **Polymorphism**: Override method `tampilMenu()`
- **Encapsulation**: Private fields dengan getter/setter
- **Abstraction**: Abstract method pada base class

### Advanced Java
- **ArrayList<T>**: Generic collection untuk dynamic data
- **Custom Exception**: `MenuNotFoundException` extends `Exception`
- **File I/O**: 
  - `FileWriter`, `PrintWriter` untuk write
  - `FileReader`, `BufferedReader` untuk read
- **Date/Time**: `LocalDateTime` dan `DateTimeFormatter`
- **String Manipulation**: `split()`, `equalsIgnoreCase()`
- **Exception Handling**: try-catch-finally, throws

## 🍽️ Menu Default

### Makanan (3 item)
- Nasi Goreng - Rp 25.000 (Porsi: sedang, 450 kkal)
- Mie Goreng - Rp 22.000 (Porsi: sedang, 400 kkal)
- Ayam Bakar - Rp 30.000 (Porsi: besar, 550 kkal)

### Minuman (3 item)
- Es Teh - Rp 7.000 (Medium, dingin)
- Es Jeruk - Rp 9.000 (Medium, dingin)
- Kopi Hitam - Rp 12.000 (Small, panas)

### Diskon (2 item)
- Diskon 10% - 10%
- Diskon 20% - 20%

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

### Tambah Item Menu
```
=== MENU UTAMA ===
1. Tambah Item Menu
2. Tampilkan Menu
3. Pesan Menu
4. Simpan Menu ke File
5. Muat Menu dari File
6. Keluar
Pilih: 1

=== TAMBAH ITEM MENU ===
1. Makanan
2. Minuman
3. Diskon
Pilih: 1

Nama makanan: Soto Ayam
Harga: 23000
Porsi (kecil/sedang/besar): sedang
Kalori: 380
✓ Makanan berhasil ditambahkan!
```

### Pemesanan dengan Exception Handling
```
Pilih: 3

=== PEMESANAN ===

=== DAFTAR MENU RESTORAN ===
[MAKANAN] Nasi Goreng - Rp 25000.0 | Porsi: sedang | Kalori: 450 kkal
...

Masukkan nama item (atau selesai): Nasi Goreng
Jumlah: 2
Ditambahkan!

Masukkan nama item (atau selesai): Pizza
Menu 'Pizza' tidak ditemukan.

Masukkan nama item (atau selesai): Es Teh
Jumlah: 1
Ditambahkan!

Masukkan nama item (atau selesai): selesai
```

### Output Struk
```
=========================================
           STRUK PESANAN RESTORAN        
=========================================
Tanggal: 01/12/2025 14:30:45
-----------------------------------------
Nasi Goreng          x2   = Rp 50,000
Es Teh               x1   = Rp 7,000
-----------------------------------------
Subtotal            : Rp 57,000
Pajak (10%)         : Rp 5,700
Biaya Pelayanan     : Rp 20,000
-----------------------------------------
TOTAL BAYAR         : Rp 82,700
=========================================
      Terima Kasih Atas Kunjungan Anda  
=========================================

Struk disimpan ke: struk_20251201_143045.txt
```

## 📊 Struktur Program

```
Main.java
├── Abstract Class MenuItem
│   ├── Fields: nama, harga, kategori
│   ├── Constructor, Getters, Setters
│   └── abstract void tampilMenu()
│
├── Class Makanan extends MenuItem
│   ├── Fields: porsi, kalori
│   └── @Override tampilMenu()
│
├── Class Minuman extends MenuItem
│   ├── Fields: ukuran, suhu
│   └── @Override tampilMenu()
│
├── Class Diskon extends MenuItem
│   ├── Field: persenDiskon
│   └── @Override tampilMenu()
│
├── Class MenuNotFoundException extends Exception
│   └── Constructor with message
│
├── Class Menu
│   ├── ArrayList<MenuItem> list
│   └── Methods:
│       ├── tambah(MenuItem)
│       ├── cari(String) throws MenuNotFoundException
│       ├── tampilkan()
│       ├── simpanKeFile(String) throws IOException
│       └── loadDariFile(String) throws IOException
│
├── Class Pesanan
│   ├── ArrayList<MenuItem> items
│   ├── ArrayList<Integer> quantities
│   └── Methods:
│       ├── tambah(MenuItem, int)
│       ├── hitungSubtotal()
│       ├── hitungPajak()
│       ├── hitungDiskon()
│       ├── totalAkhir()
│       └── cetakStruk(PrintWriter)
│
└── Main Class
    ├── static Menu menu
    ├── static Scanner sc
    └── Methods:
        ├── main()
        ├── tambahDefault()
        ├── tambahItem()
        ├── prosesPesanan()
        ├── simpanMenu()
        ├── muatMenu()
        ├── simpanStruk(Pesanan)
        ├── safeInt()
        └── safeDouble()
```

## 📄 Format File

### menu.txt (CSV dengan delimiter `;`)
```
MAKANAN;Nasi Goreng;25000.0;sedang;450
MINUMAN;Es Teh;7000.0;medium;dingin
DISKON;Diskon 10%;0.1
```

### struk_YYYYMMDD_HHmmss.txt
- Nama file otomatis dengan timestamp
- Format sama dengan output konsol
- Disimpan di folder yang sama dengan program

## 📝 Catatan Penting

1. **File Persistence**: Menu disimpan otomatis di `menu.txt`
2. **Auto-Load**: Program otomatis load menu dari file saat start
3. **Struk File**: Setiap pemesanan tersimpan dengan timestamp unik
4. **Exception Handling**: 
   - `MenuNotFoundException` untuk menu tidak ditemukan
   - `IOException` untuk error file I/O
   - Try-catch untuk input validation
5. **Polymorphism**: Method `tampilMenu()` dipanggil sesuai tipe object
6. **Format Timestamp**: `yyyyMMdd_HHmmss` untuk nama file, `dd/MM/yyyy HH:mm:ss` untuk struk

## 🔄 Alur Program

### Startup
1. Load menu dari `menu.txt`
2. Jika file tidak ada atau kosong, tambah menu default
3. Tampilkan menu utama

### Pemesanan
1. Tampilkan menu dengan polymorphism
2. Input nama menu → cari dengan exception handling
3. Jika tidak ditemukan, throw `MenuNotFoundException`
4. Simpan ke object `Pesanan`
5. Cetak struk ke konsol dan file

### Save/Load
1. **Save**: Loop semua item → write ke file dengan format spesifik
2. **Load**: Read file → parse → recreate object berdasarkan tipe

## 🎓 Pembelajaran

Tugas ini mengajarkan:
- **OOP Pillars**: Abstraction, Encapsulation, Inheritance, Polymorphism
- **Exception Handling**: Custom exception dan try-catch
- **File I/O**: Read/write file dengan berbagai class
- **Collections Framework**: ArrayList dan generics
- **Date/Time API**: LocalDateTime dan formatting
- **Design Pattern**: Separation of concerns (Menu, Pesanan, MenuItem)
- **Type Casting**: `instanceof` dan downcasting

## 🆚 Perbedaan dengan Tugas 1 & 2

| Aspek | Tugas 1 | Tugas 2 | Tugas 3 |
|-------|---------|---------|---------|
| Data Structure | Array | Array | ArrayList |
| OOP | Basic class | Nested class | Abstract class + Inheritance |
| Exception | Tidak ada | Basic validation | Custom exception |
| File I/O | Tidak ada | Tidak ada | Save/Load + Struk |
| Polymorphism | Tidak ada | Tidak ada | Method overriding |
| Menu Types | 2 (Makanan/Minuman) | 2 | 3 + Abstract |
| Timestamp | Tidak ada | Tidak ada | LocalDateTime |
| Diskon | Hardcode | Hardcode | Object dengan persen |

## 🏆 Best Practices

✅ Abstract class untuk base functionality  
✅ Inheritance untuk code reuse  
✅ Polymorphism untuk flexible code  
✅ Custom exception untuk specific errors  
✅ File I/O untuk data persistence  
✅ ArrayList untuk dynamic data  
✅ Separation of concerns  
✅ Proper exception handling  
✅ Input validation  
✅ Auto-save feature  

## 👤 Author

**Budi Komarudin**

---

**Bagian dari:** [Desktop - Aplikasi Restoran](../README.md)
