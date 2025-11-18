package com.example.numericalcodesofkryon;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.math.BigInteger;
import java.text.FieldPosition;
import java.util.Locale;

public class NumberToWords {

    private static final RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);

    /**
     *
     * @param number число для перевода в слова
     * @return словесное написание числа
     */
    public static String numberToWords(BigInteger number, Scale scale) {
        if (number.compareTo(BigInteger.valueOf(1000000000000000000L)) < 0) {
            return nf.format(number);
        }
        return numberToWords(new StringBuilder(number.toString()), scale);//переводим число в StringBuffer и отправляем
    }

    /**
     *
     * @param number число для перевода в слова
     * @return словесное написание числа
     */
    public static String numberToWords(long number, Scale scale) {
        if (number < 1000000000000000000L) {
            return nf.format(number);
        }
        return numberToWords(new StringBuilder(String.valueOf(number)), scale);//переводим число в StringBuffer и отправляем
    }

    /**
     *
     * @param number число для перевода в слова
     * @return словесное написание числа
     */
    public static String numberToWords(int number, Scale scale) {
        return nf.format(number);
    }

    /**
     *
     * @param numberStringBuffer число для перевода в слова
     * @return словесное написание числа
     */
    private static String numberToWords(StringBuilder numberStringBuffer, Scale scale) {
        StringBuffer resultStringBuffer = new StringBuffer();//здесь будем собирать слова
        int length = numberStringBuffer.length();
        int orderIndex = length / 3;
        int remain = length % 3;
        if (remain == 0) {
            orderIndex--;
            remain = 3;
        }
        if (remain == 1) {
            numberStringBuffer.insert(0, "00");
        }
        if (remain == 2) {
            numberStringBuffer.insert(0, "0");
        }

        String textOrder;
        String numberHeadString;
        while (orderIndex > 5) {//Пока выводимое число больше максимально поддерживаемого библиотекой icu4j собираем слова самостоятельно
            textOrder = scale.getName(orderIndex);
            numberHeadString = numberStringBuffer.substring(0, 3);
            if (Integer.parseInt(numberHeadString) != 0) {//если не "000",
                nf.format(Long.valueOf(numberHeadString), resultStringBuffer, new FieldPosition(0));//то выводим число с помощью библиотеки icu4j
                resultStringBuffer.append(" ").append(textOrder).append(getPostfix(numberHeadString)).append(" ");// и добавляем правильное окончание
            }
            numberStringBuffer.delete(0, 3);
            orderIndex--;
        }
        if (Long.parseLong(numberStringBuffer.toString()) != 0L) {
            nf.format(new BigInteger(numberStringBuffer.toString()), resultStringBuffer, new FieldPosition(0));
        }
        return resultStringBuffer.toString().trim();
    }

    /**
     *
     * @param numberHeadString строка из 3 символов, для определения окончания в названии разряда
     * @return окончание
     */
    private static String getPostfix(String numberHeadString) {
        String postfix;
        String lastDigit;
        String lastTwoDigits;
        postfix = "ов";
        lastDigit = numberHeadString.substring(2);
        lastTwoDigits = numberHeadString.substring(1);
        if (lastDigit.equals("1") && !lastTwoDigits.equals("11")) {
            postfix = "";//один миллиард, двадцать один миллиард, но одиннадцать миллиардОВ
        }
        if (lastDigit.equals("2") && !lastTwoDigits.equals("12")) {
            postfix = "а";//два миллиардА, двадцать два миллиардА, но двенадцать миллиардОВ
        }
        if (lastDigit.equals("3") && !lastTwoDigits.equals("13")) {
            postfix = "а";//три миллиардА, двадцать три миллиардА, но тринадцать миллиардОВ
        }
        if (lastDigit.equals("4") && !lastTwoDigits.equals("14")) {
            postfix = "а";//четыре миллиардА, двадцать четыре миллиардА, но четырнадцать миллиардОВ
        }
        return postfix;
    }
}