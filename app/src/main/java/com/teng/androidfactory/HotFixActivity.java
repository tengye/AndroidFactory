package com.teng.androidfactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;

/**
 * Created by teng on 10/21/16.
 *
 * 安卓热修复
 *
 * QQ空间技术介绍： https://mp.weixin.qq.com/s?__biz=MzI1MTA1MzM2Nw==&mid=400118620&idx=1&sn=b4fdd5055731290eef12ad0d17f39d4a&scene=1&srcid=1106Imu9ZgwybID13e7y2nEi#wechat_redirect
 *
 * RocooFix： https://github.com/dodola/RocooFix
 *
 * 微信：https://github.com/Tencent/tinker
 *
 * 阿里：https://github.com/alibaba/AndFix
 *
 * 几种方式的比较：http://www.open-open.com/lib/view/open1467441373782.html
 *               http://www.diycode.cc/topics/231
 *
 */
public class HotFixActivity extends FragmentActivity {
    BitmapShaders bitmapShader = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GPUImage gpuImage = new GPUImage(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.txt_color);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGammaFilter(0.1f));
        Bitmap bitmapWithFilterApplied = gpuImage.getBitmapWithFilterApplied();

//        GPUImageView mImageView = (GPUImageView)findViewById(R.id.image);
//        mImageView.setBackgroundResource(R.drawable.txt_color);
//        mImageView.setFilter(new GPUImageSepiaFilter()); // sepia
//        mImageView.setFilter(new GPUImageGrayscaleFilter()); // gray
//        mImageView.setFilter(new GPUImageSharpenFilter()); // sharp
//        mImageView.setFilter(new GPUImageSobelEdgeDetection()); // edge

//        bitmapShader = new BitmapShaders(this);
//        setContentView(bitmapShader);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmapWithFilterApplied);
        setContentView(imageView);

    }


    public class BitmapShaders extends View
    {
        private  BitmapShader bitmapShader = null;
        private Bitmap bitmap = null;
        private Paint paint = null;
        private ShapeDrawable shapeDrawable = null;
        private int BitmapWidth  = 0;
        private int BitmapHeight = 0;
        public BitmapShaders(Context context)
        {
            super(context);
            //得到图像
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ny)).getBitmap();
            BitmapWidth = bitmap.getWidth();
            BitmapHeight = bitmap.getHeight();
            //构造渲染器BitmapShader
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,Shader.TileMode.REPEAT);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            //将图片裁剪为椭圆形
            //构建ShapeDrawable对象并定义形状为椭圆
            shapeDrawable = new ShapeDrawable(new OvalShape());
            //得到画笔并设置渲染器
            shapeDrawable.getPaint().setShader(bitmapShader);
            //设置显示区域
            shapeDrawable.setBounds(20, 20,BitmapWidth-60,BitmapHeight-60);
            //绘制shapeDrawable
            shapeDrawable.draw(canvas);
        }
    }
}
