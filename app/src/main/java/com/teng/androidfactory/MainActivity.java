package com.teng.androidfactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private Context getContext(){
        return this;
    }

    public void retrofitAndRx(View view) {
        startActivity(new Intent(getContext() , MenuActivity.class));
    }


    public void videoThumbnail(View view) {

        startActivity(new Intent(getContext() , VideoThumbActivity.class));

    }

    public void drawableTest(View view) {

        startActivity(new Intent(getContext() , DrawableTextActivity.class));

    }

    public void recyclerItemDrag(View view) {
        startActivity(new Intent(getContext() , DragItemActivity.class));
    }

    public void downLoadVideo(View view) {
        startActivity(new Intent(getContext() , DownLoadVideoActivity.class));
    }

    public void exoPlayerEvent(View view) {
        startActivity(new Intent(getContext() , SampleChooserActivity.class));
    }

    public void fadingActionBarEvent(View view) {
        startActivity(new Intent(getContext() , FadingActionBarActivity.class));
    }
}
