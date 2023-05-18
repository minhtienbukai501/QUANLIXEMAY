package hue.edu.vn.model;

public class PhanLoaiKhachHang {
	private int loaiKhachHang;
	private String tenLoai;
	public int getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(int loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	
	
	@Override
	public String toString() {
		return this.tenLoai;
	}
}
