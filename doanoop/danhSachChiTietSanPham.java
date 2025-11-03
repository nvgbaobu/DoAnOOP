
import java.io.*;
import java.util.*;

public class danhSachChiTietSanPham {

    private chiTietSanPham[] ds;
    private int n;
    danhSachMayTinh mt;

    // ĐỌC FILE NGAY KHI KHỞI TẠO
    public danhSachChiTietSanPham() {

        ds = new chiTietSanPham[0];
        n = 0;

    }

    public danhSachChiTietSanPham(danhSachMayTinh mt) {
        this();
        this.mt = mt;
    }

    // Thêm sản phẩm (nhập tay)
    public void them(String ma, String ten, chiTietSanPham ctsp) {
        Scanner sc = new Scanner(System.in);

        ctsp.setMaSP(ma);
        ctsp.setTenSP(ten);
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = ctsp;
    }

    // Tìm theo mã
    public chiTietSanPham timTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    // Kiểm tra mã trùng
    public boolean kiemTraMaTrung(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    // Xuất danh sách
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.printf("%-12s %-25s %-15s %-15s %-10s %-10s %-10s\n",
                "MaSP", "TenSP", "CPU", "GPU", "RAM", "ROM", "TanSoQuet");
        for (int i = 0; i < n; i++) {
            ds[i].xuat();
        }
    }

    // Xóa sản phẩm
    public void xoa() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                System.out.println("Tim thay san pham:");
                ds[i].xuat();
                System.out.print("Xoa san pham nay (Y/N)? ");
                if (sc.nextLine().equalsIgnoreCase("Y")) {
                    for (int j = i; j < n - 1; j++) {
                        ds[j] = ds[j + 1];
                    }
                    n--;
                    ds = Arrays.copyOf(ds, n);
                    System.out.println("Da xoa!");
                } else {
                    System.out.println("Huy xoa!");
                }
                return;
            }
        }
        System.out.println("Khong tim thay ma nay!");
    }

    // Sửa sản phẩm
    public void sua() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                System.out.println("Tim thay:");
                ds[i].xuat();
                int choice;
                System.out.println("Nhap thong tin moi:");
                do {
                    System.out.println("1. Nhap CPU");
                    System.out.println("2. Nhap GPU");
                    System.out.println("3. Nhap Ram");
                    System.out.println("4. nhap Rom");
                    System.out.println("5. Nhap tan so quet (vd: 144Hz):");
                    System.out.println("0. Thoat");
                    System.out.println("chon->");
                    choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("Nhap CPU moi: ");
                            ds[i].setCPU(sc.nextLine());
                            break;
                        case 2:
                            System.out.println("Nhap GPU moi: ");
                            ds[i].setGPU(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Nhap Ram moi: ");
                            ds[i].setRam(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("Nhap Rom moi: ");
                            ds[i].setRom(sc.nextLine());
                            break;
                        case 5:
                            System.out.println("Nhap Tan so Quet moi:");
                            ds[i].setTanSoQuet(sc.nextLine());
                            break;
                        case 0:
                            System.out.println("da sua thanh cong.");
                            return;
                        default:
                            System.out.println("vui long chon lai.");
                            break;
                    }

                } while (choice != 0);
            }
        }
        System.out.println("Khong tim thay ma nay!");
    }

    // Thống kê tần số quét
    public void thongKeTanSoQuet() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.printf("%-12s %-25s %-15s\n", "MaSP", "TenSP", "TanSoQuet");
        int tong = 0;
        for (int i = 0; i < n; i++) {
            chiTietSanPham sp = ds[i];
            String tsq = sp.getTanSoQuet().replaceAll("[^0-9]", "");
            int so = tsq.isEmpty() ? 0 : Integer.parseInt(tsq);
            System.out.printf("%-12s %-25s %-15s\n", sp.getMaSP(), sp.getTenSP(), sp.getTanSoQuet());
            tong += so;
        }
        System.out.println("------------------------------------");
        System.out.println("Tong tan so quet (so): " + tong + " Hz");
    }

    // ĐỌC FILE – CÓ DEBUG CHI TIẾT
    public void docFile(String tenFile) {
        File file = new File(tenFile);
        if (!file.exists()) {
            System.out.println("FILE KHONG TON TAI: " + tenFile);
            System.out.println("Vui long tao file va them du lieu mau!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            int lineCount = 0;
            int successCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 7) {
                    System.out.println("DONG SAI DINH DANG (dong " + lineCount + "): " + line);
                    continue;
                }

                chiTietSanPham ctsp = new chiTietSanPham(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        parts[5].trim(),
                        parts[6].trim()
                );

                ds = Arrays.copyOf(ds, n + 1);
                ds[n++] = ctsp;
                successCount++;
            }

            System.out.println("DA DOC FILE THANH CONG!");
            System.out.println(" - So dong doc: " + lineCount);
            System.out.println(" - So san pham them: " + successCount);
            System.out.println(" - Tong san pham hien tai: " + n);

        } catch (FileNotFoundException e) {
            System.out.println("KHONG TIM THAY FILE: " + tenFile);
        } catch (IOException e) {
            System.out.println("LOI DOC FILE: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("LOI KHAC: " + e.getMessage());
        }
    }

    // GHI FILE
    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < n; i++) {
                chiTietSanPham ctsp = ds[i];
                pw.println(ctsp.getMaSP() + "," + ctsp.getTenSP() + ","
                        + ctsp.getCPU() + "," + ctsp.getGPU() + ","
                        + ctsp.getRAM() + "," + ctsp.getROM() + ","
                        + ctsp.getTanSoQuet());
            }
            System.out.println("Da ghi file thanh cong: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void them(chiTietSanPham ctsp) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = ctsp;
    }

    // MENU – ĐÃ XÓA docFile() TRÙNG LẶP
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU CHI TIET SAN PHAM =====");
            System.out.println("1. Xuat danh sach");
            System.out.println("2. Them san pham");
            System.out.println("3. Sua san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Thong ke tan so quet");
            System.out.println("6. Doc danh sach tu file");
            System.out.println("7. Ghi danh sach ra file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 ->
                    xuat();
                case 2 -> {
                    System.out.print("nhap ma san pham");
                    String ma = sc.nextLine();
                    while (true) {
                        if (kiemTraMaTrung(ma)) {
                            System.out.println("Ma chi tiet da ton tai:");
                        } else if (!mt.kiemTraMaTrung(ma)) {
                            System.out.println("Ma san pham khong ton tai trong danh sach may tinh:");
                        } else {
                            break;
                        }
                        ma = sc.nextLine();
                    }
                    mayTinh ds = mt.layMayTinh(ma);
                    String ten = ds.getTenSP();
                    chiTietSanPham ctsp = new chiTietSanPham();
                    ctsp.nhap();
                    them(ma, ten, ctsp);
                }
                case 3 ->
                    sua();
                case 4 ->
                    xoa();
                case 5 ->
                    thongKeTanSoQuet();
                case 6 -> {
                    System.out.print("Nhap ten file can doc: ");
                    String tenFile = sc.nextLine();
                    docFile(tenFile);
                }

                case 0 -> {
                    ghiFile("danhsachchitietsanpham.txt");
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
