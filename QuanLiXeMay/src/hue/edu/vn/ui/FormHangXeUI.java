package hue.edu.vn.ui;

import javax.swing.JFrame;

public class FormHangXeUI extends JFrame{
	public FormHangXeUI()
	{
		super("Thêm hãng xe");
	}
	
	public void showWindows()
	{
		this.setSize(300, 200);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
