
import java.io.*;
import java.util.*;

public class danhSachNhaCungCap {

    private nhaCungCap[] ds;
    private int n;

    public danhSachNhaCungCap() {
        ds = new nhaCungCap[0];
        n = 0;
    }
    // THEM HAM NAY VAO TRONG CLASS danhSachNhaCungCap

    public nhaCungCap timKiemTheoTen(String ten) {
        for (nhaCungCap ncc : ds) {
            if (ncc.getTenNCC().toLowerCase().contains(ten.toLowerCase())
                    || ncc.getMaNCC().equalsIgnoreCase(ten)) {
                return ncc;
            }
        }
        return null;
    }

    // ===== DOC & GHI FILE =====
    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 5) {
                    nhaCungCap ncc = new nhaCungCap(p[0], p[1], p[2], p[3], p[4]);
                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n++] = ncc;
                }
            }
            System.out.println("Doc file thanh cong!");
        } catch (IOException e) {
            System.out.println("Khong the doc file!");
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (nhaCungCap ncc : ds) {
                pw.println(ncc.getMaNCC() + "," + ncc.getTenNCC() + "," + ncc.getDiaChi() + ","
                        + ncc.getHotline() + "," + ncc.getEmail());
            }
            System.out.println("Da ghi file!");
        } catch (IOException e) {
            System.out.println("Loi ghi file!");
        }
    }

    // ===== XUAT =====
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.printf("%-10s %-25s %-25s %-15s %-25s\n",
                "MaNCC", "TenNhaCungCap", "DiaChi", "Hotline", "Email");
        for (nhaCungCap ncc : ds) {
            ncc.xuat();
        }
    }

    // ===== THEM =====
    public void them(String ma, nhaCungCap ncc) {
        ncc.setMaNCC(ma);
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = ncc;

    }

    public boolean kiemTraMaTrung(String ma) {
        for (nhaCungCap ncc : ds) {
            if (ncc.getMaNCC().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    // ===== TIM KIEM =====
    public void timTheoMa(String ma) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;
        for (nhaCungCap ncc : ds) {
            if (ncc.getMaNCC().equalsIgnoreCase(ma)) {
                if (!found) {
                    System.out.printf("%-10s %-25s %-25s %-15s %-25s\n",
                            "MaNCC", "TenNhaCungCap", "DiaChi", "Hotline", "Email");
                }
                ncc.xuat();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay ma: " + ma);
        }
    }

    public void timTheoTen(String ten) {
        Scanner sc = new Scanner(System.in);

        boolean found = false;
        System.out.printf("%-10s %-25s %-25s %-15s %-25s\n",
                "MaNCC", "TenNhaCungCap", "DiaChi", "Hotline", "Email");
        for (nhaCungCap ncc : ds) {
            if (ncc.getTenNCC().toLowerCase().contains(ten)) {
                ncc.xuat();
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
        for (nhaCungCap ncc : ds) {
            if (ncc.getMaNCC().equalsIgnoreCase(ma)) {
                found = true;
                while (true) {
                    System.out.println("\n--- SUA THONG TIN NHA CUNG CAP ---");
                    System.out.println("1. Sua ten");
                    System.out.println("2. Sua dia chi");
                    System.out.println("3. Sua hotline");
                    System.out.println("4. Sua email");
                    System.out.println("0. Quay lai");
                    System.out.print("Chon: ");
                    int chon = sc.nextInt();
                    sc.nextLine();

                    switch (chon) {
                        case 1 -> {
                            System.out.print("Nhap ten moi: ");
                            ncc.setTenNhaCungCap(sc.nextLine());
                        }
                        case 2 -> {
                            System.out.print("Nhap dia chi moi: ");
                            ncc.setDiaChi(sc.nextLine());
                        }
                        case 3 -> {
                            System.out.print("Nhap hotline moi: ");
                            ncc.setHotline(sc.nextLine());
                        }
                        case 4 -> {
                            System.out.print("Nhap email moi: ");
                            ncc.setEmail(sc.nextLine());
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

        }
        if (!found) {
            System.out.print("ma nha cung cap khong ton tai.");
        }

    }

    // ===== XOA =====
    public void xoa(String ma) {

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                System.out.println("Da xoa nha cung cap co ma: " + ma);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nha cung cap!");
        }
    }

    // ===== THONG KE =====
    public void thongKeSoLuong() {
        System.out.println("\nTong so nha cung cap hien co: " + n);
    }

}
