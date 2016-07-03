// MainActivity.java 
// This program runs the calculator performs addition, subtraction, multiplication and division.
// David Li
package pika.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
	//initialization
        public String str ="";
        Character op = 'q';
        float num,numtemp;
        EditText showResult;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            showResult = (EditText)findViewById(R.id.result_id);
        }
		// Insert number or operand when the corresponding button is pressed
        public void btn1Clicked(View v){
            insert(1);
        }
        public void btn2Clicked(View v){
            insert(2);
        }
        public void btn3Clicked(View v){
            insert(3);
        }
        public void btn4Clicked(View v){
            insert(4);
        }
        public void btn5Clicked(View v){
            insert(5);
        }
        public void btn6Clicked(View v){
            insert(6);
        }
        public void btn7Clicked(View v){
            insert(7);
        }
        public void btn8Clicked(View v){
            insert(8);

        }
        public void btn9Clicked(View v){
            insert(9);
        }
        public void btnplusClicked(View v){
            perform();
            op = '+';
            insertOp(op);
        }
        public void btnminusClicked(View v){
            perform();
            op = '-';
            insertOp(op);
        }
        public void btndivideClicked(View v){
            perform();
            op = '/';
            insertOp(op);
        }
        public void btnmultiClicked(View v){
            perform();
            op = '*';
            insertOp(op);
        }
	//calculate result
        public void btnequalClicked(View v){
            calculate();
            perform();
        }
        public void btnclearClicked(View v){
            reset();
        }
	//reset values
        private void reset() {
            str ="";
            op ='q';
            num = 0;
            numtemp = 0;
            showResult.setText("");
        }
	//insert the number as a string and keep track of its value
        private void insert(int j) {
            String app = Integer.toString(j);
            str = str+app;
            num = Integer.valueOf(str).floatValue();
            showResult.append(app);
        }
	//insert operation 
        private void insertOp(char op) {
            String string1 = Character.toString(op);
            showResult.append("" + string1);
        }
        private void perform() {
            str = "";
            numtemp = num;
        }
	//calculate values based on the operator and display result
        private void calculate() {
            if(op == '+')
                num = numtemp+num;
            else if(op == '-')
                num = numtemp-num;
            else if(op == '/')
                num = numtemp/num;
            else if(op == '*')
                num = numtemp*num;
            showResult.setText(""+num);
        }
    }
