package henriquejunger.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalcActivity extends AppCompatActivity {

    private String resultText = "";
    private TextView resultView;
    private double rightNumber = 0;
    private double leftNumber = 0;
    private int currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutBtns);
        resultView = (TextView) findViewById(R.id.resultsTextView);

        setButtons(layout);
        final ImageButton equal = (ImageButton) findViewById(R.id.equalBtn);
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                doOperation(equal.getId());
            }
        });

    }

    private void setButtons(LinearLayout layout){
        for(int i=0;i<layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof Button) {
                Button b = (Button) v;
                final String text = b.getText().toString();
                if(tryParseInt(text)){
                    b.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            numberPressed(text);
                        }
                    });
                }
                else if(b.getId() == R.id.clearBtn){
                    b.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            clearButton();
                        }
                    });
                }
            }
            else if(v instanceof LinearLayout){
                LinearLayout ll = (LinearLayout) v;
                setButtons(ll);
            }else if(v instanceof ImageButton){
                ImageButton ib = (ImageButton)v;
                final int id = ib.getId();
                ib.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        doOperation(id);
                    }
                });
            }
        }
    }

    private void numberPressed(String number){
        if(resultText.length() < 11 && (!number.equals("0")||resultText.length()>0)) {
            resultText += number;
            resultView.setText(resultText);
        }
    }

    private void doOperation(int id){
        if(resultText.length() > 0) {
            if (leftNumber == 0) {
                leftNumber = Double.parseDouble(resultText);
                resultText = "";
                rightNumber = 0;
            } else {
                rightNumber = Double.parseDouble(resultText);
                switch (currentOperation){
                    case R.id.sumBtn:
                        leftNumber += rightNumber;
                        break;
                    case R.id.subtractBtn:
                        leftNumber -= rightNumber;
                        break;
                    case R.id.multiplyBtn:
                        leftNumber *= rightNumber;
                        break;
                    case R.id.divideBtn:
                        if(rightNumber == 0){
                            clearButton();
                        }
                        else {
                            leftNumber /= rightNumber;
                        }
                        break;
                    case R.id.equalBtn:
                        break;
                }
                DecimalFormat df = new DecimalFormat("#");
                resultText = df.format(leftNumber);
                resultView.setText(resultText);
                resultText = "";
            }
        }
        currentOperation = id;
    }

    private void clearButton(){
        resultText = "";
        rightNumber = 0;
        leftNumber = 0;
        resultView.setText("0");
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


