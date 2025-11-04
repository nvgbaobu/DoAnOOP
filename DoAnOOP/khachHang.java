
import java.util.Scanner;

public class khachHang {

    private String maKH;
    private String hoKH;
    private String tenKH;
    private String gioiTinh;
    private String sdtKH;
    private String diaChiKH;

    public khachHang() {
    }

    public khachHang(String maKH, String hoKH, String tenKH, String gioiTinh, String sdtKH, String diaChiKH) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho: ");
        hoKH = sc.nextLine();
        System.out.print("Nhap ten: ");
        tenKH = sc.nextLine();
        System.out.print("Nhap gioi tinh: ");
        gioiTinh = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        sdtKH = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        diaChiKH = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n",
                maKH, hoKH, tenKH, gioiTinh, sdtKH, diaChiKH);
    }
}
