public class Test {
    public static void main(String[] args) {
        Database db = new Database();
        db.open();

        System.out.println("Data Mahasiswa:");
        for (Mahasiswa mhs : db.data) {
            System.out.println(mhs);
        }

        Mahasiswa baru = new Mahasiswa("D1213456", "Budi", "Makassar", 6, 58, 2.31);
        db.data.add(baru);
        db.save();
        System.out.println("Data berhasil ditambahkan dan disimpan!");
    }
}
