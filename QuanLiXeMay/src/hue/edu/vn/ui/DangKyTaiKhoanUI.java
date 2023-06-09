package hue.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.microsoft.sqlserver.jdbc.dataclassification.Label;

import hue.edu.vn.model.Account;
import hue.edu.vn.service.DangNhapService;

public class DangKyTaiKhoanUI extends JFrame{
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhau, txtXacNhanMatKhau;
	JButton btnDangKy, btnTroLai;
	DangNhapService dn;
	
	
	public DangKyTaiKhoanUI()
	{
		super("ĐĂNG KÝ TÀI KHOẢN");
		addControls();
		addEvents();
		ketNoiCoSoDuLieu();
	}

	private void ketNoiCoSoDuLieu() {
		dn = new DangNhapService();
	}

	private void addEvents() {
		btnTroLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		btnDangKy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtTaiKhoan.getText().compareTo("") == 0 || txtMatKhau.getText().compareTo("") == 0 || txtXacNhanMatKhau.getText().compareTo("") == 0)
				{
					JOptionPane.showMessageDialog(null, "Không được để trống các thông tin!Xin kiểm tra lại");
					return;
				}
				
				boolean checkTK = dn.kiemTraTrungMa(txtTaiKhoan.getText());
				if(checkTK == true)
				{
					JOptionPane.showMessageDialog(null, "Tài Khoản Đã Tồn Tại!");
					return;
				}
				
				
				if(txtMatKhau.getText().compareTo(txtXacNhanMatKhau.getText()) != 0)
				{
					JOptionPane.showMessageDialog(null, "Mật Khẩu Xác Nhận Không Đúng!");
					return;
				}
				
				Account ac = new  Account();
				ac.setTaiKhoan(txtTaiKhoan.getText());
				ac.setMatKhau(txtMatKhau.getText());
				boolean checkThanhCong  = dn.themTaiKhoan(ac);
				if(checkThanhCong == true)
				{
					JOptionPane.showMessageDialog(null, "Thêm Tài Khoản Thành Công");
				} else 
				{
					JOptionPane.showMessageDialog(null, "Lỗi Xảy ra!");
				}
				
				
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		con.add(pnMain);
		
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel lblTitle = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font(lblTitle.getText(), Font.BOLD, 25));
		lblTitle.setForeground(Color.BLUE);

		pnMain.add(pnTitle, BorderLayout.NORTH);
		
		JPanel pnThongTinDangKy = new JPanel();
		pnThongTinDangKy.setLayout(new BoxLayout(pnThongTinDangKy, BoxLayout.Y_AXIS));
		pnMain.add(pnThongTinDangKy, BorderLayout.CENTER);
		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTaiKhoan = new JLabel("Tài Khoản: ");
		txtTaiKhoan = new JTextField(25);
		pnTaiKhoan.add(lblTaiKhoan);
		pnTaiKhoan.add(txtTaiKhoan);
		pnThongTinDangKy.add(pnTaiKhoan);
		
		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMatKhau = new JLabel("Mật Khẩu: ");
		txtMatKhau = new JPasswordField(25);
		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhau);
		pnThongTinDangKy.add(pnMatKhau);
		
		JPanel pnXacNhanMatKhau = new JPanel();
		pnXacNhanMatKhau.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblXacNhanMK = new JLabel("Xác Nhận Mật Khẩu: ");
		txtXacNhanMatKhau = new JPasswordField(25);
		pnXacNhanMatKhau.add(lblXacNhanMK);
		pnXacNhanMatKhau.add(txtXacNhanMatKhau);
		pnThongTinDangKy.add(pnXacNhanMatKhau);
		
		
		lblTaiKhoan.setPreferredSize(new Dimension(lblXacNhanMK.getPreferredSize()));
		lblMatKhau.setPreferredSize(new Dimension(lblXacNhanMK.getPreferredSize()));
		
		lblTaiKhoan.setForeground(Color.BLUE);
		lblMatKhau.setForeground(Color.BLUE);
		lblXacNhanMK.setForeground(Color.BLUE);
		
		
		
		JPanel pnButtonXuLy = new JPanel();
		pnButtonXuLy.setLayout(new FlowLayout());
		pnMain.add(pnButtonXuLy, BorderLayout.SOUTH);
		btnDangKy = new  JButton("ĐĂNG KÝ");
		btnTroLai = new JButton("TRỞ LẠI");
		pnButtonXuLy.add(btnDangKy);
		pnButtonXuLy.add(btnTroLai);
		
	}
	
	public void showWindow()
	{
		this.setSize(500,300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}
