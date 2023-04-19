package hue.edu.vn.uii;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.microsoft.sqlserver.jdbc.dataclassification.Label;

import hue.edu.vn.model.Acount;
import hue.edu.vn.service.DangNhapService;

public class DangNhapUI extends JFrame{
	
	DangNhapService dnService;
	
	
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhau;
	JButton btnDangNhap, btnDangKy;
	public DangNhapUI()
	{
		super("ĐĂNG NHẬP");
		addControls();
		addEvents();
		ketNoiCoSoDuLieuDangNhap();
		
		
	}
	
	

	private void ketNoiCoSoDuLieuDangNhap() {
		dnService = new DangNhapService();
	}



	private void addEvents() {
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtTaiKhoan.getText().compareTo("") == 0 || txtMatKhau.getText().compareTo("") == 0)
				{
					JOptionPane.showMessageDialog(null, "Tài Khoản hoặc mật khẩu không được để trống");
					return;
				}
				
				
				boolean checkTonTai = dnService.KTTaiKhoanTonTai(txtTaiKhoan.getText(), txtMatKhau.getText());
				if(!checkTonTai)
					JOptionPane.showMessageDialog(null, "Tài Khoản hoặc mật khẩu sai");
				else 
				{
					JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công");
				}
				
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnlblDangNhap = new JPanel();
		pnlblDangNhap.setLayout(new BorderLayout());
		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP");
		lblDangNhap.setHorizontalAlignment(JLabel.CENTER);
		lblDangNhap.setFont(new Font(lblDangNhap.getText(), Font.BOLD, 20));
		lblDangNhap.setForeground(Color.blue);
		pnlblDangNhap.add(lblDangNhap,BorderLayout.CENTER);
		
		con.add(pnlblDangNhap,BorderLayout.NORTH);
		
		
		JPanel pnThongTinDangNhap = new JPanel();
		pnThongTinDangNhap.setLayout(new BoxLayout(pnThongTinDangNhap, BoxLayout.Y_AXIS));
		con.add(pnThongTinDangNhap, BorderLayout.CENTER);
		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setLayout(new FlowLayout());
		
		JLabel lblTenDN = new JLabel("Tài Khoản:");
		txtTaiKhoan =  new JTextField(25);
		pnTaiKhoan.add(lblTenDN);
		pnTaiKhoan.add(txtTaiKhoan);
		pnThongTinDangNhap.add(pnTaiKhoan);
		
		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setLayout(new FlowLayout());
		
		JLabel lblMatKhau = new JLabel("Mật Khẩu:");
		txtMatKhau = new JPasswordField(25);
		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhau);
		pnThongTinDangNhap.add(pnMatKhau);
		
		JPanel pnbutDN = new JPanel();
		pnbutDN.setLayout(new FlowLayout());
		con.add(pnbutDN, BorderLayout.SOUTH);
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangKy = new JButton("Đăng Ký");
		
		pnbutDN.add(btnDangNhap);
		pnbutDN.add(btnDangKy);
		
	}
	
	
	
	public void showWindow()
	{
		this.setSize(420,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
