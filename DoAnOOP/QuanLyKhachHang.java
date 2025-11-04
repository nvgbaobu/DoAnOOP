
import java.util.Scanner;

public class QuanLyKhachHang extends QuanLyBanHang {

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String tenFile = "danhsachkhachhang.txt";
        dsKhachHang.docFile(tenFile);

        while (true) {
            System.out.println("\n===== MENU KHACH HANG =====");
            System.out.println("1. Xuat danh sach");
            System.out.println("2. Them khach hang");
            System.out.println("3. Sua thong tin");
            System.out.println("4. Xoa khach hang");
            System.out.println("5. Tim kiem");

            System.out.println("6. Thong ke gioi tinh");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsKhachHang.xuat();
                case 2 -> {
                    System.out.print("nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    while (dsKhachHang.kiemTraMaTrung(ma)) {
                        System.out.print("khach hang da ton tai. Vui long nhap lai: ");
                        ma = sc.nextLine();
                    }
                    khachHang kh = new khachHang();
                    kh.nhap();
                    dsKhachHang.them(kh, ma);
                }
                case 3 -> {
                    System.out.print("nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    dsKhachHang.sua(ma);
                }
                case 4 -> {
                    System.out.print("nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    dsKhachHang.xoa(ma);
                }
                case 5 -> {
                    int choice;
                    System.out.println("1. tim theo ma: ");
                    System.out.println("2. tim theo ten:");
                    System.out.print("chon-> ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 1) {
                        System.out.print("nhap ma khach hang: ");
                        String ma = sc.nextLine();
                        dsKhachHang.timTheoMa(ma);
                    } else if (choice == 2) {
                        System.out.print("nhap ten khach hang: ");
                        String ten = sc.nextLine();
                        dsKhachHang.timTheoTen(ten);
                    }
                }

                case 6 ->
                    dsKhachHang.thongKeGioiTinh();

                case 0 -> {
                    dsKhachHang.ghiFile(tenFile);
                    System.out.println("Thoat chuong trinh!");
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }
    }

}
