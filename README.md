# Desktop - Aplikasi Restoran

Repository ini berisi kumpulan tugas pengembangan aplikasi restoran dengan berbagai fitur dan konsep pemrograman.

## 📁 Struktur Folder

```
desktop/
├── tugas1/          # Aplikasi Restoran dengan Array
├── tugas2/          # Aplikasi Restoran dengan ArrayList dan For-Each
└── tugas3/          # Aplikasi Restoran dengan OOP dan File I/O
```

## 📝 Deskripsi Tugas

### Tugas 1 - Aplikasi Restoran Sederhana dengan Array

**File:** `tugas1/Main.java`

Aplikasi restoran sederhana yang menggunakan array untuk mengelola menu dan pesanan.

**Fitur:**
- ✨ Input menu baru (makanan/minuman)
- 📋 Tampilkan daftar menu (dikelompokkan berdasarkan kategori)
- 🛒 Pemesanan (maksimal 4 item per transaksi)
- 🧾 Cetak struk pembayaran dengan perhitungan:
  - Subtotal pesanan
  - Biaya pelayanan (Rp 20.000)
  - Pajak 10%
  - Diskon 10% (jika total > Rp 100.000)
  - Promo Beli 1 Gratis 1 Minuman (jika total > Rp 50.000)

**Konsep yang digunakan:**
- Array untuk menyimpan data menu dan pesanan
- Class `Menu` dengan encapsulation
- Multiple transaksi dengan array 2D
- Format Rupiah dengan pemisah ribuan

**Menu Default:**
- Makanan: Nasi Padang, Mie Goreng, Ayam Bakar, Sate Ayam
- Minuman: Es Teh, Es Jeruk, Jus Alpukat, Kopi

---

### Tugas 2 - Aplikasi Restoran dengan For-Each Loop

**File:** `tugas2/Main.java`

Pengembangan aplikasi restoran dengan penggunaan for-each loop dan ArrayList.

**Fitur:**
- 👨‍💼 Menu Pelanggan (pemesanan)
- 🔧 Menu Pengelolaan Restoran:
  - Tambah menu baru
  - Ubah harga menu
  - Hapus menu (dengan konfirmasi)
- 🧾 Cetak struk dengan perhitungan otomatis:
  - Pajak 10%
  - Biaya pelayanan Rp 20.000
  - Diskon 10% (jika total > Rp 100.000)
  - Promo Beli 1 Gratis 1 Minuman termurah (jika total > Rp 50.000 dan beli minimal 2 minuman)

**Konsep yang digunakan:**
- Array dengan nested class
- For-each loop untuk iterasi
- Input validation dan error handling
- Pemesanan tanpa batas jumlah item
- Konfirmasi sebelum perubahan data

**Menu Default:**
- Makanan: Nasi Goreng, Ayam Bakar, Mie Ayam, Soto Ayam
- Minuman: Es Teh, Es Jeruk, Kopi Hitam, Susu Coklat

---

### Tugas 3 - Aplikasi Restoran dengan OOP dan File I/O

**File:** `tugas3/Main.java`

Aplikasi restoran lengkap dengan konsep OOP (Object-Oriented Programming) dan file handling.

**Fitur:**
- 🎯 Abstract class `MenuItem` dengan subclass:
  - `Makanan` (dengan atribut porsi dan kalori)
  - `Minuman` (dengan atribut ukuran dan suhu)
  - `Diskon` (dengan persen diskon)
- 💾 File I/O:
  - Simpan menu ke file `menu.txt`
  - Muat menu dari file
  - Simpan struk otomatis dengan timestamp
- 🚫 Custom Exception (`MenuNotFoundException`)
- 📊 ArrayList untuk manajemen data dinamis
- 🧾 Struk dengan format profesional dan timestamp

**Konsep yang digunakan:**
- Abstract class dan inheritance
- Polymorphism (method overriding)
- Exception handling (try-catch, custom exception)
- File I/O (FileWriter, FileReader, BufferedReader, PrintWriter)
- LocalDateTime dan DateTimeFormatter
- ArrayList generics
- Encapsulation dan abstraction

**Menu Default:**
- Makanan: Nasi Goreng (450 kkal), Mie Goreng (400 kkal), Ayam Bakar (550 kkal)
- Minuman: Es Teh, Es Jeruk, Kopi Hitam
- Diskon: Diskon 10%, Diskon 20%

**Format File menu.txt:**
```
MAKANAN;Nasi Goreng;25000.0;sedang;450
MINUMAN;Es Teh;7000.0;medium;dingin
DISKON;Diskon 10%;0.1
```

---

## 🚀 Cara Menjalankan

### Prasyarat
- Java Development Kit (JDK) 8 atau lebih tinggi
- Terminal/Command Prompt

### Kompilasi dan Jalankan

**Tugas 1:**
```bash
cd tugas1
javac Main.java
java Main
```

**Tugas 2:**
```bash
cd tugas2
javac Main.java
java Main
```

**Tugas 3:**
```bash
cd tugas3
javac Main.java
java Main
```

## 📊 Perbandingan Fitur

| Fitur | Tugas 1 | Tugas 2 | Tugas 3 |
|-------|---------|---------|---------|
| Struktur Data | Array | Array | ArrayList |
| OOP | Class sederhana | Nested Class | Abstract Class & Inheritance |
| File I/O | ❌ | ❌ | ✅ |
| Exception Handling | ❌ | Input validation | Custom Exception |
| Loop | For biasa | For-each | For-each + For biasa |
| Menu Management | Terbatas | Full CRUD | Full CRUD + Persistence |
| Struk | Konsol | Konsol | Konsol + File |

## 🛠️ Teknologi

- **Bahasa:** Java
- **Paradigma:** Object-Oriented Programming (OOP)
- **Konsep:** Array, ArrayList, Inheritance, Polymorphism, Exception Handling, File I/O

## 📌 Catatan

- Setiap tugas merupakan iterasi pengembangan dengan peningkatan fitur dan konsep
- Tugas 3 adalah implementasi paling lengkap dengan semua best practices
- File struk di Tugas 3 disimpan otomatis dengan format `struk_YYYYMMDD_HHmmss.txt`

## 👤 Author

**Budi Komarudin**

## 📄 Lisensi

Project ini dibuat untuk keperluan pembelajaran dan tugas akademik.
