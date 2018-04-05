package com.teng.androidfactory;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    @TargetApi(21)
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        // 在Activity被强制销毁之前保存数据
        // 这个方法是安卓5.0（21）之后的，之前的版本请使用onSaveInstanceState(Bundle outState)
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // 获取Activity被强制销毁后保存的数据

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.jniTest)).setText(new JniTest().getJniString());
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

    public void hotFixEvent(View view) {
        startActivity(new Intent(getContext() , ImagePlayerActivity.class));
    }

    public void richEditorEvent(View view) {
        startActivity(new Intent(getContext() , RichEditorActivity.class));
    }

    public void bottomNavigationBarEvent(View view) {
        startActivity(new Intent(getContext() , BottomNavigationBarActivity.class));
    }

//    public void materialDesignEvent(View view) {
//        startActivity(new Intent(getContext() , MaterialDesignActivity.class));
//    }

    public void removeEmojiEvent(View view){
        startActivity(new Intent(getContext() , EmojiAvtivity.class));
    }

    public void seekbarEvent(View view){
        startActivity(new Intent(getContext() , HotFixActivity.class));
    }

    public void gifEvent(View view){
        startActivity(new Intent(getContext() , GifActivity.class));
    }

    public void aidlEvent(View view){
//        startActivity(new Intent(getContext() , AidlTestActivity.class));
        showDatePickerDialog(this, 5, Calendar.getInstance());
    }

    public static void showDatePickerDialog(Activity activity, int themeResId,  Calendar calendar) {
        new DatePickerDialog(activity
                ,  themeResId
                ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

            }
        }
        , calendar.get(Calendar.YEAR)
        ,calendar.get(Calendar.MONTH)
        ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
