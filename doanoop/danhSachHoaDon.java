
import java.io.*;
import java.util.*;

public class danhSachHoaDon {

    private hoaDon[] ds;
    private int n;
    private String fileName = "danhsachhoadon.txt";
    danhSachMayTinh dsMT;

    // KHỞI TẠO – TỰ ĐỘNG ĐỌC FILE
    public danhSachHoaDon(danhSachMayTinh dsmt) {
        ds = new hoaDon[0];
        n = 0;

        this.dsMT = dsmt;

    }

    // ĐỌC FILE
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

                String[] p = line.split(",");
                if (p.length != 6) {
                    System.out.println("DONG SAI DINH DANG (dong " + lineCount + "): " + line);
                    continue;
                }

                hoaDon hd = new hoaDon(
                        p[0].trim(), // maHD
                        p[1].trim(), // maSP
                        Integer.parseInt(p[2].trim()), // soLuong
                        Double.parseDouble(p[3].trim()) // donGia
                );
                // thanhTien và ngayXuat tự động tính trong constructor

                ds = Arrays.copyOf(ds, n + 1);
                ds[n++] = hd;
                successCount++;
            }

            System.out.println("DA DOC FILE HOA DON THANH CONG!");
            System.out.println(" - So dong doc: " + lineCount);
            System.out.println(" - So hoa don them: " + successCount);
            System.out.println(" - Tong hoa don hien tai: " + n);

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
                hoaDon hd = ds[i];
                pw.println(hd.getMaHD() + "," + hd.getMaSP() + "," + hd.getSoLuong() + ","
                        + hd.getDonGia() + "," + hd.getThanhTien() + "," + hd.getNgayXuat());
            }
            System.out.println("Da ghi " + n + " hoa don vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // THÊM HÓA ĐƠN MỚI (TỰ ĐỘNG TỪ DANH SÁCH SẢN PHẨM)
    public void them(String maSP, int soLuong, mayTinh sp) {

        double donGia = sp.getGiaTien();

        // TẠO MÃ HÓA ĐƠN TỰ ĐỘNG
        String maHD = "HD" + String.format("%03d", n + 1);

        hoaDon hd = new hoaDon(maHD, maSP, soLuong, donGia);
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = hd;

        // CẬP NHẬT TỒN KHO
        sp.setSoLuong(sp.getSoLuong() - soLuong);
        dsMT.ghiFile("danhsachmaytinh.txt");

        System.out.println("DA THEM HOA DON: " + maHD);
        System.out.println("Thanh tien: " + hd.getThanhTien() + " VND");

    }

    // XUẤT DANH SÁCH
    public void xuat() {
        if (n == 0) {
            System.out.println("DANH SACH HOA DON TRONG!");
            return;
        }
        hoaDon.xuatTieuDe();
        for (int i = 0; i < n; i++) {
            ds[i].xuat();
        }
    }

    // TÌM KIẾM THEO MÃ HÓA ĐƠN
    public void timKiemTheoMaHD(String maHD) {
        if (n == 0) {
            System.out.println("DANH SACH TRONG!");
            return;
        }

        boolean found = false;
        hoaDon.xuatTieuDe();
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                ds[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("KHONG TIM THAY HOA DON: " + maHD);
        }
    }

    // TÌM KIẾM THEO MÃ SẢN PHẨM
    public void timKiemTheoMaSP(String maSP) {
        if (n == 0) {
            System.out.println("DANH SACH TRONG!");
            return;
        }

        boolean found = false;
        hoaDon.xuatTieuDe();
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(maSP)) {
                ds[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("KHONG TIM THAY SAN PHAM: " + maSP);
        }
    }

    // XÓA HÓA ĐƠN
    public void xoa() {
        if (n == 0) {
            System.out.println("DANH SACH TRONG!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can xoa: ");
        String maHD = sc.nextLine().trim();

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                System.out.println("Tim thay:");
                ds[i].xuat();
                System.out.print("Xac nhan xoa (Y/N)? ");
                if (sc.nextLine().equalsIgnoreCase("Y")) {
                    for (int j = i; j < n - 1; j++) {
                        ds[j] = ds[j + 1];
                    }
                    n--;
                    ds = Arrays.copyOf(ds, n);
                    System.out.println("DA XOA HOA DON: " + maHD);
                } else {
                    System.out.println("HUY XOA!");
                }
                return;
            }
        }
        System.out.println("KHONG TIM THAY HOA DON: " + maHD);
    }

    // THỐNG KÊ TỔNG DOANH THU
    public void thongKeDoanhThu() {
        double tong = 0;
        for (int i = 0; i < n; i++) {
            tong += ds[i].getThanhTien();
        }
        System.out.println("\n=== THONG KE DOANH THU ===");
        System.out.println("Tong so hoa don: " + n);
        System.out.println("Tong doanh thu: " + String.format("%,.0f", tong) + " VND");
    }

    // MENU CHÍNH
}
