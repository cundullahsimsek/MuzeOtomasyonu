package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class YoneticiLogin extends JFrame {
	static Yonetici yonetici = new Yonetici();

	private JPanel contentPane;
	private JTextField fld_ad;
	private JTextField fld_Tc;
	private JTextField textField_3;
	private JTextField fld_Soyad;
	private JTextField fld_Id;
	private JTable table_Personel;
	private JTextField fld_MuzeAdi;
	private JTable table_Muze;

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
	 */
	public YoneticiLogin(Yonetici yonetici) {
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(87, 127, 138, 20);
		w_personel.add(textField_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Soyad\u0131");
		lblNewLabel_1_1_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(10, 52, 46, 14);
		w_personel.add(lblNewLabel_1_1_2);
		
		fld_Soyad = new JTextField();
		fld_Soyad.setColumns(10);
		fld_Soyad.setBounds(87, 47, 138, 20);
		w_personel.add(fld_Soyad);
		
		JButton btnPersonelKaydet1 = new JButton("Kaydet");
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
		
		JButton brnPersonelSil = new JButton("Sil");
		brnPersonelSil.setBounds(10, 267, 215, 23);
		w_personel.add(brnPersonelSil);
		
		JScrollPane w_scrollPersonel = new JScrollPane();
		w_scrollPersonel.setBounds(257, 11, 452, 325);
		w_personel.add(w_scrollPersonel);
		
		table_Personel = new JTable();
		w_scrollPersonel.setViewportView(table_Personel);
		
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
}
