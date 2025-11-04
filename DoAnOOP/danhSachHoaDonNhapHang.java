
import java.io.*;
import java.util.Arrays;

class danhSachHoaDonNhapHang {

    private hoaDonNhapHang[] ds;
    private int n;
    private final String fileName = "danhsachhoadon.txt";
    nhapHang nh;
    danhSachNhapHang dsNhap;
    danhSachNhaCungCap dsNCC;
    danhSachMayTinh dsMayTinh;

    public danhSachHoaDonNhapHang(nhapHang nh, danhSachNhapHang dsNhap, danhSachNhaCungCap dsNCC, danhSachMayTinh dsMayTinh) {
        ds = new hoaDonNhapHang[0];
        n = 0;
        docFile();
        this.nh = nh;
        this.dsNCC = dsNCC;
        this.dsNhap = dsNhap;
        this.dsMayTinh = dsMayTinh;
    }

    public void themHoaDon(String nhaCungCap, double donGia) {
        String maHD = "HD" + (n + 1);
        hoaDonNhapHang hd = new hoaDonNhapHang(
                maHD, nhaCungCap, nh.getMaSP(), nh.getTenSP(),
                nh.getSoLuong(), donGia, nh.getTenNguoiNhan()
        );

        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = hd;
        hd.ghiFile(fileName); // ghi vao file ngay khi them
        System.out.println("Da tao hoa don: " + maHD);
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach hoa don rong!");
            return;
        }
        System.out.printf("%-10s %-15s %-10s %-20s %-10s %-10s %-12s %-15s\n",
                "MaHD", "NhaCungCap", "MaSP", "TenSP", "SoLuong", "DonGia", "ThanhTien", "NguoiNhan");
        for (hoaDonNhapHang hd : ds) {
            hd.xuat();
        }
    }

    public void thongKe() {
        int tongSoLuong = 0;
        double tongTien = 0;
        for (hoaDonNhapHang hd : ds) {
            tongSoLuong += hd.getSoLuong();
            tongTien += hd.getThanhTien();
        }
        System.out.println("Tong so luong may da nhan: " + tongSoLuong);
        System.out.printf("Tong so tien da thanh toan: %.2f\n", tongTien);
    }

    // ===== DOC FILE =====
    private void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    hoaDonNhapHang hd = new hoaDonNhapHang(
                            parts[0], parts[1], parts[2], parts[3],
                            Integer.parseInt(parts[4]),
                            Double.parseDouble(parts[5]),
                            parts[7]
                    );
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n++] = hd;
                }
            }
        } catch (IOException e) {
            System.out.println("Khong co file hoa don hoac loi doc: " + e.getMessage());
        }
    }

    public void taoHoaDonTuDong() {
        nhapHang nh = dsNhap.layPhieuCuoi();
        if (nh == null) {
            System.out.println("Khong co phieu nhap nao de tao hoa don!");
            return;
        }

        mayTinh mt = dsMayTinh.layMayTinh(nh.getMaSP());
        if (mt == null) {
            System.out.println("Khong tim thay san pham trong kho!");
            return;
        }

        String hoGiao = nh.getHoNguoiGiao().trim();
        nhaCungCap ncc = dsNCC.timKiemTheoTen(hoGiao); // ← HÀM ĐÃ CÓ
        String tenNCC = ncc != null ? ncc.getTenNCC() : hoGiao + " (Chua dang ky)"; // ← SỬA

        double donGia = mt.getGiaTien();
        String maHD = "HD" + String.format("%03d", n + 1);

        hoaDonNhapHang hd = new hoaDonNhapHang(
                maHD, tenNCC, nh.getMaSP(), nh.getTenSP(),
                nh.getSoLuong(), donGia, nh.getTenNguoiNhan()
        );

        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = hd;
        hd.ghiFile(fileName);

        System.out.println("\nHOA DON DA TAO TU DONG:");
        System.out.printf("%-10s %-20s %-10s %-20s %-10d %-12.2f %-12.2f %-15s\n",
                "MaHD", "NhaCungCap", "MaSP", "TenSP", "SL", "DonGia", "ThanhTien", "NguoiNhan");
        hd.xuat();
        System.out.println("Da luu vao file: " + fileName);
    }

}
