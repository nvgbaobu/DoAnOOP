
import java.io.*;
import java.util.*;

public class danhSachKhachHang {

    private khachHang[] ds;
    private int n;

    public danhSachKhachHang() {
        ds = new khachHang[0];
        n = 0;
    }

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 6) {
                    khachHang kh = new khachHang(p[0], p[1], p[2], p[3], p[4], p[5]);
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n++] = kh;
                }
            }
            System.out.println("Doc file thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong the doc file!");
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (khachHang kh : ds) {
                pw.println(kh.getMaKH() + "," + kh.getHoKH() + "," + kh.getTenKH() + ","
                        + kh.getGioiTinh() + "," + kh.getSdtKH() + "," + kh.getDiaChiKH());
            }
            System.out.println("Da ghi file!");
        } catch (IOException e) {
            System.out.println("Loi ghi file!");
        }
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n",
                "MaKH", "Ho", "Ten", "GioiTinh", "SDT", "DiaChi");
        for (khachHang kh : ds) {
            kh.xuat();
        }
    }

    public void them(khachHang kh, String ma) {

        kh.setMaKH(ma);
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = kh;
    }

    public boolean kiemTraMaTrung(String ma) {
        for (khachHang kh : ds) {
            if (kh.getMaKH().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    public khachHang timKiemTheoMa(String ma) {
        for (khachHang kh : ds) {
            if (kh.getMaKH().equalsIgnoreCase(ma)) {
                return kh;
            }

        }
        return null;
    }

    public void timTheoMa(String ma) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;
        for (khachHang kh : ds) {
            if (kh.getMaKH().equalsIgnoreCase(ma)) {
                System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n",
                        "MaKH", "Ho", "Ten", "GioiTinh", "SDT", "DiaChi");
                kh.xuat();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay ma " + ma);
        }
    }

    public void timTheoTen(String ten) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;

        for (khachHang kh : ds) {
            if (kh.getTenKH().equalsIgnoreCase(ten)) {
                if (!found) {
                    System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n",
                            "MaKH", "Ho", "Ten", "GioiTinh", "SDT", "DiaChi");
                }
                kh.xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay ten: " + ten);
        }
    }

    // ===== SUA =====
    public void sua(String ma) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;
        int chon;
        for (khachHang kh : ds) {
            if (kh.getMaKH().equalsIgnoreCase(ma)) {
                found = true;
                do {
                    System.out.println("\n--- SUA THONG TIN KHACH HANG ---");
                    System.out.println("1. Sua ho");
                    System.out.println("2. Sua ten");
                    System.out.println("3. Sua gioi tinh");
                    System.out.println("4. Sua SDT");
                    System.out.println("5. Sua dia chi");
                    System.out.println("0. Quay lai");
                    System.out.print("Chon: ");
                    chon = sc.nextInt();
                    sc.nextLine();

                    switch (chon) {
                        case 1 -> {
                            System.out.print("Nhap ho moi: ");
                            kh.setHoKH(sc.nextLine());
                        }
                        case 2 -> {
                            System.out.print("Nhap ten moi: ");
                            kh.setTenKH(sc.nextLine());
                        }
                        case 3 -> {
                            System.out.print("Nhap gioi tinh moi: ");
                            kh.setGioiTinh(sc.nextLine());
                        }
                        case 4 -> {
                            System.out.print("Nhap SDT moi: ");
                            kh.setSdtKH(sc.nextLine());
                        }
                        case 5 -> {
                            System.out.print("Nhap dia chi moi: ");
                            kh.setDiaChiKH(sc.nextLine());
                        }
                        case 0 -> {
                            return;
                        }
                        default ->
                            System.out.println("Chon sai!");
                    }
                    System.out.println("Da cap nhat thong tin!");
                } while (chon != 0);
            }
        }
        if (!found) {
            System.out.println("ma khach hang khong ton tai " + ma);
        }
    }

    // ===== XOA =====
    public void xoa(String ma) {

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaKH().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                System.out.println("Da xoa khach hang co ma: " + ma);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    // ===== THONG KE =====
    public void thongKeGioiTinh() {
        int nam = 0, nu = 0;
        for (khachHang kh : ds) {
            if (kh.getGioiTinh().equalsIgnoreCase("nam")) {
                nam++;
            } else if (kh.getGioiTinh().equalsIgnoreCase("nu")) {
                nu++;
            }
        }
        System.out.println("\n=== THONG KE GIOI TINH ===");
        System.out.println("So khach hang nam: " + nam);
        System.out.println("So khach hang nu: " + nu);
    }

}
