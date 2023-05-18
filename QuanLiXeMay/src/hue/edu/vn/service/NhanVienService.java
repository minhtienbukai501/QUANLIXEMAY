package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import hue.edu.vn.model.NhanVien;

public class NhanVienService extends SqlConnection {
	
	public boolean kiemTraTrungMa(String Ma)
	{
		
		try {
			String sql = "select *from NhanVien where MaNhanVien = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, Ma);	
			ResultSet rx = pre.executeQuery();
			if(rx.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean xoaNhanVien(String Ma)
	{
		try {
			String sql = "Delete NhanVien where MaNhanVien = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, Ma);	
			int row = pre.executeUpdate();
			
			if(row > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean themNhanVien(NhanVien nv)
	{
		try {
			String sql = "insert into NhanVien values(?, ?, ?, ?, ? , ? , ?)";
			PreparedStatement pre =conn.prepareStatement(sql);
			pre.setNString(1, nv.getMaNhanVien());
			pre.setNString(2, nv.getTenNhanVien());
			pre.setDate(3, nv.getNgaySinh());
			pre.setNString(4, nv.getGioiTinh());
			pre.setDate(5, nv.getNgayVaoLam());
			pre.setNString(6, nv.getDiaChi());
			pre.setNString(7, nv.getSoDienThoai());
			
			int row = pre.executeUpdate();
			if(row > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Vector<NhanVien> getData()
	{
		Vector<NhanVien> arr = new  Vector<NhanVien>();
		try {
			String sql = "select *From NhanVien";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			
			while(rs.next())
			{
				NhanVien x = new NhanVien();
				x.setMaNhanVien(rs.getNString(1) + "");
				x.setTenNhanVien(rs.getNString(2));
				x.setNgaySinh(rs.getDate(3));
				x.setGioiTinh(rs.getNString(4));
				x.setNgayVaoLam(rs.getDate(5));
				x.setDiaChi(rs.getNString(6));
				x.setSoDienThoai(rs.getNString(7));
				arr.add(x);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}
}
