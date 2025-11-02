
import java.util.Scanner;

public class nhanVien {

    private String maNV;
    private String hoNV;
    private String tenNV;
    private String viTriNV;
    private double gioLam;
    private double luongNV;

    public nhanVien() {
    }

    public nhanVien(String maNV, String hoNV, String tenNV, String viTriNV, double gioLam) {
        this.maNV = maNV;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.viTriNV = viTriNV;
        this.gioLam = gioLam;
        this.luongNV = tinhLuong();
    }

    public String getMaNV() {
        return maNV;
    }

    public String getHoNV() {
        return hoNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getViTriNV() {
        return viTriNV;
    }

    public double getGioLam() {
        return gioLam;
    }

    public double getLuongNV() {
        return luongNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setViTriNV(String viTriNV) {
        this.viTriNV = viTriNV;
    }

    public void setGioLam(double gioLam) {
        this.gioLam = gioLam;
        this.luongNV = tinhLuong();
    }

    public void setLuong(double luong) {
        this.luongNV = luong;
    }

    public double tinhLuong() {
        double luongCung;
        String vt = viTriNV.trim().toLowerCase();
        switch (vt) {
            case "quan li" ->
                luongCung = 80;
            case "ban hang" ->
                luongCung = 35;
            case "bo phan kho" ->
                luongCung = 40;
            case "bo phan ho tro" ->
                luongCung = 55;
            case "ky thuat" ->
                luongCung = 30;
            default ->
                luongCung = 0;
        }
        return gioLam * luongCung;
    }

    private String VT(int chon) {
        String[] a = {"quan li", "ban hang", "bo phan kho", "bo phan ho tro", "ky thuat"};
        return a[chon];
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        int chon;
        System.out.print("Nhap ho va ten lot: ");
        hoNV = sc.nextLine();
        System.out.print("Nhap ten: ");
        tenNV = sc.nextLine();
        System.out.println("chon vi tri lam viec: ");
        System.out.println("1. quan ly");
        System.out.println("2. ban hang");
        System.out.println("3. bo phan kho");
        System.out.println("4. bo phan ho tro");
        System.out.println("5. ky thuat ");
        System.out.print("chon->");
        chon = sc.nextInt();

        while (chon < 1 || chon > 5) {
            System.out.println("vui long chon lai");
            chon = sc.nextInt();
        }
        viTriNV = VT(chon - 1);
        System.out.print("Nhap so gio lam: ");
        gioLam = sc.nextDouble();
        sc.nextLine();
        luongNV = tinhLuong();
    }

    public void xuat() {
        System.out.printf("%-10s %-20s %-12s %-25s %-10.1f %-10.2f\n",
                maNV, hoNV, tenNV, viTriNV, gioLam, luongNV);
    }
}
