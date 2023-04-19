package hue.edu.vn.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSql {
	protected Connection conn = null;
	public ConnectionSql()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl= "jdbc:sqlserver://localhost:1433;" +
				     "databaseName=dbQuanLiXeMay;integratedSecurity=true;" +
				     "encrypt=true;trustServerCertificate=true";
			
			conn= DriverManager.getConnection(connectionUrl);
			
			
			
			} catch (SQLException e) {
			System.out.println("SQL Exception: "+ e.toString());
			} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: "+ cE.toString());
			}
	}
}
