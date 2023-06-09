package hue.edu.vn.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

import hue.edu.vn.model.Account;

public class DangNhapService extends SqlConnection {
	public Vector<Account> layDuLieuDangNhap()
	{
		Vector<Account> vec = new  Vector<Account>();
		try {
			String sql = "select * from account";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next())
			{
				Account ac = new Account();
				ac.setTaiKhoan(result.getString(1));
				ac.setMatKhau(result.getString(2));
				vec.add(ac);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	
	public boolean kiemTraTrungMa(String ma)
	{
		try {
			String sql = "select *from account where TaiKhoan = '" + ma +"' ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean themTaiKhoan(Account ac)
	{
		try {
			String sql = "insert into account values ('"+ac.getTaiKhoan()+"', '"+ac.getMatKhau()+"')";
			Statement statement = conn.createStatement();
			int x = statement.executeUpdate(sql);
			if(x > 0)
			{
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public boolean suaTaiKhoan(String tk, String mkMoi)
	{
		try {
			String sql = "update account set MatKhau = '" + mkMoi+"' where TaiKhoan = '"+tk+"'";
			Statement statement = conn.createStatement();
			int x = statement.executeUpdate(sql);
			if(x > 0)
				return true;
			
			
		} catch (Exception e) {
		}
		
		return false;
	}
	
	public boolean kiemTraTonTaiTheo(String tk, String mk)
	{
		try {
			String sql = "select *from account where TaiKhoan = '" + tk+"' and MatKhau = '"+mk+"'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			if(rs.next())
			{
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
