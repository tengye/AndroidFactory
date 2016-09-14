package com.teng.androidfactory.function.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

import com.teng.androidfactory.common.util.WindowUtils;

public class CircleImageDrawable extends Drawable
{

	private Paint mPaint;
	private int mWidth;
	private Bitmap mBitmap ;
	private final int mBitmapHeight;
	private final int mBitmapWidth;
	private Context context;

	public CircleImageDrawable(Context context , Bitmap bitmap)
	{
		mBitmap = bitmap ;

		this.context = context;
		BitmapShader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP,
				TileMode.CLAMP);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setShader(bitmapShader);
		mBitmapHeight = mBitmap.getHeight();
		mBitmapWidth = mBitmap.getWidth();
		mWidth = Math.min(mBitmapWidth, mBitmapHeight);
	}

	@Override
	public void draw(Canvas canvas)
	{

//		canvas.setBitmap(mBitmap);


//		canvas.drawRect(150, 75, 250, 120, mPaint);
//		canvas.drawColor(Color.WHITE);
//		canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);
//		canvas.drawRect(100, 100, mBitmapWidth, mBitmapHeight-100, mPaint); // WindowUtils.dip2px(context , 100)
//		canvas.drawBitmap(mBitmap , 100 , 100 , mPaint );

		Paint mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBitPaint.setFilterBitmap(true);
		mBitPaint.setDither(true);
		Rect rect = new Rect(60,60,mBitmapWidth - 10 ,mBitmapHeight - 10);
		Rect destRect = new Rect(60,60,mBitmapWidth - 10 ,mBitmapHeight - 10);
		canvas.drawBitmap(mBitmap , rect , destRect , mBitPaint);

		Paint p = new Paint();
		p.setColor(Color.BLACK);
		p.setAntiAlias(true);
		p.setStrokeWidth(1f);
		p.setStyle(Paint.Style.STROKE);
//
		canvas.drawRect(50, 50, mBitmapWidth, mBitmapHeight, p);
//		canvas.drawLine(50, 50, 95, 95, p);

//		canvas.drawRect(150, 75, 250, 120, mPaint);
//		canvas.drawRect(250, 75, 350, 120, p);
//		canvas.drawRect(150, 120, 250, 170, p);
//		canvas.drawRect(250, 120, 350, 170, p);

	}

	@Override
	public int getIntrinsicWidth()
	{
		return WindowUtils.dip2px(context , 100);
	}

	@Override
	public int getIntrinsicHeight()
	{
		return WindowUtils.dip2px(context , 120);
	}

	@Override
	public void setAlpha(int alpha)
	{
		mPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf)
	{
		mPaint.setColorFilter(cf);
	}

	@Override
	public int getOpacity()
	{
		return PixelFormat.TRANSLUCENT;
	}

}
