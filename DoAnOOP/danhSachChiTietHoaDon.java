
import java.io.*;
import java.util.*;

public class danhSachChiTietHoaDon {

    private chiTietHoaDon[] ds;
    private int n;
    private String fileName = "danhsachchitiethoadon.txt";
    danhSachMayTinh dsMT;
    danhSachKhachHang dsKH;
    danhSachNhanVien dsNV;

    // KHỞI TẠO – TỰ ĐỘNG ĐỌC FILE
    public danhSachChiTietHoaDon(danhSachMayTinh dsMayTinh, danhSachKhachHang dsKhachHang, danhSachNhanVien dsNhanVien) {
        ds = new chiTietHoaDon[0];
        n = 0;
        docFile(fileName);
        this.dsMT = dsMayTinh;
        this.dsKH = dsKhachHang;
        this.dsNV = dsNhanVien;
    }

    // ĐỌC FILE
    public void docFile(String tenFile) {
        File file = new File(tenFile);
        if (!file.exists()) {
            System.out.println("FILE KHONG TON TAI: " + tenFile);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            int lineCount = 0, successCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] p = line.split(",");
                if (p.length != 11) {
                    System.out.println("DONG SAI (dong " + lineCount + "): " + line);
                    continue;
                }

                chiTietHoaDon ct = new chiTietHoaDon(
                        p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(),
                        p[4].trim(), p[5].trim(), p[6].trim(),
                        Integer.parseInt(p[7].trim()), Double.parseDouble(p[8].trim()),
                        p[9].trim(), p[10].trim()
                );

                ds = Arrays.copyOf(ds, n + 1);
                ds[n++] = ct;
                successCount++;
            }

            System.out.println("DA DOC CHI TIET HOA DON THANH CONG!");
            System.out.println(" - So dong doc: " + lineCount);
            System.out.println(" - So chi tiet them: " + successCount);
            System.out.println(" - Tong chi tiet: " + n);

        } catch (Exception e) {
            System.out.println("LOI DOC FILE: " + e.getMessage());
        }
    }

    // GHI FILE
    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < n; i++) {
                chiTietHoaDon ct = ds[i];
                pw.println(ct.getMaHD() + "," + ct.getMaSP() + "," + ct.getMaKH() + ","
                        + ct.getMaNV() + "," + ct.getTenSP() + "," + ct.getTenKH() + ","
                        + ct.getTenNV() + "," + ct.getSoLuong() + "," + ct.getDonGia() + ","
                        + ct.getThanhTien() + "," + ct.getSdt() + "," + ct.getNgayXuat());
            }
            System.out.println("DA GHI " + n + " CHI TIET VAO FILE: " + tenFile);
        } catch (Exception e) {
            System.out.println("LOI GHI FILE: " + e.getMessage());
        }
    }

    // THÊM CHI TIẾT HÓA ĐƠN (TỰ ĐỘNG LẤY THÔNG TIN)
    public void them(
            String maHD, String ngayXuat, khachHang kh, nhanVien nv, mayTinh sp, int sl
    ) {

        double donGia = sp.getGiaTien();
        double thanhTien = sl * donGia;

        chiTietHoaDon ct = new chiTietHoaDon(
                maHD, sp.getMaSP(), kh.getMaKH(), nv.getMaNV(),
                sp.getTenSP(), kh.getTenKH(), nv.getTenNV(),
                sl, donGia, kh.getSdtKH(), ngayXuat
        );

        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = ct;

        // CẬP NHẬT TỒN KHO
        sp.setSoLuong(sp.getSoLuong() - sl);
        dsMT.ghiFile("danhsachmaytinh.txt");

        System.out.println("DA THEM CHI TIET CHO HOA DON: " + maHD);
    }

    // XUẤT DANH SÁCH
    public void xuat() {
        if (n == 0) {
            System.out.println("DANH SACH CHI TIET TRONG!");
            return;
        }
        chiTietHoaDon.xuatTieuDe();
        for (int i = 0; i < n; i++) {
            ds[i].xuat();
        }
    }

    // TÌM THEO MÃ HÓA ĐƠN
    public void timKiemTheoMaHD(String maHD) {
        if (n == 0) {
            System.out.println("DANH SACH TRONG!");
            return;
        }

        boolean found = false;
        chiTietHoaDon.xuatTieuDe();
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

    public boolean kiemTraMaTrung(String maHD) {
        for (chiTietHoaDon hd : ds) {
            if (hd.getMaHD().equalsIgnoreCase(maHD)) {
                return true;
            }
        }
        return false;
    }

    // TỔNG TIỀN THEO HÓA ĐƠN
    public double tongTienTheoMaHD(String maHD) {
        double tong = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaHD().equalsIgnoreCase(maHD)) {
                tong += ds[i].getThanhTien();
            }
        }
        return tong;
    }

    // MENU
}
