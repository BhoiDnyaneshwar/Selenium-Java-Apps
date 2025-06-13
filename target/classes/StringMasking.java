import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringMasking {

	
	public static void main(String[] args) throw IOException {
		MaskUtil mskUtil=new MaskUtil();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		String key="";
		String message="";
		try {
			key maskUtil.getSaltString();
			System.out.print("Enter String to Mask: ");
			message =reader.readLine();
			String maskedString = maskUtil.maskString(message, key);
			String configString = "<maskedString key=\"keyString\" value=\"" + key + "\"/>" + "\n<maskedString key=\"maskedString\" value=\"" + maskedString + "\"/>";
			StringSelection selection = new StringSelection(configString);
			clipboard.setContents(selection, selection);
			System.out.println("Masked string was copied to the clipboard");
			} catch (IOException e) {

			throw new IOException("Problem with reading user input");

			}
		
	}
}
