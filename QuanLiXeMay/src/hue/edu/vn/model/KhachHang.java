package hue.edu.vn.model;

public class KhachHang {
	private String maKhachHang, tenKhachHang, diaChi, email,tenLoaiKhach, soDienThoai;

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTenLoaiKhach() {
		return tenLoaiKhach;
	}

	public void setTenLoaiKhach(String tenLoaiKhach) {
		this.tenLoaiKhach = tenLoaiKhach;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	
	@Override
	public String toString() {
		return this.tenKhachHang + " (" + this.maKhachHang + ")";
	}
}
