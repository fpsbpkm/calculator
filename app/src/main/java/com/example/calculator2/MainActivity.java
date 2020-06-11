package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    // 計算式を保存する変数
    private String formula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        // ACボタンのイベント設定
        Button buttonAC = findViewById(R.id.buttonAC);
        buttonAC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textView.setText("0");
                formula = "";
            }
        });

        // 演算子ボタンのイベント設定
        Button buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new ButtonListener(buttonPlus));

        Button buttonMinus = findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(new ButtonListener(buttonMinus));

        Button buttonMultiplication = findViewById(R.id.buttonMultiplication);
        buttonMultiplication.setOnClickListener(new ButtonListener(buttonMultiplication));

        Button buttonDivision = findViewById(R.id.buttonDivision);
        buttonDivision.setOnClickListener(new ButtonListener(buttonDivision));

        // equalボタンのイベント
        Button buttonEqual = findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scope = context.initStandardObjects();
                try {
                    Object result =
                            context.evaluateString(scope, formula, "<cmd>", 1, null);
                    textView.setText(result.toString());
                    formula = result.toString();
                }catch(Exception e){
                    textView.setText("エラー");
                }
            }
        });

        // 小数点ボタンのイベント
        Button buttonPoint = findViewById(R.id.buttonPoint);
        buttonPoint.setOnClickListener(new ButtonListener(buttonPoint));

        // 数字ボタンのイベント
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(new ButtonListener(button0));

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new ButtonListener(button1));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new ButtonListener(button2));

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new ButtonListener(button3));

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new ButtonListener(button4));

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new ButtonListener(button5));

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new ButtonListener(button6));

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new ButtonListener(button7));

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new ButtonListener(button8));

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new ButtonListener(button9));

        // かっこボタンのイベント設定
        Button buttonLeftPar = findViewById(R.id.buttonLeftPar);
        buttonLeftPar.setOnClickListener(new ButtonListener(buttonLeftPar));

        Button buttonRightPar = findViewById(R.id.buttonRightPar);
        buttonRightPar.setOnClickListener(new ButtonListener(buttonRightPar));

        // Deleteボタンのイベント設定
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formula.length() > 1){
                    formula = formula.substring(0, formula.length()-1);
                    textView.setText(formula);
                }
                else{
                    formula = "";
                    textView.setText("0");
                }
            }
        });
    }

    class ButtonListener implements View.OnClickListener{
        Button button;
        public ButtonListener(Button button){
            this.button = button;
        }
        @Override
        public void onClick(View v) {
            formula = formula + button.getText();
            textView.setText(formula);
        }
    }
}
