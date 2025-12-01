import java.util.Scanner;

public class Main {

    // ==========================
    // ==== CLASS MENU ==========
    // ==========================
    static class Menu {
        String nama;
        int harga;
        String kategori; // makanan / minuman

        Menu(String nama, int harga, String kategori) {
            this.nama = nama;
            this.harga = harga;
            this.kategori = kategori;
        }
    }

    // ==========================
    // ===== MAIN PROGRAM =======
    // ==========================
    static Scanner input = new Scanner(System.in);

    // Array Menu Restoran
    static Menu[] menuList = new Menu[50];
    static int menuCount = 0;

    // Array Pesanan
    static String[] pesananNama = new String[50];
    static int[] pesananJumlah = new int[50];
    static int pesananCount = 0;

    public static void main(String[] args) {

        tambahMenuDefault();

        int pilih;
        do {
            System.out.println("\n======= APLIKASI RESTORAN =======");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pengelolaan Restoran");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilih = safeInt();

            switch (pilih) {
                case 1 -> menuPelanggan();
                case 2 -> menuPengelolaan();
                case 3 -> System.out.println("Terima kasih menggunakan aplikasi.");
                default -> System.out.println("Pilihan tidak valid.");
            }

        } while (pilih != 3);
    }

    // ==========================
    // ==== MENU DEFAULT ========
    // ==========================
    static void tambahMenuDefault() {
        menuList[menuCount++] = new Menu("Nasi Goreng", 25000, "makanan");
        menuList[menuCount++] = new Menu("Ayam Bakar", 30000, "makanan");
        menuList[menuCount++] = new Menu("Mie Ayam", 20000, "makanan");
        menuList[menuCount++] = new Menu("Soto Ayam", 22000, "makanan");

        menuList[menuCount++] = new Menu("Es Teh", 7000, "minuman");
        menuList[menuCount++] = new Menu("Es Jeruk", 9000, "minuman");
        menuList[menuCount++] = new Menu("Kopi Hitam", 12000, "minuman");
        menuList[menuCount++] = new Menu("Susu Coklat", 15000, "minuman");
    }

    // ==========================
    // ====== MENU PELANGGAN ========
    // ==========================
    static void menuPelanggan() {
        pesananCount = 0; // reset

        System.out.println("\n===== MENU PELANGGAN =====");

        input.nextLine(); // clear buffer

        while (true) {
            tampilkanMenu();

            System.out.print("Masukkan nama menu (atau 'selesai'): ");
            String nama = input.nextLine();

            if (nama.equalsIgnoreCase("selesai"))
                break;

            int index = cariMenu(nama);

            if (index == -1) {
                System.out.println("Menu tidak ditemukan! Coba lagi.");
                continue;
            }

            System.out.print("Jumlah: ");
            int jumlah = safeInt();

            pesananNama[pesananCount] = menuList[index].nama;
            pesananJumlah[pesananCount] = jumlah;
            pesananCount++;

            System.out.println("Pesanan ditambahkan!");
        }

        if (pesananCount > 0)
            cetakStruk();
        else
            System.out.println("Tidak ada pesanan.");
    }

    // ==========================
    // ===== CETAK STRUK ========
    // ==========================
    static void cetakStruk() {
        System.out.println("\n=========== STRUK PEMBAYARAN ===========");

        int total = 0;
        int totalMinuman = 0;
        int jmlMinuman = 0;

        // Menggunakan for loop biasa untuk menampilkan pesanan
        for (int i = 0; i < pesananCount; i++) {
            int index = cariMenu(pesananNama[i]);
            int harga = menuList[index].harga;
            int jumlah = pesananJumlah[i];
            int subtotal = harga * jumlah;

            System.out.println(pesananNama[i] + " x" + jumlah + " = Rp " + subtotal);
            total += subtotal;

            if (menuList[index].kategori.equals("minuman")) {
                totalMinuman += subtotal;
                jmlMinuman += jumlah;
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Subtotal               : Rp " + total);

        int pajak = (int) (0.10 * total);
        int service = 20000;

        // Diskon 10% jika total > 100.000
        int diskon = (total > 100000) ? (int) (0.10 * total) : 0;

        // Promo Beli 1 Gratis 1 Minuman (gratis minuman termurah dari pesanan)
        int diskonMinuman = 0;
        if (total > 50000 && jmlMinuman > 1) {
            diskonMinuman = hargaMinumanTermurahDariPesanan();
        }

        int totalAkhir = total + pajak + service - diskon - diskonMinuman;

        System.out.println("Pajak 10%              : Rp " + pajak);
        System.out.println("Biaya pelayanan        : Rp " + service);
        if (diskon > 0)
            System.out.println("Diskon 10%             : -Rp " + diskon);
        if (diskonMinuman > 0)
            System.out.println("Promo Beli 1 Gratis 1  : -Rp " + diskonMinuman);

        System.out.println("----------------------------------------");
        System.out.println("TOTAL BAYAR            : Rp " + totalAkhir);
        System.out.println("========================================");
    }

    // Mencari harga minuman termurah dari pesanan (bukan dari semua menu)
    static int hargaMinumanTermurahDariPesanan() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < pesananCount; i++) {
            int index = cariMenu(pesananNama[i]);
            if (menuList[index].kategori.equals("minuman")) {
                if (menuList[index].harga < min)
                    min = menuList[index].harga;
            }
        }
        return (min == Integer.MAX_VALUE) ? 0 : min;
    }

