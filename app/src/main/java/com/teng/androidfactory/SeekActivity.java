package com.teng.androidfactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.teng.androidfactory.common.util.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class SeekActivity extends AppCompatActivity {
    LinearLayout parentLayout;
    HorizontalScrollView scrollView;
    View bar;
    ViewGroup moveLayout;
    int widthPixels , marginLeft , parentLayoutWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        moveLayout = (ViewGroup) findViewById(R.id.moveLayout);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);

        bar = findViewById(R.id.bar);

        List<String> list = new ArrayList<>();
        list.add("https://media-dev3.musely.com/tm-/pi/fe377209-2a42-44a7-b09d-c816bf7c75a2.png");
        list.add("https://media-dev3.musely.com/tm-/pi/472fa3e4-3aeb-4184-b6f8-015cc89572fe.png");
        list.add("https://media-dev3.musely.com/tm-/pi/b758c97f-d6d6-47a9-9302-dee3fc2d8c9b.png");
        list.add("https://media-dev3.musely.com/tm-/si/947c60fb-050e-4ece-8ab0-46956178fc06.png");
        list.add("https://media-dev3.musely.com/tm-/pi/ef515dca-08f0-4b37-985b-926242f77641.png");
        list.add("https://media-dev3.musely.com/tm-/pi/fe377209-2a42-44a7-b09d-c816bf7c75a2.png");
        list.add("https://media-dev3.musely.com/tm-/pi/472fa3e4-3aeb-4184-b6f8-015cc89572fe.png");
        list.add("https://media-dev3.musely.com/tm-/pi/b758c97f-d6d6-47a9-9302-dee3fc2d8c9b.png");
        list.add("https://media-dev3.musely.com/tm-/si/947c60fb-050e-4ece-8ab0-46956178fc06.png");
        list.add("https://media-dev3.musely.com/tm-/pi/ef515dca-08f0-4b37-985b-926242f77641.png");

        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(WindowUtils.dip2px(this , 90) , WindowUtils.dip2px(this , 90));
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(list.get(i)).placeholder(R.mipmap.ic_launcher).into(imageView);
            parentLayout.addView(imageView);
        }

        widthPixels = getResources().getDisplayMetrics().widthPixels;
        marginLeft = widthPixels;

        Log.d("widthPixels" , "widthPixels-------->" + widthPixels);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollView.scrollBy(200 , 0);
//                Log.d("scrollView" , "scrollView---->" +scrollView.getMaxScrollAmount());
//                Log.d("scrollView" , "scrollView---->" +scrollView.getWidth());
//                Log.d("LinearLayout" , "LinearLayout---->" +parentLayout.getWidth());
                runMan = !runMan;
                if (runMan){
                    parentLayoutWidth = parentLayout.getWidth();
                    new Thread(new RunBar()).start();
                }
            }
        });


//        bar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
////                switch (event.getAction()){
////                    case MotionEvent.ACTION_DOWN:
//                        v.getParent().requestDisallowInterceptTouchEvent(true);
////                        break;
////                }
//
//                return false;
//            }
//        });


    }


    private boolean runOut = false;

    private boolean runMan = true;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) bar.getLayoutParams();
            layoutParams.leftMargin = layoutParams.leftMargin + WindowUtils.dip2px(SeekActivity.this , 30);

            if (layoutParams.leftMargin > marginLeft ){
                runOut = true;
            }

            if (marginLeft + WindowUtils.dip2px(SeekActivity.this , 10) >= parentLayoutWidth){
                layoutParams.leftMargin = parentLayoutWidth - WindowUtils.dip2px(SeekActivity.this , 10);
                runMan = false;
            }

            if (runOut) {
                runOut = false;
                marginLeft = marginLeft+WindowUtils.dip2px(SeekActivity.this, 80);
                scrollView.smoothScrollBy(WindowUtils.dip2px(SeekActivity.this, 80), 0);
            }

            bar.setLayoutParams(layoutParams);

        }
    };

    long oldTime = 0;

    class RunBar implements Runnable{
        @Override
        public void run() {
            while (runMan) {
                long l = System.currentTimeMillis();

                if (l - oldTime > 1000) {
                    oldTime = l;
                    handler.sendEmptyMessage(0x11);
                }
            }
        }
    }
}
