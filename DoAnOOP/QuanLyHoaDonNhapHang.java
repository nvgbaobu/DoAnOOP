
import java.util.Scanner;

public class QuanLyHoaDonNhapHang extends QuanLyBanHang {

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== MENU HOA DON NHAP HANG =====");
            System.out.println("1. Tao hoa don tu phieu nhap moi nhat (TU DONG)");
            System.out.println("2. Xuat danh sach hoa don va thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsHoaDon.taoHoaDonTuDong();
                case 2 -> {
                    dsHoaDon.xuat();
                    dsHoaDon.thongKe();
                }
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }
    }
}
