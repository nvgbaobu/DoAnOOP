
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class nhapHang {

    private String maSP, tenSP;
    private int soLuong;
    private String hoNguoiGiao, tenNguoiGiao;
    private String hoNguoiNhan, tenNguoiNhan;
    private String ngayNhap; // ← Định dạng: dd/MM/yyyy

    // TỰ ĐỘNG LẤY NGÀY HIỆN TẠI
    public nhapHang() {
        this.ngayNhap = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public nhapHang(String maSP, String tenSP, int soLuong,
            String hoNguoiGiao, String tenNguoiGiao,
            String hoNguoiNhan, String tenNguoiNhan, String ngayNhap) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.hoNguoiGiao = hoNguoiGiao;
        this.tenNguoiGiao = tenNguoiGiao;
        this.hoNguoiNhan = hoNguoiNhan;
        this.tenNguoiNhan = tenNguoiNhan;
        this.ngayNhap = ngayNhap;
    }

    // GETTERS
    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getHoNguoiGiao() {
        return hoNguoiGiao;
    }

    public String getTenNguoiGiao() {
        return tenNguoiGiao;
    }

    public String getHoNguoiNhan() {
        return hoNguoiNhan;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    // NHẬP – KHÔNG NHẬP NGÀY
    public void nhap(String maSP, String tenSP) {
        Scanner sc = new Scanner(System.in);
        this.maSP = maSP;
        this.tenSP = tenSP;

        System.out.print("Nhap ho nguoi giao: ");
        hoNguoiGiao = sc.nextLine();
        System.out.print("Nhap ten nguoi giao: ");
        tenNguoiGiao = sc.nextLine();
        System.out.print("Nhap ho nguoi nhan: ");
        hoNguoiNhan = sc.nextLine();
        System.out.print("Nhap ten nguoi nhan: ");
        tenNguoiNhan = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        sc.nextLine();

        // NGÀY TỰ ĐỘNG
        this.ngayNhap = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Ngay nhap (tu dong): " + this.ngayNhap);
    }

    public void xuat() {
        System.out.printf("%-10s %-20s %-10d %-15s %-10s %-15s %-10s %-12s\n",
                maSP, tenSP, soLuong, hoNguoiGiao, tenNguoiGiao,
                hoNguoiNhan, tenNguoiNhan, ngayNhap);
    }
}
