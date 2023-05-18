package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import hue.edu.vn.model.PhanLoaiKhachHang;

public class PhanLoaiKhachHangService extends SqlConnection {
	
	public Vector<PhanLoaiKhachHang> getData()
	{
		Vector<PhanLoaiKhachHang> arr = new Vector<PhanLoaiKhachHang>();
		try {
			String sql = "select *from PhanLoaiKhachHang";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next())
			{
				PhanLoaiKhachHang data = new PhanLoaiKhachHang();
				data.setLoaiKhachHang(rs.getInt(1));
				data.setTenLoai(rs.getNString(2));
				arr.add(data);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arr;
	}
	
}
