//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.parsing.utils.StringSequence;
import java.text.ParseException;

public final class Vion {
    public static VionObject parseObject(String string) throws ParseException {
        VionParser vionParser = parserFor(string);
        VionObject result = vionParser.parseObject();
        vionParser.expectEnd();
        return result;
    }

    public static String parseString(String string) throws ParseException {
        VionParser vionParser = parserFor(string);
        String result = vionParser.parseString();
        vionParser.expectEnd();
        return result;
    }

    private static VionParser parserFor(String string) throws ParseException {
        return new VionParser(new StringSequence(string));
    }

    private Vion() {
        throw new AssertionError("Utility class");
    }
}
