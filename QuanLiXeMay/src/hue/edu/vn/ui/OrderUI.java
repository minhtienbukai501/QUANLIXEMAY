package hue.edu.vn.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderUI extends JFrame {
	public OrderUI()
	{
		super("order");
		addControl();
		addEvents();
	}
	
	private void addEvents() {
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtSoLuong.getText().compareTo("") == 0)
					return;
				try
				{
					x = Integer.parseInt(txtSoLuong.getText());
					dispose();
				
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Bạn Phải Nhập Số");
				}
				
			}
		});
	}

	private void addControl() {
		
		Container con = getContentPane();
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pndong1 = new JPanel();
		JPanel pndong2 = new JPanel();
		pndong1.setLayout(new FlowLayout());
		JLabel lblSoLuong = new JLabel("Nhập Số Lượng");
		
		txtSoLuong = new JTextField(25);
		pndong1.add(lblSoLuong);
		pndong1.add(txtSoLuong);
		
		pnMain.add(pndong1);
		pnMain.add(pndong2);
		
		
		pndong2.setLayout(new FlowLayout());
		btnXacNhan = new JButton("Xác Nhận");
		pndong2.add(btnXacNhan);
		
		
		
	}

	JTextField txtSoLuong;
	JButton btnXacNhan = null;
	
	private static int x = 0;
	public static int getSoLuong()
	{
		
		return x;
	}
	
	public void showWindows()
	{
		this.setSize(350, 140);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
}
