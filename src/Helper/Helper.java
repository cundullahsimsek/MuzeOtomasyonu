package Helper;

import javax.swing.JOptionPane;

public class Helper {
	
	public static void showMsg(String str) {
		
		String msg;
		
		switch (str) {
		case "fill":
			msg = "T.C Numaras� veya �ifre Bo� Ge�ilemez!";
			break;
		default:
				msg=str;
		}
		JOptionPane.showMessageDialog(null, msg, "Uyar�", JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
