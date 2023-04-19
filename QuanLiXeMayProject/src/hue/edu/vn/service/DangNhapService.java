package hue.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import hue.edu.vn.model.Acount;

public class DangNhapService extends ConnectionSql {
	
	public DangNhapService()
	{
		super();
	}
	
	public Vector<Acount> layThongTinTaiKhoan()
	{
		Vector<Acount> vec = new Vector<Acount>();
		try {
			String sql = "select *from acccount";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				Acount ac = new Acount();
				ac.setTaiKhoan(rs.getString(1));
				ac.setMatKhau(rs.getString(2));
				vec.add(ac);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	public boolean KTTaiKhoanTonTai(String Tk, String MK)
	{
		
		try {
			String sql = "select *from acccount where TaiKhoan = '"+Tk+"' and MatKhau = "+MK+"";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if(result.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
