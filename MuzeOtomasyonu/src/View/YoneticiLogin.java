package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Yonetici;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper.*;

public class YoneticiLogin extends JFrame {
	static Yonetici yonetici = new Yonetici();

	private JPanel contentPane;
	private JTextField fld_ad;
	private JTextField fld_Tc;
	private JTextField fld_sifre;
	private JTextField fld_soyad;
	private JTextField fld_Id;
	private JTable table_Personel;
	private JTextField fld_MuzeAdi;
	private JTable table_Muze;
	private DefaultTableModel personelModel=null;
	private Object [] personelData =null;
	

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
	 * @param yonetici 
	 * @throws SQLException 
	 */
	public YoneticiLogin(Yonetici yonetici) throws SQLException {
		personelModel =new DefaultTableModel();
		Object[] colPersonelName = new Object[5];
		colPersonelName[0] = "ID";
		colPersonelName[1] = "TC No";
		colPersonelName[2] = "Adý";
		colPersonelName[3] = "Soyadý";
		colPersonelName[4] = "Þifre";
		personelModel.setColumnIdentifiers(colPersonelName);
		personelData = new Object[5];
		for (int i=0; i < yonetici.getPersonelList().size();i++) {
			personelData[0]= yonetici.getPersonelList().get(i).getId();
			personelData[1]= yonetici.getPersonelList().get(i).getTcno();
			personelData[2]= yonetici.getPersonelList().get(i).getAdi();
			personelData[3]= yonetici.getPersonelList().get(i).getSoyadi();
			personelData[4]= yonetici.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(YoneticiLogin.class.getResource("/View/LogoMuze.png")));
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n :");
		lblNewLabel.setBounds(20, 11, 235, 21);
		lblNewLabel.setFont(new Font("Constantia", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);
		
		JButton btnCýkýs = new JButton("Oturumu Kapat");
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
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Soyad\u0131");
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
				if(fld_ad.getText().length() ==0 || fld_soyad.getText().length()== 0 || fld_Tc.getText().length()==0 || fld_sifre.getText().length()==0 ) {
			Helper.showMsg("fill");
			}else  {
			 try {
				boolean control = yonetici.addPersonel(fld_Tc.getText(), fld_ad.getText(),fld_soyad.getText(),fld_sifre.getText());
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
		btnPersonelKaydet1.setBounds(10, 175, 215, 23);
		w_personel.add(btnPersonelKaydet1);
		
		fld_Id = new JTextField();
		fld_Id.setColumns(10);
		fld_Id.setBounds(87, 231, 138, 20);
		w_personel.add(fld_Id);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(10, 236, 78, 14);
		w_personel.add(lblNewLabel_1_1_1_1);
		
		JButton btnPersonelSil = new JButton("Sil");
		btnPersonelSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_Id.getText().length()==0 ) {
					Helper.showMsg("Lütfen bir Personel Seçiniz!");
					
				}else {
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
		btnPersonelSil.setBounds(10, 267, 215, 23);
		w_personel.add(btnPersonelSil);
		
		JScrollPane w_scrollPersonel = new JScrollPane();
		w_scrollPersonel.setBounds(257, 11, 452, 325);
		w_personel.add(w_scrollPersonel);
		
		table_Personel = new JTable(personelModel);
		w_scrollPersonel.setViewportView(table_Personel);
		table_Personel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent ex){
			
			try {
				fld_Id.setText(table_Personel.getValueAt(table_Personel.getSelectedRow(), 0 ) .toString());

			}catch (Exception e) {
				
			}
			}

			
		});
		table_Personel.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType()== TableModelEvent.UPDATE) {
					 int selectedID = Integer.parseInt(table_Personel.getValueAt(table_Personel.getSelectedRow(),0).toString());
					 String selecttcno=table_Personel.getValueAt(table_Personel.getSelectedRow(),3).toString();
					 String selectadi=table_Personel.getValueAt(table_Personel.getSelectedRow(),1).toString();
					 String selectsoyadi=table_Personel.getValueAt(table_Personel.getSelectedRow(),2).toString();
					 String selectsifre=table_Personel.getValueAt(table_Personel.getSelectedRow(),4).toString();
					 
					 try {
						 boolean control = yonetici.updatePersonel(selectedID, selectadi, selectsoyadi, selecttcno,selectsifre);
						 
					 }catch (SQLException e1) {
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
		lblNewLabel_1_2.setBounds(313, 11, 124, 14);
		w_muze.add(lblNewLabel_1_2);
		
		fld_MuzeAdi = new JTextField();
		fld_MuzeAdi.setColumns(10);
		fld_MuzeAdi.setBounds(313, 35, 138, 20);
		w_muze.add(fld_MuzeAdi);
		
		JScrollPane w_scrollMuze = new JScrollPane();
		w_scrollMuze.setBounds(10, 11, 279, 325);
		w_muze.add(w_scrollMuze);
		
		table_Muze = new JTable();
		w_scrollMuze.setViewportView(table_Muze);
		
		JButton btnMuzeEkle = new JButton("M\u00FCze Kaydet");
		btnMuzeEkle.setBounds(313, 75, 138, 23);
		w_muze.add(btnMuzeEkle);
	}
	public void updatePersonelModel() throws SQLException {
		DefaultTableModel clearModel =(DefaultTableModel) table_Personel.getModel();
		clearModel.setRowCount(0);
		for (int i=0; i < yonetici.getPersonelList().size();i++) {
			personelData[0]= yonetici.getPersonelList().get(i).getId();
			personelData[1]= yonetici.getPersonelList().get(i).getTcno();
			personelData[2]= yonetici.getPersonelList().get(i).getAdi();
			personelData[3]= yonetici.getPersonelList().get(i).getSoyadi();
			personelData[4]= yonetici.getPersonelList().get(i).getSifre();
			personelModel.addRow(personelData);
		}
	}
}
