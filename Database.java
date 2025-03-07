import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Database {
    ArrayList<Mahasiswa> data = new ArrayList<>();
    private String filename = "data.csv";
    private Path path = Path.of(filename);

    public Database() {
        open();
    }

    public ArrayList<Mahasiswa> getData() {
        return data;
    }

    public void open() {
        try {
            List<String> lines = Files.readAllLines(path);
            data.clear();
            for (int i = 1; i < lines.size(); i++) {
                String[] element = lines.get(i).split(",");
                if (element.length == 6) {
                    Mahasiswa mhs = new Mahasiswa(
                            element[0], element[1], element[2],
                            Integer.parseInt(element[3]),
                            Integer.parseInt(element[4]),
                            Double.parseDouble(element[5])
                    );
                    data.add(mhs);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }

    public void save() {
        try {
            StringBuilder sb = new StringBuilder("NIM,NAMA,ALAMAT,SEMESTER,SKS,IPK");
            for (Mahasiswa mhs : data) {
                sb.append(mhs.getNim()).append(",")
                        .append(mhs.getNama()).append(",")
                        .append(mhs.getAlamat()).append(",")
                        .append(mhs.getSemester()).append(",")
                        .append(mhs.getSks()).append(",")
                        .append(mhs.getIpk()).append("\n");
            }
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public void view() {
        System.out.println("==========================================================");
        System.out.printf("| %-10s | %-20s | %-10s | %-3s | %-3s | %-4s |\n",
                "NIM", "NAMA", "ALAMAT", "SEMESTER", "SKS", "IPK");
        System.out.println("----------------------------------------------------------");
        for (Mahasiswa mhs : data) {
            System.out.printf("| %-10s | %-20s | %-10s | %-3d | %-3d | %-4.2f |\n",
                    mhs.getNim(), mhs.getNama(), mhs.getAlamat(),
                    mhs.getSemester(), mhs.getSks(), mhs.getIpk());
        }
        System.out.println("==========================================================");
    }

    public boolean insert(Mahasiswa mhs) {
        for (Mahasiswa mahasiswa : data) {
            if (mahasiswa.getNim().equalsIgnoreCase(mhs.getNim())) {
                return false;
            }
        }
        data.add(mhs);
        save();
        return true;
    }

    public int search(String nim) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(int index, Mahasiswa mhs) {
        if (index >= 0 && index < data.size()) {
            data.set(index, mhs);
            save();
            return true;
        }
        return false;
    }

    public boolean delete(int index) {
        if (index >= 0 && index < data.size()) {
            data.remove(index);
            save();
            return true;
        }
        return false;
    }
}
