
import java.io.*;
import java.util.*;

public class danhSachNhanVien {

    private nhanVien[] ds;
    private int n;

    public danhSachNhanVien() {
        ds = new nhanVien[0];
        n = 0;
    }

    public nhanVien layNhanVienDauTien(String ma) {
        if (n == 0) {
            return null;
        }
        for (nhanVien nv : ds) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                return nv;
            }
        }
        return null;
    }

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 5) {
                    nhanVien nv = new nhanVien(p[0], p[1], p[2], p[3], Double.parseDouble(p[4]));
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n++] = nv;
                }
            }
            System.out.println("Doc file thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong the doc file!");
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < n; i++) {
                nhanVien nv = ds[i];
                pw.println(nv.getMaNV() + "," + nv.getHoNV() + "," + nv.getTenNV() + ","
                        + nv.getViTriNV() + "," + nv.getGioLam());
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
        System.out.printf("%-10s %-20s %-12s %-25s %-10s %-10s\n",
                "MaNV", "Ho", "Ten", "ViTri", "GioLam", "Luong");
        for (nhanVien nv : ds) {
            nv.xuat();
        }
    }

    public void them(String ma, nhanVien nv) {

        nv.nhap();
        ds = Arrays.copyOf(ds, n + 1);
        nv.setMaNV(ma);
        ds[n++] = nv;

    }

    public boolean kiemTraMaTrung(String ma) {
        for (nhanVien nv : ds) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    // ======= TIM KIEM =======
    public void timTheoMa(String ma) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;
        for (nhanVien nv : ds) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                System.out.printf("%-10s %-20s %-12s %-25s %-10s %-10s\n",
                        "MaNV", "Ho", "Ten", "ViTri", "GioLam", "Luong");
                nv.xuat();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nhan vien co ma: " + ma);
        }
    }

    public void timTheoTen(String ten) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;

        for (nhanVien nv : ds) {
            if (nv.getTenNV().toLowerCase().contains(ten)) {
                if (!found) {
                    System.out.printf("%-10s %-20s %-12s %-25s %-10s %-10s\n",
                            "MaNV", "Ho", "Ten", "ViTri", "GioLam", "Luong");
                }
                nv.xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nhan vien co ten: " + ten);
        }
    }

    // ======= SUA =======
    public void sua(String ma) {
        Scanner sc = new Scanner(System.in);
        nhanVien nvSua = null;

        for (nhanVien nv : ds) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                nvSua = nv;
                break;
            }
        }

        if (nvSua == null) {
            System.out.println("Khong tim thay nhan vien co ma: " + ma);
            return;
        }

        while (true) {
            System.out.println("\n--- SUA THONG TIN NHAN VIEN ---");
            System.out.println("1. Sua ho");
            System.out.println("2. Sua ten");
            System.out.println("3. Sua vi tri");
            System.out.println("4. Sua gio lam");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> {
                    System.out.print("Nhap ho moi: ");
                    nvSua.setHoNV(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nhap ten moi: ");
                    nvSua.setTenNV(sc.nextLine());
                }
                case 3 -> {
                    int choice;
                    System.out.print("Nhap vi tri moi: ");
                    System.out.println("1. quan ly");
                    System.out.println("2. ban hang");
                    System.out.println("3. bo phan kho");
                    System.out.println("4. bo phan ho tro");
                    System.out.println("5. ky thuat ");
                    System.out.print("chon->");
                    choice = sc.nextInt();
                    String[] a = {"quan li", "ban hang", "bo phan kho", "bo phan ho tro", "ky thuat"};
                    while (chon < 1 || chon > 5) {
                        System.out.println("vui long chon lai");
                        chon = sc.nextInt();
                    }
                    nvSua.setViTriNV(a[choice - 1]);
                    nvSua.setLuong(nvSua.tinhLuong());
                }
                case 4 -> {
                    System.out.print("Nhap gio lam moi: ");
                    nvSua.setGioLam(sc.nextDouble());
                    nvSua.setLuong(nvSua.tinhLuong());
                    sc.nextLine();
                }
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println("Chon sai!");
            }
            System.out.println("Da cap nhat thong tin!");
        }
    }

    // ======= XOA =======
    public void xoa(String ma) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNV().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                System.out.println("Da xoa nhan vien co ma: " + ma);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nhan vien can xoa!");
        }
    }

    // ======= THONG KE =======
    public void thongKeLuong() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        double tongLuong = 0;
        System.out.printf("%-10s %-20s %-12s %-25s %-10s %-10s\n",
                "MaNV", "Ho", "Ten", "ViTri", "GioLam", "Luong");
        for (nhanVien nv : ds) {
            nv.xuat();
            tongLuong += nv.getLuongNV();
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("Tong luong phai tra: %.2f\n", tongLuong);
    }

    // ======= MENU CHINH =======
}
