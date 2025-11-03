
import java.util.Scanner;

public class nhaCungCap {

    private String maNCC;
    private String tenNhaCungCap;
    private String diaChi;
    private String hotline;
    private String email;

    public nhaCungCap() {
    }

    public nhaCungCap(String maNCC, String tenNhaCungCap, String diaChi, String hotline, String email) {
        this.maNCC = maNCC;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.hotline = hotline;
        this.email = email;
    }

    // GETTERS
    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNhaCungCap;
    }  // ← THÊM DÒNG NÀY

    public String getDiaChi() {
        return diaChi;
    }

    public String getHotline() {
        return hotline;
    }

    public String getEmail() {
        return email;
    }

    // SETTERS
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten nha cung cap: ");
        tenNhaCungCap = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChi = sc.nextLine();
        System.out.print("Nhap hotline: ");
        hotline = sc.nextLine();
        System.out.print("Nhap email: ");
        email = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-25s %-25s %-15s %-25s\n",
                maNCC, tenNhaCungCap, diaChi, hotline, email);
    }
}
