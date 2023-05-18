package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import hue.edu.vn.model.Xe;

public class XeService extends SqlConnection {
	
	
	public Xe layXe(String Ma)
	{
		Xe x = null;
		
		try {
			String sql = "Select MaXe, TenXe, TenHangXe, NamSanXuat, TrongLuong, SoLuongConLai, GiaBan "
					+ "from Xe JOIN HangXe on Xe.MaHangXe = HangXe.MaHangXe where MaXe =? ";
			
			PreparedStatement preStatattement = conn.prepareStatement(sql);
			preStatattement.setNString(1, Ma);
			ResultSet result = preStatattement.executeQuery();
		
			if(result.next())
			{
				x = new Xe();
				x.setMaXe(result.getNString(1));
				x.setTenXe(result.getNString(2));
				x.setTenHangXe(result.getNString(3));
				x.setNamSanXuat(result.getInt(4));
				x.setTrongLuong(result.getInt(5));
				x.setSoLuongConLai(result.getInt(6));
				x.setGiaBan(result.getInt(7));
			}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}
	
	public Vector<Xe>layDuLieuXe(String MaHangXe)
	{
		Vector<Xe> arr = new Vector<Xe>();
		try {
			String sql = "Select MaXe, TenXe, TenHangXe, NamSanXuat, TrongLuong, SoLuongConLai, GiaBan "
					+ "from Xe JOIN HangXe on Xe.MaHangXe = HangXe.MaHangXe where HangXe.MaHangXe = ? order by MaXe ";
			
			PreparedStatement preStatattement = conn.prepareStatement(sql);
			preStatattement.setNString(1, MaHangXe);
			ResultSet result = preStatattement.executeQuery();
		
			while(result.next())
			{
				Xe x = new Xe();
				x.setMaXe(result.getNString(1));
				x.setTenXe(result.getNString(2));
				x.setTenHangXe(result.getNString(3));
				x.setNamSanXuat(result.getInt(4));
				x.setTrongLuong(result.getInt(5));
				x.setSoLuongConLai(result.getInt(6));
				x.setGiaBan(result.getInt(7));
				
				arr.add(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	
	
	public boolean themXe(String MaXe, String MaHangXe,String TenXe, int NamSanXuat, int TrongLuong, int SoLuong, int GiaBan)
	{
		try {
			String sql = "insert into Xe values (?, ?, ?, ?, ?, ? , ?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, MaXe);
			pre.setString(2, MaHangXe);
			pre.setNString(3, TenXe);
			pre.setInt(4, NamSanXuat);
			
			pre.setInt(5, TrongLuong);
			
			pre.setInt(6, SoLuong);
			pre.setInt(7, GiaBan);
			
			int row  = pre.executeUpdate();
			if(row > 0)
				return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean checkTrungMa(String Ma)
	{
		try {
			String sql = "select *from Xe where MaXe = ?";
			PreparedStatement prestatement = conn.prepareStatement(sql);
			prestatement.setNString(1, Ma);
			ResultSet rs = prestatement.executeQuery();
			if(rs.next())
			{
				return true;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean xoaXe(String MaXe)
	{
		
		try {
			String sql = "delete Xe where MaXe = ?";

			PreparedStatement pre =conn.prepareStatement(sql);
			pre.setNString(1, MaXe);
			int row = pre.executeUpdate();
			if(row > 0)
				return true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<Xe> timKiemTheoTenXe(String Ten)
	{
		Vector<Xe> arr = new Vector<Xe>();
		try {
			String sql = "Select MaXe, TenXe, TenHangXe, NamSanXuat, TrongLuong, SoLuongConLai, GiaBan \r\n"
					+ "					from Xe JOIN HangXe on Xe.MaHangXe = HangXe.MaHangXe\r\n"
					+ "					Where TenXe like N'%"+Ten+"%'\r\n"
					+ "					order by MaXe";
			
			PreparedStatement preStatattement = conn.prepareStatement(sql);
			ResultSet result = preStatattement.executeQuery();
		
			while(result.next())
			{
				Xe x = new Xe();
				x.setMaXe(result.getNString(1));
				x.setTenXe(result.getNString(2));
				x.setTenHangXe(result.getNString(3));
				x.setNamSanXuat(result.getInt(4));
				x.setTrongLuong(result.getInt(5));
				x.setSoLuongConLai(result.getInt(6));
				x.setGiaBan(result.getInt(7));
				
				arr.add(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
	public Vector<Xe>layDuLieuXe()
	{
		Vector<Xe> arr = new Vector<Xe>();
		try {
			String sql = "Select MaXe, TenXe, TenHangXe, NamSanXuat, TrongLuong, SoLuongConLai, GiaBan "
					+ "from Xe JOIN HangXe on Xe.MaHangXe = HangXe.MaHangXe order by MaXe";
			
			PreparedStatement preStatattement = conn.prepareStatement(sql);
			ResultSet result = preStatattement.executeQuery();
		
			while(result.next())
			{
				Xe x = new Xe();
				x.setMaXe(result.getNString(1));
				x.setTenXe(result.getNString(2));
				x.setTenHangXe(result.getNString(3));
				x.setNamSanXuat(result.getInt(4));
				x.setTrongLuong(result.getInt(5));
				x.setSoLuongConLai(result.getInt(6));
				x.setGiaBan(result.getInt(7));
				
				arr.add(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
}
