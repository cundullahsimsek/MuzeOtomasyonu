package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Muze;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateMuze extends JFrame {

	private JPanel contentPane;
	private JTextField fld_muzeAdi;
	private static Muze muze;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMuze frame = new UpdateMuze(muze);
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
	public UpdateMuze(Muze muze) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("M\u00FCze Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Constantia", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 11, 124, 14);
		contentPane.add(lblNewLabel_1_2);
		
		fld_muzeAdi = new JTextField();
		fld_muzeAdi.setColumns(10);
		fld_muzeAdi.setBounds(10, 35, 189, 20);
		fld_muzeAdi.setText(muze.getAdi());
		contentPane.add(fld_muzeAdi);
		
		JButton btn_updateMuze = new JButton("D\u00FCzenle");
		btn_updateMuze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					try {
						muze.updateMuze(muze.getId(),fld_muzeAdi.getText());
						Helper.showMsg("Success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateMuze.setBounds(10, 66, 189, 23);
		contentPane.add(btn_updateMuze);
	}
}
