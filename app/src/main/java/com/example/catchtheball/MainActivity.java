package com.example.catchtheball;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView cat;
    private ImageView pie;
    private ImageView apple;
    private ImageView blackHole;

    private int frameHeight;
    private int catSize;

    private int screenWidth;
    private int screenHeight;

    private int catY;
    private int pieX;
    private int pieY;
    private int appleX;
    private int appleY;
    private int blackHoleX;
    private int blackHoleY;



    private Handler handler = new Handler();
    private Timer timer = new Timer();


    private boolean action_flag = false;
    private boolean start_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //initData();
    }

    private void initData() {
        FrameLayout frame = (FrameLayout)findViewById(R.id.frame);
        frameHeight = frame.getHeight();
        catSize = cat.getHeight();

        Log.d("main", "frameHeight:"+frameHeight);
        Log.d("main", "catSize:"+frameHeight);
    }

    private void initView() {
        scoreLabel = (TextView)findViewById(R.id.scoreLabel);
        startLabel = (TextView)findViewById(R.id.startLabel);
        cat = (ImageView)findViewById(R.id.cat);
        pie = (ImageView)findViewById(R.id.pie);
        apple = (ImageView)findViewById(R.id.apple);
        blackHole = (ImageView)findViewById(R.id.black_hole);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        pie.setX(-80);
        pie.setY(-80);
        apple.setX(-80);
        apple.setY(-80);
        blackHole.setX(-80);
        blackHole.setY(-80);

    }

    private void changePos() {

        pieX -= 12;
        if(pieX < 0) {
            pieX = screenWidth + 24;
            pieY = (int)Math.floor(Math.random() * (frameHeight - pie.getHeight()));
        }
        pie.setX(pieX);
        pie.setY(pieY);

        blackHoleX -= 16;
        if(blackHoleX < 0) {
            blackHoleX = screenWidth + 10;
            blackHoleY = (int)Math.floor(Math.random() * (frameHeight - blackHole.getHeight()));
        }
        blackHole.setX(blackHoleX);
        blackHole.setY(blackHoleY);


        appleX -= 20;
        if(appleX < 0) {
            appleX = screenWidth + 5000;
            appleY = (int)Math.floor(Math.random() * (frameHeight - apple.getHeight()));
        }
        apple.setX(appleX);
        apple.setY(appleY);



        if(action_flag == true) {
            catY -= 20;
        } else {
            catY += 20;
        }

        if(catY < 0) {
            catY = 0;
        }

        if(catY > frameHeight - catSize) {
            catY = frameHeight -catSize;
        }

        cat.setY(catY);
    }

    public boolean onTouchEvent(MotionEvent me) {
        if(start_flag == false) {

            start_flag = true;

            catY = (int)cat.getY();
            startLabel.setVisibility(View.GONE);
            initData();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);


        }else {
            if(me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;

            } else if(me.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
        }
        return true;
    }
}
