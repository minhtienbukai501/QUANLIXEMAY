package hue.edu.vn.model;

import java.util.Date;

public class HoaDon {
	private String SoHD,MaKH, MaNV;
	private int TongTien;
	private String GhiChu;
	private Date NgayLapDon;
	
	
	
	
	
	public String getSoHD() {
		return SoHD;
	}
	public void setSoHD(String soHD) {
		SoHD = soHD;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public int getTongTien() {
		return TongTien;
	}
	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	public Date getNgayLapDon() {
		return NgayLapDon;
	}
	public void setNgayLapDon(Date ngayLapDon) {
		NgayLapDon = ngayLapDon;
	}
	
	
}
