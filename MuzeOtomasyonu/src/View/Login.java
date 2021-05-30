package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Kullanici;
import Model.Yonetici;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel w_pane;
	private JTextField fld_YoneticiTc;
	private JTextField fld_GiseTc;
	private JPasswordField fld_YoneticiSifre;
	private JPasswordField fld_GiseSifre;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/View/LogoMuze.png")));
		setResizable(false);
		setTitle("Müze Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 463);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setForeground(Color.BLACK);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel LabelLogo = new JLabel(new ImageIcon(getClass().getResource("LogoMuze.png")));
		LabelLogo.setBounds(199, 27, 100, 101);
		w_pane.add(LabelLogo);
		
		JLabel lblNewLabel = new JLabel("M\u00FCze Otomasyonu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(187, 132, 123, 27);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 170, 474, 240);
		w_pane.add(w_tabpane);
		
		JPanel GiseLogin = new JPanel();
		GiseLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Giþe Personel Giriþi", null, GiseLogin, null);
		GiseLogin.setLayout(null);
		
		JButton btn_GiseGiris = new JButton("Giriþ Yap");
		btn_GiseGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (fld_GiseTc.getText().length()==0 || fld_GiseSifre.getText().length()==0) {
						Helper.showMsg("fill");
					}else {
					
						try {
							Connection con = conn.connDb();
							
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery ("SELECT * From kullanici");
							
							while (rs.next()) {
								if (fld_GiseTc.getText().equals(rs.getString("tcno"))&& fld_GiseSifre.getText().equals(rs.getString("sifre"))) {
								
									if (rs.getString("turu").equals("Gise_Personeli")) {
										Kullanici kullanici = new Kullanici();
										kullanici.setId(rs.getInt("id"));
										kullanici.setSifre("sifre");
										kullanici.setTcno(rs.getString("tcno"));
										kullanici.setAdi(rs.getString("adi"));
										kullanici.setSoyadi(rs.getString("soyadi"));
										kullanici.setTuru(rs.getString("turu"));
										KullaniciLogin klogin = new KullaniciLogin(kullanici);
										klogin.setVisible(true);
										dispose();
									}else {
										Helper.showMsg("Kullanýcý bulunamadý !");
									}
								
								
							}
								
							}
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}					
				}
			
		});
		btn_GiseGiris.setBounds(182, 127, 208, 32);
		GiseLogin.add(btn_GiseGiris);
		
		fld_GiseTc = new JTextField();
		fld_GiseTc.setColumns(10);
		fld_GiseTc.setBounds(182, 34, 208, 26);
		GiseLogin.add(fld_GiseTc);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaran\u0131z : ");
		lblTcNumaranz_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcNumaranz_1.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblTcNumaranz_1.setBounds(60, 36, 123, 27);
		GiseLogin.add(lblTcNumaranz_1);
		
		JLabel lblifre_1 = new JLabel("Þifre :");
		lblifre_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre_1.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblifre_1.setBounds(60, 72, 123, 27);
		GiseLogin.add(lblifre_1);
		
		fld_GiseSifre = new JPasswordField();
		fld_GiseSifre.setBounds(182, 71, 208, 26);
		GiseLogin.add(fld_GiseSifre);
		
		JPanel YoneticiLogin = new JPanel();
		YoneticiLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Yönetici Giriþi", null, YoneticiLogin, null);
		YoneticiLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranýz : ");
		lblTcNumaranz.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcNumaranz.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblTcNumaranz.setBounds(60, 36, 123, 27);
		YoneticiLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Þifre :");
		lblifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblifre.setBounds(60, 72, 123, 27);
		YoneticiLogin.add(lblifre);
		
		fld_YoneticiTc = new JTextField();
		fld_YoneticiTc.setBounds(182, 34, 208, 26);
		YoneticiLogin.add(fld_YoneticiTc);
		fld_YoneticiTc.setColumns(10);
		
		JButton btn_YoneticiGiris = new JButton("Giriþ Yap");
		btn_YoneticiGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_YoneticiTc.getText().length()==0 || fld_YoneticiSifre.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
				
					try {
						Connection con = conn.connDb();
						
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery ("SELECT * From kullanici");
						while (rs.next()) {
							if (fld_YoneticiTc.getText().equals(rs.getString("tcno"))&& fld_YoneticiSifre.getText().equals(rs.getString("sifre"))) {
								if(rs.getString("turu").equals("yonetici")) {
									Yonetici yonetici = new Yonetici();
									yonetici.setId(rs.getInt("id"));
									yonetici.setSifre("sifre");
									yonetici.setTcno(rs.getString("tcno"));
									yonetici.setAdi(rs.getString("adi"));
									yonetici.setSoyadi(rs.getString("soyadi"));
									yonetici.setTuru(rs.getString("turu"));
									YoneticiLogin yLogin = new YoneticiLogin(yonetici);
									yLogin.setVisible(true);
									dispose();
								}else {
									Helper.showMsg("Yetkisiz Kullanýcý veya Kullanýcý Bulunamadý !");
								}
								
							}
							
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}					
			}
		});
		btn_YoneticiGiris.setBounds(182, 127, 208, 32);
		YoneticiLogin.add(btn_YoneticiGiris);
		
		fld_YoneticiSifre = new JPasswordField();
		fld_YoneticiSifre.setBounds(182, 71, 208, 26);
		YoneticiLogin.add(fld_YoneticiSifre);
	}
}
