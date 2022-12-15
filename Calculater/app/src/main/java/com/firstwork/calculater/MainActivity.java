package com.firstwork.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton Add,Sub,Mul,Div;
    MaterialButton C,AC,Dot,Open,Close,equ;
    MaterialButton Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.res);
        solution = findViewById(R.id.sol);

        assignid(C,R.id.button_c);
        assignid(equ,R.id.button_e);
        assignid(AC,R.id.button_ac);
        assignid(Dot,R.id.button__);
        assignid(Open,R.id.button_c1);
        assignid(Close,R.id.button_c2);
        assignid(Div,R.id.button_c3);
        assignid(Button0,R.id.button_0);
        assignid(Button1,R.id.button_1);
        assignid(Button2,R.id.button_2);
        assignid(Button3,R.id.button_3);
        assignid(Button4,R.id.button_4);
        assignid(Button5,R.id.button_5);
        assignid(Button6,R.id.button_6);
        assignid(Button7,R.id.button_7);
        assignid(Button8,R.id.button_8);
        assignid(Button9,R.id.button_9);
        assignid(Add,R.id.button_a);
        assignid(Sub,R.id.button_s);
        assignid(Mul,R.id.button_x);

    }

    void assignid(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MaterialButton Button = (MaterialButton) v;
        String buttonText = Button.getText().toString();
        String cal = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            cal = cal.substring(0,cal.length()-1);
        }else{
            cal = cal+buttonText;
        }
        solution.setText(cal);

        String finalResult = getResult(cal);

        if(!finalResult.equals("ERROR")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }

            return finalResult;
        }catch (Exception e){
            return "ERROR";
        }
    }

}