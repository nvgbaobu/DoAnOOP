
import java.util.Scanner;

public class QuanLyHoaDon extends QuanLyBanHang {
    // MENU CHÍNH

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU QUAN LY HOA DON BAN HANG =====");
            System.out.println("1. Xuat danh sach hoa don");
            System.out.println("2. Them hoa don moi");
            System.out.println("3. Tim kiem theo ma hoa don");
            System.out.println("4. Tim kiem theo ma san pham");
            System.out.println("5. Xoa hoa don");
            System.out.println("6. Thong ke doanh thu");
            System.out.println("7. Doc lai tu file");
            System.out.println("8. Ghi ra file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsHoaDonBanHang.xuat();
                case 2 -> {

                    System.out.print("Nhap so luong hoa don can them: ");
                    int sl = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < sl; i++) {
                        System.out.println("\n--- HOA DON THU " + (i + 1) + " ---");

                        // HIỂN THỊ SẢN PHẨM CÓ SẴN
                        dsMayTinh.xuat();
                        System.out.print("Nhap ma san pham: ");
                        String maSP = sc.nextLine().trim();

                        mayTinh sp = dsMayTinh.layMayTinh(maSP);
                        if (sp == null) {
                            System.out.println("SAN PHAM KHONG TON TAI! Nhap lai.");
                            i--;
                            continue;
                        }

                        System.out.print("Nhap so luong: ");
                        int soLuong = sc.nextInt();
                        sc.nextLine();

                        if (soLuong > sp.getSoLuong()) {
                            System.out.println("CHI CON " + sp.getSoLuong() + " SAN PHAM! Nhap lai.");
                            i--;
                            continue;
                        }
                        dsHoaDonBanHang.them(maSP, soLuong, sp);
                    }
                }
                case 3 -> {
                    System.out.print("nhap ma hoa don can tim ");
                    String ma = sc.nextLine();
                    dsHoaDonBanHang.timKiemTheoMaHD(ma);
                }
                case 4 -> {
                    System.out.print("nhap ma san pham can tim ");
                    String ma = sc.nextLine();
                    dsHoaDonBanHang.timKiemTheoMaSP(ma);
                }
                case 5 ->
                    dsHoaDonBanHang.xoa();
                case 6 ->
                    dsHoaDonBanHang.thongKeDoanhThu();
                case 7 -> {
                    System.out.print("Nhap ten file: ");
                    dsHoaDonBanHang.docFile(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("Nhap ten file ghi: ");
                    dsHoaDonBanHang.ghiFile(sc.nextLine());
                }
                case 0 -> {
                    dsHoaDonBanHang.ghiFile("danhsachhoadon.txt");
                    System.out.println("DA LUU VA THOAT MENU HOA DON!");
                    return;
                }
                default ->
                    System.out.println("CHON SAI! Vui long chon lai.");
            }
        }
    }
}
