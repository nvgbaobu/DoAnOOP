
import java.io.*;

public class hoaDonNhapHang {

    private String maHD;
    private String nhaCungCap;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String tenNguoiNhan;

    public hoaDonNhapHang(String maHD, String nhaCungCap, String maSP, String tenSP,
            int soLuong, double donGia, String tenNguoiNhan) {
        this.maHD = maHD;
        this.nhaCungCap = nhaCungCap;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
        this.tenNguoiNhan = tenNguoiNhan;
    }

    // ===== GETTERS =====
    public String getMaHD() {
        return maHD;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
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

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    // ===== XUẤT =====
    public void xuat() {
        System.out.printf("%-10s %-15s %-10s %-20s %-10d %-10.2f %-12.2f %-15s\n",
                maHD, nhaCungCap, maSP, tenSP, soLuong, donGia, thanhTien, tenNguoiNhan);
    }

    // ===== GHI FILE =====
    public void ghiFile(String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(maHD + "," + nhaCungCap + "," + maSP + "," + tenSP + ","
                    + soLuong + "," + donGia + "," + thanhTien + "," + tenNguoiNhan);
        } catch (IOException e) {
            System.out.println("Loi ghi file hoa don: " + e.getMessage());
        }
    }
}
