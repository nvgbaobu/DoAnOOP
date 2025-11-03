
abstract class QuanLyBanHang {
    //chỉ demo chay thu thoi có gì thay public thành private
//public danhSachMayTinh getDsMayTinh() {
//return dsMayTinh;
//}
//qlbh.getDsMayTinh().xuat();
//thay the cho tất cả các hàm 

    public danhSachMayTinh dsMayTinh = new danhSachMayTinh();
    public danhSachChiTietSanPham dsCTSP = new danhSachChiTietSanPham(dsMayTinh);
    public danhSachNhanVien dsNhanVien = new danhSachNhanVien();
    public danhSachKhachHang dsKhachHang = new danhSachKhachHang();
    public danhSachNhaCungCap dsNhaCungCap = new danhSachNhaCungCap();
    public danhSachNhapHang dsNhapHang = new danhSachNhapHang(dsMayTinh);
    nhapHang nh = new nhapHang();
    public danhSachHoaDonNhapHang dsHoaDon = new danhSachHoaDonNhapHang(nh, dsNhapHang, dsNhaCungCap, dsMayTinh);
    public danhSachHoaDon dsHoaDonBanHang = new danhSachHoaDon(dsMayTinh);
    public danhSachChiTietHoaDon dsChiTietHoaDon = new danhSachChiTietHoaDon(dsMayTinh, dsKhachHang, dsNhanVien);

    abstract void menu();

}
