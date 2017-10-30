package com.teng.androidfactory;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GifActivity extends AppCompatActivity {

    public static String SD_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath()+"/esShare/";


    String[] array = {
            SD_PATH + "1505900602089_08.jpg",
            SD_PATH + "1505900602089_07.jpg"
//            SD_PATH + "1505900602089_06.jpg",
//            SD_PATH + "1505900602089_05.jpg",
//            SD_PATH + "1505900602089_04.jpg",
//            SD_PATH + "1505900602089_03.jpg",
//            SD_PATH + "1505900602089_02.jpg"
    };

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        image = (ImageView) findViewById(R.id.image);

//        createGif();

        Glide.with(GifActivity.this).load("file://"+Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/Chaji/ggg.gif").diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);



    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            File file = (File) msg.obj;

            Glide.with(GifActivity.this).load("file://"+Environment.getExternalStorageDirectory()
                    .getAbsolutePath()+"/Chaji/test.gif").diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);

        }
    };


    private void createGif(){
        new Thread(() -> {
            AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            animatedGifEncoder.start(bos);

            animatedGifEncoder.setDelay(500);

            animatedGifEncoder.setFrameRate(1);


            for (int i = 0; i < array.length; i++) {
                animatedGifEncoder.addFrame(BitmapFactory.decodeFile(array[i]));
            }

//        askPermissions();
            try {

                File file = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()+"/Chaji/test.gif");

                if (!file.exists()){
                    file.createNewFile();
                }

                FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath());

                outputStream.write(bos.toByteArray());
                outputStream.flush();
                outputStream.close();
                animatedGifEncoder.finish();

                Message msg = handler.obtainMessage();
                msg.obj = file;
                handler.sendMessage(msg);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
