
public class chiTietHoaDon {

    private String maHD;
    private String maSP;
    private String maKH;
    private String maNV;
    private String tenSP;
    private String tenKH;
    private String tenNV;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String sdt;
    private String ngayXuat;

    // CONSTRUCTOR RỖNG
    public chiTietHoaDon() {
    }

    // CONSTRUCTOR ĐẦY ĐỦ
    public chiTietHoaDon(String maHD, String maSP, String maKH, String maNV,
            String tenSP, String tenKH, String tenNV,
            int soLuong, double donGia, String sdt, String ngayXuat) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tenSP = tenSP;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
        this.sdt = sdt;
        this.ngayXuat = ngayXuat;
    }

    // GETTERS
    public String getMaHD() {
        return maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public String getSdt() {
        return sdt;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    // XUẤT DỮ LIỆU
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-10s %-25s %-20s %-15s %-5d %-12.0f %-12.0f %-12s %-12s\n",
                maHD, maSP, maKH, maNV, tenSP, tenKH, tenNV, soLuong, donGia, thanhTien, sdt, ngayXuat);
    }

    // TIÊU ĐỀ
    public static void xuatTieuDe() {
        System.out.printf("%-10s %-10s %-10s %-10s %-25s %-20s %-15s %-5s %-12s %-12s %-12s %-12s\n",
                "MaHD", "MaSP", "MaKH", "MaNV", "TenSP", "TenKH", "TenNV", "SL", "DonGia", "ThanhTien", "SDT", "NgayXuat");

    }
}
