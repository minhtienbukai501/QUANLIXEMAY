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

import hue.edu.vn.service.DangNhapService;

public class DangNhapDoiMatKhauUI extends JFrame {
	
	
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhauCu, txtMatKhauMoi, txtXacNhanMatKhauMoi;
	JButton btnDoiMatKhau, btnTroLai;
	DangNhapService dn;
	public DangNhapDoiMatKhauUI()
	{
		super("Đổi Mật Khẩu");
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
		
		btnDoiMatKhau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtTaiKhoan.getText().compareTo("") == 0 || txtMatKhauCu.getText().compareTo("") == 0 || txtMatKhauMoi.getText().compareTo("") == 0 || txtXacNhanMatKhauMoi.getText().compareTo("")==0)
				{
					JOptionPane.showMessageDialog(null, "Thông tin không được để trống");
					return;
				} 
				
				
				boolean checkTaiKhoanHoacMatKhauTonTai = dn.kiemTraTonTaiTheo(txtTaiKhoan.getText(), txtMatKhauCu.getText());
				if(checkTaiKhoanHoacMatKhauTonTai == false)
				{
					JOptionPane.showMessageDialog(null, "Tài Khoản Hoặc Mật khảu sai");
					return;
				}
				
				if(txtMatKhauCu.getText().compareTo(txtMatKhauMoi.getText()) == 0)
				{
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ đã trùng với mật khẩu mới! Xin kiểm tra lại");
					return;
				}
				
				if(txtMatKhauMoi.getText().compareTo(txtXacNhanMatKhauMoi.getText()) == 0)
				{
					JOptionPane.showMessageDialog(null, "mật khẩu xác nhận không hợp lệ!");
					return;
				}
				
				boolean checkTrungMa = dn.kiemTraTrungMa(txtTaiKhoan.getText());
				if(checkTrungMa == false)
				{
					JOptionPane.showMessageDialog(null, "Tài Khoản Không Tồn Tại!");
					return;
				} else 
				{
					int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đổi mật khẩu", "Xác nhận đổi", JOptionPane.YES_NO_OPTION);
					if(ret == JOptionPane.NO_OPTION)
					{
						return;
					} else 
					{
						boolean capNhatTK = dn.suaTaiKhoan(txtTaiKhoan.getText(), txtMatKhauMoi.getText());
						if(capNhatTK == true)
						{
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						} else 
						{
							JOptionPane.showMessageDialog(null, "hệ thống lỗi");
						}
								
					}
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

		JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
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
		JLabel lblMatKhau = new JLabel("Mật Khẩu Cũ: ");
		txtMatKhauCu = new JPasswordField(25);
		pnMatKhau.add(lblMatKhau);
		pnMatKhau.add(txtMatKhauCu);
		pnThongTinDangKy.add(pnMatKhau);
		
		JPanel pnXacNhanMatKhau = new JPanel();
		pnXacNhanMatKhau.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblXacNhanMK = new JLabel("Mật Khẩu Mới: ");
		txtMatKhauMoi = new JPasswordField(25);
		pnXacNhanMatKhau.add(lblXacNhanMK);
		pnXacNhanMatKhau.add(txtMatKhauMoi);
		pnThongTinDangKy.add(pnXacNhanMatKhau);
		
		JPanel pnXacNhanMatKhauMoi = new JPanel();
		pnXacNhanMatKhauMoi.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblXacNhanMKM = new JLabel("Xác Nhận Mật Khẩu: ");
		txtXacNhanMatKhauMoi = new JPasswordField(25);
		pnXacNhanMatKhauMoi.add(lblXacNhanMKM);
		pnXacNhanMatKhauMoi.add(txtXacNhanMatKhauMoi);
		pnThongTinDangKy.add(pnXacNhanMatKhauMoi);
		
		
		lblTaiKhoan.setPreferredSize(new Dimension(lblXacNhanMKM.getPreferredSize()));
		lblMatKhau.setPreferredSize(new Dimension(lblXacNhanMKM.getPreferredSize()));
		lblXacNhanMK.setPreferredSize(new Dimension(lblXacNhanMKM.getPreferredSize()));
		
		lblTaiKhoan.setForeground(Color.BLUE);
		lblMatKhau.setForeground(Color.BLUE);
		lblXacNhanMK.setForeground(Color.BLUE);
		lblXacNhanMKM.setForeground(Color.BLUE);
		
		
		
		JPanel pnButtonXuLy = new JPanel();
		pnButtonXuLy.setLayout(new FlowLayout());
		pnMain.add(pnButtonXuLy, BorderLayout.SOUTH);
		btnDoiMatKhau = new  JButton("ĐỔI MẬT KHẨU");
		btnTroLai = new JButton("TRỞ LẠI");
		pnButtonXuLy.add(btnDoiMatKhau);
		pnButtonXuLy.add(btnTroLai);
	}

	public void showWindow()
	{
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
