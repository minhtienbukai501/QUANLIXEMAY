package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import hue.edu.vn.model.HangXe;

public class HangXeService extends SqlConnection {
	
	public boolean trungMa(String ma)
	{
		try {
			String sql = "select *From HangXe where MaHangXe = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setNString(1, ma);
			ResultSet rs = pre.executeQuery();
			if(rs.next())
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean xoaHangXe(String ma)
	{
		try {
			String sql = "delete HangXe where MaHangXe = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1,ma);
			int row = pre.executeUpdate();
			if(row  > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean themHangXe(HangXe hx)
	{
		try {
			String sql = "insert into HangXe values (? , ?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, hx.getMaHangXe());
			pre.setNString(2, hx.getTenHangXe());
			
			int row = pre.executeUpdate();
			if(row  > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public Vector<HangXe> layDuLieuHangXe()
	{
		Vector<HangXe> arr = new Vector<HangXe>();;
		try {
			String sql = "Select *from HangXe";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next())
			{
				HangXe x= new HangXe(rs.getNString(1), rs.getNString(2));
				arr.add(x);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}

}
