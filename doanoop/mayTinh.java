
import java.util.Scanner;

public class mayTinh {

    protected String maSP;
    protected String tenSP;
    protected String thuongHieu;
    protected String phanLoai;
    protected double giaTien;
    protected int soLuong;

    public mayTinh() {
    }

    public mayTinh(String maSP, String tenSP, String thuongHieu, String phanLoai, double giaTien, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.thuongHieu = thuongHieu;
        this.phanLoai = phanLoai;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ten SP: ");
        tenSP = sc.nextLine();
        System.out.print("Nhap thuong hieu: ");
        thuongHieu = sc.nextLine();
        System.out.print("Nhap phan loai (Gaming/Van phong): ");
        phanLoai = sc.nextLine();
        System.out.print("Nhap gia tien: ");
        giaTien = sc.nextDouble();
        sc.nextLine();
        System.out.print("Nhap so luong may: ");
        soLuong = sc.nextInt();
        sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-14s %-30s %-18s %-18s %-12.2f %-10d %-12s %-12s %-14s\n",
                maSP, tenSP, thuongHieu, phanLoai, giaTien, soLuong, "-", "-", "-");
    }
}
