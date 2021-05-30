package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Muze;
import Model.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class YoneticiLogin extends JFrame {
	static Yonetici yonetici = new Yonetici();
	Muze muze = new Muze();
	private JPanel contentPane;
	private JTextField fld_ad;
	private JTextField fld_Tc;
	private JTextField fld_sifre;
	private JTextField fld_soyad;
	private JTextField fld_Id;
	private JTable table_Personel;
	private JTextField fld_MuzeAdi;
	private JTable table_muze;
	private DefaultTableModel personelModel = null;
	private Object[] personelData = null;
	private DefaultTableModel muzeModel = null;
	private Object[] muzeData = null;
	private JPopupMenu muzeMenu;
	private JTable table_calisan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiLogin frame = new YoneticiLogin(yonetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param yonetici
	 * @throws SQLException
	 */
	// Personel Model
	public YoneticiLogin(Yonetici yonetici) throws SQLException {
		setResizable(false);
		setTitle("M\u00FCze Otomasyonu");
		personelModel = new DefaultTableModel();
		Object[] colPersonelName = new Object[5];
		colPersonelName[0] = "ID";
		colPersonelName[1] = "TC No";
		colPersonelName[2] = "Adý";
		colPersonelName[3] = "Soyadý";
		colPersonelName[4] = "Þifre";
		personelModel.setColumnIdentifiers(colPersonelName);
		personelData = new Object[5];
		for (int i = 0; i < yonetici.getPersonelList().size(); i++) {
			personelData[0] = yonetici.getPersonelList().get(i).getId();
			personelData[1] = yonetici.getPersonelList().get(i).getTcno();
			personelData[2] = yonetici.getPersonelList().get(i).getAdi();
			personelData[3] = yonetici.getPersonelList().get(i).getSoyadi();
			personelData[4] = yonetici.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
		// Muze Model
		muzeModel = new DefaultTableModel();
		Object[] colMuze = new Object[2];
		colMuze[0] = "ID";
		colMuze[1] = "Muze Adý";

		muzeModel.setColumnIdentifiers(colMuze);
		muzeData = new Object[2];
		for (int i = 0; i < muze.getList().size(); i++) {
			muzeData[0] = muze.getList().get(i).getId();
			muzeData[1] = muze.getList().get(i).getAdi();
			muzeModel.addRow(muzeData);
		}
		// Çalýþan Model
		DefaultTableModel  calisanModel = new DefaultTableModel();
		Object[] colCalisan = new Object [3];
		colCalisan[0] = "ID";
		colCalisan[1] = "Adý";
		colCalisan[2] = "Soyadý";
		calisanModel.setColumnIdentifiers(colCalisan);		
		Object[] calisanData = new Object[3];

		setIconImage(Toolkit.getDefaultToolkit().getImage(YoneticiLogin.class.getResource("/View/LogoMuze.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn :" + yonetici.getAdi() + ' ' + yonetici.getSoyadi());
		lblNewLabel.setBounds(20, 11, 235, 21);
		lblNewLabel.setFont(new Font("Constantia", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);

		JButton btnCýkýs = new JButton("Oturumu Kapat");
		btnCýkýs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnCýkýs.setBounds(593, 10, 141, 23);
		btnCýkýs.setFont(new Font("Constantia", Font.PLAIN, 13));
		contentPane.add(btnCýkýs);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 85, 724, 375);
		contentPane.add(tabbedPane);

		JPanel w_personel = new JPanel();
		w_personel.setBackground(Color.WHITE);
		tabbedPane.addTab("Personel Yönetimi", null, w_personel, null);
		w_personel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad\u0131");
		lblNewLabel_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		w_personel.add(lblNewLabel_1);

		fld_ad = new JTextField();
		fld_ad.setBounds(87, 6, 138, 20);
		w_personel.add(fld_ad);
		fld_ad.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 93, 46, 14);
		w_personel.add(lblNewLabel_1_1);

		fld_Tc = new JTextField();
		fld_Tc.setColumns(10);
		fld_Tc.setBounds(87, 88, 138, 20);
		w_personel.add(fld_Tc);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(10, 132, 46, 14);
		w_personel.add(lblNewLabel_1_1_1);

		fld_sifre = new JTextField();
		fld_sifre.setColumns(10);
		fld_sifre.setBounds(87, 127, 138, 20);
		w_personel.add(fld_sifre);

		JLabel lblNewLabel_1_1_2 = new JLabel("Soyadý");
		lblNewLabel_1_1_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(10, 52, 46, 14);
		w_personel.add(lblNewLabel_1_1_2);

		fld_soyad = new JTextField();
		fld_soyad.setColumns(10);
		fld_soyad.setBounds(87, 47, 138, 20);
		w_personel.add(fld_soyad);

		JButton btnPersonelKaydet1 = new JButton("Kaydet");
		btnPersonelKaydet1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_ad.getText().length() == 0 || fld_soyad.getText().length() == 0
						|| fld_Tc.getText().length() == 0 || fld_sifre.getText().length() == 0 ) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = yonetici.addPersonel(fld_Tc.getText(), fld_ad.getText(), fld_soyad.getText(),
								fld_sifre.getText());
						if (control) {
							Helper.showMsg("Success");
							fld_ad.setText(null);
							fld_soyad.setText(null);
							fld_Tc.setText(null);
							fld_sifre.setText(null);
							updatePersonelModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnPersonelKaydet1.setBounds(10, 202, 215, 23);
		w_personel.add(btnPersonelKaydet1);

		fld_Id = new JTextField();
		fld_Id.setColumns(10);
		fld_Id.setBounds(87, 261, 138, 20);
		w_personel.add(fld_Id);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullanýcý ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 266, 78, 14);
		w_personel.add(lblNewLabel_1_1_1_1);

		JButton btnPersonelSil = new JButton("Sil");
		btnPersonelSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_Id.getText().length() == 0) {
					Helper.showMsg("Lütfen bir Personel Seçiniz!");

				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_Id.getText());
						try {
							boolean control = yonetici.deletePersonel(selectID);
							if (control) {
								Helper.showMsg("Success");
								fld_Id.setText(null);
								updatePersonelModel();

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
		btnPersonelSil.setBounds(10, 297, 215, 23);
		w_personel.add(btnPersonelSil);

		JScrollPane w_scrollPersonel = new JScrollPane();
		w_scrollPersonel.setBounds(257, 11, 452, 325);
		w_personel.add(w_scrollPersonel);

		table_Personel = new JTable(personelModel);
		w_scrollPersonel.setViewportView(table_Personel);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("T\u00FCr\u00FC");
		lblNewLabel_1_1_1_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_1_2.setBounds(10, 165, 46, 14);
		w_personel.add(lblNewLabel_1_1_1_2);
		
		JComboBox fld_turu = new JComboBox();
		fld_turu.setModel(new DefaultComboBoxModel(new String[] {"", "yonetici", "Gise_Personeli"}));
		fld_turu.setBounds(87, 158, 138, 22);
		w_personel.add(fld_turu);
		table_Personel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent ex) {

				try {
					fld_Id.setText(table_Personel.getValueAt(table_Personel.getSelectedRow(), 0).toString());

				} catch (Exception e) {

				}
			}

		});
		table_Personel.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer
							.parseInt(table_Personel.getValueAt(table_Personel.getSelectedRow(), 0).toString());
					String selecttcno = table_Personel.getValueAt(table_Personel.getSelectedRow(), 3).toString();
					String selectadi = table_Personel.getValueAt(table_Personel.getSelectedRow(), 1).toString();
					String selectsoyadi = table_Personel.getValueAt(table_Personel.getSelectedRow(), 2).toString();
					String selectsifre = table_Personel.getValueAt(table_Personel.getSelectedRow(), 4).toString();

					try {
						boolean control = yonetici.updatePersonel(selectedID, selectadi, selectsoyadi, selecttcno,
								selectsifre);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		JPanel w_muze = new JPanel();
		w_muze.setBackground(Color.WHITE);
		tabbedPane.addTab("Müze Yönetimi", null, w_muze, null);
		w_muze.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("M\u00FCze Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(295, 11, 124, 14);
		w_muze.add(lblNewLabel_1_2);

		fld_MuzeAdi = new JTextField();
		fld_MuzeAdi.setColumns(10);
		fld_MuzeAdi.setBounds(295, 35, 138, 20);
		w_muze.add(fld_MuzeAdi);

		JScrollPane w_scrollMuze = new JScrollPane();
		w_scrollMuze.setBounds(10, 8, 259, 325);
		w_muze.add(w_scrollMuze);

		muzeMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		muzeMenu.add(updateMenu);
		muzeMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_muze.getValueAt(table_muze.getSelectedRow(), 0).toString());
				Muze selectMuze = muze.getFetch(selID);
				UpdateMuze update = new UpdateMuze(selectMuze);
				update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				update.setVisible(true);
				update.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateMuzeModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_muze.getValueAt(table_muze.getSelectedRow(), 0).toString());
					try {
						if (muze.deleteMuze(selID)) {
							Helper.showMsg("Success");
							updateMuzeModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		table_muze = new JTable(muzeModel);
		table_muze.setComponentPopupMenu(muzeMenu);
		table_muze.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_muze.rowAtPoint(point);
				table_muze.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		w_scrollMuze.setViewportView(table_muze);

		JButton btnMuzeEkle = new JButton("M\u00FCze Kaydet");
		btnMuzeEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_MuzeAdi.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (muze.addMuze(fld_MuzeAdi.getText())) {
							Helper.showMsg("Success");
							fld_MuzeAdi.setText(null);
							updateMuzeModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnMuzeEkle.setBounds(295, 75, 138, 23);
		w_muze.add(btnMuzeEkle);

		JScrollPane w_scrolCalisan = new JScrollPane();
		w_scrolCalisan.setBounds(450, 11, 260, 325);
		w_muze.add(w_scrolCalisan);
		
		table_calisan = new JTable();
		w_scrolCalisan.setViewportView(table_calisan);
		
		
		
		JComboBox muzeSec = new JComboBox();
		muzeSec.setBounds(295, 249, 138, 31);
		for (int i = 0 ; i<yonetici.getPersonelList().size() ; i++ ) {
			 muzeSec.addItem(new Item(yonetici.getPersonelList().get(i).getId(), yonetici.getPersonelList().get(i).getAdi()));
		}
		muzeSec.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_muze.add(muzeSec);
		
		JButton btn_calisanEkle = new JButton("Ekle");
		btn_calisanEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_muze.getSelectedRow();
				if (selRow>= 0 ) {
					String selMuze = table_muze.getModel().getValueAt(selRow, 0).toString();
					int selMuzeID = Integer.parseInt(selMuze);
					Item personelItem = (Item) muzeSec.getSelectedItem();
				try {
					boolean control = yonetici.addcalisan(personelItem.getKey() , selMuzeID);
					if (control) {
						Helper.showMsg("Success");
						DefaultTableModel  clearModel = (DefaultTableModel) table_calisan.getModel();
						clearModel.setRowCount(0);
						for (int i=0; i< yonetici.getMuzePersonelList(selMuzeID).size();i++) {
							calisanData[0]= yonetici.getMuzePersonelList(selMuzeID).get(i).getId();
							calisanData[1]= yonetici.getMuzePersonelList(selMuzeID).get(i).getAdi();
							calisanData[2]= yonetici.getMuzePersonelList(selMuzeID).get(i).getSoyadi();
							calisanModel.addRow(calisanData);
						}
						table_calisan.setModel(calisanModel);
					}else {
						Helper.showMsg("error");
					}
				}catch (SQLException e1){
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg("Lütfen bir müze seçiniz!");
				}
			}
		});
		btn_calisanEkle.setBounds(295, 297, 138, 23);
		w_muze.add(btn_calisanEkle);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("M\u00FCze Ad\u0131");
		lblNewLabel_1_2_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(295, 132, 124, 14);
		w_muze.add(lblNewLabel_1_2_1);
		
		JButton btn_calisanSec = new JButton("Se\u00E7");
		btn_calisanSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_muze.getSelectedRow();
				if(selRow>=0) {
					String selMuze = table_muze.getModel().getValueAt(selRow, 0).toString();
					int selMuzeID = Integer.parseInt(selMuze);
					DefaultTableModel  clearModel = (DefaultTableModel) table_calisan.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int i=0; i< yonetici.getMuzePersonelList(selMuzeID).size();i++) {
							calisanData[0]= yonetici.getMuzePersonelList(selMuzeID).get(i).getId();
							calisanData[1]= yonetici.getMuzePersonelList(selMuzeID).get(i).getAdi();
							calisanData[2]= yonetici.getMuzePersonelList(selMuzeID).get(i).getSoyadi();
							calisanModel.addRow(calisanData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_calisan.setModel(calisanModel);
				}else {
					Helper.showMsg("Lütfen bir müze seçinizi !");
				}
			}
		});
		btn_calisanSec.setBounds(295, 157, 138, 23);
		w_muze.add(btn_calisanSec);
	}

	public void updatePersonelModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_Personel.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < yonetici.getPersonelList().size(); i++) {
			personelData[0] = yonetici.getPersonelList().get(i).getId();
			personelData[1] = yonetici.getPersonelList().get(i).getTcno();
			personelData[2] = yonetici.getPersonelList().get(i).getAdi();
			personelData[3] = yonetici.getPersonelList().get(i).getSoyadi();
			personelData[4] = yonetici.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
	}

	public void updateMuzeModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_muze.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < muze.getList().size(); i++) {
			muzeData[0] = muze.getList().get(i).getId();
			muzeData[1] = muze.getList().get(i).getAdi();
			muzeModel.addRow(muzeData);
		}
	}
}
