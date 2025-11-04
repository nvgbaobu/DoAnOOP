
import java.util.Scanner;

public class QuanLyHeThong {

    QuanLyBanHang dsMayTinh = new QuanLyMayTinh();
    QuanLyBanHang dsCTSP = new QuanLyChiTietSanPham();
    QuanLyBanHang dsNhanVien = new QuanLyNhanVien();
    QuanLyBanHang dsKhachHang = new QuanLyKhachHang();
    QuanLyBanHang dsNhaCungCap = new QuanLyNhaCungCap();
    QuanLyBanHang dsNhapHang = new QuanLyNhapHang();

    QuanLyBanHang dsHoaDon = new QuanLyHoaDonNhapHang();

    QuanLyBanHang dsHoaDonBanHang = new QuanLyHoaDon();
    QuanLyBanHang dsChiTietHoaDon = new QuanLyChiTietHoaDon();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           QUAN LY CUA HANG MAY TINH");
            System.out.println("=".repeat(60));
            System.out.println("1. Quan ly may tinh");
            System.out.println("2. Quan ly chi tiet san pham");
            System.out.println("3. Quan ly nhan vien");
            System.out.println("4. Quan ly khach hang");
            System.out.println("5. Quan ly nha cung cap");
            System.out.println("6. Quan ly phieu nhap");
            System.out.println("7. Quan ly hoa don nhap hang");
            System.out.println("8. Quan ly hoa don ban hang");     // MỚI
            System.out.println("9. Quan ly chi tiet hoa don");   // MỚI
            System.out.println("0. Thoat");
            System.out.println("=".repeat(60));
            System.out.print("CHON: ");

            String input = sc.nextLine().trim();
            int chon;
            try {
                chon = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("NHAP KHONG HOP LE! VUI LONG NHAP SO TU 0-9.");
                continue;
            }

            switch (chon) {
                case 1 ->
                    dsMayTinh.menu();
                case 2 ->
                    dsCTSP.menu();
                case 3 ->
                    dsNhanVien.menu();
                case 4 ->
                    dsKhachHang.menu();
                case 5 ->
                    dsNhaCungCap.menu();
                case 6 ->
                    dsNhapHang.menu();
                case 7 ->
                    dsHoaDon.menu();
                case 8 ->
                    dsHoaDonBanHang.menu();
                case 9 ->
                    dsChiTietHoaDon.menu();

                default ->
                    System.out.println("LUA CHON KHONG HOP LE! CHON LAI TU 0-9.");
            }
        }
    }

}
