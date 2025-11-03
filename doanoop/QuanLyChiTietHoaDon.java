
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class QuanLyChiTietHoaDon extends QuanLyBanHang {

    public QuanLyChiTietHoaDon(danhSachChiTietHoaDon ds) {
        this.dsChiTietHoaDon = ds;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("________________________________________");
            System.out.println("||         MENU CHI TIET HOA DON       ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Xuat danh sach                   ||");
            System.out.println("||2. Them chi tiet (cho hoa don moi)  ||");
            System.out.println("||3. Tim kiem theo ma hoa don         ||");
            System.out.println("||4. Xem tong tien hoa don            ||");
            System.out.println("||0. Thoat                            ||");
            System.out.println("________________________________________");
            System.out.print("Chon: ");

            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsChiTietHoaDon.xuat();
                case 2 -> {
                    System.out.print("Nhap ma hoa don: ");
                    String maHD = sc.nextLine().trim();

                    while (dsChiTietHoaDon.kiemTraMaTrung(maHD)) {
                        System.out.println("nhap lai ma hoa don ");
                        maHD = sc.nextLine();

                    }
                    String ngayXuat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    // LẤY KHÁCH HÀNG
                    dsKhachHang.xuat();
                    System.out.print("Chon ma KH: ");
                    String maKH = sc.nextLine().trim();
                    khachHang kh = dsKhachHang.timKiemTheoMa(maKH);
                    if (kh == null) {
                        System.out.println("KHONG TIM THAY KHACH HANG!");
                        return;
                    }

                    System.out.print("nhap ma nhan vien ");
                    String ma = sc.nextLine();
                    nhanVien nv = dsNhanVien.layNhanVienDauTien(ma);
                    if (nv == null) {
                        System.out.println("KHONG CO NHAN VIEN!");
                        return;
                    }

                    System.out.print("Nhap so san pham trong hoa don: ");
                    int slSP = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < slSP; i++) {
                        System.out.println("\n--- SAN PHAM THU " + (i + 1) + " ---");
                        dsMayTinh.xuat();
                        System.out.print("Chon ma SP: ");
                        String maSP = sc.nextLine().trim();
                        mayTinh sp = dsMayTinh.layMayTinh(maSP);
                        if (sp == null) {
                            System.out.println("SAN PHAM KHONG TON TAI!");
                            i--;
                            continue;
                        }

                        System.out.print("So luong: ");
                        int sl = sc.nextInt();
                        sc.nextLine();
                        if (sl > sp.getSoLuong()) {
                            System.out.println("CHI CON " + sp.getSoLuong() + " SAN PHAM!");
                            i--;
                            continue;
                        }
                        dsChiTietHoaDon.them(maHD, ngayXuat, kh, nv, sp, sl);
                    }
                }
                case 3 -> {
                    System.out.print("nhap ma hoa don can tim ");
                    String ma = sc.nextLine();
                    dsChiTietHoaDon.timKiemTheoMaHD(ma);
                }
                case 4 -> {
                    System.out.print("Nhap ma hoa don: ");
                    String maHD = sc.nextLine().trim();
                    double tong = dsChiTietHoaDon.tongTienTheoMaHD(maHD);
                    System.out.println("TONG TIEN HOA DON " + maHD + ": " + String.format("%,.0f", tong) + " VND");
                }
                case 0 -> {
                    dsChiTietHoaDon.ghiFile("danhsachchitiethoadon.txt");
                    System.out.println("DA LUU VA THOAT!");
                    return;
                }
                default ->
                    System.out.println("CHON SAI!");
            }
        }
    }
}
