package hue.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.JavaBean;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Flow;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;

import hue.edu.vn.model.ChiTietHoaDon;
import hue.edu.vn.model.HangXe;
import hue.edu.vn.model.HoaDon;
import hue.edu.vn.model.KhachHang;
import hue.edu.vn.model.NhanVien;
import hue.edu.vn.model.PhanLoaiKhachHang;
import hue.edu.vn.model.Xe;
import hue.edu.vn.service.ChiTietHoaDonService;
import hue.edu.vn.service.HangXeService;
import hue.edu.vn.service.HoaDonService;
import hue.edu.vn.service.KhachHangService;
import hue.edu.vn.service.NhanVienService;
import hue.edu.vn.service.PhanLoaiKhachHangService;
import hue.edu.vn.service.XeService;

public class MainUI extends JFrame {

	XeService duLieuXeSQL = null;
	JTabbedPane jtab;
	Vector<Xe> dsXe = null;
	Vector<HangXe> dsHangXe = null;
	Vector<KhachHang> dsKhachHang = null;
	PhanLoaiKhachHangService phanLoaiKhachHangService;
	KhachHangService khachHangService;
	NhanVienService nhanVienService;
	KhachHang khachHangSelected;
	ChiTietHoaDonService chiTietHoaDonService = null;
	Vector<ChiTietHoaDon> dsChiTietHoaDon = null;

	HoaDonService hoaDonService = null;
	Vector<HoaDon> listHD;

	JPanel pnThemHangXe;

	HangXeService duLieuHangXeSQL;
	DefaultComboBoxModel<HangXe> model;
	Xe xeSelected;
	HangXe hangXeSelected;

	DefaultTableModel dtm, dtmNhanVien;

	JTable tblListXe, tblListNhanVien;
	JPanel pnLeft, pnRight;
	JTextField txtTimkiemXe;
	JButton btnTimKiemXe;

	JButton btnThemXe, btnXoaXe, btnSuaXe;

	// xử lý form KhachHang
	JTextField txtMaKhachHang, txtTenKhachHang, txtDiaChi, txtSoDienThoai, txtEmail;
	DefaultTableModel dtmKhachHang;
	JTable tblKhachKhang;
	JComboBox<PhanLoaiKhachHang> cmbLoaiKhachHang;
	DefaultComboBoxModel<PhanLoaiKhachHang> modelLoaiKhachHang;
	//
	JComboBox<HangXe> cmbHangXe;
	JTextField txtMaXe, txtTenXe, txtNamSanXuat, txtTrongLuong, txtSoLuong, txtGiaBan;
	JButton btnThemKhachHang, btnXoaKhachHang, btnSuaKhachHang;

	public MainUI() {
		super("QUẢN LÝ XE");
		ketNoiSql();
		addControls();
		addEvents();

	}

	private void ketNoiSql() {
		duLieuXeSQL = new XeService();
		duLieuHangXeSQL = new HangXeService();
		khachHangService = new KhachHangService();
		phanLoaiKhachHangService = new PhanLoaiKhachHangService();
		nhanVienService = new NhanVienService();
		chiTietHoaDonService = new ChiTietHoaDonService();
		hoaDonService = new HoaDonService();

	}

	boolean kiemTraRongThongTin(String s) {
		return s.compareTo("") == 0;
	}

	PhanLoaiKhachHang plSelect = null;

	NhanVien nvSelected = null;

	Xe xeOrderSelected = null;
	Random rd = new Random();
	int SoHoaDonRandom = 0;

	int soluong = 0;

