package hue.edu.vn.model;

public class Account {
	private String taiKhoan,matKhau;

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

	public Account() {
		super();
	}
	
	@Override
	public String toString() {
		
		return this.taiKhoan;
	}
}
