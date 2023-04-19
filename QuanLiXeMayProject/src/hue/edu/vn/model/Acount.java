package hue.edu.vn.model;

public class Acount {
	private String taiKhoan;
	private String matKhau;
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTaiKhoan();
	}
}
