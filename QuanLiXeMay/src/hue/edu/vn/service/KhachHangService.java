package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import hue.edu.vn.model.KhachHang;

public class KhachHangService extends SqlConnection {


	
	public boolean suaKhachHang(KhachHang kh, int loaiKhachHang)
	{
		try {
			String sql = "update KhachHang set TenKhachHang = ? , DiaChi = ?, SoDienThoai = ?, Email = ?, LoaiKhachHang = ? "
					+ "where MaKhachHang = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, kh.getTenKhachHang());
			pre.setNString(2, kh.getDiaChi());
			pre.setNString(3, kh.getSoDienThoai());
			pre.setNString(4, kh.getEmail());
			pre.setInt(5, loaiKhachHang);
			pre.setNString(6, kh.getMaKhachHang());
			
			int row = pre.executeUpdate();
			if(row > 0)
				return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean xoaKhachHang(String Ma) {
		try {

			String sql = "delete KhachHang where MaKhachHang = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, Ma);
			int row = pre.executeUpdate();
			if (row > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkTrungMa(String ma) {
		try {

			String sql = "select *from KhachHang where MaKhachHang = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, ma);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean themKhachHang(KhachHang x, int loaiKhachHang) {
		try {
			String sql = "insert into KhachHang values(?, ? , ? , ? , ? , ?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, x.getMaKhachHang());
			pre.setNString(2, x.getTenKhachHang());
			pre.setNString(3, x.getDiaChi());
			pre.setNString(4, x.getSoDienThoai());
			pre.setNString(5, x.getEmail());
			pre.setInt(6, loaiKhachHang);

			int row = pre.executeUpdate();
			if (row > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public Vector<KhachHang> getData() {
		Vector<KhachHang> arr = new Vector<KhachHang>();

		try {
			String sql = "select MaKhachHang, TenKhachHang, DiaChi, SoDienThoai, Email, TenLoaiKhach\r\n"
					+ "from KhachHang join PhanLoaiKhachHang \r\n"
					+ "on KhachHang.LoaiKhachHang = PhanLoaiKhachHang.LoaiKhachHang";

			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				KhachHang data = new KhachHang();
				data.setMaKhachHang(rs.getNString(1));
				data.setTenKhachHang(rs.getNString(2));
				data.setDiaChi(rs.getNString(3));
				data.setSoDienThoai(rs.getNString(4));
				data.setEmail(rs.getNString(5));
				data.setTenLoaiKhach(rs.getNString(6));
				arr.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}
