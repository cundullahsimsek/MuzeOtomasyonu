package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Kullanici;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class KullaniciLogin extends JFrame {

	private JPanel contentPane;
	private static Kullanici kullanici = new Kullanici();
	private JTable table_Whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciLogin frame = new KullaniciLogin(kullanici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public KullaniciLogin(Kullanici kullanici) throws SQLException {
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object [2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for(int i= 0; i< kullanici.getWhourList(kullanici.getId()).size(); i++) {
			whourData[0]= kullanici.getWhourList(kullanici.getId()).get(i).getId();
			whourData[1]= kullanici.getWhourList(kullanici.getId()).get(i).getTarih();
			whourModel.addRow(whourData);
		}
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(KullaniciLogin.class.getResource("/View/LogoMuze.png")));
		setTitle("Müze Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn :" + kullanici.getAdi() + ' ' + kullanici.getSoyadi());
		lblNewLabel.setBounds(10, 12, 235, 21);
		lblNewLabel.setFont(new Font("Constantia", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);
		
		JButton btnCýkýs = new JButton("Oturumu Kapat");
		btnCýkýs.setBounds(583, 11, 141, 23);
		btnCýkýs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnCýkýs.setFont(new Font("Constantia", Font.PLAIN, 13));
		contentPane.add(btnCýkýs);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 85, 724, 375);
		contentPane.add(w_tab);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, panel, null);
		panel.setLayout(null);
		
		JDateChooser tarih_sec = new JDateChooser();
		tarih_sec.setBounds(10, 11, 130, 20);
		panel.add(tarih_sec);
		
		JComboBox saat_sec = new JComboBox();
		saat_sec.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00"}));
		saat_sec.setBounds(150, 11, 60, 20);
		panel.add(saat_sec);
		
		JButton btn_ = new JButton("Seç");
		btn_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				String date = "";
				try {
					 date = sdf.format(tarih_sec.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (date.length()==0) {
					Helper.showMsg("Lütfen tarih seçiniz !");
				}
				else {
					String time = " " + saat_sec.getSelectedItem().toString() + ":00";
					String tarihSec = date+time;
					try {
						boolean control = kullanici.addWhour(kullanici.getId(),kullanici.getAdi(),tarihSec);
						if (control) {
							Helper.showMsg("Success");
							updateWhourModel(kullanici);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		btn_.setBounds(231, 11, 89, 22);
		panel.add(btn_);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 699, 281);
		panel.add(scrollPane);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		scrollPane.setViewportView(w_scrollWhour);
		
		table_Whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_Whour);
		
		JButton btn_silWhour = new JButton("Sil");
		btn_silWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_Whour.getSelectedRow();
				if (selRow >= 0) {
					String selectRow= table_Whour.getModel().getValueAt(selRow,0).toString();
					int selID =Integer.parseInt(selectRow);
					boolean control;
					try {
						control = kullanici.deleteWhour(selID);
						if (control) {
							Helper.showMsg("Success");
							updateWhourModel(kullanici);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg(" Lütfen bir tarih seçiniz !");
				}
			}
		});
		btn_silWhour.setBounds(607, 11, 89, 22);
		panel.add(btn_silWhour);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_tab.addTab("Bilet Satýþ", null, tabbedPane, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		w_tab.addTab("Stok Takip", null, tabbedPane_1, null);
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		w_tab.addTab("Rapor", null, tabbedPane_1, null);
	}
	public void updateWhourModel(Kullanici kullanici) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_Whour.getModel();
		clearModel.setRowCount(0);
		for(int i= 0; i< kullanici.getWhourList(kullanici.getId()).size(); i++) {
			whourData[0]= kullanici.getWhourList(kullanici.getId()).get(i).getId();
			whourData[1]= kullanici.getWhourList(kullanici.getId()).get(i).getTarih();
			whourModel.addRow(whourData);
		}
	}
}
