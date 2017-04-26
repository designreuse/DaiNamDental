package com.datawings.app.common;

public class MailUtil {
	public static String getMailContent(String journee) {
		StringBuffer text = new StringBuffer("");			
		text.append("Bonjour,");
		text.append("\r\n\r\n");
		text.append("Veuillez trouvez ci-joint le fichier Sage de la journ\u00e9e du " + journee);
		text.append("\r\n\r\n");
		text.append("Cordialement,");
		text.append("\r\n\r\n");
		text.append("---------------------------");
		text.append("\r\n\r\n");
		text.append("DataWings France");
		text.append("\r\n\r\n");
		text.append("Service production");
		text.append("\r\n\r\n");
		text.append("Email : production@data-wings.com");
		text.append("\r\n\r\n");
		text.append("www.data-wings.com");		
        return text.toString();
    }
	public static String getMailContentAno(String journee) {
		StringBuffer text = new StringBuffer("");			
		text.append("Bonjour,");
		text.append("\r\n\r\n");
		text.append("Ci-joint le fichier Ano de la journ\u00e9e du " + journee);
		text.append("\r\n\r\n");
		text.append("Bonne r\u00e9ception,");
		text.append("\r\n\r\n");
		text.append("---------------------------");
		text.append("\r\n\r\n");
		text.append("DataWings France");
		text.append("\r\n\r\n");
		text.append("Service production");
		text.append("\r\n\r\n");
		text.append("Email : production@data-wings.com");
		text.append("\r\n\r\n");
		text.append("www.data-wings.com");		
        return text.toString();
    }
}
