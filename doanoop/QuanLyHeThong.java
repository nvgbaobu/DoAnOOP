
import java.util.Scanner;

public class QuanLyHeThong {

    danhSachMayTinh dsMT = new danhSachMayTinh();
    QuanLyBanHang dsMayTinh = new QuanLyMayTinh(dsMT);

    danhSachChiTietSanPham dsCT = new danhSachChiTietSanPham();
    QuanLyBanHang dsCTSP = new QuanLyChiTietSanPham(dsCT);

    danhSachNhanVien dsNV = new danhSachNhanVien();
    QuanLyBanHang dsNhanVien = new QuanLyNhanVien(dsNV);

    danhSachKhachHang dsKH = new danhSachKhachHang();
    QuanLyBanHang dsKhachHang = new QuanLyKhachHang(dsKH);

    danhSachNhaCungCap dsNCC = new danhSachNhaCungCap();
    QuanLyBanHang dsNhaCungCap = new QuanLyNhaCungCap(dsNCC);

    danhSachNhapHang dsNH = new danhSachNhapHang(dsMT);
    QuanLyBanHang dsNhapHang = new QuanLyNhapHang(dsNH);

    nhapHang nh = new nhapHang();
    danhSachHoaDonNhapHang dsHDNH = new danhSachHoaDonNhapHang(nh, dsNH, dsNCC, dsMT);
    QuanLyBanHang dsHoaDon = new QuanLyHoaDonNhapHang(dsHDNH);

    danhSachHoaDon dsHD = new danhSachHoaDon(dsMT);
    QuanLyBanHang dsHoaDonBanHang = new QuanLyHoaDon(dsHD);
    danhSachChiTietHoaDon dsCTHD = new danhSachChiTietHoaDon(dsMT, dsKH, dsNV);
    QuanLyBanHang dsChiTietHoaDon = new QuanLyChiTietHoaDon(dsCTHD);

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("________________________________________");
            System.out.println("||    QUAN LY CUA HANG MAY TINH       ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Quan ly may tinh                 ||");
            System.out.println("||2. Quan ly chi tiet san pham        ||");
            System.out.println("||3. Quan ly nhan vien                ||");
            System.out.println("||4. Quan ly khach hang               ||");
            System.out.println("||5. Quan ly nha cung cap             ||");
            System.out.println("||6. Quan ly phieu nhap               ||");
            System.out.println("||7. Quan ly hoa don nhap hang        ||");
            System.out.println("||8. Quan ly hoa don ban hang         ||");
            System.out.println("||9. Quan ly chi tiet hoa don         ||");
            System.out.println("||10. Thong ke theo nhan vien         ||");
            System.out.println("||0. Thoat                            ||");
            System.out.println("________________________________________");
            System.out.print("CHON: ");
            int chon = sc.nextInt();
            sc.nextLine();

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

                case 10 -> {
                    System.out.println("===== DOANH THU THEO NHAN VIEN =====");
                    System.out.printf("%10s %10s %15s\n", "MaNV", "SoLuong", "TongTien");
                    for (int i = 0; i < dsNV.getN(); i++) {
                        nhanVien nv = dsNV.getNV(i);
                        chiTietHoaDon[] dsTheoNV = dsCTHD.timTatCaTheoMaNV(nv.getMaNV());
                        double tongTien = 0;
                        int tongSL = 0;

                        for (chiTietHoaDon cthd : dsTheoNV) {
                            tongTien += cthd.getThanhTien();
                            tongSL += cthd.getSoLuong();
                        }

                        if (tongSL > 0) {
                            System.out.printf("%10s %10d %15.2f\n", nv.getMaNV(), tongSL, tongTien);
                        }
                    }
                }

                default ->
                    System.out.println("LUA CHON KHONG HOP LE! CHON LAI TU 0-9.");
            }
        }
    }

    public void docFile() {
        dsMT.docFile("danhsachmaytinh.txt");
        dsCT.docFile("danhsachchitietsanpham.txt");
        dsNV.docFile("danhsachnhanvien.txt");
        dsKH.docFile("danhsachkhachhang.txt");
        dsNCC.docFile("danhsachnhacungcap.txt");
        dsNH.docFile("danhsachphieunhap.txt");
        dsHDNH.docFile("danhsachhoadonnhaphang.txt");
        dsHD.docFile("danhsachhoadon.txt");
        dsCTHD.docFile("danhsachchitiethoadon.txt");
    }

}
