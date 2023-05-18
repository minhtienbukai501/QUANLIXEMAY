package hue.edu.vn.model;

import java.sql.Date;

public class NhanVien {
	private String MaNhanVien,TenNhanVien, GioiTinh, DiaChi,SoDienThoai;
	
	private Date NgaySinh, NgayVaoLam;
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return TenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSoDienThoai() {
		return SoDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public Date getNgayVaoLam() {
		return NgayVaoLam;
	}
	public void setNgayVaoLam(Date ngayVaoLam) {
		NgayVaoLam = ngayVaoLam;
	}
	
	@Override
	public String toString() {
		
		return this.TenNhanVien;
	}
	
	
}
