package hue.edu.vn.ui;

import java.awt.Color;
import java.awt.Container;
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

import hue.edu.vn.service.DangNhapService;

public class DangNhapUI extends JFrame{
	
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhau;
	JButton btnDangNhap, btnDangKy, btnDoiMatKhau;
	DangNhapService dangNhapSV;
	
	
	public DangNhapUI()
	{
		super("ĐĂNG NHẬP");
		addControls();
		addEvents();
		ketNoiCoSoDuLieu();
		
	}

	private void ketNoiCoSoDuLieu() {
		dangNhapSV = new DangNhapService();
	}

	private void addEvents() {
		btnDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DangNhapDoiMatKhauUI ui = new DangNhapDoiMatKhauUI();
				ui.showWindow();
			}
		});
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtTaiKhoan.getText().compareTo("") == 0 && txtMatKhau.getText().compareTo("") == 0)
				{
					JOptionPane.showMessageDialog(null, "Tài Khoản Hoặc Mật Khẩu Không Được Để Trống");
					return;
				}
				
				boolean check = dangNhapSV.kiemTraTonTaiTheo(txtTaiKhoan.getText(), txtMatKhau.getText());
				if(check == true)
				{
					JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công");
				} else 
				{
					JOptionPane.showMessageDialog(null, "Sai Tài Khoản Hoặc Mật Khẩu");
				}
			}
		});
		
		btnDangKy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DangKyTaiKhoanUI uiDKTaiKhoan = new DangKyTaiKhoanUI();
				uiDKTaiKhoan.showWindow();
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnTitleDN = new JPanel();
		pnTitleDN.setLayout(new FlowLayout());
		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setFont(new Font(lblTitle.getText(), Font.BOLD, 25));
		lblTitle.setForeground(Color.blue);
		pnTitleDN.add(lblTitle);
		
		pnMain.add(pnTitleDN);
		
		
		JPanel pnThongTinDangNhap = new JPanel();
	
		pnThongTinDangNhap.setLayout(new BoxLayout(pnThongTinDangNhap, BoxLayout.Y_AXIS));
		pnMain.add(pnThongTinDangNhap);
		JPanel pnTaiKhoan = new JPanel();
		JLabel lblTaiKhoan = new JLabel("Tài Khoản:");
		txtTaiKhoan = new JTextField(25);
		pnTaiKhoan.setLayout(new FlowLayout());
		pnTaiKhoan.add(lblTaiKhoan);
		pnTaiKhoan.add(txtTaiKhoan);
		pnThongTinDangNhap.add(pnTaiKhoan);
		
		JLabel lblMatKhau = new JLabel("Mật Khẩu:");
		txtMatKhau = new JPasswordField(25);
		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setLayout(new FlowLayout());
		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhau);
		pnThongTinDangNhap.add(pnMatKhau);
		
		
		JPanel pnButDangNhap = new JPanel();
		pnMain.add(pnButDangNhap);
		
		pnButDangNhap.setLayout(new FlowLayout());
		btnDangNhap = new JButton("ĐĂNG NHẬP");
		pnButDangNhap.add(btnDangNhap);
		btnDangKy = new JButton("ĐĂNG KÝ");
		btnDoiMatKhau = new JButton("ĐỔI MẬT KHẨU");
		pnButDangNhap.add(btnDangKy);
		pnButDangNhap.add(btnDoiMatKhau);
		
	}
	
	
	public void showWindow()
	{
		this.setSize(400, 270);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
}
