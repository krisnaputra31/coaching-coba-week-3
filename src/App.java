import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String semuaBarang[][] = null;
        boolean nambahBarang = true;

        do {
            System.out.printf("Aplikasi Kasir %n 1. Input Barang %n 2. Cek Harga %n 3. Beli Barang %n Pilih Menu: ");
            int menu = Integer.parseInt(scanner.nextLine());

            printLine();

            if (menu == 1) {
                // Lakukan Input Barang sesuai soal
                System.out.print("Masukkan jumlah barang yang diinput: ");
                int jumlahBarang = Integer.parseInt(scanner.nextLine());
                semuaBarang = new String[jumlahBarang][3];
                for (int i = 0; i < jumlahBarang; i++) {
                    System.out.printf("Masukkan nama barang (Kode %d): ", i);
                    semuaBarang[i][0] = scanner.nextLine();
                    System.out.printf("Masukkan harga barang (Kode %d): ", i);
                    semuaBarang[i][1] = scanner.nextLine();
                }
                printLine();
            } else if (menu == 2) {
                System.out.print("Masukkan kode barang: ");
                int kodeBarang = Integer.parseInt(scanner.nextLine());
                try {
                    System.out.printf("%d | %s | %s %n", kodeBarang, semuaBarang[kodeBarang][0],
                            semuaBarang[kodeBarang][1]);
                } catch (Exception e) {
                    System.out.println("404 Not Found");
                }
                printLine();
            } else if (menu == 3) {
                try {
                    // seed hanya pancingan agar pengecekan array terjadi
                    String seed = semuaBarang[0][0];
                    // menu == 3 sebenarnya terjadi dibawah sini.
                    beliBarang(semuaBarang);
                    String jawaban = "n";
                    do {
                        // apakah ingin nambah barang
                        jawaban = inputReturnStr("Apakah ingin menambah barang?");
                        System.out.println("\n");
                        if (jawaban.equalsIgnoreCase("y")) {
                            beliBarang(semuaBarang);
                        } else if (jawaban.equalsIgnoreCase("n")) {
                            printTotal(semuaBarang);
                        } else {
                            System.out.println("Mohon masukkan hanya y atau n saja!");
                        }
                    } while (!jawaban.equalsIgnoreCase("n"));
                    nambahBarang = false;
                } catch (Exception e) {
                    System.out.println("Mohon Inputkan Barang sebelum beli barang");
                }
            } else {
                System.out.println("Mohon masukkan nomor menu yang benar < 1 / 2 / 3 >");
                printLine();
            }

        } while (nambahBarang);

    } // END OF MAIN

    private static void beliBarang(String[][] semuaBarang) {
        try {
            int kodeBarang = inputReturnInt("Masukkan kode barang: ");
            int jumlahBeli = inputReturnInt("Masukkan jumlah pembelian: ");
            int hargaSatuan = Integer.parseInt(semuaBarang[kodeBarang][1]);
            semuaBarang[kodeBarang][2] = hitungTotal(hargaSatuan, jumlahBeli) + "";
            System.out.printf("%-6s | %-6s | %-12s | %-11s %n", "Nama", "Jumlah", "Harga Satuan",
                    "Harga Total");
            System.out.printf("%-6s | %-6s | %-12d | %-11s %n%n",
                    semuaBarang[kodeBarang][0], jumlahBeli + "x", hargaSatuan, semuaBarang[kodeBarang][2]);
        } catch (Exception e) {
            System.out.println("404 Not Found");
        }
    }

    static void printTotal(String[][] barang) {
        System.out.println();
        int total = 0, grandTotal = 0;
        String diskon = "0%";
        for (int i = 0; i < barang.length; i++) {
            total += Integer.parseInt(barang[i][2]);
        }
        grandTotal = total;
        if (total > 100_000) {
            diskon = "10%";
            grandTotal -= (total * 0.1);
        }

        System.out.printf("%-12s: %d%n", "Total", total);
        System.out.printf("%-12s: %s%n", "Diskon", diskon);
        System.out.printf("%-12s: %d%n", "Grand Total", grandTotal);
    }

    private static int hitungTotal(int hargaSatuan, int jumlahBeli) {
        return hargaSatuan * jumlahBeli;
    }

    static String inputReturnStr(String pesan) {
        System.out.printf(pesan);
        return scanner.nextLine();
    }

    static String inputReturnStr(String pesan, int i) {
        System.out.printf(pesan, i);
        return scanner.nextLine();
    }

    static int inputReturnInt(String pesan) {
        System.out.printf(pesan);
        return Integer.parseInt(scanner.nextLine());
    }

    static void printLine() {
        System.out.println("=====");
    }
}
