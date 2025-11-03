
import java.util.Scanner;

public class QuanLyMayTinh extends QuanLyBanHang {

    public QuanLyMayTinh(danhSachMayTinh ds) {
        this.dsMayTinh = ds;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("________________________________________");
            System.out.println("||             MENU MAY TINH           ||");
            System.out.println("||------------------------------------||");
            System.out.println("||1. Xuat danh sach                    ||");
            System.out.println("||2. Them may                          ||");
            System.out.println("||3. Tim kiem                          ||");
            System.out.println("||4. Sua may                           ||");
            System.out.println("||5. Xoa may                           ||");
            System.out.println("||6. Thong ke so luong                 ||");
            System.out.println("||7. Thong ke gia tri                  ||");
            System.out.println("||0. Thoat                             ||");
            System.out.println("________________________________________");
            System.out.print("Chon: ");

            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1 ->
                    dsMayTinh.xuat();
                case 2 -> {

                    mayTinh mt;
                    int choice;
                    System.out.println("nhap ma san pham: ");
                    String ma = sc.nextLine();
                    while (dsMayTinh.kiemTraMaTrung(ma)) {
                        System.out.println("vui long nhap lai ma moi ");
                        ma = sc.nextLine();
                    }

                    do {
                        System.out.println("1. them brandnew");
                        System.out.println("2.them secondhand");
                        System.out.print("chon->");
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice == 1) {
                            mt = new brandNew();
                            mt.nhap();
                            break;
                        } else if (choice == 2) {
                            mt = new secondHand();
                            mt.nhap();
                            break;
                        } else {
                            System.out.println("lua chon khong hop le vui long chon lai");
                        }
                    } while (true);
                    mt.setMaSP(ma);
                    dsMayTinh.them(mt);
                }
                case 3 -> {
                    System.out.println("1. tim theo ma");
                    System.out.println("2. tim theo ten");
                    System.out.print("chon->");
                    int Choice = sc.nextInt();
                    sc.nextLine();
                    if (Choice == 1) {
                        System.out.println("nhap ma can tim: ");
                        String ma = sc.nextLine();
                        dsMayTinh.timKiemTheoMa(ma);
                    } else if (Choice == 2) {
                        System.out.println("nhap ten can tim: ");
                        String ten = sc.nextLine();
                        dsMayTinh.timKiemTheoTen(ten);
                    } else {
                        System.out.println("lua chon khong hop le");
                    }
                }
                case 4 -> {
                    System.out.println("nhap ma can sua: ");
                    String ma = sc.nextLine();
                    dsMayTinh.sua(ma);

                }
                case 5 -> {
                    System.out.println("nhap ma can xoa ");
                    String ma = sc.nextLine();
                    dsMayTinh.xoa(ma);
                }
                case 6 ->
                    dsMayTinh.thongKeSoLuong();
                case 7 ->
                    dsMayTinh.thongKeGiaTri();

                case 0 -> {
                    dsMayTinh.ghiFile("danhsachmaytinh.txt");
                    System.out.println("Da luu va thoat!");
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
        }

    }
}
