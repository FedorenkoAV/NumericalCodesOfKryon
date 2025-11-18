package com.example.numericalcodesofkryon;

import static java.util.Map.entry;

import java.util.Map;

public enum Alphabet {
    RUSSIAN_ALPHABET (Map.ofEntries(
    entry('а', 1),
    entry('б', 2),
    entry('в', 3),
    entry('г', 4),
    entry('д', 5),
    entry('е', 6),
    entry('ё', 7),
    entry('ж', 8),
    entry('з', 9),
    entry('и', 10),
    entry('й', 11),
    entry('к', 12),
    entry('л', 13),
    entry('м', 14),
    entry('н', 15),
    entry('о', 16),
    entry('п', 17),
    entry('р', 18),
    entry('с', 19),
    entry('т', 20),
    entry('у', 21),
    entry('ф', 22),
    entry('х', 23),
    entry('ц', 24),
    entry('ч', 25),
    entry('ш', 26),
    entry('щ', 27),
    entry('ъ', 28),
    entry('ы', 29),
    entry('ь', 30),
    entry('э', 31),
    entry('ю', 32),
    entry('я', 33)
    )),
    KRYON_ALPHABET (Map.ofEntries(
    entry('а', 1),
    entry('б', 2),
    entry('в', 3),
    entry('г', 4),
    entry('д', 5),
    entry('е', 6),
    entry('ё', 7),
    entry('ж', 8),
    entry('з', 9),
    entry('и', 10),
    entry('й', 11),
    entry('к', 12),
    entry('л', 13),
    entry('м', 14),
    entry('н', 15),
    entry('о', 16),
    entry('п', 17),
    entry('р', 18),
    entry('с', 19),
    entry('т', 20),
    entry('у', 21),
    entry('ф', 22),
    entry('х', 23),
    entry('ц', 24),
    entry('ч', 25),
    entry('ш', 26),
    entry('щ', 27),
    entry('ь', 28),
    entry('ы', 29),
    entry('ъ', 30),
    entry('э', 31),
    entry('ю', 32),
    entry('я', 33)
    ));
    private final Map<Character, Integer> alphabet;

    Alphabet(Map<Character, Integer> alphabet) {
        this.alphabet = alphabet;
    }

    public Integer getCharNum(char letter) {
        return alphabet.get(letter);
    }
}
