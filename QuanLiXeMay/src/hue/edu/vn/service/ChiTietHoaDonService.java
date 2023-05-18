package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.util.Vector;

import hue.edu.vn.model.ChiTietHoaDon;

public class ChiTietHoaDonService extends SqlConnection {
	public boolean updateChiTietHD(ChiTietHoaDon ct)
	{
		try {
			String sql = "INSERT INTO [dbo].[CHITIETHOADON]\r\n"
					+ "           ([SoHD]\r\n"
					+ "           ,[MaXe]\r\n"
					+ "           ,[SoLuong]\r\n"
					+ "           ,[DonGia]\r\n"
					+ "           ,[ThanhTien])\r\n"
					+ "     VALUES  (?,  ?, ?, ?, ?)";
			
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setNString(1,ct.getMaHD() );
			pre.setNString(2,ct.getMaXe() );
			pre.setInt(3, ct.getSoLuong());
			pre.setInt(4, ct.getDonGia());
			pre.setInt(5, ct.getThanhTien());
			
			int row = pre.executeUpdate();
			if(row > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
