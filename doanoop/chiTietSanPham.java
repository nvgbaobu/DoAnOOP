
import java.util.Scanner;

public class chiTietSanPham {

    private String maSP;
    private String tenSP;
    private String CPU;
    private String GPU;
    private String RAM;
    private String ROM;
    private String tanSoQuet;  // ← String, không cần parse
    Scanner sc = new Scanner(System.in);

    public chiTietSanPham() {
    }

    public chiTietSanPham(String maSP, String tenSP, String CPU, String GPU, String RAM, String ROM, String tanSoQuet) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        this.ROM = ROM;
        this.tanSoQuet = tanSoQuet;
    }

    // GETTERS
    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getROM() {
        return ROM;
    }

    public String getTanSoQuet() {
        return tanSoQuet;
    }  // ← Trả String

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public void setRom(String Rom) {
        this.ROM = Rom;
    }

    public void setRam(String Ram) {
        this.RAM = Ram;
    }

    public void setTanSoQuet(String TanSoQuet) {
        this.tanSoQuet = TanSoQuet;
    }

    public void nhap() {
        System.out.print("Nhap CPU: ");
        CPU = sc.nextLine();
        System.out.print("Nhap GPU: ");
        GPU = sc.nextLine();
        System.out.print("Nhap RAM: ");
        RAM = sc.nextLine();
        System.out.print("Nhap ROM: ");
        ROM = sc.nextLine();
        System.out.print("Nhap tan so quet (vd: 144Hz): ");
        tanSoQuet = sc.nextLine();  // ← Nhập String
    }

    public void xuat() {
        System.out.printf("%-12s %-25s %-15s %-15s %-10s %-10s %-10s\n",
                maSP, tenSP, CPU, GPU, RAM, ROM, tanSoQuet);
    }
}
