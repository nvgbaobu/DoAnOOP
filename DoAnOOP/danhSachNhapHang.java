
import java.io.*;
import java.util.Arrays;

public class danhSachNhapHang {

    private nhapHang[] ds;
    private int n;
    danhSachMayTinh dssp;

    public danhSachNhapHang(danhSachMayTinh mt) {
        ds = new nhapHang[0];
        n = 0;
        docFile("danhsachphieunhap.txt");
        dssp = mt;
    }

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 8) {
                    nhapHang nh = new nhapHang(p[0], p[1], Integer.parseInt(p[2]),
                            p[3], p[4], p[5], p[6], p[7]);
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n++] = nh;
                }
            }
            System.out.println("Da doc " + n + " phieu nhap tu file.");
        } catch (FileNotFoundException e) {
            System.out.println("Chua co file phieu nhap (se tao moi).");
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (nhapHang nh : ds) {
                pw.println(nh.getMaSP() + "," + nh.getTenSP() + "," + nh.getSoLuong() + ","
                        + nh.getHoNguoiGiao() + "," + nh.getTenNguoiGiao() + ","
                        + nh.getHoNguoiNhan() + "," + nh.getTenNguoiNhan() + "," + nh.getNgayNhap());
            }
            System.out.println("Da ghi " + n + " phieu nhap vao file.");
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public nhapHang layPhieuCuoi() {
        return n == 0 ? null : ds[n - 1];
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.printf("%-10s %-20s %-10s %-15s %-10s %-15s %-10s %-12s\n",
                "MaSP", "TenSP", "SoLuong", "HoGiao", "TenGiao", "HoNhan", "TenNhan", "NgayNhap");
        for (nhapHang nh : ds) {
            nh.xuat();
        }
    }

    public void them(String maSP, mayTinh mt) {

        nhapHang nh = new nhapHang();
        nh.nhap(maSP, mt.getTenSP());
        mt.setSoLuong(mt.getSoLuong() + nh.getSoLuong());
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = nh;
        System.out.println("Da them phieu nhap va cap nhat so luong!");

    }

    public void xoa(String ma) {

        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                System.out.println("Da xoa phieu nhap san pham: " + ma);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay phieu nhap!");
        }
    }

    public void timTheoMa(String ma) {

        boolean found = false;
        for (nhapHang nh : ds) {
            if (nh.getMaSP().equalsIgnoreCase(ma)) {
                mayTinh mt = dssp.layMayTinh(ma);
                System.out.printf("%-10s %-20s %-10s %-15s %-10s %-15s %-10s %-12s\n",
                        "MaSP", "TenSP", "SoLuongNhap", "HoGiao", "TenGiao", "HoNhan", "TenNhan", "SoLuongHT");
                nh.xuat();
                if (mt != null) {
                    System.out.print("Thong tin hien tai: ");
                    mt.xuat();
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay ma: " + ma);
        }
    }

    public void timTheoTen(String ten) {

        boolean found = false;
        System.out.printf("%-10s %-20s %-10s %-15s %-10s %-15s %-10s %-12s\n",
                "MaSP", "TenSP", "SoLuongNhap", "HoGiao", "TenGiao", "HoNhan", "TenNhan", "SoLuongHT");
        for (nhapHang nh : ds) {
            if (nh.getTenSP().toLowerCase().contains(ten)) {
                mayTinh mt = dssp.layMayTinh(nh.getMaSP());
                nh.xuat();
                if (mt != null) {
                    System.out.print("Thong tin hien tai: ");
                    mt.xuat();
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay ten: " + ten);
        }
    }

    public void thongKeSoLuong() {
        System.out.println("\nTong so phieu nhap hien co: " + n);
        int count = 0;
        for (nhapHang nh : ds) {
            count += nh.getSoLuong();
        }
        System.out.print("so luong may tinh kho da nhan: " + count);
    }

}