    // ==========================
    // ====== MENU PENGELOLAAN ======
    // ==========================
    static void menuPengelolaan() {

        int pilih;
        do {
            System.out.println("\n===== MENU PENGELOLAAN RESTORAN =====");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            pilih = safeInt();

            switch (pilih) {
                case 1 -> tambahMenu();
                case 2 -> ubahHargaMenu();
                case 3 -> hapusMenu();
                case 4 -> {
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 4);
    }

    // Tambah menu
    static void tambahMenu() {
        input.nextLine();
        System.out.print("Nama menu baru: ");
        String nama = input.nextLine();
        System.out.print("Harga: ");
        int harga = safeInt();

        String kategori;
        while (true) {
            System.out.print("Kategori (makanan/minuman): ");
            kategori = input.nextLine();
            if (kategori.equals("makanan") || kategori.equals("minuman"))
                break;
            System.out.println("Kategori tidak valid!");
        }

        menuList[menuCount++] = new Menu(nama, harga, kategori);
        System.out.println("Menu berhasil ditambahkan!");
    }

    // Ubah harga menu
    static void ubahHargaMenu() {
        tampilkanMenu();
        input.nextLine();
        System.out.print("Masukkan nama menu yang ingin diubah: ");
        String nama = input.nextLine();

        int index = cariMenu(nama);
        if (index == -1) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        System.out.print("Yakin ingin mengubah harga? (Ya/Tidak): ");
        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("Ya")) {
            System.out.print("Harga baru: ");
            int hargaBaru = safeInt();
            menuList[index].harga = hargaBaru;
            System.out.println("Harga berhasil diubah!");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    // Hapus menu
    static void hapusMenu() {
        tampilkanMenu();
        input.nextLine();
        System.out.print("Masukkan nama menu yang ingin dihapus: ");
        String nama = input.nextLine();

        int index = cariMenu(nama);
        if (index == -1) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        System.out.print("Yakin ingin menghapus menu? (Ya/Tidak): ");
        String confirm = input.nextLine();
        if (!confirm.equalsIgnoreCase("Ya")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        for (int i = index; i < menuCount - 1; i++) {
            menuList[i] = menuList[i + 1];
        }

        menuCount--;
        System.out.println("Menu berhasil dihapus!");
    }

    // ==========================
    // ======= FUNGSI BANTU ======
    // ==========================
    static void tampilkanMenu() {
        System.out.println("\n------ DAFTAR MENU ------");
        System.out.println("-- MAKANAN --");
        
        // Menggunakan for-each loop untuk menampilkan makanan
        for (Menu menu : menuList) {
            if (menu == null) break;
            if (menu.kategori.equals("makanan"))
                System.out.println(menu.nama + " - Rp " + menu.harga);
        }

        System.out.println("-- MINUMAN --");
        
        // Menggunakan for-each loop untuk menampilkan minuman
        for (Menu menu : menuList) {
            if (menu == null) break;
            if (menu.kategori.equals("minuman"))
                System.out.println(menu.nama + " - Rp " + menu.harga);
        }
    }

    static int cariMenu(String nama) {
        // Menggunakan for loop dengan index karena perlu return index
        for (int i = 0; i < menuCount; i++) {
            if (menuList[i].nama.equalsIgnoreCase(nama))
                return i;
        }
        return -1;
    }

    static int safeInt() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.print("Masukkan angka valid: ");
            }
        }
    }
}
