package com.example.numericalcodesofkryon;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Alphabet alphabet;

    private EditText inputText;
    private ImageButton searchButton;
    private TextView answer1;
    private TextView answer2;

    private RadioButton russianAlphabet;
    private RadioButton kryonAlphabet;
    private RadioGroup alphabetSwitch;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Находим элементы разметки
        inputText = findViewById(R.id.input_text);
        searchButton = findViewById(R.id.search_button);
        russianAlphabet = findViewById(R.id.radio_russian);
        kryonAlphabet = findViewById(R.id.radio_kryon);
//         получаем объект RadioGroup
        alphabetSwitch = findViewById(R.id.alphabet_switch);
        alphabetSwitch.setOnCheckedChangeListener((radiogroup, id)-> {
            // получаем выбранную кнопку
            RadioButton radio = findViewById(id);
            if(radio.getId() == R.id.radio_russian) {
                alphabet = Alphabet.RUSSIAN_ALPHABET;
            }
            if (radio.getId() == R.id.radio_kryon) {
                alphabet = Alphabet.KRYON_ALPHABET;
            } else {
                alphabet = Alphabet.RUSSIAN_ALPHABET;
            }
        });
        alphabetSwitch.check(R.id.radio_kryon);
        alphabetSwitch.check(R.id.radio_russian);

        inputText.setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                searchButton.callOnClick();
                return true;
            }
            return false;
        });

// Получаем экземпляр элемента Spinner
//        spinner = findViewById(R.id.spinner);
// Настраиваем адаптер
//        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.alphabets, R.layout.spinner);
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

// Вызываем адаптер
//        spinner.setAdapter(adapter);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        searchButton.setOnClickListener(view -> {
            String originalText = this.inputText.getText().toString();
            String inputText = originalText.replaceAll("\\s", "");

            //Если ввели число, то переводим его в текстовый формат и выводим число
            if (NumberUtils.isDigits(inputText)) {
                BigInteger inputNumber = new BigInteger(inputText);
                inputText = Converter.numberToText(inputNumber);
                extracted1(inputText);
                return;
            }
            BigInteger inputTextNumber = Converter.textToNumber(inputText, alphabet);
            String convertedTextToStringNumber = Converter.textToNumberString(inputText, alphabet);
            String convertedNumberToText = "";
            if (convertedTextToStringNumber.isEmpty()) {
                answer1.setText("");
                answer2.setText("");
                return;
            }

            BigInteger convertedTextToNumber = new BigInteger(convertedTextToStringNumber);
//            if (convertedTextToNumber.compareTo(new BigInteger("1000000000000000000")) != -1) {
//                answer1.setText(convertedTextToStringNumber);
//                answer2.setText("Слишком большое число, не могу перевести в текст.");
//                return;
//            }
            convertedNumberToText = Converter.numberToText(convertedTextToNumber);
            BigInteger firstNumber = Converter.textToNumber(convertedNumberToText, alphabet);
            String firstNumberInText = Converter.numberToText(firstNumber);
            BigInteger secondNumber = Converter.textToNumber(firstNumberInText, alphabet);

            BigInteger thirdNumber = firstNumber.add(inputTextNumber);
            String thirdNumberInText = Converter.numberToText(thirdNumber);
            BigInteger fourthNumber = Converter.textToNumber(thirdNumberInText, alphabet);

            String resultString1 = "";
            String resultString2 = "";
            if (firstNumber.compareTo(BigInteger.ZERO) != 0) {
                resultString1 = String.format("[%s] -> %d-%d", originalText, firstNumber, secondNumber);
                resultString2 = String.format("%s +> %d-%d", originalText, thirdNumber, fourthNumber);
            }
            answer1.setText(resultString1);
            answer2.setText(resultString2);

        });
    }

    private void extracted1(String text) {
        BigInteger firstNumber = Converter.textToNumber(text, alphabet);//Переводим буквы в цифры и складываем
        String firstNumberInText = Converter.numberToText(firstNumber);//полученное число преобразуем в текстовый формат
        BigInteger secondNumber = Converter.textToNumber(firstNumberInText, alphabet);//Переводим буквы в цифры и складываем
        String resultString1 = "";
        String resultString2 = "";
        if (firstNumber.compareTo(BigInteger.ZERO) != 0) {
            resultString1 = String.format("[%s] -> %d-%d", text, firstNumber, secondNumber);
        }
        answer1.setText(resultString1);
        answer2.setText(resultString2);
    }
}