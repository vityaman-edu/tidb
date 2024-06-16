//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.parsing.utils.CharacterSequence;
import commons.vion.parsing.utils.ParsingSequence;
import commons.vion.parsing.utils.characters.CharacterTest;
import commons.vion.parsing.utils.characters.CharacterTests;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VionParser {
    protected final ParsingSequence sequence;

    public VionParser(CharacterSequence source) throws ParseException {
        this.sequence = new ParsingSequence(source);
    }

    void expectEnd() throws ParseException {
        this.sequence.skipWhitespaces();
        this.sequence.expectEnded();
    }

    public Object parseValue() throws ParseException {
        if (this.sequence.isCurrent(CharacterTests.in("-0123456789"))) {
            return this.parseNumber();
        } else if (this.sequence.isCurrent(CharacterTests.equalTo('"'))) {
            return this.parseString();
        } else if (this.sequence.isCurrent(CharacterTests.equalTo('['))) {
            return this.parseList();
        } else if (this.sequence.isCurrent(CharacterTests.equalTo('{'))) {
            return this.parseObject();
        } else {
            throw this.sequence.error("Value expected");
        }
    }

    public VionObject parseObject() throws ParseException {
        this.sequence.expect('{');
        this.sequence.skipWhitespaces();
        if (this.sequence.take(CharacterTests.equalTo('}'))) {
            return new MapBasedVionObject();
        } else {
            VionObject result = new MapBasedVionObject();
            Map.Entry<String, Object> member = this.parseMember();
            this.extend(result, member);

            while(this.sequence.take(CharacterTests.equalTo(','))) {
                member = this.parseMember();
                if (result.contains((String)member.getKey())) {
                    throw this.sequence.error(String.format("Duplicate key: '%s'", member.getKey()));
                }

                this.extend(result, member);
            }

            this.sequence.expect('}');
            return result;
        }
    }

    public String parseChars() throws ParseException {
        StringBuilder result = new StringBuilder();

        while(this.sequence.isCurrent(CharacterTests.any(new CharacterTest[]{CharacterTests.between('A', 'Z'), CharacterTests.between('a', 'z'), CharacterTests.between('0', '9'), CharacterTests.equalTo('_')}))) {
            result.append(this.sequence.take());
        }

        return result.toString();
    }

    Map.Entry<String, Object> parseMember() throws ParseException {
        this.sequence.skipWhitespaces();
        final String key = this.parseChars();
        this.sequence.skipWhitespaces();
        this.sequence.expect(':');
        this.sequence.skipWhitespaces();
        final Object value = this.parseValue();
        this.sequence.skipWhitespaces();
        return new Map.Entry<String, Object>() {
            public String getKey() {
                return key;
            }

            public Object getValue() {
                return value;
            }

            public Object setValue(Object o) {
                return null;
            }
        };
    }

    public List<Object> parseList() throws ParseException {
        this.sequence.expect('[');
        this.sequence.skipWhitespaces();
        if (this.sequence.take(CharacterTests.equalTo(']'))) {
            return new ArrayList();
        } else {
            List<Object> result = this.parseValues();
            this.sequence.expect(']');
            return result;
        }
    }

    public List<Object> parseValues() throws ParseException {
        List<Object> result = new ArrayList();
        this.sequence.skipWhitespaces();
        result.add(this.parseValue());
        this.sequence.skipWhitespaces();

        while(this.sequence.take(CharacterTests.equalTo(','))) {
            this.sequence.skipWhitespaces();
            result.add(this.parseValue());
            this.sequence.skipWhitespaces();
        }

        return result;
    }

    public Number parseNumber() throws ParseException {
        StringBuilder number = new StringBuilder();
        if (this.sequence.take(CharacterTests.equalTo('-'))) {
            number.append('-');
        }

        while(this.sequence.isCurrent(CharacterTests.in("0123456789"))) {
            number.append(this.sequence.take());
        }

        if (this.sequence.take(CharacterTests.equalTo('.'))) {
            number.append('.');

            while(this.sequence.isCurrent(CharacterTests.in("0123456789"))) {
                number.append(this.sequence.take());
            }
        }

        String result = number.toString();

        try {
            return Integer.valueOf(result);
        } catch (NumberFormatException var7) {
            try {
                return Long.valueOf(result);
            } catch (NumberFormatException var6) {
                try {
                    return Float.valueOf(result);
                } catch (NumberFormatException var5) {
                    try {
                        return Double.valueOf(result);
                    } catch (NumberFormatException var4) {
                        throw this.sequence.error("Invalid number");
                    }
                }
            }
        }
    }

    public String parseString() throws ParseException {
        this.sequence.expect('"');
        StringBuilder result = new StringBuilder();

        while(!this.sequence.take(CharacterTests.equalTo('"'))) {
            result.append(this.sequence.take());
        }

        return result.toString();
    }

    private void extend(VionObject vion, Map.Entry<String, Object> member) {
        String key = (String)member.getKey();
        Object value = member.getValue();
        if (value instanceof String) {
            vion.put(key, (String)value);
        } else if (value instanceof Number) {
            vion.put(key, (Number)value);
        } else {
            if (!(value instanceof VionObject)) {
                throw new IllegalArgumentException(String.format("Type %s is not supported", value.getClass()));
            }

            vion.put(key, (VionObject)value);
        }

    }
}
