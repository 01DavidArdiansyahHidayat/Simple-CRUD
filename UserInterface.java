import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("+====================+");
            System.out.println("|    Pilih Menu      |");
            System.out.println("+--------------------+");
            System.out.println("|    [C] : Create    |");
            System.out.println("|    [R] : Read      |");
            System.out.println("|    [U] : Update    |");
            System.out.println("|    [D] : Delete    |");
            System.out.println("|    [E] : Exit      |");
            System.out.println("+====================+");
            System.out.print("Pilih: ");
            String pilihan = sc.nextLine().toUpperCase();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO : Anda memilih menu Create");
                    System.out.print("NIM: ");
                    String nim = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama = sc.nextLine();
                    System.out.print("Alamat: ");
                    String alamat = sc.nextLine();
                    System.out.print("Semester: ");
                    int semester = Integer.parseInt(sc.nextLine());
                    System.out.print("SKS: ");
                    int sks = Integer.parseInt(sc.nextLine());
                    System.out.print("IPK: ");
                    double ipk = Double.parseDouble(sc.nextLine());

                    if (db.insert(new Mahasiswa(nim, nama, alamat, semester, sks, ipk))) {
                        System.out.println("Data berhasil ditambahkan.");
                    } else {
                        System.out.println("NIM sudah ada, gagal menambahkan data.");
                    }
                    break;

                case "R":
                    System.out.println("INFO : Anda memilih menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO : Anda memilih menu Update");
                    System.out.print("Masukkan NIM mahasiswa yang akan diupdate: ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index != -1) {
                        System.out.print("Apakah Anda yakin ingin mengubah data ini? (Y/N): ");
                        String konfirmasi = sc.nextLine().toUpperCase();

                        if (!konfirmasi.equals("Y")) {
                            System.out.println("Update dibatalkan.");
                            break;
                        }

                        System.out.print("Nama: ");
                        nama = sc.nextLine();
                        System.out.print("Alamat: ");
                        alamat = sc.nextLine();
                        System.out.print("Semester: ");
                        semester = Integer.parseInt(sc.nextLine());
                        System.out.print("SKS: ");
                        sks = Integer.parseInt(sc.nextLine());
                        System.out.print("IPK: ");
                        ipk = Double.parseDouble(sc.nextLine());

                        if (db.update(index, new Mahasiswa(key, nama, alamat, semester, sks, ipk))) {
                            System.out.println("Data berhasil diperbarui.");
                        } else {
                            System.out.println("Gagal memperbarui data.");
                        }
                    } else {
                        System.out.println("Mahasiswa tidak ditemukan.");
                    }

                    break;

                case "D":
                    System.out.println("INFO : Anda memilih menu Delete");
                    System.out.print("Masukkan NIM mahasiswa yang akan dihapus: ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index != -1) {
                        System.out.print("Apakah Anda yakin ingin menghapus data ini? (Y/N): ");
                        String konfirmasi = sc.nextLine().toUpperCase();

                        if (!konfirmasi.equals("Y")) {
                            System.out.println("Penghapusan dibatalkan.");
                            break;
                        }

                        if (db.delete(index)) {
                            System.out.println("Data berhasil dihapus.");
                        } else {
                            System.out.println("Gagal menghapus data.");
                        }
                    } else {
                        System.out.println("Mahasiswa tidak ditemukan.");
                    }

                    break;

                case "E":
                    System.out.println("INFO : Anda memilih menu EXIT");
                    System.out.println("Keluar dari program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
