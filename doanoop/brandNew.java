
import java.util.Scanner;

public class brandNew extends mayTinh {

    private double uuDai;
    private int baoHanh;

    public brandNew() {
    }

    public brandNew(String maSP, String tenSP, String thuongHieu, String phanLoai,
            double giaTien, int soLuong, double uuDai, int baoHanh) {
        super(maSP, tenSP, thuongHieu, phanLoai, giaTien, soLuong);
        this.uuDai = uuDai;
        this.baoHanh = baoHanh;
    }

    public double getUuDai() {
        return uuDai;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setUuDai(double uuDai) {
        this.uuDai = uuDai;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap uu dai (%): ");
        uuDai = sc.nextDouble();
        sc.nextLine();
        System.out.print("Nhap bao hanh (thang): ");
        baoHanh = sc.nextInt();
        sc.nextLine();
    }

    @Override
    public void xuat() {
        System.out.printf("%-14s %-30s %-18s %-18s %-12.2f %-10d %-12.2f %-12d %-14s\n",
                maSP, tenSP, thuongHieu, phanLoai, giaTien, soLuong, uuDai, baoHanh, "-");
    }
}
