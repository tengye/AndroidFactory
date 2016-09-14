package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by teng on 16/9/13.
 */
public class DrawableTextActivity extends FragmentActivity implements View.OnTouchListener {
    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.drawable_seekbar)
    SeekBar seekBar;

    @BindView(R.id.root)
    ViewGroup root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_text);
        ButterKnife.bind(this);


        imageView.setOnTouchListener(this);




//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.jietu);
//        Bitmap scaledBitmap = bitmap.createScaledBitmap(bitmap,
//                    WindowUtils.dip2px(this, 90),
//                WindowUtils.dip2px(this, 90), true);
//        imageView.setImageDrawable(new CircleImageDrawable(this , scaledBitmap));
//
//
//        seekBar.setMax(1000);
//        seekBar.setProgress(500);
//
//        CircleImageDrawable thumb = new CircleImageDrawable(this, scaledBitmap);
////        Drawable drawable = new BitmapDrawable(scaledBitmap);
//
//
//        Drawable seekBarThumb = seekBar.getThumb();
//        Rect bounds = seekBarThumb.getBounds();
//        thumb.setBounds(bounds);
//        seekBar.setThumb(thumb);

    }

    private int _xDelta;
    private int _yDelta;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) imageView
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                imageView.setLayoutParams(layoutParams);
                break;
        }
        root.invalidate();
        return true;
    }
}
