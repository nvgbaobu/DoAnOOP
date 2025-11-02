
abstract class QuanLyBanHang {

    public danhSachMayTinh dsMayTinh = new danhSachMayTinh();
    public danhSachChiTietSanPham dsCTSP = new danhSachChiTietSanPham(dsMayTinh);
    public danhSachNhanVien dsNhanVien = new danhSachNhanVien();
    public danhSachKhachHang dsKhachHang = new danhSachKhachHang();
    public danhSachNhaCungCap dsNhaCungCap = new danhSachNhaCungCap();
    public danhSachNhapHang dsNhapHang = new danhSachNhapHang(dsMayTinh);
    nhapHang nh = new nhapHang();
    public danhSachHoaDonNhapHang dsHoaDon = new danhSachHoaDonNhapHang(nh, dsNhapHang, dsNhaCungCap, dsMayTinh);

    // THÊM 2 ĐỐI TƯỢNG MỚI
    public danhSachHoaDon dsHoaDonBanHang = new danhSachHoaDon(dsMayTinh);
    public danhSachChiTietHoaDon dsChiTietHoaDon = new danhSachChiTietHoaDon(dsMayTinh, dsKhachHang, dsNhanVien);

    abstract void menu();

}
