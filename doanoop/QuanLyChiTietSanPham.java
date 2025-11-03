
import java.util.Scanner;

public class QuanLyChiTietSanPham extends QuanLyBanHang {

    public QuanLyChiTietSanPham(danhSachChiTietSanPham ds) {
        this.dsCTSP = ds;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("________________________________________");
            System.out.println("||        MENU CHI TIET SAN PHAM      ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Xuat danh sach                    ||");
            System.out.println("||2. Them san pham                     ||");
            System.out.println("||3. Sua san pham                      ||");
            System.out.println("||4. Xoa san pham                      ||");
            System.out.println("||5. Thong ke tan so quet              ||");
            System.out.println("||6. Doc danh sach tu file             ||");
            System.out.println("||7. Ghi danh sach ra file             ||");
            System.out.println("||0. Thoat                             ||");
            System.out.println("________________________________________");
            System.out.print("Chon: ");

            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    dsCTSP.xuat();
                case 2 -> {
                    System.out.print("nhap ma san pham");
                    String ma = sc.nextLine();
                    while (true) {
                        if (dsCTSP.kiemTraMaTrung(ma)) {
                            System.out.println("Ma chi tiet da ton tai:");
                        } else if (!dsMayTinh.kiemTraMaTrung(ma)) {
                            System.out.println("Ma san pham khong ton tai trong danh sach may tinh:");
                        } else {
                            break;
                        }
                        ma = sc.nextLine();
                    }
                    mayTinh ds = dsMayTinh.layMayTinh(ma);
                    String ten = ds.getTenSP();
                    chiTietSanPham ctsp = new chiTietSanPham();
                    ctsp.nhap();
                    dsCTSP.them(ma, ten, ctsp);
                }
                case 3 ->
                    dsCTSP.sua();
                case 4 ->
                    dsCTSP.xoa();
                case 5 ->
                    dsCTSP.thongKeTanSoQuet();
                case 6 -> {
                    System.out.print("Nhap ten file can doc: ");
                    String tenFile = sc.nextLine();
                    dsCTSP.docFile(tenFile);
                }

                case 0 -> {
                    dsCTSP.ghiFile("danhsachchitietsanpham.txt");
                    System.out.println("da ghi vao file danhsach");
                    System.out.println("Thoat menu chi tiet san pham!");
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }
    }
}
