import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    // =====================================================
    // =============== ABSTRACT CLASS ======================
    // =====================================================
    static abstract class MenuItem {

        private String nama;
        private double harga;
        private String kategori; // makanan, minuman, diskon

        public MenuItem(String nama, double harga, String kategori) {
            this.nama = nama;
            this.harga = harga;
            this.kategori = kategori;
        }

        public String getNama() {
            return nama;
        }

        public double getHarga() {
            return harga;
        }

        public String getKategori() {
            return kategori;
        }

        public void setNama(String n) {
            nama = n;
        }

        public void setHarga(double h) {
            harga = h;
        }

        public void setKategori(String k) {
            kategori = k;
        }

        public abstract void tampilMenu();
    }

    // =====================================================
    // ================= SUBCLASS MAKANAN ==================
    // =====================================================
    static class Makanan extends MenuItem {

        private String porsi; // kecil, sedang, besar
        private int kalori;

        public Makanan(String nama, double harga, String porsi, int kalori) {
            super(nama, harga, "makanan");
            this.porsi = porsi;
            this.kalori = kalori;
        }

        public String getPorsi() {
            return porsi;
        }

        public int getKalori() {
            return kalori;
        }

        @Override
        public void tampilMenu() {
            System.out.println("[MAKANAN] " + getNama() +
                    " - Rp " + getHarga() +
                    " | Porsi: " + porsi +
                    " | Kalori: " + kalori + " kkal");
        }
    }

    // =====================================================
    // ================= SUBCLASS MINUMAN ==================
    // =====================================================
    static class Minuman extends MenuItem {

        private String ukuran; // small, medium, large
        private String suhu; // panas, dingin, normal

        public Minuman(String nama, double harga, String ukuran, String suhu) {
            super(nama, harga, "minuman");
            this.ukuran = ukuran;
            this.suhu = suhu;
        }

        public String getUkuran() {
            return ukuran;
        }

        public String getSuhu() {
            return suhu;
        }

        @Override
        public void tampilMenu() {
            System.out.println("[MINUMAN] " + getNama() +
                    " - Rp " + getHarga() +
                    " | Ukuran: " + ukuran +
                    " | Suhu: " + suhu);
        }
    }

    // =====================================================
    // ================= SUBCLASS DISKON ===================
    // =====================================================
    static class Diskon extends MenuItem {

        private double persenDiskon;

        public Diskon(String nama, double persen) {
            super(nama, 0.0, "diskon");
            this.persenDiskon = persen;
        }

        public double getPersenDiskon() {
            return persenDiskon;
        }

        @Override
        public void tampilMenu() {
            System.out.println("[DISKON] " + getNama() +
                    " - " + (persenDiskon * 100) + "%");
        }
    }

    // =====================================================
    // ================= CUSTOM EXCEPTION ==================
    // =====================================================
    static class MenuNotFoundException extends Exception {
        public MenuNotFoundException(String msg) {
            super(msg);
        }
    }

    // =====================================================
    // ================= KELAS MENU ========================
    // =====================================================
    static class Menu {

        private ArrayList<MenuItem> list = new ArrayList<>();

        public ArrayList<MenuItem> getItems() {
            return list;
        }

        public void tambah(MenuItem item) {
            list.add(item);
        }

        public MenuItem cari(String nama) throws MenuNotFoundException {
            for (MenuItem m : list) {
                if (m.getNama().equalsIgnoreCase(nama))
                    return m;
            }
            throw new MenuNotFoundException("Menu '" + nama + "' tidak ditemukan.");
        }

        public void tampilkan() {
            System.out.println("\n=== DAFTAR MENU RESTORAN ===");
            for (MenuItem m : list)
                m.tampilMenu();
        }

        public void simpanKeFile(String file) throws IOException {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (MenuItem m : list) {
                if (m instanceof Makanan) {
                    Makanan mk = (Makanan) m;
                    pw.println("MAKANAN;" + m.getNama() + ";" + m.getHarga() 
                            + ";" + mk.getPorsi() + ";" + mk.getKalori());
                } else if (m instanceof Minuman) {
                    Minuman mn = (Minuman) m;
                    pw.println("MINUMAN;" + m.getNama() + ";" + m.getHarga()
                            + ";" + mn.getUkuran() + ";" + mn.getSuhu());
                } else if (m instanceof Diskon) {
                    pw.println("DISKON;" + m.getNama()
                            + ";" + ((Diskon) m).getPersenDiskon());
                }
            }
            pw.close();
        }

        public void loadDariFile(String file) throws IOException {
            list.clear();
            File f = new File(file);
            if (!f.exists())
                return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p[0].equals("MAKANAN")) {
                    String porsi = (p.length > 3) ? p[3] : "sedang";
                    int kalori = (p.length > 4) ? Integer.parseInt(p[4]) : 0;
                    list.add(new Makanan(p[1], Double.parseDouble(p[2]), porsi, kalori));
                } else if (p[0].equals("MINUMAN")) {
                    String ukuran = (p.length > 3) ? p[3] : "medium";
                    String suhu = (p.length > 4) ? p[4] : "dingin";
                    list.add(new Minuman(p[1], Double.parseDouble(p[2]), ukuran, suhu));
                } else if (p[0].equals("DISKON")) {
                    list.add(new Diskon(p[1], Double.parseDouble(p[2])));
                }
            }
            br.close();
        }
    }

    // =====================================================
    // ================== KELAS PESANAN ====================
    // =====================================================
    static class Pesanan {

        private ArrayList<MenuItem> items = new ArrayList<>();
        private ArrayList<Integer> quantities = new ArrayList<>();
        private static final double PAJAK_RATE = 0.10; // 10%
        private static final double BIAYA_PELAYANAN = 20000;

        public void tambah(MenuItem item, int jumlah) {
            items.add(item);
            quantities.add(jumlah);
        }

        public double hitungSubtotal() {
            double total = 0;
            for (int i = 0; i < items.size(); i++) {
                if (!(items.get(i) instanceof Diskon)) {
                    total += items.get(i).getHarga() * quantities.get(i);
                }
            }
            return total;
        }

        public double hitungPajak() {
            return hitungSubtotal() * PAJAK_RATE;
        }

        public double hitungDiskon() {
            double max = 0;
            for (MenuItem m : items) {
                if (m instanceof Diskon) {
                    double d = ((Diskon) m).getPersenDiskon();
                    if (d > max)
                        max = d;
                }
            }
            return hitungSubtotal() * max;
        }

        public double totalAkhir() {
            return hitungSubtotal() + hitungPajak() + BIAYA_PELAYANAN - hitungDiskon();
        }

        public void cetakStruk(PrintWriter pw) {
            boolean fileMode = pw != null;
            if (!fileMode)
                pw = new PrintWriter(System.out);

            pw.println("\n=========================================");
            pw.println("           STRUK PESANAN RESTORAN        ");
            pw.println("=========================================");
            pw.println("Tanggal: " + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            pw.println("-----------------------------------------");

            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                int qty = quantities.get(i);

                if (!(item instanceof Diskon)) {
                    pw.printf("%-20s x%-3d = Rp %,.0f%n",
                            item.getNama(), qty, (item.getHarga() * qty));
                }
            }

            pw.println("-----------------------------------------");
            pw.printf("Subtotal            : Rp %,.0f%n", hitungSubtotal());
            pw.printf("Pajak (10%%)         : Rp %,.0f%n", hitungPajak());
            pw.printf("Biaya Pelayanan     : Rp %,.0f%n", BIAYA_PELAYANAN);
            
            if (hitungDiskon() > 0) {
                pw.printf("Diskon              : Rp %,.0f%n", hitungDiskon());
            }
            
            pw.println("-----------------------------------------");
            pw.printf("TOTAL BAYAR         : Rp %,.0f%n", totalAkhir());
            pw.println("=========================================");
            pw.println("      Terima Kasih Atas Kunjungan Anda  ");
            pw.println("=========================================");

            pw.flush();
        }
    }

    // =====================================================
    // ======================== MAIN ========================
    // =====================================================

    static Scanner sc = new Scanner(System.in);
    static Menu menu = new Menu();

    public static void main(String[] args) {

        // Load menu jika file ada, jika tidak tambah default
        try {
            menu.loadDariFile("menu.txt");
            if (menu.getItems().isEmpty())
                tambahDefault();
        } catch (IOException e) {
            tambahDefault();
        }

        int pilih;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah Item Menu");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Pesan Menu");
            System.out.println("4. Simpan Menu ke File");
            System.out.println("5. Muat Menu dari File");
            System.out.println("6. Keluar");
            System.out.print("Pilih: ");
            pilih = safeInt();

            switch (pilih) {
                case 1 -> tambahItem();
                case 2 -> menu.tampilkan();
                case 3 -> prosesPesanan();
                case 4 -> simpanMenu();
                case 5 -> muatMenu();
                case 6 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilihan tidak valid.");
            }

        } while (pilih != 6);
    }

    // =====================================================
    // ================ FUNGSI PENDUKUNG ===================
    // =====================================================

    static void tambahDefault() {
        menu.tambah(new Makanan("Nasi Goreng", 25000, "sedang", 450));
        menu.tambah(new Makanan("Mie Goreng", 22000, "sedang", 400));
        menu.tambah(new Makanan("Ayam Bakar", 30000, "besar", 550));
        menu.tambah(new Minuman("Es Teh", 7000, "medium", "dingin"));
        menu.tambah(new Minuman("Es Jeruk", 9000, "medium", "dingin"));
        menu.tambah(new Minuman("Kopi Hitam", 12000, "small", "panas"));
        menu.tambah(new Diskon("Diskon 10%", 0.10));
        menu.tambah(new Diskon("Diskon 20%", 0.20));
    }

    static void tambahItem() {
        System.out.println("\n=== TAMBAH ITEM MENU ===");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");
        System.out.print("Pilih: ");
        int p = safeInt();

        sc.nextLine();

        switch (p) {
            case 1 -> {
                System.out.print("Nama makanan: ");
                String n = sc.nextLine();
                System.out.print("Harga: ");
                double h = safeDouble();
                System.out.print("Porsi (kecil/sedang/besar): ");
                String porsi = sc.nextLine();
                System.out.print("Kalori: ");
                int kalori = safeInt();
                menu.tambah(new Makanan(n, h, porsi, kalori));
                System.out.println("✓ Makanan berhasil ditambahkan!");
            }
            case 2 -> {
                System.out.print("Nama minuman: ");
                String n = sc.nextLine();
                System.out.print("Harga: ");
                double h = safeDouble();
                System.out.print("Ukuran (small/medium/large): ");
                String ukuran = sc.nextLine();
                System.out.print("Suhu (panas/dingin/normal): ");
                String suhu = sc.nextLine();
                menu.tambah(new Minuman(n, h, ukuran, suhu));
                System.out.println("✓ Minuman berhasil ditambahkan!");
            }
            case 3 -> {
                System.out.print("Nama diskon: ");
                String n = sc.nextLine();
                System.out.print("Persen diskon (0.1 untuk 10%): ");
                double d = safeDouble();
                menu.tambah(new Diskon(n, d));
                System.out.println("✓ Diskon berhasil ditambahkan!");
            }
            default -> System.out.println("✗ Pilihan tidak valid!");
        }
    }

    static void prosesPesanan() {
        Pesanan pesanan = new Pesanan();
        String nama;

        System.out.println("\n=== PEMESANAN ===");

        sc.nextLine();

        while (true) {
            menu.tampilkan();
            System.out.print("Masukkan nama item (atau selesai): ");
            nama = sc.nextLine();
            if (nama.equalsIgnoreCase("selesai"))
                break;

            try {
                MenuItem item = menu.cari(nama);
                System.out.print("Jumlah: ");
                int j = safeInt();
                pesanan.tambah(item, j);
                System.out.println("Ditambahkan!");
            } catch (MenuNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        pesanan.cetakStruk(null);
        simpanStruk(pesanan);
    }

    static void simpanMenu() {
        try {
            menu.simpanKeFile("menu.txt");
            System.out.println("Menu disimpan ke menu.txt");
        } catch (Exception e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
        }
    }

    static void muatMenu() {
        try {
            menu.loadDariFile("menu.txt");
            System.out.println("Menu dimuat dari menu.txt");
        } catch (Exception e) {
            System.out.println("Gagal memuat: " + e.getMessage());
        }
    }

    static void simpanStruk(Pesanan p) {
        try {
            String waktu = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            PrintWriter pw = new PrintWriter(new FileWriter("struk_" + waktu + ".txt"));
            p.cetakStruk(pw);
            pw.close();
            System.out.println("Struk disimpan.");
        } catch (Exception e) {
            System.out.println("Gagal menyimpan struk.");
        }
    }

    static int safeInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Masukkan angka: ");
            }
        }
    }

    static double safeDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Masukkan angka desimal: ");
            }
        }
    }
}
