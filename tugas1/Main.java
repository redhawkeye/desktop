import java.util.Scanner;

class Menu {
    private String nama;
    private int harga;
    private String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }
}

public class Main {
    private static Menu[] menuItems;
    private static int[] pesananIndex = new int[4];
    private static int[] pesananJumlah = new int[4];
    private static int jumlahPesanan = 0;
    private static int jumlahMenu = 8;
    private static String namaPemesan = "";

    // Array untuk menyimpan multiple transaksi
    private static String[] daftarNamaPemesan = new String[10];
    private static int[][] daftarPesananIndex = new int[10][4];
    private static int[][] daftarPesananJumlah = new int[10][4];
    private static int[] daftarJumlahPesanan = new int[10];
    private static int jumlahTransaksi = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiMenu();

        boolean running = true;
        while (running) {
            tampilkanMenuUtama();
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (pilihan == 1) {
                inputMenuBaru();
            } else if (pilihan == 2) {
                tampilkanMenu();
            } else if (pilihan == 3) {
                inputPesanan();
            } else if (pilihan == 4) {
                cetakStruk();
            } else if (pilihan == 5) {
                System.out.println("\nTerima kasih telah menggunakan aplikasi restoran!");
                running = false;
            } else {
                System.out.println("\nPilihan tidak valid. Silakan coba lagi.\n");
            }
        }

        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n=================================");
        System.out.println("   APLIKASI RESTORAN SEDERHANA");
        System.out.println("=================================");
        System.out.println("1. Input Menu");
        System.out.println("2. Lihat Menu");
        System.out.println("3. Pemesanan");
        System.out.println("4. Cetak Struk");
        System.out.println("5. Keluar");
        System.out.println("=================================");
    }

    private static String formatRupiah(int nominal) {
        return String.format("%,d", nominal).replace(',', '.');
    }

    // Soal nomer 1
    private static void inisialisasiMenu() {
        menuItems = new Menu[20]; // Diperbesar untuk menampung menu baru
        // Makanan
        menuItems[0] = new Menu("Nasi Padang", 25000, "Makanan");
        menuItems[1] = new Menu("Mie Goreng", 20000, "Makanan");
        menuItems[2] = new Menu("Ayam Bakar", 30000, "Makanan");
        menuItems[3] = new Menu("Sate Ayam", 28000, "Makanan");
        // Minuman
        menuItems[4] = new Menu("Es Teh", 5000, "Minuman");
        menuItems[5] = new Menu("Es Jeruk", 8000, "Minuman");
        menuItems[6] = new Menu("Jus Alpukat", 15000, "Minuman");
        menuItems[7] = new Menu("Kopi", 10000, "Minuman");
    }

    // Soal nomer 1
    private static void inputMenuBaru() {
        if (jumlahMenu >= menuItems.length) {
            System.out.println("\nKapasitas menu sudah penuh!");
            return;
        }

        System.out.println("\n=================================");
        System.out.println("     INPUT MENU RESTORAN");
        System.out.println("=================================");

        System.out.print("Nama Menu: ");
        String nama = scanner.nextLine();

        System.out.print("Harga Menu: ");
        int harga = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Kategori (Makanan/Minuman): ");
        String kategori = scanner.nextLine();

        // Validasi kategori
        if (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
            System.out.println("\nKategori tidak valid! Harus 'Makanan' atau 'Minuman'");
            return;
        }

        // Simpan menu baru ke array berdasarkan kategori
        // Index 0-7: Menu awal (0-3 Makanan, 4-7 Minuman)
        // Index 8-15: Menu tambahan Makanan
        // Index 16-19: Menu tambahan Minuman
        if (kategori.equalsIgnoreCase("Makanan")) {
            // Cari slot kosong untuk makanan (index 8-15)
            if (menuItems[8] == null) {
                menuItems[8] = new Menu(nama, harga, kategori);
            } else if (menuItems[9] == null) {
                menuItems[9] = new Menu(nama, harga, kategori);
            } else if (menuItems[10] == null) {
                menuItems[10] = new Menu(nama, harga, kategori);
            } else if (menuItems[11] == null) {
                menuItems[11] = new Menu(nama, harga, kategori);
            } else if (menuItems[12] == null) {
                menuItems[12] = new Menu(nama, harga, kategori);
            } else if (menuItems[13] == null) {
                menuItems[13] = new Menu(nama, harga, kategori);
            } else if (menuItems[14] == null) {
                menuItems[14] = new Menu(nama, harga, kategori);
            } else if (menuItems[15] == null) {
                menuItems[15] = new Menu(nama, harga, kategori);
            } else {
                System.out.println("\nKapasitas menu makanan sudah penuh!");
                return;
            }
        } else {
            // Cari slot kosong untuk minuman (index 16-19)
            if (menuItems[16] == null) {
                menuItems[16] = new Menu(nama, harga, kategori);
            } else if (menuItems[17] == null) {
                menuItems[17] = new Menu(nama, harga, kategori);
            } else if (menuItems[18] == null) {
                menuItems[18] = new Menu(nama, harga, kategori);
            } else if (menuItems[19] == null) {
                menuItems[19] = new Menu(nama, harga, kategori);
            } else {
                System.out.println("\nKapasitas menu minuman sudah penuh!");
                return;
            }
        }

        jumlahMenu++;

        System.out.println("\nMenu berhasil ditambahkan!");
        System.out.println("Total menu sekarang: " + jumlahMenu);
    }

    private static void tampilkanMenu() {
        System.out.println("\n=================================");
        System.out.println("   MENU RESTORAN SEDERHANA");
        System.out.println("=================================\n");

        System.out.println(">>> MAKANAN <<<");
        int nomorMakanan = 1;
        if (menuItems[0] != null && menuItems[0].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[0].getNama() + " - Rp " + formatRupiah(menuItems[0].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[1] != null && menuItems[1].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[1].getNama() + " - Rp " + formatRupiah(menuItems[1].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[2] != null && menuItems[2].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[2].getNama() + " - Rp " + formatRupiah(menuItems[2].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[3] != null && menuItems[3].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[3].getNama() + " - Rp " + formatRupiah(menuItems[3].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[8] != null && menuItems[8].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[8].getNama() + " - Rp " + formatRupiah(menuItems[8].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[9] != null && menuItems[9].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[9].getNama() + " - Rp " + formatRupiah(menuItems[9].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[10] != null && menuItems[10].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[10].getNama() + " - Rp " + formatRupiah(menuItems[10].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[11] != null && menuItems[11].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[11].getNama() + " - Rp " + formatRupiah(menuItems[11].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[12] != null && menuItems[12].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[12].getNama() + " - Rp " + formatRupiah(menuItems[12].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[13] != null && menuItems[13].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[13].getNama() + " - Rp " + formatRupiah(menuItems[13].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[14] != null && menuItems[14].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[14].getNama() + " - Rp " + formatRupiah(menuItems[14].getHarga()));
            nomorMakanan++;
        }
        if (menuItems[15] != null && menuItems[15].getKategori().equals("Makanan")) {
            System.out.println(
                    nomorMakanan + ". " + menuItems[15].getNama() + " - Rp " + formatRupiah(menuItems[15].getHarga()));
            nomorMakanan++;
        }

        System.out.println("\n>>> MINUMAN <<<");
        int nomorMinuman = nomorMakanan;
        if (menuItems[4] != null && menuItems[4].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[4].getNama() + " - Rp " + formatRupiah(menuItems[4].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[5] != null && menuItems[5].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[5].getNama() + " - Rp " + formatRupiah(menuItems[5].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[6] != null && menuItems[6].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[6].getNama() + " - Rp " + formatRupiah(menuItems[6].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[7] != null && menuItems[7].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[7].getNama() + " - Rp " + formatRupiah(menuItems[7].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[16] != null && menuItems[16].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[16].getNama() + " - Rp " + formatRupiah(menuItems[16].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[17] != null && menuItems[17].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[17].getNama() + " - Rp " + formatRupiah(menuItems[17].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[18] != null && menuItems[18].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[18].getNama() + " - Rp " + formatRupiah(menuItems[18].getHarga()));
            nomorMinuman++;
        }
        if (menuItems[19] != null && menuItems[19].getKategori().equals("Minuman")) {
            System.out.println(
                    nomorMinuman + ". " + menuItems[19].getNama() + " - Rp " + formatRupiah(menuItems[19].getHarga()));
        }
        System.out.println("=================================");
    }

    // Soal nomer 2
    private static void inputPesanan() {
        if (jumlahTransaksi >= daftarNamaPemesan.length) {
            System.out.println("\nKapasitas transaksi sudah penuh!");
            return;
        }

        // Tampilkan menu terlebih dahulu
        tampilkanMenu();

        System.out.println("\n=================================");
        System.out.println("        PEMESANAN");
        System.out.println("=================================");

        // Input nama pemesan
        System.out.print("Nama Pemesan: ");
        namaPemesan = scanner.nextLine();

        System.out.println("\nMasukkan pesanan Anda (maksimal 4 menu)");
        System.out.println("Format: Masukkan nomor menu (1-" + jumlahMenu + "), lalu jumlah pesanan");
        System.out.println("Ketik '0' pada nomor menu jika sudah selesai memesan\n");

        jumlahPesanan = 0;

        // Pesanan 1
        System.out.print("Nomor Menu Pesanan 1: ");
        int nomorMenu1 = scanner.nextInt();
        if (nomorMenu1 != 0) {
            System.out.print("Jumlah: ");
            int jumlah1 = scanner.nextInt();
            prosesInput(nomorMenu1, jumlah1, 0);
            jumlahPesanan++;

            // Pesanan 2
            System.out.print("Nomor Menu Pesanan 2: ");
            int nomorMenu2 = scanner.nextInt();
            if (nomorMenu2 != 0) {
                System.out.print("Jumlah: ");
                int jumlah2 = scanner.nextInt();
                prosesInput(nomorMenu2, jumlah2, 1);
                jumlahPesanan++;

                // Pesanan 3
                System.out.print("Nomor Menu Pesanan 3: ");
                int nomorMenu3 = scanner.nextInt();
                if (nomorMenu3 != 0) {
                    System.out.print("Jumlah: ");
                    int jumlah3 = scanner.nextInt();
                    prosesInput(nomorMenu3, jumlah3, 2);
                    jumlahPesanan++;

                    // Pesanan 4
                    System.out.print("Nomor Menu Pesanan 4: ");
                    int nomorMenu4 = scanner.nextInt();
                    if (nomorMenu4 != 0) {
                        System.out.print("Jumlah: ");
                        int jumlah4 = scanner.nextInt();
                        prosesInput(nomorMenu4, jumlah4, 3);
                        jumlahPesanan++;
                    }
                }
            }
        }

        // Simpan transaksi ke daftar
        daftarNamaPemesan[jumlahTransaksi] = namaPemesan;
        daftarJumlahPesanan[jumlahTransaksi] = jumlahPesanan;
        if (jumlahPesanan >= 1) {
            daftarPesananIndex[jumlahTransaksi][0] = pesananIndex[0];
            daftarPesananJumlah[jumlahTransaksi][0] = pesananJumlah[0];
        }
        if (jumlahPesanan >= 2) {
            daftarPesananIndex[jumlahTransaksi][1] = pesananIndex[1];
            daftarPesananJumlah[jumlahTransaksi][1] = pesananJumlah[1];
        }
        if (jumlahPesanan >= 3) {
            daftarPesananIndex[jumlahTransaksi][2] = pesananIndex[2];
            daftarPesananJumlah[jumlahTransaksi][2] = pesananJumlah[2];
        }
        if (jumlahPesanan >= 4) {
            daftarPesananIndex[jumlahTransaksi][3] = pesananIndex[3];
            daftarPesananJumlah[jumlahTransaksi][3] = pesananJumlah[3];
        }
        jumlahTransaksi++;

        System.out.println("\nPesanan berhasil disimpan!");
    }

    private static void prosesInput(int nomorMenu, int jumlah, int index) {
        if (nomorMenu >= 1 && nomorMenu <= jumlahMenu) {
            // Mapping nomor menu ke index array yang benar
            int realIndex = getMenuIndexFromNumber(nomorMenu);
            pesananIndex[index] = realIndex;
            pesananJumlah[index] = jumlah;
        }
    }

    // Helper method untuk mapping nomor menu ke index array
    private static int getMenuIndexFromNumber(int nomorMenu) {
        int counter = 0;

        // Hitung makanan (0-3, 8-15)
        if (menuItems[0] != null && menuItems[0].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 0;
        }
        if (menuItems[1] != null && menuItems[1].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 1;
        }
        if (menuItems[2] != null && menuItems[2].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 2;
        }
        if (menuItems[3] != null && menuItems[3].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 3;
        }
        if (menuItems[8] != null && menuItems[8].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 8;
        }
        if (menuItems[9] != null && menuItems[9].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 9;
        }
        if (menuItems[10] != null && menuItems[10].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 10;
        }
        if (menuItems[11] != null && menuItems[11].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 11;
        }
        if (menuItems[12] != null && menuItems[12].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 12;
        }
        if (menuItems[13] != null && menuItems[13].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 13;
        }
        if (menuItems[14] != null && menuItems[14].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 14;
        }
        if (menuItems[15] != null && menuItems[15].getKategori().equals("Makanan")) {
            counter++;
            if (counter == nomorMenu)
                return 15;
        }

        // Hitung minuman (4-7, 16-19)
        if (menuItems[4] != null && menuItems[4].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 4;
        }
        if (menuItems[5] != null && menuItems[5].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 5;
        }
        if (menuItems[6] != null && menuItems[6].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 6;
        }
        if (menuItems[7] != null && menuItems[7].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 7;
        }
        if (menuItems[16] != null && menuItems[16].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 16;
        }
        if (menuItems[17] != null && menuItems[17].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 17;
        }
        if (menuItems[18] != null && menuItems[18].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 18;
        }
        if (menuItems[19] != null && menuItems[19].getKategori().equals("Minuman")) {
            counter++;
            if (counter == nomorMenu)
                return 19;
        }

        return 0; // default
    }

    // Soal nomer 3 dan 4
    private static void cetakStruk() {
        if (jumlahTransaksi == 0) {
            System.out.println("\nBelum ada pesanan. Silakan lakukan pemesanan terlebih dahulu.");
            return;
        }

        // Tampilkan daftar pemesan
        System.out.println("\n=================================");
        System.out.println("     DAFTAR NAMA PEMESAN");
        System.out.println("=================================");

        if (jumlahTransaksi >= 1 && daftarNamaPemesan[0] != null) {
            System.out.println("1. " + daftarNamaPemesan[0]);
        }
        if (jumlahTransaksi >= 2 && daftarNamaPemesan[1] != null) {
            System.out.println("2. " + daftarNamaPemesan[1]);
        }
        if (jumlahTransaksi >= 3 && daftarNamaPemesan[2] != null) {
            System.out.println("3. " + daftarNamaPemesan[2]);
        }
        if (jumlahTransaksi >= 4 && daftarNamaPemesan[3] != null) {
            System.out.println("4. " + daftarNamaPemesan[3]);
        }
        if (jumlahTransaksi >= 5 && daftarNamaPemesan[4] != null) {
            System.out.println("5. " + daftarNamaPemesan[4]);
        }
        if (jumlahTransaksi >= 6 && daftarNamaPemesan[5] != null) {
            System.out.println("6. " + daftarNamaPemesan[5]);
        }
        if (jumlahTransaksi >= 7 && daftarNamaPemesan[6] != null) {
            System.out.println("7. " + daftarNamaPemesan[6]);
        }
        if (jumlahTransaksi >= 8 && daftarNamaPemesan[7] != null) {
            System.out.println("8. " + daftarNamaPemesan[7]);
        }
        if (jumlahTransaksi >= 9 && daftarNamaPemesan[8] != null) {
            System.out.println("9. " + daftarNamaPemesan[8]);
        }
        if (jumlahTransaksi >= 10 && daftarNamaPemesan[9] != null) {
            System.out.println("10. " + daftarNamaPemesan[9]);
        }

        System.out.println("=================================");
        System.out.print("\nPilih nomor pemesan (1-" + jumlahTransaksi + "): ");
        int pilihanPemesan = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Validasi pilihan
        if (pilihanPemesan < 1 || pilihanPemesan > jumlahTransaksi) {
            System.out.println("\nPilihan tidak valid!");
            return;
        }

        // Load data transaksi yang dipilih
        int indexTransaksi = pilihanPemesan - 1;
        namaPemesan = daftarNamaPemesan[indexTransaksi];
        jumlahPesanan = daftarJumlahPesanan[indexTransaksi];
        pesananIndex[0] = daftarPesananIndex[indexTransaksi][0];
        pesananIndex[1] = daftarPesananIndex[indexTransaksi][1];
        pesananIndex[2] = daftarPesananIndex[indexTransaksi][2];
        pesananIndex[3] = daftarPesananIndex[indexTransaksi][3];
        pesananJumlah[0] = daftarPesananJumlah[indexTransaksi][0];
        pesananJumlah[1] = daftarPesananJumlah[indexTransaksi][1];
        pesananJumlah[2] = daftarPesananJumlah[indexTransaksi][2];
        pesananJumlah[3] = daftarPesananJumlah[indexTransaksi][3];

        // Cetak struk
        System.out.println("\n=================================");
        System.out.println("        STRUK PESANAN");
        System.out.println("=================================");
        System.out.println("Nama Pemesan: " + namaPemesan);
        System.out.println("---------------------------------");

        int totalBiaya = 0;
        int jumlahMinuman = 0;
        int hargaMinumanTertinggi = 0;

        // Tampilkan pesanan 1
        if (jumlahPesanan >= 1) {
            Menu menu1 = menuItems[pesananIndex[0]];
            if (menu1 != null) {
                int subtotal1 = menu1.getHarga() * pesananJumlah[0];
                System.out.println(menu1.getNama() + " x" + pesananJumlah[0]);
                System.out.println("  @Rp " + formatRupiah(menu1.getHarga()) + " = Rp " + formatRupiah(subtotal1));
                totalBiaya += subtotal1;

                if (menu1.getKategori().equals("Minuman")) {
                    jumlahMinuman += pesananJumlah[0];
                    if (menu1.getHarga() > hargaMinumanTertinggi) {
                        hargaMinumanTertinggi = menu1.getHarga();
                    }
                }
            }
        }

        // Tampilkan pesanan 2
        if (jumlahPesanan >= 2) {
            Menu menu2 = menuItems[pesananIndex[1]];
            if (menu2 != null) {
                int subtotal2 = menu2.getHarga() * pesananJumlah[1];
                System.out.println(menu2.getNama() + " x" + pesananJumlah[1]);
                System.out.println("  @Rp " + formatRupiah(menu2.getHarga()) + " = Rp " + formatRupiah(subtotal2));
                totalBiaya += subtotal2;

                if (menu2.getKategori().equals("Minuman")) {
                    jumlahMinuman += pesananJumlah[1];
                    if (menu2.getHarga() > hargaMinumanTertinggi) {
                        hargaMinumanTertinggi = menu2.getHarga();
                    }
                }
            }
        }

        // Tampilkan pesanan 3
        if (jumlahPesanan >= 3) {
            Menu menu3 = menuItems[pesananIndex[2]];
            if (menu3 != null) {
                int subtotal3 = menu3.getHarga() * pesananJumlah[2];
                System.out.println(menu3.getNama() + " x" + pesananJumlah[2]);
                System.out.println("  @Rp " + formatRupiah(menu3.getHarga()) + " = Rp " + formatRupiah(subtotal3));
                totalBiaya += subtotal3;

                if (menu3.getKategori().equals("Minuman")) {
                    jumlahMinuman += pesananJumlah[2];
                    if (menu3.getHarga() > hargaMinumanTertinggi) {
                        hargaMinumanTertinggi = menu3.getHarga();
                    }
                }
            }
        }

        // Tampilkan pesanan 4
        if (jumlahPesanan >= 4) {
            Menu menu4 = menuItems[pesananIndex[3]];
            if (menu4 != null) {
                int subtotal4 = menu4.getHarga() * pesananJumlah[3];
                System.out.println(menu4.getNama() + " x" + pesananJumlah[3]);
                System.out.println("  @Rp " + formatRupiah(menu4.getHarga()) + " = Rp " + formatRupiah(subtotal4));
                totalBiaya += subtotal4;

                if (menu4.getKategori().equals("Minuman")) {
                    jumlahMinuman += pesananJumlah[3];
                    if (menu4.getHarga() > hargaMinumanTertinggi) {
                        hargaMinumanTertinggi = menu4.getHarga();
                    }
                }
            }
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal: Rp " + formatRupiah(totalBiaya));

        // Biaya pelayanan
        int biayaPelayanan = 20000;
        System.out.println("Biaya Pelayanan: Rp " + formatRupiah(biayaPelayanan));

        // Pajak 10%
        int pajak = (int) (totalBiaya * 0.1);
        System.out.println("Pajak (10%): Rp " + formatRupiah(pajak));

        int totalSebelumDiskon = totalBiaya + biayaPelayanan + pajak;
        System.out.println("Total: Rp " + formatRupiah(totalSebelumDiskon));

        // Promo Beli 1 Gratis 1 Minuman (jika total > 50.000)
        int diskonPromo = 0;
        if (totalBiaya > 50000) {
            if (jumlahMinuman > 0) {
                diskonPromo = hargaMinumanTertinggi;
                System.out.println("\nPromo Beli 1 Gratis 1 Minuman!");
                System.out.println("Diskon Minuman: -Rp " + formatRupiah(diskonPromo));
            }
        }

        // Diskon 10% (jika total > 100.000)
        int diskon10Persen = 0;
        if (totalBiaya > 100000) {
            diskon10Persen = (int) (totalSebelumDiskon * 0.1);
            System.out.println("Diskon 10%: -Rp " + formatRupiah(diskon10Persen));
        }

        // Soal nomer 3
        int totalAkhir = totalSebelumDiskon - diskonPromo - diskon10Persen;

        System.out.println("=================================");
        System.out.println("TOTAL BAYAR: Rp " + formatRupiah(totalAkhir));
        System.out.println("=================================");
    }
}