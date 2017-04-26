package com.datawings.app.common;

import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtilz {
	private static final int MIN = 192;
	private static final int MAX = 255;
	private static final List<String> map = initMap();

	public static String traiterAccents(String chaine) {
		StringBuffer result = new StringBuffer(chaine);

		for (int bcl = 0; bcl < result.length(); bcl++) {
			int carVal = chaine.charAt(bcl);
			if (carVal >= MIN && carVal <= MAX) { // Remplacement
				String newVal = map.get(carVal - MIN);
				result.replace(bcl, bcl + 1, newVal);
			}
		}

		return result.toString();
	}

	public static String normaliserString(String chaine) {
		String chaineSansAccent = traiterAccents(chaine);
		String chaineTraitee = chaineSansAccent.toUpperCase();
		chaineTraitee = chaineTraitee.replaceAll("[^a-zA-Z0-9]", "");
		chaineTraitee = chaineTraitee.replaceAll("^0*", "");
		return chaineTraitee;
	}
	
	public static String replaceMoney(String text) {
		String tmp = StringUtils.replace(text, ",", "");
		String rs = StringUtils.replace(tmp, ".", "");
		return rs;
	}
	

	public static void replaceTagInText(StringBuffer text, String tagName, String value) {
		if ((text == null) || (tagName == null)) {
			return;
		}

		if (value == null) {
			value = " ";
		}

		if (value.length() == 0) {
			value = " ";
		}

		if ((text.length() > 0) && (tagName.length() > 0) && (value.length() > 0)) {
			while (text.indexOf(tagName) > 0) {
				text.replace(text.indexOf(tagName), text.indexOf(tagName) + tagName.length(), value);
			}
		}
	}
	
	public static String encodeString(String input, String algorithme)
			throws NoSuchAlgorithmException {
		byte[] hash = MessageDigest.getInstance("MD5").digest(input.getBytes());
		StringBuffer hashString = new StringBuffer();

		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}

			else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}

		return hashString.toString();

	}
	
	public static boolean isSignedNumeric(String value) {
		String valueToTest = null;
		if (value == null || value.equals(""))
			return false;
		if (value.charAt(0) == '-') {
			valueToTest = value.substring(1);
		} else {
			valueToTest = value;
		}
		return StringUtils.isNumeric(valueToTest);
	}

	public static boolean isNumbericString(String str) {
		// /Check input string is only numberic string
		String regex1 = "\\d+\\.\\d+";
		String regex2 = "\\d+";
		if ((str.matches(regex1) || str.matches(regex2))) {
			return true;
		}
		return false;
	}

	public static boolean isSignedDecimal(String value, int nbDecimal) {
		String pattern = "^-?[0-9]+";
		if (nbDecimal < 0) {
			throw new InvalidParameterException();
		} else if (nbDecimal == 0) {
			pattern += "$";
		} else {
			pattern += "\\.[0-9]{" + nbDecimal + "}?";
		}
		if (!Pattern.matches(pattern, value)) {
			return false;
		}
		return true;
	}

	private static List<String> initMap() {
		List<String> result = new ArrayList<String>();
		String car = null;

		car = new String("A");
		result.add(car); /* '\u00C0' � alt-0192 */
		result.add(car); /* '\u00C1' �? alt-0193 */
		result.add(car); /* '\u00C2' � alt-0194 */
		result.add(car); /* '\u00C3' � alt-0195 */
		result.add(car); /* '\u00C4' � alt-0196 */
		result.add(car); /* '\u00C5' � alt-0197 */
		car = new String("AE");
		result.add(car); /* '\u00C6' � alt-0198 */
		car = new String("C");
		result.add(car); /* '\u00C7' � alt-0199 */
		car = new String("E");
		result.add(car); /* '\u00C8' � alt-0200 */
		result.add(car); /* '\u00C9' � alt-0201 */
		result.add(car); /* '\u00CA' � alt-0202 */
		result.add(car); /* '\u00CB' � alt-0203 */
		car = new String("I");
		result.add(car); /* '\u00CC' � alt-0204 */
		result.add(car); /* '\u00CD' �? alt-0205 */
		result.add(car); /* '\u00CE' � alt-0206 */
		result.add(car); /* '\u00CF' �? alt-0207 */
		car = new String("D");
		result.add(car); /* '\u00D0' �? alt-0208 */
		car = new String("N");
		result.add(car); /* '\u00D1' � alt-0209 */
		car = new String("O");
		result.add(car); /* '\u00D2' � alt-0210 */
		result.add(car); /* '\u00D3' � alt-0211 */
		result.add(car); /* '\u00D4' � alt-0212 */
		result.add(car); /* '\u00D5' � alt-0213 */
		result.add(car); /* '\u00D6' � alt-0214 */
		car = new String("*");
		result.add(car); /* '\u00D7' � alt-0215 */
		car = new String("0");
		result.add(car); /* '\u00D8' � alt-0216 */
		car = new String("U");
		result.add(car); /* '\u00D9' � alt-0217 */
		result.add(car); /* '\u00DA' � alt-0218 */
		result.add(car); /* '\u00DB' � alt-0219 */
		result.add(car); /* '\u00DC' � alt-0220 */
		car = new String("Y");
		result.add(car); /* '\u00DD' �? alt-0221 */
		car = new String("�");
		result.add(car); /* '\u00DE' � alt-0222 */
		car = new String("B");
		result.add(car); /* '\u00DF' � alt-0223 */
		car = new String("a");
		result.add(car); /* '\u00E0' � alt-0224 */
		result.add(car); /* '\u00E1' � alt-0225 */
		result.add(car); /* '\u00E2' � alt-0226 */
		result.add(car); /* '\u00E3' � alt-0227 */
		result.add(car); /* '\u00E4' � alt-0228 */
		result.add(car); /* '\u00E5' � alt-0229 */
		car = new String("ae");
		result.add(car); /* '\u00E6' � alt-0230 */
		car = new String("c");
		result.add(car); /* '\u00E7' � alt-0231 */
		car = new String("e");
		result.add(car); /* '\u00E8' � alt-0232 */
		result.add(car); /* '\u00E9' � alt-0233 */
		result.add(car); /* '\u00EA' � alt-0234 */
		result.add(car); /* '\u00EB' � alt-0235 */
		car = new String("i");
		result.add(car); /* '\u00EC' � alt-0236 */
		result.add(car); /* '\u00ED' � alt-0237 */
		result.add(car); /* '\u00EE' � alt-0238 */
		result.add(car); /* '\u00EF' � alt-0239 */
		car = new String("d");
		result.add(car); /* '\u00F0' � alt-0240 */
		car = new String("n");
		result.add(car); /* '\u00F1' � alt-0241 */
		car = new String("o");
		result.add(car); /* '\u00F2' � alt-0242 */
		result.add(car); /* '\u00F3' � alt-0243 */
		result.add(car); /* '\u00F4' � alt-0244 */
		result.add(car); /* '\u00F5' � alt-0245 */
		result.add(car); /* '\u00F6' � alt-0246 */
		car = new String("/");
		result.add(car); /* '\u00F7' � alt-0247 */
		car = new String("0");
		result.add(car); /* '\u00F8' � alt-0248 */
		car = new String("u");
		result.add(car); /* '\u00F9' � alt-0249 */
		result.add(car); /* '\u00FA' � alt-0250 */
		result.add(car); /* '\u00FB' � alt-0251 */
		result.add(car); /* '\u00FC' � alt-0252 */
		car = new String("y");
		result.add(car); /* '\u00FD' � alt-0253 */
		car = new String("�");
		result.add(car); /* '\u00FE' � alt-0254 */
		car = new String("y");
		result.add(car); /* '\u00FF' � alt-0255 */
		result.add(car); /* '\u00FF' alt-0255 */

		return result;
	}

}
