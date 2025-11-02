
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class danhSachMayTinh {

    private mayTinh[] ds;
    private danhSachChiTietSanPham dsCTSP;
    private int n;

    public danhSachMayTinh() {
        ds = new mayTinh[0];
        n = 0;
        docFile("danhsachmaytinh.txt");
        dsCTSP = new danhSachChiTietSanPham();

    }

    public void docFile(String tenFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                mayTinh mt = null;
                if (parts.length == 8) {
                    mt = new brandNew(parts[0], parts[1], parts[2], parts[3],
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]),
                            Double.parseDouble(parts[6]), Integer.parseInt(parts[7]));
                } else if (parts.length == 7) {
                    mt = new secondHand(parts[0], parts[1], parts[2], parts[3],
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6]);
                } else {
                    System.out.println("Dinh dang dong sai: " + line);
                    continue;
                }
                ds = Arrays.copyOf(ds, n + 1);
                ds[n++] = mt;
            }
            System.out.println("Da doc file may tinh!");
        } catch (Exception e) {
            System.out.println("Chua co file may tinh hoac loi doc!");
        }
    }

    public void ghiFile(String tenFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < n; i++) {
                mayTinh mt = ds[i];
                if (mt instanceof brandNew bn) {
                    pw.println(bn.getMaSP() + "," + bn.getTenSP() + "," + bn.getThuongHieu() + ","
                            + bn.getPhanLoai() + "," + bn.getGiaTien() + "," + bn.getSoLuong() + ","
                            + bn.getUuDai() + "," + bn.getBaoHanh());
                } else if (mt instanceof secondHand sh) {
                    pw.println(sh.getMaSP() + "," + sh.getTenSP() + "," + sh.getThuongHieu() + ","
                            + sh.getPhanLoai() + "," + sh.getGiaTien() + "," + sh.getSoLuong() + ","
                            + sh.getTinhTrang());
                }
            }
            System.out.println("Da ghi file may tinh!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public mayTinh layMayTinh(String maSP) {
        for (mayTinh mt : ds) {
            if (mt.getMaSP().equalsIgnoreCase(maSP)) {
                return mt;
            }
        }
        return null;
    }

    public String layTenSP(String maSP) {
        for (mayTinh mt : ds) {
            if (mt.getMaSP().equalsIgnoreCase(maSP)) {
                return mt.getTenSP();
            }
        }
        return null;
    }

    public void them(mayTinh mt) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = mt;

    }

    public boolean kiemTraMaTrung(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.printf("%-12s %-25s %-15s %-15s %-10s %-8s %-10s %-10s %-12s\n",
                "MaSP", "TenSP", "ThuongHieu", "PhanLoai", "GiaTien", "SoLuong", "UuDai", "BaoHanh", "TinhTrang");
        for (int i = 0; i < n; i++) {
            mayTinh mt = ds[i];
            if (mt instanceof brandNew bn) {
                System.out.printf("%-12s %-25s %-15s %-15s %-10.2f %-8d %-10.2f %-10d %-12s\n",
                        bn.getMaSP(), bn.getTenSP(), bn.getThuongHieu(), bn.getPhanLoai(),
                        bn.getGiaTien(), bn.getSoLuong(), bn.getUuDai(), bn.getBaoHanh(), "-");
            } else if (mt instanceof secondHand sh) {
                System.out.printf("%-12s %-25s %-15s %-15s %-10.2f %-8d %-10s %-10s %-12s\n",
                        sh.getMaSP(), sh.getTenSP(), sh.getThuongHieu(), sh.getPhanLoai(),
                        sh.getGiaTien(), sh.getSoLuong(), "-", "-", sh.getTinhTrang());
            }
        }
    }

    public void timKiemTheoMa(String ma) {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                if (!found) {
                    xuatHeader();
                }
                ds[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay!");
        }
    }

    public void timKiemTheoTen(String ten) {
        if (n == 0) {
            System.out.println("danh sach rong");
            return;
        }
        boolean found = false;
        for (mayTinh mt : ds) {
            if (mt.getTenSP().contentEquals(ten)) {
                if (!found) {
                    xuatHeader();
                }
                mt.xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("khong tim thay san pham");
        }
    }

    private void xuatHeader() {
        System.out.printf("%-14s %-30s %-18s %-18s %-12s %-10s %-12s %-12s %-14s\n",
                "MaSP", "TenSP", "ThuongHieu", "PhanLoai", "GiaTien", "SoLuong", "UuDai", "BaoHanh", "TinhTrang");
    }

    public void sua(String ma) {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                mayTinh mt = ds[i];
                System.out.println("Tim thay:");
                mt.xuat();
                System.out.println("\nChon thong tin can sua:");
                System.out.println("1. Ten SP\n2. Thuong hieu\n3. Phan loai\n4. Gia goc\n5. So luong");
                if (mt instanceof brandNew) {
                    System.out.println("6. Uu dai (%)\n7. Bao hanh (thang)");
                } else if (mt instanceof secondHand) {
                    System.out.println("6. Tinh trang");
                }
                System.out.print("Chon: ");
                int chon = sc.nextInt();
                sc.nextLine();

                switch (chon) {
                    case 1 ->
                        mt.setTenSP(sc.nextLine());
                    case 2 ->
                        mt.setThuongHieu(sc.nextLine());
                    case 3 ->
                        mt.setPhanLoai(sc.nextLine());
                    case 4 -> {
                        mt.setGiaTien(sc.nextDouble());
                        sc.nextLine();
                    }
                    case 5 -> {
                        mt.setSoLuong(sc.nextInt());
                        sc.nextLine();
                    }
                    case 6 -> {
                        if (mt instanceof brandNew bn) {
                            bn.setUuDai(sc.nextDouble());
                            sc.nextLine();
                        } else if (mt instanceof secondHand sh) {
                            sh.setTinhTrang(sc.nextLine());
                        }
                    }
                    case 7 -> {
                        if (mt instanceof brandNew bn) {
                            bn.setBaoHanh(sc.nextInt());
                            sc.nextLine();
                        }
                    }
                    default ->
                        System.out.println("Chon sai!");
                }
                System.out.println("Da sua xong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma nay!");
    }

    public void xoa(String ma) {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaSP().equalsIgnoreCase(ma)) {
                System.out.println("Tim thay:");
                ds[i].xuat();
                System.out.print("Xoa may nay (Y/N)? ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
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

    public void thongKeSoLuong() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.printf("%-14s %-30s %-10s\n", "MaSP", "TenSP", "SoLuong");
        int tong = 0;
        for (int i = 0; i < n; i++) {
            mayTinh mt = ds[i];
            System.out.printf("%-14s %-30s %-10d\n", mt.getMaSP(), mt.getTenSP(), mt.getSoLuong());
            tong += mt.getSoLuong();
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Tong so luong: " + tong);
    }

    public void thongKeGiaTri() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.printf("%-14s %-30s %-12s %-12s\n", "MaSP", "TenSP", "GiaTien", "TongTienSP");
        double tong = 0;
        double giaTri = 0;
        for (int i = 0; i < n; i++) {
            mayTinh mt = ds[i];
            if (mt instanceof brandNew br) {
                giaTri = mt.getGiaTien() * mt.getSoLuong() * (1 - br.getUuDai() / 100.0);

            } else {
                giaTri = mt.getGiaTien() * mt.getSoLuong();
            }
            System.out.printf("%-14s %-30s %-12.2f %-12.2f\n", mt.getMaSP(), mt.getTenSP(), mt.getGiaTien(), giaTri);
            tong += giaTri;
        }
        System.out.println("-------------------------------------------------");
        System.out.printf("Tong gia tri: %.2f\n", tong);
    }

}
