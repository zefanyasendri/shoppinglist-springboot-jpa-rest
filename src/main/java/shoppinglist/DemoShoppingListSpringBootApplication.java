package shoppinglist;

//import com.sun.tools.jdeprscan.scan.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shoppinglist.entity.DaftarBelanja;
import shoppinglist.entity.DaftarBelanjaDetil;
import shoppinglist.repository.DaftarBelanjaRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class DemoShoppingListSpringBootApplication implements CommandLineRunner
{

    @Autowired
    private DaftarBelanjaRepo repo;

    public static void main(String[] args)
    {
        SpringApplication.run(DemoShoppingListSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        int pilihan;
        while(true){
            System.out.print("1. Cari Semua Daftar Belanja\n" +
                    "2. Cari Daftar Belanjda berdasarkan id\n" +
                    "3. Cari Daftar Belanjda berdasarkan Judul\n" +
                    "4. Tambah Daftar Belanja\n" +
                    "5. Update Daftar Belanja\n" +
                    "6. Hapus Daftar Belanja\n"+
                    "7. Keluar"+
                    "\nMasukan Pilihan : ");
            pilihan = scan.nextInt();

            switch (pilihan){
                case 1:
                    CariSemuaDaftarBarang();
                    break;
                case 2:
                    CariDaftarBarangBerdasarkanId();
                    break;
                case 3:
                    CariDaftarBarangBerdasarkanJudul();
                    break;
                case 4:
                    TambahDaftarBelanja();
                    break;
                case 5:
                    PerbaruiDaftarBelanja();
                    break;
                case 6:
                    HapusDaftarBelanja();
                    break;
                case 7:
                    break;
            }
        }

    }

    public void CariSemuaDaftarBarang(){
        System.out.println("Membaca Semua Record DaftarBelanja:");
        List<DaftarBelanja> all = repo.findAll();
        for (DaftarBelanja db: all) {
            System.out.println("Judul : " + db.getJudul());
            List<DaftarBelanjaDetil> listDbd = db.getDaftarBarang();
            System.out.println("   DetailDaftarBelanja :");
            for (DaftarBelanjaDetil dbd : listDbd) {
                System.out.println("\t Nama Barang : " + dbd.getNamaBarang() + "\n\t Jumlah : " +
                        dbd.getByk() + "\n\t Satuan : " + dbd.getSatuan());
            }
        }
        gambarGaris();
    }

    public void CariDaftarBarangBerdasarkanId(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Membaca Record DaftarBelanja Berdasarkan Id:");
        System.out.print("Masukkan ID : ");
        String tempId = scan.nextLine();
        long id = Long.parseLong(tempId);
        System.out.println("Hasil pencarian: ");

        Optional<DaftarBelanja> optDB = repo.findById(id);
        if (optDB.isPresent()) {
            DaftarBelanja db = optDB.get();
            System.out.println("Judul : " + db.getJudul());
            List<DaftarBelanjaDetil> listDbd = db.getDaftarBarang();
            System.out.println("   DetailDaftarBelanja :");
            for (DaftarBelanjaDetil dbd : listDbd) {
                System.out.println("\t Nama Barang : " + dbd.getNamaBarang() + "\n\t Jumlah : " +
                        dbd.getByk() + "\n\t Satuan : " + dbd.getSatuan());
            }
            gambarGaris();
        }
        else {
            System.out.println("\tData tidak ditemukan.");
            gambarGaris();
        }
    }

    public void CariDaftarBarangBerdasarkanJudul() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Membaca Record DaftarBelanja Berdasarkan Judul:");
        System.out.print("Masukkan Judul : ");
        String Judul = scan.nextLine();
        System.out.println("Hasil pencarian: ");
        DaftarBelanja tempDb = null;
        List<DaftarBelanja> all = repo.findAll();
        for (DaftarBelanja db : all) {
            if(db.getJudul().equals(Judul)){
                tempDb = db;
            }
        }
        if(tempDb != null){
            System.out.println("Judul : " + tempDb.getJudul());
            List<DaftarBelanjaDetil> listDbd = tempDb.getDaftarBarang();
            System.out.println("   DetailDaftarBelanja :");
            for (DaftarBelanjaDetil dbd : listDbd) {
                System.out.println("\t Nama Barang : " + dbd.getNamaBarang() + "\n\t Jumlah : " +
                        dbd.getByk() + "\n\t Satuan : " + dbd.getSatuan());
            }
            gambarGaris();
        }else{
            System.out.println("Data tidak ditemukan");
            gambarGaris();
        }
    }

    public void TambahDaftarBelanja(){
        LocalDateTime tglBuat = LocalDateTime.now().withNano(0);
        Scanner scan = new Scanner(System.in);
        System.out.println("Tambah Record DaftarBelanja");
        System.out.print("Masukkan Judul : ");
        String Judul = scan.nextLine();
        DaftarBelanja db = new DaftarBelanja();

        db.setJudul(Judul);
        db.setTanggal(tglBuat);
        repo.save(db);
        System.out.println("Berhasil Dtambahkan");
        gambarGaris();
    }

    public void PerbaruiDaftarBelanja(){
        LocalDateTime tglBuat = LocalDateTime.now().withNano(0);
        Scanner scan = new Scanner(System.in);
        System.out.println("Update Reccord DaftarBelanja:");
        System.out.print("Masukkan ID : ");
        String tempId = scan.nextLine();
        long id = Long.parseLong(tempId);
        System.out.println("Hasil pencarian: ");

        Optional<DaftarBelanja> optDB = repo.findById(id);
        if (optDB.isPresent()) {
            DaftarBelanja db = optDB.get();
            System.out.println("Judul : " + db.getJudul());
            List<DaftarBelanjaDetil> listDbd = db.getDaftarBarang();
            System.out.println("   DetailDaftarBelanja :");
            for (DaftarBelanjaDetil dbd : listDbd) {
                System.out.println("\t Nama Barang : " + dbd.getNamaBarang() + "\n\t Jumlah : " +
                        dbd.getByk() + "\n\t Satuan : " + dbd.getSatuan());
            }
            System.out.println("Masukan Data Update");
            System.out.print("Masukan Judul : ");
            String Judul = scan.nextLine();
            db.setJudul(Judul);
            db.setTanggal(tglBuat);
            repo.save(db);
            System.out.println("Berhasil Diupdate");
            gambarGaris();
        }
        else {
            System.out.println("\tData tidak ditemukan.");
            gambarGaris();
        }
    }

    public void HapusDaftarBelanja(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Hapus DaftarBelanja:");
        System.out.print("Masukkan ID : ");
        String tempId = scan.nextLine();
        long id = Long.parseLong(tempId);

        Optional<DaftarBelanja> optDB = repo.findById(id);
        System.out.println("Data yang ingin dihapus : ");
        if (optDB.isPresent()) {
            DaftarBelanja db = optDB.get();
            System.out.println("Judul : " + db.getJudul());
            List<DaftarBelanjaDetil> listDbd = db.getDaftarBarang();
            System.out.println("   DetailDaftarBelanja :");
            for (DaftarBelanjaDetil dbd : listDbd) {
                System.out.println("\t Nama Barang : " + dbd.getNamaBarang() + "\n\t Jumlah : " +
                        dbd.getByk() + "\n\t Satuan : " + dbd.getSatuan());
            }
            System.out.println("Yakin ingin menghapus?(y/n)");
            String del = scan.nextLine();
            if(del.equals("y")){
                repo.deleteById(id);
                System.out.println("Penghapusan Berhasil");
            }
            gambarGaris();
        }
        else {
            System.out.println("\tData tidak ditemukan.");
            gambarGaris();
        }

    }

    public void gambarGaris(){
        System.out.println("------------------------------------------");
    }
}
