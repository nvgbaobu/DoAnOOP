
import java.util.Scanner;

public class secondHand extends mayTinh {

    private String tinhTrang;

    public secondHand() {
    }

    public secondHand(String maSP, String tenSP, String thuongHieu, String phanLoai,
            double giaTien, int soLuong, String tinhTrang) {
        super(maSP, tenSP, thuongHieu, phanLoai, giaTien, soLuong);
        this.tinhTrang = tinhTrang;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tinh trang (cu/moi): ");
        tinhTrang = sc.nextLine();
    }

    @Override
    public void xuat() {
        System.out.printf("%-14s %-30s %-18s %-18s %-12.2f %-10d %-12s %-12s %-14s\n",
                maSP, tenSP, thuongHieu, phanLoai, giaTien, soLuong, "-", "-", tinhTrang);
    }
}
