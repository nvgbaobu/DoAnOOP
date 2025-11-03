
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class hoaDon {

    private String maHD;
    private String maSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String ngayXuat;

    // CONSTRUCTOR MẶC ĐỊNH – TỰ ĐỘNG NGÀY
    public hoaDon() {
        this.ngayXuat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // CONSTRUCTOR ĐẦY ĐỦ
    public hoaDon(String maHD, String maSP, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
        this.ngayXuat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // GETTERS
    public String getMaHD() {
        return maHD;
    }

    public String getMaSP() {
        return maSP;
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

    public String getNgayXuat() {
        return ngayXuat;
    }

    // SETTERS (có tính lại thành tiền)
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = soLuong * donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    // XUẤT DỮ LIỆU
    public void xuat() {
        System.out.printf("%-12s %-12s %-8d %-12.0f %-12.0f %-15s\n",
                maHD, maSP, soLuong, donGia, thanhTien, ngayXuat);
    }

    // XUẤT TIÊU ĐỀ
    public static void xuatTieuDe() {
        System.out.printf("%-12s %-12s %-8s %-12s %-12s %-15s\n",
                "MaHD", "MaSP", "SoLuong", "DonGia", "ThanhTien", "NgayXuat");
        System.out.println("-".repeat(80));
    }
}
