package hue.edu.vn.model;

public class HangXe {
	private String maHangXe, tenHangXe;

	public String getMaHangXe() {
		return maHangXe;
	}

	public void setMaHangXe(String maHangXe) {
		this.maHangXe = maHangXe;
	}

	public String getTenHangXe() {
		return tenHangXe;
	}

	public void setTenHangXe(String tenHangXe) {
		this.tenHangXe = tenHangXe;
	}

	public HangXe(String maHangXe, String tenHangXe) {
		super();
		this.maHangXe = maHangXe;
		this.tenHangXe = tenHangXe;
	}

	public HangXe() {
		super();
	}
	
	@Override
	public String toString() {
		return this.tenHangXe;
	}
	
	
}
