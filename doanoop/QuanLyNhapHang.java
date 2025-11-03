
import java.util.Scanner;

public class QuanLyNhapHang extends QuanLyBanHang {

    public QuanLyNhapHang(danhSachNhapHang ds) {
        this.dsNhapHang = ds;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("________________________________________");
            System.out.println("||       MENU QUAN LY PHIEU NHAP       ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Them phieu nhap                   ||");
            System.out.println("||2. Xoa phieu nhap theo ma san pham   ||");
            System.out.println("||3. Tim kiem phieu nhap               ||");
            System.out.println("||4. Xuat danh sach phieu nhap         ||");
            System.out.println("||5. Thong ke so luong may tinh        ||");
            System.out.println("||0. Thoat                             ||");
            System.out.println("________________________________________");
            System.out.print("Chon: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {

                    System.out.print("Nhap so luong phieu nhap can them: ");
                    int sl = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < sl; i++) {
                        System.out.println("\n--- Phieu nhap thu " + (i + 1) + " ---");
                        mayTinh mt = null;
                        String maSP;
                        while (true) {
                            System.out.print("Nhap ma san pham: ");
                            maSP = sc.nextLine().trim();
                            mt = dsMayTinh.layMayTinh(maSP);
                            if (mt != null) {
                                System.out.println("Thong tin may tinh:");
                                mt.xuat();
                                break;
                            } else {
                                System.out.println("Ma san pham khong ton tai! Nhap lai.");
                            }
                        }
                        dsNhapHang.them(maSP, mt);
                    }
                }
                case 2 -> {
                    System.out.println("nhap ma san pham can xoa: ");
                    String ma = sc.nextLine();
                    dsNhapHang.xoa(ma);
                }
                case 3 -> {
                    System.out.println("1.tim theo ma san pham");
                    System.out.println("2. tim theo ten san pham");
                    System.out.print("chon->");
                    int chon = sc.nextInt();
                    sc.nextLine();
                    if (chon == 1) {
                        System.out.print("Nhap ma san pham ");
                        String ma = sc.nextLine();
                        dsNhapHang.timTheoMa(ma);
                    } else if (chon == 2) {
                        System.out.print("nhap ten san pham ");
                        String ten = sc.nextLine().toLowerCase();
                        dsNhapHang.timTheoTen(ten);
                    } else {
                        System.out.print("da thoat ");
                        return;
                    }
                }
                case 4 ->
                    dsNhapHang.xuat();
                case 5 ->
                    dsNhapHang.thongKeSoLuong();
                case 0 -> {
                    dsNhapHang.ghiFile("danhsachphieunhap.txt");
                    System.out.println("Da luu va thoat.");
                    return;
                }
                default ->
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
