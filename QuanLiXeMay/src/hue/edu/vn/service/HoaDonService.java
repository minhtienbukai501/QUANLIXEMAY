package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import hue.edu.vn.model.HoaDon;


public class HoaDonService  extends SqlConnection{

	public boolean isTrungHD(String m)
	{
		try {
			String sql = "Select *From HoaDon where SoHoaDon = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, m);
			ResultSet rs = pre.executeQuery();
			if(rs.next())
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean themHD(HoaDon hd)
	{
		try {
			String sql = "INSERT INTO [dbo].[HoaDon]\r\n"
					+ "           ([SoHoaDon]\r\n"
					+ "           ,[NgayLapHoaDon]\r\n"
					+ "           ,[GhiChu]\r\n"
					+ "           ,[MaKhachHang]\r\n"
					+ "           ,[MaNhanVien]\r\n"
					+ "           ,[TongTien])\r\n"
					+ "     VALUES\r\n"
					+ "           (?, ?,?,?,?,?)";
			
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1, hd.getSoHD());
			pre.setDate(2, (java.sql.Date) hd.getNgayLapDon());
			pre.setNString(3, hd.getGhiChu());
			pre.setNString(4, hd.getMaKH());
			pre.setNString(5, hd.getMaNV());
			pre.setInt(6, hd.getTongTien());
			
			int row = pre.executeUpdate();
			if(row > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
