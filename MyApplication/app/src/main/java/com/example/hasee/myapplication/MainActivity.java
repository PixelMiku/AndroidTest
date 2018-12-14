package com.example.hasee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    Button button_per,button_poi,button_clr,button_exc,button_mul,button_back,button_les,button_pls;
    Button button_mc,button_m1,button_m2,button_mr,button_equal;
    TextView textView;
    boolean clr_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button) findViewById(R.id.button_0);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        button_6 = (Button) findViewById(R.id.button_6);
        button_7 = (Button) findViewById(R.id.button_7);
        button_8 = (Button) findViewById(R.id.button_8);
        button_9 = (Button) findViewById(R.id.button_9);
        button_per = (Button) findViewById(R.id.button_per);
        button_poi = (Button) findViewById(R.id.button_poi);
        button_clr = (Button) findViewById(R.id.button_clr);
        button_exc = (Button) findViewById(R.id.button_exc);
        button_mul = (Button) findViewById(R.id.button_mul);
        button_back = (Button) findViewById(R.id.button_back);
        button_les = (Button) findViewById(R.id.button_les);
        button_pls = (Button) findViewById(R.id.button_pls);
        button_mc = (Button) findViewById(R.id.button_mc);
        button_m1 = (Button) findViewById(R.id.button_m1);
        button_m2 = (Button) findViewById(R.id.button_m2);
        button_mr = (Button) findViewById(R.id.button_mr);
        button_equal = (Button) findViewById(R.id.button_equal);
        textView = (TextView) findViewById(R.id.textView);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_per.setOnClickListener(this);
        button_poi.setOnClickListener(this);
        button_clr.setOnClickListener(this);
        button_exc.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_back.setOnClickListener(this);
        button_les.setOnClickListener(this);
        button_pls.setOnClickListener(this);
        button_mc.setOnClickListener(this);
        button_m1.setOnClickListener(this);
        button_m2.setOnClickListener(this);
        button_mr.setOnClickListener(this);
        button_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = textView.getText().toString();
        switch (v.getId()){
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_poi:
                if(clr_flag){
                    clr_flag = false;
                    str = "";
                    textView.setText("");
                }
                textView.setText(str+((Button)v).getText());
                break;
            case R.id.button_pls:
            case R.id.button_mul:
            case R.id.button_exc:
            case R.id.button_les:
                if(clr_flag){
                    clr_flag = false;
                    str = "";
                    textView.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                textView.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.button_clr:
                if(clr_flag)
                    clr_flag=false;
                str="";
                textView.setText("");
                break;
            case R.id.button_back: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    textView.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    textView.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.button_equal: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {
        String exp=textView.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) cnt=0;
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                textView.setText(res+"");
            }else {
                textView.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                textView.setText(res+"");
            }else {
                textView.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                textView.setText(res+"");
            }else {
                textView.setText(cnt+"");}
        }
        else {
            textView.setText("");
        }


        }
    }