	java.util.Date dateNow = null;
	private void addEvents() {
		
		
		
		btnThemHangXe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(kiemTraRongThongTin(txtTenHangXe.getText()) || kiemTraRongThongTin(txtMaHangXe.getText()))
				{
					JOptionPane.showMessageDialog(null, "Không Để Trống Thông Tin");
					return;
				}
				
				if(duLieuHangXeSQL.trungMa(txtMaHangXe.getText()))
				{
					JOptionPane.showMessageDialog(null, "Mã đã tồn tại");
					txtMaHangXe.setText("");
					return;
				}
				HangXe hx = new HangXe();
				hx.setMaHangXe(txtMaHangXe.getText());
				hx.setTenHangXe(txtTenHangXe.getText());
				
				boolean themhangxe = duLieuHangXeSQL.themHangXe(hx);
				if(themhangxe)
				{
					JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
					txtMaHangXe.setText("");
					txtTenHangXe.setText("");
					hienThiDuLieuHangXe();
					return;
				}
					
				
			}
		});
		
		tblHangXe.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHangXe.getSelectedRow();
				if(row == -1)
					return;
				
				txtMaHangXe.setText(tblHangXe.getValueAt(row, 1) + "");
				txtTenHangXe.setText(tblHangXe.getValueAt(row, 2) + "");
				
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmHoaDon.setRowCount(0);
				lblHienThiTongTien.setText(0 + "");
				
			}
		});
		
		
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Integer.parseInt(txtTienKhachTra.getText()) < Integer.parseInt(lblHienThiTongTien.getText()))
				{
					JOptionPane.showMessageDialog(null, "Số Tiền Khách Trả Ko Đủ Xin Kiểm Tra Lại");
					txtTienKhachTra.setText(0 +"");
					return;
					
				}
				HoaDon x = new HoaDon();
				
				KhachHang khDatHang = (KhachHang) cmbChonKhachHang.getSelectedItem();
				x.setMaKH(khDatHang.getMaKhachHang());
				NhanVien nvLamHoaDon = (NhanVien) cmbnvLapHoaDon.getSelectedItem();
				x.setMaNV(nvLamHoaDon.getMaNhanVien());
				x.setSoHD(lblHienThiSoHoaDon.getText());
				x.setGhiChu(txtGhiChu.getText());
				
				
				
				
				java.sql.Date date1sql = new java.sql.Date(dateNow.getTime());
				
				x.setNgayLapDon(date1sql);
				
				x.setTongTien(Integer.parseInt(lblHienThiTongTien.getText()));
				boolean ThemHoaDon = hoaDonService.themHD(x);
				if(ThemHoaDon == true)
				{
					JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");
					dtmHoaDon.setRowCount(0);
					lblHienThiTongTien.setText(0 + "");
					
					SoHoaDonRandom = rd.nextInt(0, 100000000);
					boolean kiemTraTrungHoaDon = hoaDonService.isTrungHD(lblHienThiSoHoaDon.getText());
					String soHD = "HD" + SoHoaDonRandom;
					while(hoaDonService.isTrungHD(soHD) == true)
					{
						SoHoaDonRandom = rd.nextInt(0, 100000000);
						soHD = "HD" + SoHoaDonRandom;
					}

					
					lblHienThiSoHoaDon.setText("HD" + SoHoaDonRandom);

					return;
				}
				
				
				
				
				
				
			}
		});

		tblListOrder.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = tblListOrder.getSelectedRow();
				if (row == -1)
					return;

				OrderUI ui = new OrderUI();
				ui.showWindows();

				ui.btnXacNhan.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (ui.txtSoLuong.getText().compareTo("") == 0)
							return;
						try {
							soluong = Integer.parseInt(ui.txtSoLuong.getText());
							ui.dispose();

							if (soluong > 0) {

								if (xeOrderSelected == null)
									xeOrderSelected = new Xe();
								int rowlistt = tblListOrder.getSelectedRow();

								String ma = (String) tblListOrder.getValueAt(rowlistt, 0);

								xeOrderSelected = duLieuXeSQL.layXe(ma);
								boolean checkTrungTen = false;
								for (int i = 0; i < dtmHoaDon.getRowCount(); i++)
								{
									if(tblHoaDon.getValueAt(i, 1).toString().compareTo(xeOrderSelected.getTenXe()) == 0 )
									{
										int SoLuongMoi = (int) tblHoaDon.getValueAt(i, 2) + soluong;
										tblHoaDon.setValueAt(SoLuongMoi, i, 2);
										tblHoaDon.setValueAt(SoLuongMoi * xeOrderSelected.getGiaBan(), i, 4);
										checkTrungTen = true;
										
										break;
										
									}
								}
								
								if(checkTrungTen == false)
								{
									Vector<Object> vec = new Vector<Object>();
									int STT = dtmHoaDon.getRowCount() + 1;
									vec.add(STT);
									vec.add(xeOrderSelected.getTenXe());
									vec.add(soluong);
									vec.add(xeOrderSelected.getGiaBan());
									vec.add(soluong * xeOrderSelected.getGiaBan());
									vec.add(txtGhiChu.getText());
									dtmHoaDon.addRow(vec);
								}
									

								int TongTien = 0;
								for (int i = 0; i < dtmHoaDon.getRowCount(); i++) {
									int s = (int) tblHoaDon.getValueAt(i, 4);
									TongTien += s;
								}
								lblHienThiTongTien.setText(TongTien + "");

							}

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Bạn Phải Nhập Số");
						}
					}
				});

			}
		});

		cmbChonKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KhachHang khselect = new KhachHang();
				khselect = (KhachHang) cmbChonKhachHang.getSelectedItem();
				lblHienThiDiaChi.setText(khselect.getDiaChi());
				lblHienThiTenKhachHang.setText(khselect.getTenKhachHang());
			}
		});

		cmbChonLoaiXe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HangXe temp = (HangXe) cmbChonLoaiXe.getSelectedItem();
				layDuLieuXeDuaVaoOrder(temp.getMaHangXe());
			}
		});

		btnXoaNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNV.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Chọn Dòng Bạn Muốn Xóa");
					return;
				}

				int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chăc Muốn Xóa", "Xác Nhận",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					boolean checkXoa = nhanVienService.xoaNhanVien(txtMaNV.getText());
					if (checkXoa) {
						JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
						layDuLieuNhanVien();
					} else {
						return;
					}
				} else {
					return;
				}
			}
		});

		tblNV.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				nvSelected = new NhanVien();
				int row = tblNV.getSelectedRow();

				if (row == -1)
					return;

				nvSelected.setMaNhanVien(tblNV.getValueAt(row, 0) + "");
				nvSelected.setTenNhanVien(tblNV.getValueAt(row, 1) + "");

				java.util.Date date1 = null;
				try {
					date1 = sdf.parse(tblNV.getValueAt(row, 2) + "");
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				java.sql.Date date1sql = new java.sql.Date(date1.getTime());

				java.util.Date date2 = null;
				try {
					date2 = sdf.parse(tblNV.getValueAt(row, 4) + "");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date date2sql = new java.sql.Date(date2.getTime());

				nvSelected.setNgaySinh(date1sql);
				nvSelected.setNgayVaoLam(date2sql);
				nvSelected.setGioiTinh(tblNV.getValueAt(row, 3) + "");

				nvSelected.setDiaChi(tblNV.getValueAt(row, 5) + "");
				nvSelected.setSoDienThoai(tblNV.getValueAt(row, 6) + "");

				txtMaNV.setText(nvSelected.getMaNhanVien());
				txtTenNV.setText(nvSelected.getTenNhanVien());
				txtNgaySinhNV.setText(sdf.format(nvSelected.getNgaySinh()));

				cmbGioiTinh.setSelectedItem(nvSelected.getGioiTinh());
				txtNgayVaoLam.setText(sdf.format(nvSelected.getNgayVaoLam()));

				txtDiaChiNV.setText(nvSelected.getDiaChi());
				txtSoDienThoaiNV.setText(nvSelected.getSoDienThoai());

			}
		});

		btnXoaNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnThemNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (kiemTraRongThongTin(txtMaNV.getText()) || kiemTraRongThongTin(txtTenNV.getText())
						|| kiemTraRongThongTin(txtNgaySinhNV.getText()) || kiemTraRongThongTin(txtNgayVaoLam.getText())
						|| kiemTraRongThongTin(txtDiaChiNV.getText())
						|| kiemTraRongThongTin(txtSoDienThoaiNV.getText())) {
					JOptionPane.showMessageDialog(null, "Không Được Để Trống Các Thông Tin");
					return;
				}

				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(txtMaNV.getText());
				nv.setTenNhanVien(txtTenNV.getText());

				java.util.Date date1 = null;
				try {
					date1 = sdf.parse(txtNgaySinhNV.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				java.sql.Date date1sql = new java.sql.Date(date1.getTime());
				nv.setNgaySinh(date1sql);

				java.util.Date date2 = null;
				try {
					date2 = sdf.parse(txtNgayVaoLam.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date date2sql = new java.sql.Date(date2.getTime());
				nv.setNgayVaoLam(date2sql);

				nv.setDiaChi(txtDiaChiNV.getText());
				nv.setSoDienThoai(txtSoDienThoaiNV.getText());
				nv.setGioiTinh(cmbGioiTinh.getSelectedItem() + "");
				if (nhanVienService.kiemTraTrungMa(txtMaNV.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Mã Nhân Viên Đã Tồn Tại");
					return;
				}

				boolean checkThem = nhanVienService.themNhanVien(nv);
				if (checkThem == false) {
					JOptionPane.showMessageDialog(null, "Thêm Thất Bại");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					layDuLieuNhanVien();
				}

			}
		});

		btnSuaKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(txtMaKhachHang.getText());
				kh.setTenKhachHang(txtTenKhachHang.getText());

				kh.setDiaChi(txtDiaChi.getText());
				kh.setEmail(txtEmail.getText());
				kh.setSoDienThoai(txtSoDienThoai.getText());
				PhanLoaiKhachHang pl = new PhanLoaiKhachHang();
				pl = (PhanLoaiKhachHang) cmbLoaiKhachHang.getSelectedItem();

				if (khachHangService.suaKhachHang(kh, pl.getLoaiKhachHang()) == true) {
					JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
					layDuLieuKhachHang();
				} else {

				}

			}
		});

		btnXoaKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblKhachKhang.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng Chọn Dòng Cần Xóa");
					return;
				}

				int chon = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Chắn Muốn Xóa", "Thông Báo",
						JOptionPane.YES_NO_OPTION);

				if (chon == JOptionPane.YES_OPTION) {
					boolean xoa = khachHangService.xoaKhachHang(txtMaKhachHang.getText());
					if (xoa == true) {
						JOptionPane.showMessageDialog(null, "Xóa Thành Công");
						layDuLieuKhachHang();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa Không Thành Công!");
						return;
					}
				}

			}
		});

		btnThemKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				plSelect = new PhanLoaiKhachHang();

				plSelect = (PhanLoaiKhachHang) cmbLoaiKhachHang.getSelectedItem();

				boolean kiemTraTrungMa = khachHangService.checkTrungMa(txtMaKhachHang.getText());

				if (kiemTraTrungMa) {
					JOptionPane.showMessageDialog(null, "Mã Đã Tồn tại trong dữ liệu");
					return;
				} else {
					KhachHang kh = new KhachHang();
					kh.setMaKhachHang(txtMaKhachHang.getText());
					kh.setTenKhachHang(txtTenKhachHang.getText());
					kh.setDiaChi(txtDiaChi.getText());
					kh.setSoDienThoai(txtSoDienThoai.getText());
					kh.setEmail(txtEmail.getText());
					boolean check = khachHangService.themKhachHang(kh, plSelect.getLoaiKhachHang());
					if (check == true) {
						JOptionPane.showMessageDialog(null, "Thêm Thành Công");
						layDuLieuKhachHang();

					} else {
						JOptionPane.showMessageDialog(null, "Thêm Thất Bại");
						return;
					}

				}
			}
		});
		cmbLoaiKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		tblKhachKhang.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				khachHangSelected = new KhachHang();

				int row = tblKhachKhang.getSelectedRow();
				if (row == -1) {
					return;
				}
				khachHangSelected.setMaKhachHang(tblKhachKhang.getValueAt(row, 0) + "");
				khachHangSelected.setTenKhachHang(tblKhachKhang.getValueAt(row, 1) + "");
				khachHangSelected.setDiaChi(tblKhachKhang.getValueAt(row, 2) + "");
				khachHangSelected.setSoDienThoai(tblKhachKhang.getValueAt(row, 3) + "");
				khachHangSelected.setEmail(tblKhachKhang.getValueAt(row, 4) + "");

				txtMaKhachHang.setText(khachHangSelected.getMaKhachHang());
				txtTenKhachHang.setText(khachHangSelected.getTenKhachHang());
				txtDiaChi.setText(khachHangSelected.getDiaChi());
				txtEmail.setText(khachHangSelected.getEmail());
				txtSoDienThoai.setText(khachHangSelected.getSoDienThoai());

				String tenKhachHang = tblKhachKhang.getValueAt(row, 5) + "";

				for (PhanLoaiKhachHang x : dsPhanLoai) {
					if (x.getTenLoai().compareTo(tenKhachHang) == 0) {
						cmbLoaiKhachHang.setSelectedItem(x);
						return;
					}
				}

			}
		});

		btnThemKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (kiemTraRongThongTin(txtMaKhachHang.getText()) || kiemTraRongThongTin(txtTenKhachHang.getText())
						|| kiemTraRongThongTin(txtDiaChi.getText()) || kiemTraRongThongTin(txtSoDienThoai.getText())
						|| kiemTraRongThongTin(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Không Được Để Trống Thông Tin!");
					return;
				}

			}
		});

		// xử lý xóa
		btnXoaXe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblListXe.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null,
							"Bạn Chưa Chọn Vào Dòng Nào Để Xóa. Vui Lòng Chọn Dòng Để Xóa!");
					return;
				}

				int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chăc Chắn Muốn Xóa", "Xác Nhận",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.NO_OPTION) {
					return;
				} else {
					boolean xoaXe = duLieuXeSQL.xoaXe(txtMaXe.getText());
					if (xoaXe == false) {
						JOptionPane.showMessageDialog(null, "Xóa Thất Bại!");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Xóa Xóa Thành Công");
						HienThiDuLieuXe();
					}

				}
			}
		});

		// xử lý click vào
		tblListXe.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblListXe.getSelectedRow();
				if (row == -1)
					return;

				txtMaXe.setText(tblListXe.getValueAt(row, 0) + "");
				txtTenXe.setText(tblListXe.getValueAt(row, 1) + "");
				txtNamSanXuat.setText(tblListXe.getValueAt(row, 3) + "");
				txtTrongLuong.setText(tblListXe.getValueAt(row, 4) + "");
				txtSoLuong.setText(tblListXe.getValueAt(row, 5) + "");
				txtGiaBan.setText(tblListXe.getValueAt(row, 6) + "");
				String layHangXe = tblListXe.getValueAt(row, 2) + "";
				for (HangXe x : dsHangXe) {
					if (x.getTenHangXe().compareToIgnoreCase(layHangXe) == 0) {
						hangXeSelected = x;
						break;
					}
				}

				cmbHangXe.setSelectedItem(hangXeSelected);

			}
		});

		// xử lý tìm kiếm
		btnTimKiemXe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timKiemXeTheoTen();
			}
		});
		// xử lý thêm xe
		btnThemXe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtMaXe.getText().compareTo("") == 0 || txtTenXe.getText().compareTo("") == 0
						|| txtNamSanXuat.getText().compareTo("") == 0 || txtTrongLuong.getText().compareTo("") == 0
						|| txtSoLuong.getText().compareTo("") == 0 || txtGiaBan.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(null, "Không Được Để Trống Các Thông Tin");
					return;
				}

				boolean checkTrungMa = duLieuXeSQL.checkTrungMa(txtMaXe.getText());
				if (checkTrungMa) {
					JOptionPane.showMessageDialog(null, "Mã Đã Tồn Tại!");
					return;
				} else {
					hangXeSelected = (HangXe) cmbHangXe.getSelectedItem();
					boolean checkthemxe = duLieuXeSQL.themXe(txtMaXe.getText(), hangXeSelected.getMaHangXe(),
							txtTenXe.getText(), Integer.parseInt(txtNamSanXuat.getText()),
							Integer.parseInt(txtTrongLuong.getText()), Integer.parseInt(txtSoLuong.getText()),
							Integer.parseInt(txtGiaBan.getText()));
					if (checkthemxe == true) {
						JOptionPane.showMessageDialog(null, "Thêm Thành Công");
						HienThiDuLieuXe();
					} else {
						JOptionPane.showMessageDialog(null, "Thêm Thất Bại");
						return;
					}

				}
			}
		});

		// xử lý xóa xe
		btnXoaXe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	protected void hienThiGiaoDienNhanVien() {

	}

	// HIỂN THỊ GIAO DIỆN KHÁCH HÀNG
	private void hienThiGiaoDienKhachHang(JPanel con) {
		con.setLayout(new BorderLayout());

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		pnTitle.add(lblTitle);
		con.add(pnTitle, BorderLayout.NORTH);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 32));

		JPanel pnCenter = new JPanel();
		con.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout());
		dtmKhachHang = new DefaultTableModel();
		dtmKhachHang.addColumn("Mã Khách Hàng");
		dtmKhachHang.addColumn("Tên Khách Hàng");
		dtmKhachHang.addColumn("Địa Chỉ");
		dtmKhachHang.addColumn("Số Điện Thoại");
		dtmKhachHang.addColumn("Email");
		dtmKhachHang.addColumn("Loại Khách Hàng");
		tblKhachKhang = new JTable(dtmKhachHang);
		JScrollPane scKhachHang = new JScrollPane(tblKhachKhang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scKhachHang, BorderLayout.CENTER);

		Border borderKhachHang = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleBorderKhachHang = new TitledBorder(borderKhachHang, "Quản Lý Khách Hàng");
		titleBorderKhachHang.setTitleColor(Color.red);
		pnCenter.setBorder(titleBorderKhachHang);
		layDuLieuKhachHang();

		//
		JPanel pnSouth = new JPanel();
		con.add(pnSouth, BorderLayout.SOUTH);

		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		JPanel pnDong1 = new JPanel();
		pnSouth.add(pnDong1);
		JPanel pnDong1Left = new JPanel();
		JPanel pnDong1Right = new JPanel();
		pnDong1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnDong1.add(pnDong1Left);
		pnDong1.add(pnDong1Right);
		pnDong1Left.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa = new JLabel("Mã Khách Hàng: ");

		txtMaKhachHang = new JTextField(25);
		pnDong1Left.add(lblMa);
		pnDong1Left.add(txtMaKhachHang);

		JLabel lblLoaiKhachHang = new JLabel("Loại Khách Hàng");
		layDuLieuLoaiKhachHang();

		cmbLoaiKhachHang = new JComboBox<PhanLoaiKhachHang>(modelLoaiKhachHang);
		pnDong1Right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnDong1Right.add(lblLoaiKhachHang);
		pnDong1Right.add(cmbLoaiKhachHang);

		JPanel pnDong2 = new JPanel();
		pnSouth.add(pnDong2);
		pnDong2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel pnDong2Left = new JPanel();
		JPanel pnDong2Right = new JPanel();
		pnDong2.add(pnDong2Left);
		pnDong2.add(pnDong2Right);
		pnDong2Left.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Tên Khách Hàng:");
		txtTenKhachHang = new JTextField(25);
		pnDong2Left.add(lblTen);
		pnDong2Left.add(txtTenKhachHang);

		JLabel lblDiaChi = new JLabel("Địa Chỉ: ");
		txtDiaChi = new JTextField(25);
		pnDong2Right.add(lblDiaChi);
		pnDong2Right.add(txtDiaChi);

		JPanel pnDong3 = new JPanel();
		pnDong3.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnDong3);

		JPanel pnDong3Left = new JPanel();
		JPanel pnDong3Right = new JPanel();
		pnDong3.add(pnDong3Left);
		pnDong3.add(pnDong3Right);
		pnDong3Left.setLayout(new FlowLayout());
		pnDong3Right.setLayout(new FlowLayout());
		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại: ");
		txtSoDienThoai = new JTextField(25);
		pnDong3Left.add(lblSoDienThoai);
		pnDong3Left.add(txtSoDienThoai);

		JLabel lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField(25);
		pnDong3Right.add(lblEmail);
		pnDong3Right.add(txtEmail);
		lblMa.setPreferredSize(new Dimension(lblTen.getPreferredSize()));
		lblSoDienThoai.setPreferredSize(new Dimension(lblTen.getPreferredSize()));

		JPanel pnButtonXuLyThemXoaSua = new JPanel();
		pnButtonXuLyThemXoaSua.setLayout(new FlowLayout());
		pnSouth.add(pnButtonXuLyThemXoaSua);

		btnThemKhachHang = new JButton("THÊM");
		btnXoaKhachHang = new JButton("XÓA");
		btnSuaKhachHang = new JButton("SỬA");
		pnButtonXuLyThemXoaSua.add(btnThemKhachHang);
		pnButtonXuLyThemXoaSua.add(btnXoaKhachHang);
		pnButtonXuLyThemXoaSua.add(btnSuaKhachHang);

		btnThemKhachHang.setIcon(new ImageIcon("Images/them.jpg"));
		btnXoaKhachHang.setIcon(new ImageIcon("Images/xoa.png"));
		btnSuaKhachHang.setIcon(new ImageIcon("Images/edit.png"));


	}

	/// HIỂN THỊ GIAO DIỆN SẢN PHẨM
	private void hienThiGiaoDienSanPham(JPanel con) {
		con.setLayout(new BorderLayout());

		JPanel pnRight = new JPanel();

		con.add(pnRight, BorderLayout.CENTER);
		// -------------------- TẠO GIAO DIỆN BÊN PHẢI-----------------------//

		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight = new JPanel();
		pnRight.add(pnTopOfRight, BorderLayout.NORTH);
		JPanel pnCenterOfRight = new JPanel();
		pnRight.add(pnCenterOfRight, BorderLayout.CENTER);
		JPanel pnBottomOfRight = new JPanel();
		pnRight.add(pnBottomOfRight, BorderLayout.SOUTH);

		// sét Giao Diện Ở trên
		pnTopOfRight.setLayout(new BorderLayout());
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new FlowLayout());
		JLabel lblTuKhoaTimKiem = new JLabel("Từ Khóa Tìm Kiếm: ");
		txtTimkiemXe = new JTextField(30);
		pnTimKiem.add(lblTuKhoaTimKiem);
		pnTimKiem.add(txtTimkiemXe);
		btnTimKiemXe = new JButton("Tìm");
		pnTimKiem.add(btnTimKiemXe);
		pnTopOfRight.add(pnTimKiem, BorderLayout.CENTER);
		// Lập Giao Diện Ở Giữa
		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Xe");
		dtm.addColumn("Tên Xe");
		dtm.addColumn("Tên Hãng Xe");
		dtm.addColumn("Năm Sản Xuất");
		dtm.addColumn("Trọng Lượng(Kg)");
		dtm.addColumn("Số Lượng");
		dtm.addColumn("Giá Bán");
		tblListXe = new JTable(dtm);
		JScrollPane srListXe = new JScrollPane(tblListXe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfRight.setLayout(new BorderLayout());
		pnCenterOfRight.add(srListXe, BorderLayout.CENTER);
		HienThiDuLieuXe();

		// lập giao diện ở dưới

		pnBottomOfRight.setLayout(new BorderLayout());

	}

	private void hienThiGiaoDienTrangChu(JPanel con) {

		con.setLayout(new BorderLayout());

		JPanel pnRight = new JPanel();

		con.add(pnRight, BorderLayout.CENTER);
		// -------------------- TẠO GIAO DIỆN BÊN PHẢI-----------------------//

		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight = new JPanel();
		pnRight.add(pnTopOfRight, BorderLayout.NORTH);
		JPanel pnCenterOfRight = new JPanel();
		pnRight.add(pnCenterOfRight, BorderLayout.CENTER);
		JPanel pnBottomOfRight = new JPanel();
		pnRight.add(pnBottomOfRight, BorderLayout.SOUTH);

		// sét Giao Diện Ở trên
		pnTopOfRight.setLayout(new BorderLayout());
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new FlowLayout());
		JLabel lblTuKhoaTimKiem = new JLabel("Từ Khóa Tìm Kiếm: ");
		txtTimkiemXe = new JTextField(30);
		pnTimKiem.add(lblTuKhoaTimKiem);
		pnTimKiem.add(txtTimkiemXe);
		btnTimKiemXe = new JButton("Tìm");
		pnTimKiem.add(btnTimKiemXe);
		pnTopOfRight.add(pnTimKiem, BorderLayout.CENTER);
		// Lập Giao Diện Ở Giữa
		dtm = new DefaultTableModel();
		dtm.addColumn("Mã Xe");
		dtm.addColumn("Tên Xe");
		dtm.addColumn("Tên Hãng Xe");
		dtm.addColumn("Năm Sản Xuất");
		dtm.addColumn("Trọng Lượng(Kg)");
		dtm.addColumn("Số Lượng");
		dtm.addColumn("Giá Bán");
		tblListXe = new JTable(dtm);
		JScrollPane srListXe = new JScrollPane(tblListXe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenterOfRight.setLayout(new BorderLayout());
		pnCenterOfRight.add(srListXe, BorderLayout.CENTER);
		HienThiDuLieuXe();

		// lập giao diện ở dưới
		pnBottomOfRight.setLayout(new BorderLayout());
		JPanel pnThongTinXe = new JPanel();
		pnBottomOfRight.add(pnThongTinXe, BorderLayout.CENTER);
		pnThongTinXe.setLayout(new BoxLayout(pnThongTinXe, BoxLayout.Y_AXIS));

		JPanel pnHangSanXuat = new JPanel();
		pnHangSanXuat.setLayout(new FlowLayout());
		JLabel lblHangSanXuat = new JLabel("Hãng Sản Xuất: ");
		pnHangSanXuat.add(lblHangSanXuat);
		layDuLieuHangXe();
		cmbHangXe = new JComboBox<HangXe>(model);
		pnHangSanXuat.add(cmbHangXe);
		pnThongTinXe.add(pnHangSanXuat);

		JPanel pnMaXe = new JPanel();
		pnMaXe.setLayout(new FlowLayout());
		pnThongTinXe.add(pnMaXe);

		JLabel lblMaXe = new JLabel("Mã Xe:");
		txtMaXe = new JTextField(30);
		pnMaXe.add(lblMaXe);
		pnMaXe.add(txtMaXe);

		JLabel lblTenXe = new JLabel("Tên Xe: ");
		txtTenXe = new JTextField(30);
		JPanel pnTenXe = new JPanel();
		pnTenXe.setLayout(new FlowLayout());
		pnTenXe.add(lblTenXe);
		pnTenXe.add(txtTenXe);
		pnThongTinXe.add(pnTenXe);

		JLabel lblNamSanXuat = new JLabel("Năm Sản Xuất: ");
		txtNamSanXuat = new JTextField(30);
		JPanel pnNamSanXuat = new JPanel();
		pnNamSanXuat.setLayout(new FlowLayout());
		pnNamSanXuat.add(lblNamSanXuat);
		pnNamSanXuat.add(txtNamSanXuat);
		pnThongTinXe.add(pnNamSanXuat);

		JLabel lblSoLuong = new JLabel("Số Lượng: ");
		txtSoLuong = new JTextField(30);

		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new FlowLayout());
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnThongTinXe.add(pnSoLuong);

		JPanel pnTrongLuong = new JPanel();

		pnTrongLuong.setLayout(new FlowLayout());
		JLabel lblTrongLuong = new JLabel("Trọng Lượng:");
		txtTrongLuong = new JTextField(30);
		pnTrongLuong.add(lblTrongLuong);
		pnTrongLuong.add(txtTrongLuong);
		pnThongTinXe.add(pnTrongLuong);

		JLabel lblGiaBan = new JLabel("Giá Bán: ");

		txtGiaBan = new JTextField(30);

		JPanel pnGiaBan = new JPanel();
		pnGiaBan.setLayout(new FlowLayout());
		pnGiaBan.add(lblGiaBan);
		pnGiaBan.add(txtGiaBan);
		pnThongTinXe.add(pnGiaBan);

		lblMaXe.setPreferredSize(new Dimension(lblNamSanXuat.getPreferredSize()));
		lblTenXe.setPreferredSize(new Dimension(lblNamSanXuat.getPreferredSize()));
		lblSoLuong.setPreferredSize(new Dimension(lblNamSanXuat.getPreferredSize()));
		lblTrongLuong.setPreferredSize(new Dimension(lblNamSanXuat.getPreferredSize()));
		lblGiaBan.setPreferredSize(new Dimension(lblNamSanXuat.getPreferredSize()));

		JPanel pnButtonXuLyThemXoaSua = new JPanel();
		pnButtonXuLyThemXoaSua.setLayout(new FlowLayout());
		pnThongTinXe.add(pnButtonXuLyThemXoaSua);

		btnThemXe = new JButton("THÊM");
		btnXoaXe = new JButton("XÓA");
		btnSuaXe = new JButton("SỬA");
		pnButtonXuLyThemXoaSua.add(btnThemXe);
		pnButtonXuLyThemXoaSua.add(btnXoaXe);
		pnButtonXuLyThemXoaSua.add(btnSuaXe);

		btnThemXe.setIcon(new ImageIcon("Images/them.jpg"));
		btnXoaXe.setIcon(new ImageIcon("Images/xoa.png"));
		btnSuaXe.setIcon(new ImageIcon("Images/edit.png"));

		TitledBorder titleBorderpnCenter = BorderFactory.createTitledBorder("Thông Tin Sản Phẩm");
		titleBorderpnCenter.setTitleColor(Color.blue);
		pnCenterOfRight.setBorder(titleBorderpnCenter);

	}

	private void timKiemXeTheoTen() {
		if (txtTimkiemXe.getText().compareTo("") == 0) {
			HienThiDuLieuXe();
			return;
		}

		dtm.setRowCount(0);
		dsXe = duLieuXeSQL.timKiemTheoTenXe(txtTimkiemXe.getText());
		for (Xe x : dsXe) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(x.getMaXe());
			vec.add(x.getTenXe());
			vec.add(x.getTenHangXe());
			vec.add(x.getNamSanXuat());
			vec.add(x.getTrongLuong());
			vec.add(x.getSoLuongConLai());
			vec.add(x.getGiaBan());
			dtm.addRow(vec);
		}

	}

	private void addControls() {
		Container con = getContentPane();
		jtab = new JTabbedPane(JTabbedPane.LEFT);
		con.add(jtab);

		JButton btnSanPham, btnNhanVien, btnOrder, btnThemHangXe, btnKhachHang, btnHoaDon;
		JPanel pnTrangChu = new JPanel();
		JPanel pnSanPham = new JPanel();
		pnThemHangXe = new JPanel();
		JPanel pnKhachHang = new JPanel();
		JPanel pnNhanVien = new JPanel();
		JPanel pnHoaDon = new JPanel();
		JPanel pnAboutMe = new JPanel();

		ImageIcon iconTrangChu = new ImageIcon("Images/home.png");
		ImageIcon iconSanPham = new ImageIcon("Images/giohang.jpg");
		ImageIcon iconHangXe = new ImageIcon("Images/hangxemay.png");
		ImageIcon iconKhachHang = new ImageIcon("Images/khachhang.jpg");
		ImageIcon iconHoaDon = new ImageIcon("Images/hoadon.jpg");
		ImageIcon iconNhanVien = new ImageIcon("Images/NhanVien.jpg");
		ImageIcon iconAboutme = new ImageIcon("Images/aboutme.png");

		Dimension newPreferredSize = new Dimension(400, 150);
		jtab.addTab("Trang Chủ", iconTrangChu, pnTrangChu);
		jtab.addTab("Sản Phẩm", iconSanPham, pnSanPham);
		jtab.addTab("Hãng Xe", iconHangXe, pnThemHangXe);
		jtab.addTab("Khách Hàng", iconKhachHang, pnKhachHang);
		jtab.addTab("Hóa Đơn", iconHoaDon, pnHoaDon);
		jtab.addTab("Nhân Viên", iconNhanVien, pnNhanVien);
		jtab.addTab("About Me", iconAboutme, pnAboutMe);

		pnTrangChu.setPreferredSize(newPreferredSize);
		pnSanPham.setPreferredSize(newPreferredSize);
		pnThemHangXe.setPreferredSize(newPreferredSize);
		pnKhachHang.setPreferredSize(newPreferredSize);
		pnHoaDon.setPreferredSize(newPreferredSize);
		pnNhanVien.setPreferredSize(newPreferredSize);
		pnAboutMe.setPreferredSize(newPreferredSize);

		hienThiGiaoDienTrangChu(pnSanPham);
		hienThiGiaoDienSanPham(pnTrangChu);
		hienThiGiaoDienKhachHang(pnKhachHang);

		hienThiGiaoDienHangXe(pnThemHangXe);
		hienThiGiaoDienNhanVien(pnNhanVien);
		hienThiGiaoDienHoaDon(pnHoaDon);
		hienThiGiaoDienAboutMe(pnAboutMe);

	}

	private void hienThiGiaoDienAboutMe(JPanel con) {
		con.setLayout(new BorderLayout());
		JPanel pnTitle = new JPanel();

		con.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setPreferredSize(new Dimension(0, 100));

		pnTitle.setLayout(new FlowLayout());
		JLabel lblTitle = new JLabel("PHẦN MỀM QUẢN LÝ CỦA NHÓM 8 KỸ NGHỆ PHẦN MỀM");
		lblTitle.setFont(new Font("ARIA", Font.BOLD, 32));
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);

		JPanel pnCenter = new JPanel();
		JPanel pnDong1 = new JPanel();
		pnDong1.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		JLabel lblGioiThieu = new JLabel("Nhóm Gồm 10 Thành Viên:");
		pnDong1.setPreferredSize(new Dimension(0, 30));
		lblGioiThieu.setFont(new Font("ARIA", Font.BOLD, 20));
		lblGioiThieu.setForeground(Color.RED);
		pnDong1.add(lblGioiThieu);
		pnCenter.add(pnDong1);

		
		Font fon = new Font("Aria", Font.BOLD, 15);
		JPanel pnDong2 = new JPanel();
		
		JPanel pnDong3 = new JPanel();

		JPanel pnDong4 = new JPanel();
		JPanel pnDong5 = new JPanel();
		JPanel pnDong6 = new JPanel();
		JPanel pnDong7 = new JPanel();

		JPanel pnDong8 = new JPanel();
		JPanel pnDong9 = new JPanel();
		JPanel pnDong10 = new JPanel();
		JPanel pnDong11 = new JPanel();
		
		pnDong2.setLayout(new FlowLayout());
		pnDong3.setLayout(new FlowLayout());
		pnDong4.setLayout(new FlowLayout());
		pnDong5.setLayout(new FlowLayout());
		pnDong6.setLayout(new FlowLayout());
		pnDong7.setLayout(new FlowLayout());
		pnDong8.setLayout(new FlowLayout());
		pnDong9.setLayout(new FlowLayout());
		pnDong10.setLayout(new FlowLayout());
		pnDong11.setLayout(new FlowLayout());
		pnCenter.add(pnDong2);
		pnCenter.add(pnDong3);
		pnCenter.add(pnDong4);
		pnCenter.add(pnDong5);
		pnCenter.add(pnDong6);
		pnCenter.add(pnDong7);
		pnCenter.add(pnDong8);
		pnCenter.add(pnDong9);
		pnCenter.add(pnDong10);
		pnCenter.add(pnDong11);
		
		JLabel lblTen1 = new JLabel("Nhóm Trưởng: Hồ Phước Trí");
		JLabel lblTen2 = new JLabel("Hoàng Ngọc Quốc Dũng");
		JLabel lblTen3 = new JLabel("Nguyễn Ích Nhất Nguyên");
		JLabel lblTen4 = new JLabel("Hoàng Thanh Tâm");
		JLabel lblTen5 = new JLabel("Nguyễn Đắc Thành Long");
		JLabel lblTen6 = new JLabel("Phạm Lê Đăng Khoa");
		JLabel lblTen7 = new JLabel("Lê Thị Tường Vi");
		JLabel lblTen8 = new JLabel("Nguyễn Đại Tuấn");
		JLabel lblTen9 = new JLabel("Lê Minh Tiến");
		JLabel lblTen10 = new JLabel("Lê Văn Hành");
		pnDong2.add(lblTen1);
		pnDong3.add(lblTen2);
		pnDong4.add(lblTen3);
		pnDong5.add(lblTen4);
		pnDong6.add(lblTen5);
		pnDong7.add(lblTen6);
		pnDong8.add(lblTen7);
		pnDong9.add(lblTen8);
		pnDong10.add(lblTen9);
		pnDong11.add(lblTen10);
		lblTen1.setFont(fon);
		lblTen2.setFont(fon);
		lblTen3.setFont(fon);
		lblTen4.setFont(fon);
		lblTen5.setFont(fon);
		lblTen6.setFont(fon);
		lblTen7.setFont(fon);
		lblTen8.setFont(fon);
		lblTen9.setFont(fon);
		lblTen10.setFont(fon);
		
		lblTen1.setForeground(Color.BLUE);
		lblTen2.setForeground(Color.BLUE);
		lblTen3.setForeground(Color.BLUE);
		lblTen4.setForeground(Color.BLUE);
		lblTen5.setForeground(Color.BLUE);
		lblTen6.setForeground(Color.BLUE);
		lblTen7.setForeground(Color.BLUE);
		lblTen8.setForeground(Color.BLUE);
		lblTen9.setForeground(Color.BLUE);
		lblTen10.setForeground(Color.BLUE);
	
		
	}

	JTextField txtNhapTuKhoa;
	DefaultTableModel dtmListOrder;
	JTable tblListOrder;
	JComboBox<HangXe> cmbChonLoaiXe;
	JComboBox<KhachHang> cmbChonKhachHang;

	JLabel lblHienThiSoHoaDon, lblHienThiNgayLapDon, lblHienThiTenKhachHang, lblHienThiDiaChi, lblHienThiTongTien;
	JTextField txtGhiChu, txtTienKhachTra;
	JButton btnHuy, btnThanhToan;

	DefaultTableModel dtmHoaDon;
	JTable tblHoaDon;

	JComboBox<NhanVien> cmbnvLapHoaDon;

	private void hienThiGiaoDienHoaDon(JPanel con) {
		con.setLayout(new BorderLayout());
		JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblTitle.setFont(new Font("Aria", Font.BOLD, 25));
		lblTitle.setForeground(Color.BLUE);

		JPanel pnNorth = new JPanel();
		con.add(pnNorth, BorderLayout.NORTH);
		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnNorth.add(lblTitle);

		JPanel pnLeft = new JPanel();
		JPanel pnRight = new JPanel();
		pnLeft.setPreferredSize(new Dimension(300, 0));
		JSplitPane spl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
		con.add(spl, BorderLayout.CENTER);

		pnLeft.setLayout(new BorderLayout());
		JPanel pnNhapTuKhoa = new JPanel();

		pnNhapTuKhoa.setLayout(new BoxLayout(pnNhapTuKhoa, BoxLayout.Y_AXIS));
		pnLeft.add(pnNhapTuKhoa, BorderLayout.NORTH);

		JLabel lblNhapTuKhoa = new JLabel("Nhập Từ Khóa:");
		txtNhapTuKhoa = new JTextField(25);

		JPanel pnDong11 = new JPanel();
		pnDong11.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnDong11.add(lblNhapTuKhoa);
		JPanel pnDong12 = new JPanel();
		pnDong12.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnDong12.setLayout(new FlowLayout());
		pnDong12.add(txtNhapTuKhoa);
		pnNhapTuKhoa.add(pnDong11);
		pnNhapTuKhoa.add(pnDong12);
		pnDong11.setPreferredSize(new Dimension(0, 30));
		pnDong12.setPreferredSize(new Dimension(0, 50));

		JPanel pnDong13 = new JPanel();
		pnDong13.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblChonLoaiSanPham = new JLabel("Chọn Loại Xe:");
		pnDong13.add(lblChonLoaiSanPham);
		pnNhapTuKhoa.add(pnDong13);
		pnDong13.setPreferredSize(new Dimension(0, 30));

		JPanel pnDong14 = new JPanel();
		pnDong14.setLayout(new FlowLayout(FlowLayout.LEFT));
		cmbChonLoaiXe = new JComboBox<HangXe>(model);
		pnDong14.add(cmbChonLoaiXe);
		pnNhapTuKhoa.add(pnDong14);

		dtmListOrder = new DefaultTableModel();
		dtmListOrder.addColumn("Mã");
		dtmListOrder.addColumn("Tên Xe");
		dtmListOrder.addColumn("Số Lượng");
		dtmListOrder.addColumn("Giá bán");
		tblListOrder = new JTable(dtmListOrder);

		JScrollPane scListOrder = new JScrollPane(tblListOrder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeft.add(scListOrder, BorderLayout.CENTER);
		HangXe temp = (HangXe) cmbChonLoaiXe.getSelectedItem();
		layDuLieuXeDuaVaoOrder(temp.getMaHangXe());

		Border borderListOrder = BorderFactory.createLineBorder(Color.blue);
		TitledBorder titleListOrder = new TitledBorder(borderListOrder, "Quản Lý Sản Phẩm");
		titleListOrder.setTitleColor(Color.RED);
		pnLeft.setBorder(titleListOrder);

		pnRight.setLayout(new BorderLayout());
		JPanel pnNorthofRight = new JPanel();
		pnRight.add(pnNorthofRight, BorderLayout.NORTH);

		pnNorthofRight.setLayout(new BoxLayout(pnNorthofRight, BoxLayout.Y_AXIS));

		JPanel pnDong1 = new JPanel();
		pnDong1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnNorthofRight.add(pnDong1);

		JPanel pnDong1Left = new JPanel();
		JPanel pnDong1Right = new JPanel();
		pnDong1.setLayout(new GridLayout(1, 2));
		pnDong1.add(pnDong1Left);
		pnDong1.add(pnDong1Right);

		pnDong1Left.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoHoaDon = new JLabel("Số HD:");
		pnDong1Left.add(lblSoHoaDon);
		lblHienThiSoHoaDon = new JLabel();
		pnDong1Left.add(lblHienThiSoHoaDon);

		pnDong1Right.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNgayLapDon = new JLabel("Ngày Lập:");
		pnDong1Right.add(lblNgayLapDon);
		lblHienThiNgayLapDon = new JLabel();
		pnDong1Right.add(lblHienThiNgayLapDon);

		JPanel pnDong2 = new JPanel();
		pnDong2.setLayout(new GridLayout(1, 2));
		pnNorthofRight.add(pnDong2);
		JLabel lblTenKhachHang = new JLabel("Khách Hàng:");
		JPanel pndong21 = new JPanel();
		JPanel pndong22 = new JPanel();
		pndong21.setLayout(new FlowLayout(FlowLayout.LEFT));
		pndong22.setLayout(new FlowLayout());
		pndong21.add(lblTenKhachHang);
		lblHienThiTenKhachHang = new JLabel();
		pndong21.add(lblHienThiTenKhachHang);
		pnDong2.add(pndong21);
		pnDong2.add(pndong22);
		JLabel lblChonKhachHang = new JLabel("Chọn Khách Hàng:");
		pndong22.add(lblChonKhachHang);
		cmbChonKhachHang = new JComboBox<KhachHang>(dsKhachHang);
		pndong22.add(cmbChonKhachHang);

		JPanel pnDong3 = new JPanel();
		pnNorthofRight.add(pnDong3);
		pnDong3.setLayout(new GridLayout(1, 2));
		JPanel pnDong31 = new JPanel();
		JPanel pnDong32 = new JPanel();
		pnDong31.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnDong32.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnDong3.add(pnDong31);
		pnDong3.add(pnDong32);
		
		
		

		JLabel lblDiaChi = new JLabel("Địa Chỉ: ");
		pnDong31.add(lblDiaChi);
		lblHienThiDiaChi = new JLabel();
		pnDong31.add(lblHienThiDiaChi);

		JLabel lblNhanVien = new JLabel("Nhân Viên:");
		cmbnvLapHoaDon = new JComboBox<NhanVien>(dsNhanVien);
		pnDong32.add(lblNhanVien);
		pnDong32.add(cmbnvLapHoaDon);
		
		
		

		lblHienThiDiaChi.setForeground(Color.blue);
		lblHienThiNgayLapDon.setForeground(Color.blue);
		lblHienThiTenKhachHang.setForeground(Color.blue);
		lblHienThiSoHoaDon.setForeground(Color.blue);

		JPanel pnDong4 = new JPanel();
		pnNorthofRight.add(pnDong4);

		pnDong4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGhiChu = new JLabel("Ghi Chú: ");
		txtGhiChu = new JTextField(50);
		pnDong4.add(lblGhiChu);
		pnDong4.add(txtGhiChu);
		
		
		

		dtmHoaDon = new DefaultTableModel();
		dtmHoaDon.addColumn("TT");
		dtmHoaDon.addColumn("Tên Sản Phẩm");
		dtmHoaDon.addColumn("Số Lượng");
		dtmHoaDon.addColumn("Giá Bán");
		dtmHoaDon.addColumn("Thành Tiền");
		dtmHoaDon.addColumn("Ghi Chú");
		tblHoaDon = new JTable(dtmHoaDon);

		JScrollPane scHoaDon = new JScrollPane(tblHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnRight.add(scHoaDon, BorderLayout.CENTER);

		JPanel pnSouthOfRight = new JPanel();
		pnRight.add(pnSouthOfRight, BorderLayout.SOUTH);

		pnSouthOfRight.setLayout(new BoxLayout(pnSouthOfRight, BoxLayout.Y_AXIS));
		pnSouthOfRight.setPreferredSize(new Dimension(0, 150));
		JPanel pnSouthofRightDong1 = new JPanel();
		pnSouthOfRight.add(pnSouthofRightDong1);
		pnSouthofRightDong1.setLayout(new GridLayout(1, 2));
		JPanel pnSouthofRightDong11 = new JPanel();
		JPanel pnSouthofRightDong12 = new JPanel();
		pnSouthofRightDong11.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		pnSouthofRightDong11.add(lblTongTien);
		lblHienThiTongTien = new JLabel();
		lblHienThiTongTien.setForeground(Color.BLUE);
		pnSouthofRightDong11.add(lblHienThiTongTien);
		pnSouthofRightDong1.add(pnSouthofRightDong11);
		pnSouthofRightDong1.add(pnSouthofRightDong12);

		pnSouthofRightDong12.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblSoTienKhachTra = new JLabel("Số Tiền Khách Trả: ");
		txtTienKhachTra = new JTextField(20);
		pnSouthofRightDong12.add(lblSoTienKhachTra);
		pnSouthofRightDong12.add(txtTienKhachTra);

		JPanel pnButtonXuLy = new JPanel();
		pnButtonXuLy.setLayout(new FlowLayout());
		btnHuy = new JButton("HỦY");
		btnThanhToan = new JButton("THANH TOÁN");
		pnButtonXuLy.add(btnHuy);
		pnButtonXuLy.add(btnThanhToan);

		pnSouthOfRight.add(pnButtonXuLy);
		KhachHang khselect = new KhachHang();
		khselect = (KhachHang) cmbChonKhachHang.getSelectedItem();
		lblHienThiDiaChi.setText(khselect.getDiaChi());
		lblHienThiTenKhachHang.setText(khselect.getTenKhachHang());

		btnThanhToan.setIcon(new ImageIcon("Images/thanhtoan.png"));
		dateNow=new java.util.Date();
		
		lblHienThiNgayLapDon.setText(sdf.format(dateNow));
		btnHuy.setIcon(new ImageIcon("Images/huy.jpg"));

		SoHoaDonRandom = rd.nextInt(0, 100000000);
		boolean kiemTraTrungHoaDon = hoaDonService.isTrungHD(lblHienThiSoHoaDon.getText());

		if (kiemTraTrungHoaDon != true) {
			lblHienThiSoHoaDon.setText("HD" + SoHoaDonRandom);

		}
	}

	Vector<Xe> listOrder = null;

	private void layDuLieuXeDuaVaoOrder(String MaHangXe) {
		if (duLieuXeSQL == null)
			duLieuXeSQL = new XeService();

		listOrder = duLieuXeSQL.layDuLieuXe(MaHangXe);
		dtmListOrder.setRowCount(0);
		for (Xe x : listOrder) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(x.getMaXe());
			vec.add(x.getTenXe());
			vec.add(x.getSoLuongConLai());
			vec.add(x.getGiaBan());
			dtmListOrder.addRow(vec);
		}
	}

	DefaultMutableTreeNode root;
	JTree treeKhachHang;

	private void HienThiKhachHangTrenCay() {
		if (khachHangService == null) {
			khachHangService = new KhachHangService();
			dsKhachHang = khachHangService.getData();
		}

		root.removeAllChildren();
		for (KhachHang x : dsKhachHang) {
			DefaultMutableTreeNode nodeKH = new DefaultMutableTreeNode(x);
			root.add(nodeKH);

		}

		treeKhachHang.expandRow(0);
	}

	JButton btnThemNV, btnXoaNV, btnSuaNV;
	DefaultTableModel dtmNV;
	JTable tblNV;
	JTextField txtMaNV, txtTenNV, txtDiaChiNV, txtSoDienThoaiNV;
	JComboBox cmbGioiTinh;
	JFormattedTextField txtNgaySinhNV, txtNgayVaoLam;

	private void hienThiGiaoDienNhanVien(JPanel pnNhanVien) {
		pnNhanVien.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		JPanel pnCenter = new JPanel();
		JPanel pnSouth = new JPanel();

		pnNhanVien.add(pnCenter, BorderLayout.CENTER);
		pnNhanVien.add(pnNorth, BorderLayout.NORTH);
		pnNhanVien.add(pnSouth, BorderLayout.SOUTH);

		pnNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setFont(new Font("ARIA", Font.BOLD, 25));
		lblTitle.setForeground(Color.BLUE);
		pnNorth.add(lblTitle);

		dtmNV = new DefaultTableModel();
		dtmNV.addColumn("Mã Nhân Viên");
		dtmNV.addColumn("Tên Nhân Viên");
		dtmNV.addColumn("Ngày Sinh");
		dtmNV.addColumn("Giới Tính");
		dtmNV.addColumn("Ngày Vào Làm");
		dtmNV.addColumn("Địa Chỉ");
		dtmNV.addColumn("Số Điện Thoại");
		tblNV = new JTable(dtmNV);

		JScrollPane sc = new JScrollPane(tblNV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnNhanVien.add(sc, BorderLayout.CENTER);

		layDuLieuNhanVien();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));

		// txtMaNV, txtTenNV,txtNgaySinhNV, txtGioiTinhNV, txtNgayVaoLam, txtDiaChiNV,
		// txtSoDienThoaiNV;
		JPanel pnMaNhanVien = new JPanel();
		pnMaNhanVien.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnMaNhanVien);
		JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên:");
		txtMaNV = new JTextField(25);
		pnMaNhanVien.add(lblMaNhanVien);
		pnMaNhanVien.add(txtMaNV);

		JPanel pnTenNhanVien = new JPanel();
		pnTenNhanVien.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnTenNhanVien);
		JLabel lblTenNhanVien = new JLabel("Tên Nhân Viên:");
		txtTenNV = new JTextField(25);
		pnTenNhanVien.add(lblTenNhanVien);
		pnTenNhanVien.add(txtTenNV);

		JPanel pnNS = new JPanel();
		pnNS.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnNS);
		JLabel lblns = new JLabel("Ngày Sinh:");
		MaskFormatter df = null;
		try {
			df = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtNgaySinhNV = new JFormattedTextField(df);

		pnNS.add(lblns);
		pnNS.add(txtNgaySinhNV);

		JPanel pnGioiTinhNV = new JPanel();
		pnGioiTinhNV.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnGioiTinhNV);
		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		String[] gt = new String[] { "Nam", "Nữ" };
		cmbGioiTinh = new JComboBox(gt);
		pnGioiTinhNV.add(lblGioiTinh);
		pnGioiTinhNV.add(cmbGioiTinh);

		JPanel pnnvl = new JPanel();
		pnnvl.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnnvl);
		JLabel lblnvl = new JLabel("Ngày Vào Làm:");
		txtNgayVaoLam = new JFormattedTextField(df);
		pnnvl.add(lblnvl);
		pnnvl.add(txtNgayVaoLam);

		JPanel pnDC = new JPanel();
		pnDC.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnDC);
		JLabel lbldc = new JLabel("Địa Chỉ:");
		txtDiaChiNV = new JTextField(25);
		pnDC.add(lbldc);
		pnDC.add(txtDiaChiNV);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnSouth.add(pnSDT);
		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		txtSoDienThoaiNV = new JTextField(25);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSoDienThoaiNV);

		Dimension de = new Dimension(lblTenNhanVien.getPreferredSize());
		lbldc.setPreferredSize(de);
		lblMaNhanVien.setPreferredSize(de);
		lblGioiTinh.setPreferredSize(de);
		lblSDT.setPreferredSize(de);
		lblns.setPreferredSize(de);
		lblnvl.setPreferredSize(de);

		JPanel pnButtonXuLyThemXoaSua = new JPanel();
		pnButtonXuLyThemXoaSua.setLayout(new FlowLayout());
		pnSouth.add(pnButtonXuLyThemXoaSua);

		btnThemNV = new JButton("THÊM");
		btnXoaNV = new JButton("XÓA");
		btnSuaNV = new JButton("SỬA");
		pnButtonXuLyThemXoaSua.add(btnThemNV);
		pnButtonXuLyThemXoaSua.add(btnXoaNV);
		pnButtonXuLyThemXoaSua.add(btnSuaNV);

		btnThemNV.setIcon(new ImageIcon("Images/them.jpg"));
		btnXoaNV.setIcon(new ImageIcon("Images/xoa.png"));
		btnSuaNV.setIcon(new ImageIcon("Images/edit.png"));
		pnNorth.setPreferredSize(new Dimension(0, 70));

	}

	JButton btnThemHangXe, btnXoaHangXe;
	JTextField txtTenHangXe, txtMaHangXe;
	DefaultTableModel dtmHangXe;
	JTable tblHangXe;

	private void hienThiGiaoDienHangXe(JPanel con) {
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));
		con.add(pnNorth, BorderLayout.NORTH);
		
		
		JPanel pnTitle = new JPanel();
		pnNorth.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setLayout(new FlowLayout());
		JLabel lblTitle = new JLabel("QUẢN LÝ HÃNG XE");
		lblTitle.setFont(new Font("Aria", Font.BOLD, 25));
		lblTitle.setForeground(Color.blue);
		pnTitle.add(lblTitle);
		pnTitle.setPreferredSize(new Dimension(0, 100));
		
		JPanel pnMaHangXe = new JPanel();
		pnMaHangXe.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaHangXe = new JLabel("Mã Hãng Xe:");
		txtMaHangXe = new JTextField(25);
		pnMaHangXe.add(lblMaHangXe);
		pnMaHangXe.add(txtMaHangXe);
		pnNorth.add(pnMaHangXe);
		
		
		JPanel pnTenHangXe = new JPanel();
		pnTenHangXe.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenHangXe = new JLabel("Tên Hãng Xe:");
		txtTenHangXe = new JTextField(25);
		pnTenHangXe.add(lblTenHangXe);
		pnTenHangXe.add(txtTenHangXe);
		pnNorth.add(pnTenHangXe);
		
		lblMaHangXe.setPreferredSize(new Dimension(lblTenHangXe.getPreferredSize()));
		btnThemHangXe = new JButton("Thêm");
	
		
		pnTenHangXe.setPreferredSize(new Dimension(0, 40));

		JPanel pnbutton = new JPanel();
		pnbutton.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnbutton.add(btnThemHangXe);
		pnNorth.add(pnbutton);
		
		Border borderHangXe = BorderFactory.createLineBorder(Color.red);
		TitledBorder titleHangXe = new TitledBorder(borderHangXe, "Thêm Hãng Xe");
		titleHangXe.setTitleColor(Color.BLUE);
		pnNorth.setBorder(titleHangXe);;
		
		dtmHangXe = new DefaultTableModel();
		dtmHangXe.addColumn("STT");
		dtmHangXe.addColumn("Mã Hãng Xe");
		dtmHangXe.addColumn("Tên Hãng Xe");
		tblHangXe = new JTable(dtmHangXe);
		JScrollPane pnHangXe = new JScrollPane(tblHangXe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		con.add(pnHangXe, BorderLayout.CENTER);
		
		Border borderThongTin = BorderFactory.createLineBorder(Color.red);
		TitledBorder titleThongTin = new TitledBorder(borderThongTin, "Thông Tin Hãng Xe");
		titleThongTin.setTitleColor(Color.BLUE);
		pnHangXe.setBorder(titleThongTin);;
		hienThiDuLieuHangXe();

	}

	Vector<HangXe> dshx;
	private void hienThiDuLieuHangXe() {
		if(duLieuHangXeSQL == null)
			duLieuHangXeSQL = new HangXeService();
		
		dtmHangXe.setRowCount(0);
		dshx = duLieuHangXeSQL.layDuLieuHangXe();
		
		int dem =1;
		for (HangXe x : dshx)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(dem + "");
			vec.add(x.getMaHangXe());
			vec.add(x.getTenHangXe());
			
			dtmHangXe.addRow(vec);
			dem++;
			
		}
		
	}

	private void layDuLieuKhachHang() {
		dtmKhachHang.setRowCount(0);
		dsKhachHang = khachHangService.getData();

		for (KhachHang x : dsKhachHang) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(x.getMaKhachHang());
			vec.add(x.getTenKhachHang());
			vec.add(x.getDiaChi());
			vec.add(x.getSoDienThoai());
			vec.add(x.getEmail());
			vec.add(x.getTenLoaiKhach());
			dtmKhachHang.addRow(vec);

		}

	}

	Vector<NhanVien> dsNhanVien = null;
	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private void layDuLieuNhanVien() {
		dsNhanVien = nhanVienService.getData();
		dtmNV.setRowCount(0);

		for (NhanVien x : dsNhanVien) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(x.getMaNhanVien());
			vec.add(x.getTenNhanVien());
			vec.add(sdf.format(x.getNgaySinh()));
			vec.add(x.getGioiTinh());
			vec.add(sdf.format(x.getNgayVaoLam()));
			vec.add(x.getDiaChi());
			vec.add(x.getSoDienThoai());

			dtmNV.addRow(vec);
		}
	}

	private void layDuLieuHangXe() {
		model = new DefaultComboBoxModel<HangXe>();
		dsHangXe = duLieuHangXeSQL.layDuLieuHangXe();

		for (HangXe x : dsHangXe) {
			model.addElement(x);
		}
	}

	Vector<PhanLoaiKhachHang> dsPhanLoai;

	private void layDuLieuLoaiKhachHang() {
		modelLoaiKhachHang = new DefaultComboBoxModel<PhanLoaiKhachHang>();
		dsPhanLoai = phanLoaiKhachHangService.getData();
		for (PhanLoaiKhachHang X : dsPhanLoai) {
			modelLoaiKhachHang.addElement(X);
		}

	}

	private void HienThiDuLieuXe() {
		dtm.setRowCount(0);
		dsXe = duLieuXeSQL.layDuLieuXe();
		for (Xe x : dsXe) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(x.getMaXe());
			vec.add(x.getTenXe());
			vec.add(x.getTenHangXe());
			vec.add(x.getNamSanXuat());
			vec.add(x.getTrongLuong());
			vec.add(x.getSoLuongConLai());
			vec.add(x.getGiaBan());
			dtm.addRow(vec);
		}
	}

	public void ShowWindows() {
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
