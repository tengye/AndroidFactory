package com.teng.androidfactory;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by teng on 17/12/8.
 */

public class CustomerVie extends View {

    public CustomerVie(Context context) {
        super(context);
    }

    public CustomerVie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }
}
