package org.clickatell.unicode;

/**
 * License LGPL v3
 */
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UnicodeUtilsNGTest {

    @Test
    public void testConvertToUnicode() {
        String stringToConvert = "Un bel \u00e9t\u00e9 au mois d'ao\u00fbt.";
        String unicodeValue = "0055006e002000620065006c002000e9007400e90020006100750020006d006f006900730020006400270061006f00fb0074002e";

        Assert.assertEquals(UnicodeUtils.convertToUnicode(stringToConvert), unicodeValue);
    }

    @DataProvider(name = "letters")
    public Object[][] createData1() {
        return new Object[][]{
                    {"\u00e9", "00e9"},
                    {"a", "0061"},
                    {"\u00e8", "00e8"},
                    {"\u00e0", "00e0"},
                    {"\u00f4", "00f4"},
                    {"\u00f9", "00f9"},
                    };
    }

    @Test(dataProvider = "letters")
    public void testGetUnicodeValueOfALetter(String letter, String unicode) {
        Assert.assertEquals(UnicodeUtils.getUnicodeValueOfALetter(letter), unicode);
    }

    @Test
    public void toArrayOfLetter(){
        String string = "foo bar";
        String[] array = new String[]{"f","o","o"," ","b","a","r"};

        Assert.assertEquals(UnicodeUtils.toArrayOfLetter(string), array);

    }
}
