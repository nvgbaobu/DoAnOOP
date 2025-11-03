
import java.util.Scanner;

public class QuanLyNhaCungCap extends QuanLyBanHang {

    public QuanLyNhaCungCap(danhSachNhaCungCap ds) {
        this.dsNhaCungCap = ds;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String tenFile = "danhsachnhacungcap.txt";

        while (true) {
            System.out.println("________________________________________");
            System.out.println("||           MENU NHA CUNG CAP         ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Xuat danh sach                    ||");
            System.out.println("||2. Them nha cung cap                 ||");
            System.out.println("||3. Sua thong tin                     ||");
            System.out.println("||4. Xoa nha cung cap                  ||");
            System.out.println("||5. Tim kiem                          ||");
            System.out.println("||6. Thong ke so luong                 ||");
            System.out.println("||0. Thoat                             ||");
            System.out.println("________________________________________");
            System.out.print("Chon: ");

            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsNhaCungCap.xuat();
                case 2 -> {
                    System.out.print("nhap ma nha cung cap: ");
                    String ma = sc.nextLine();
                    while (dsNhaCungCap.kiemTraMaTrung(ma)) {
                        System.out.println("ma nha cung cap da ton tai.vui long nhap lai: ");
                        ma = sc.nextLine();
                    }
                    nhaCungCap ncc = new nhaCungCap();
                    ncc.nhap();
                    dsNhaCungCap.them(ma, ncc);

                }
                case 3 -> {
                    System.out.println("nhap ma nha cung cap: ");
                    String ma = sc.nextLine();
                    dsNhaCungCap.sua(ma);
                }
                case 4 -> {
                    System.out.println("nhap ma nha cung cap: ");
                    String ma = sc.nextLine();
                    dsNhaCungCap.xoa(ma);

                }
                case 5 -> {
                    System.out.println("1. tim theo ma");
                    System.out.println("2. tim theo ten");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 1) {
                        System.out.print("nhap ma nha cung cap: ");
                        String ma = sc.nextLine();
                        dsNhaCungCap.timTheoMa(ma);
                    } else if (choice == 2) {
                        System.out.print("nhap ten nha cung cap: ");
                        String ten = sc.nextLine().toLowerCase();
                        dsNhaCungCap.timTheoTen(ten);
                    } else {
                        System.out.println("lua chon khong hop le");
                    }

                }
                case 6 ->
                    dsNhaCungCap.thongKeSoLuong();
                case 0 -> {
                    dsNhaCungCap.ghiFile(tenFile);
                    System.out.println("Thoat chuong trinh!");
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }
    }
}
