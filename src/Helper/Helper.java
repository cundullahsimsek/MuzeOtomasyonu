package Helper;

import javax.swing.JOptionPane;

public class Helper {
	
	public static void showMsg(String str) {
		
		String msg;
		
		switch (str) {
		case "fill":
			msg = "T.C Numarasý veya Þifre Boþ Geçilemez!";
			break;
		default:
				msg=str;
		}
		JOptionPane.showMessageDialog(null, msg, "Uyarý", JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
