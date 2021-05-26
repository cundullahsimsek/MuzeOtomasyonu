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
		
		JButton btn_GiseGiris = new JButton("Giri\u015F Yap");
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
		
		JLabel lblifre_1 = new JLabel("\u015Eifre :");
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
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaran\u0131z : ");
		lblTcNumaranz.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcNumaranz.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblTcNumaranz.setBounds(62, 31, 123, 27);
		YoneticiLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("\u015Eifre :");
		lblifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 13));
		lblifre.setBounds(62, 70, 123, 27);
		YoneticiLogin.add(lblifre);
		
		fld_YoneticiTc = new JTextField();
		fld_YoneticiTc.setBounds(184, 32, 208, 26);
		YoneticiLogin.add(fld_YoneticiTc);
		fld_YoneticiTc.setColumns(10);
		
		JButton btn_YoneticiGiris = new JButton("Giri\u015F Yap");
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
							if (fld_YoneticiTc.getText().equals(rs.getString("tcno"))&& fld_YoneticiSifre.getText().equals(rs.getString("sifre")));
							
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
							
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}					
			}
		});
		btn_YoneticiGiris.setBounds(184, 125, 208, 32);
		YoneticiLogin.add(btn_YoneticiGiris);
		
		fld_YoneticiSifre = new JPasswordField();
		fld_YoneticiSifre.setBounds(184, 71, 208, 32);
		YoneticiLogin.add(fld_YoneticiSifre);
	}
}
