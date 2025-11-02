
import java.util.*;

public class QuanLyNhanVien extends QuanLyBanHang {

    @Override
    public void menu() {

        Scanner sc = new Scanner(System.in);
        String tenFile = "danhsachnhanvien.txt";
        dsNhanVien.docFile(tenFile);

        while (true) {
            System.out.println("\n===== MENU NHAN VIEN =====");
            System.out.println("1. Xuat danh sach");
            System.out.println("2. Them nhan vien");
            System.out.println("3. Sua thong tin nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke luong");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsNhanVien.xuat();
                case 2 -> {
                    System.out.println("nhap ma nhan vien ");
                    String ma = sc.nextLine();
                    while (dsNhanVien.kiemTraMaTrung(ma)) {
                        System.out.println("nhap lai ma moi ");
                        ma = sc.nextLine();
                    }
                    nhanVien nv = new nhanVien();
                    dsNhanVien.them(ma, nv);
                }
                case 3 -> {
                    System.out.print("nhap ma nhan vien ");
                    String ma = sc.nextLine();
                    while (!dsNhanVien.kiemTraMaTrung(ma)) {
                        System.out.println("ma nhan vien khong ton tai. Vui long nhap lai. ");
                        ma = sc.nextLine();
                    }
                    dsNhanVien.sua(ma);
                }
                case 4 -> {
                    System.out.println("nhap ma nhan vien");
                    String ma = sc.nextLine();
                    while (!dsNhanVien.kiemTraMaTrung(ma)) {
                        System.out.println("ma nhan vien khong ton tai ");
                        ma = sc.nextLine();
                    }
                    dsNhanVien.xoa(ma);
                }
                case 5 -> {
                    int choice;
                    System.out.println("1. tim theo ma");
                    System.out.println("2. tim theo ten");
                    System.out.println("chon->");
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 1) {
                        System.out.print("nhap ma nhan vien can tim");
                        String ma = sc.nextLine();
                        while (!dsNhanVien.kiemTraMaTrung(ma)) {
                            System.out.println("vui long nhap lai ma moi");
                            ma = sc.nextLine();
                        }
                        dsNhanVien.timTheoMa(ma);
                    } else if (choice == 2) {
                        System.out.println("nhap ten nhan vien can tim");
                        String ten = sc.nextLine();

                        dsNhanVien.timTheoTen(ten);
                    }
                }
                case 6 ->
                    dsNhanVien.thongKeLuong();

                case 0 -> {
                    dsNhanVien.ghiFile(tenFile);
                    System.out.println("Thoat chuong trinh!");
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }
    }
}
