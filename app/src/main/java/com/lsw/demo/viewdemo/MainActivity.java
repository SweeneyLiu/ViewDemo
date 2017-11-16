package com.lsw.demo.viewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;
    int width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);

        getButtonWidth1();
//        getButtonWidth2();
//        getButtonWidth3();
//        getButtonWidth4();
//        getButtonWidth5();

    }

    public void getButtonWidth1() {
        mButton.post(new Runnable() {
            @Override
            public void run() {
//                width = mButton.getWidth();
                width = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate1: "+width);
            }
        });
    }

    public void getButtonWidth2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                width = mButton.getWidth();
                width = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate1: "+width);
            }
        }).start();
    }

    public void getButtonWidth3() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        mButton.measure(w,h);
        width = mButton.getMeasuredWidth();
        Log.i(TAG, "onCreate1: "+width);
    }

    public void getButtonWidth4() {
        ViewTreeObserver viewTreeObserver = mButton.getViewTreeObserver();
        //在 SDK Lvl < 16时使用 removeGlobalOnLayoutListener(this)
        //在 SDK Lvl >= 16时使用 removeOnGlobalLayoutListener(this)
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //防止被触发多次
                mButton.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                width = mButton.getWidth();
                width = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate: "+width);
            }
        });
    }

    public void getButtonWidth5() {
        ViewTreeObserver viewTreeObserver = mButton.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mButton.getViewTreeObserver().removeOnPreDrawListener(this);
//                width = mButton.getWidth();
                width = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate: "+width);
                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //        width = mButton.getWidth();
//        width = mButton.getMeasuredWidth();
//        Log.i(TAG, "onCreate: "+width);
    }
}
