package com.example.numericalcodesofkryon;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.math.BigInteger;
import java.util.Locale;

public class Converter {

//    private static Map<Character, Integer> ALPHABET;
//
//    private static final Map<Character, Integer> RUSSIAN_ALPHABET = Map.ofEntries(
//    entry('а', 1),
//    entry('б', 2),
//    entry('в', 3),
//    entry('г', 4),
//    entry('д', 5),
//    entry('е', 6),
//    entry('ё', 7),
//    entry('ж', 8),
//    entry('з', 9),
//    entry('и', 10),
//    entry('й', 11),
//    entry('к', 12),
//    entry('л', 13),
//    entry('м', 14),
//    entry('н', 15),
//    entry('о', 16),
//    entry('п', 17),
//    entry('р', 18),
//    entry('с', 19),
//    entry('т', 20),
//    entry('у', 21),
//    entry('ф', 22),
//    entry('х', 23),
//    entry('ц', 24),
//    entry('ч', 25),
//    entry('ш', 26),
//    entry('щ', 27),
//    entry('ъ', 28),
//    entry('ы', 29),
//    entry('ь', 30),
//    entry('э', 31),
//    entry('ю', 32),
//    entry('я', 33)
//    );
//
//    private static final Map<Character, Integer> KRYON_ALPHABET = Map.ofEntries(
//    entry('а', 1),
//    entry('б', 2),
//    entry('в', 3),
//    entry('г', 4),
//    entry('д', 5),
//    entry('е', 6),
//    entry('ё', 7),
//    entry('ж', 8),
//    entry('з', 9),
//    entry('и', 10),
//    entry('й', 11),
//    entry('к', 12),
//    entry('л', 13),
//    entry('м', 14),
//    entry('н', 15),
//    entry('о', 16),
//    entry('п', 17),
//    entry('р', 18),
//    entry('с', 19),
//    entry('т', 20),
//    entry('у', 21),
//    entry('ф', 22),
//    entry('х', 23),
//    entry('ц', 24),
//    entry('ч', 25),
//    entry('ш', 26),
//    entry('щ', 27),
//    entry('ь', 28),
//    entry('ы', 29),
//    entry('ъ', 30),
//    entry('э', 31),
//    entry('ю', 32),
//    entry('я', 33)
//    );
    public static long textToLong(String text, Alphabet alphabet) {
        char[] charArray = text.trim().toLowerCase().toCharArray();
        long result = 0;
        for (char c : charArray) {
            if (alphabet.getCharNum(c) != null) {
                result += alphabet.getCharNum(c);
            }
        }
        return result;
    }

    /**
     *
     * @param text текст для вычисления
     * @return BigInteger сумма всех чисел сопоставленных буквам из @param text
     */
    public static BigInteger textToNumber(String text, Alphabet alphabet) {
        char[] charArray = text.trim().toLowerCase().toCharArray();
        BigInteger result = BigInteger.ZERO;
        for (char c : charArray) {
            if (alphabet.getCharNum(c) != null) {
                result = result.add(new BigInteger(String.valueOf(alphabet.getCharNum(c))));
            }
        }
        return result;
    }

    /**
     *
     * @param text текст для вычисления
     * @return String состоящую из чисел сопоставленных буквам из @param
     */
    public static String textToNumberString(String text, Alphabet alphabet) {
        char[] charArray = text.trim().toLowerCase().toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : charArray) {
            if (alphabet.getCharNum(c) != null) {
                result.append(alphabet.getCharNum(c));
            }
        }
        return result.toString();
    }

    public static String longToText(long number) {
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        return nf.format(number);
    }

    /**
     *
     * @param number число для перевода в слова
     * @return словесное написание числа
     */
    public static String numberToText(BigInteger number) {
        return NumberToWords.numberToWords(number, Scale.SHORT_SCALE);
    }
}
