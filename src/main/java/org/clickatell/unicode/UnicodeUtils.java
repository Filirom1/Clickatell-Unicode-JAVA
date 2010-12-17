package org.clickatell.unicode;

/**
 * License LGPL v3
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>I create the class in order to use the <b>Clickatell API</b> with the <b>unicode</b> option set to true</p>
 * <p>I test this class with the utility given by Clickatell : <br />
 * <a href="https://www.clickatell.com/central/manage_products.php">https://www.clickatell.com/central/manage_products.php</a>
 * Converters -> Unicode</p>
 *
 * Works well for french words.
 */
public class UnicodeUtils {

    /**
     * Convert a String into unicode codes.
     * For exemple :
     * "Un bel été au mois d'août." ->
     * "0055006e002000620065006c002000a9007400a90020006100750020006d006f006900730020006400270061006f00bb0074002e"
     *
     * @param data
     * @return
     */
    public static String convertToUnicode(String data) {
        StringBuilder stringBuilder = new StringBuilder(4 * data.length());
        for (String letter : toArrayOfLetter(data)) {
            stringBuilder.append(getUnicodeValueOfALetter(letter));
        }
        return stringBuilder.toString();

    }

    /**
     * Return the unicode value of a letter.
     * For exemple :
     * "é" -> "00a9"
     * "a" -> "0061"
     * ...
     *
     * @param letter a String with size()==1 : so a letter.
     * @return the unicode code of this letter
     */
    public static String getUnicodeValueOfALetter(String letter) {
        if (letter.length() > 1) {
            throw new RuntimeException("This method only accept ONE letter.");
        }
        try {
            byte[] bytes = letter.getBytes("utf-8");
            byte b = (bytes.length == 1) ? bytes[0] : (byte)(bytes[1]+0x0040);
            return String.format("%04x", b);

        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * Split a String into an array of every letters.
     *
     * For exemple : "foo bar"
     * return
     * <pre>
     * 0 : "f"
     * 1 : "o"
     * 2 : "o"
     * 3 : " "
     * 4 : "b"
     * 5 : "a"
     * 6 : "r"
     * </pre>
     *
     * @param string the string to split.
     * @return an array of letters
     */
    public static String[] toArrayOfLetter(String string) {
        List<String> listChars = new ArrayList<String>();
        for (int i = 0; i < string.length(); i++) {
            String c = string.substring(i, i + 1);
            listChars.add(c);
        }
        return listChars.toArray(new String[string.length()]);
    }
}
