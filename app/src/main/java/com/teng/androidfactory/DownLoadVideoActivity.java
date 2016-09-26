package com.teng.androidfactory;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by teng on 16/9/21.
 */
public class DownLoadVideoActivity extends FragmentActivity {

    @BindView(R.id.edit_text)
    EditText editText;
    private String urlString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_video);
        ButterKnife.bind(this);

//        editText.setText("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        editText.setText("http://media-dev.musely.com/u/55c834e0-5a68-4c8b-aa1e-59cfc2cb0ec7.mp4");
    }

//    /storage/emulated/0/Chaji/Video/1474514680413.mp4
//    /storage/emulated/0/Chaji/Video/1474515738128.mp4
//    /storage/emulated/0/Chaji/Video/1474516315738.mp4
    @OnClick(R.id.download)
    public void downloadVideoEvent(View view) {

        urlString = editText.getText().toString();

        new DownloadThread().start();

    }



    class DownloadThread extends Thread{

        @Override
        public void run() {
            super.run();
            String fileName = System.currentTimeMillis() + ".mp4";
            OutputStream output = null;
            try {

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                String SDCard = Environment.getExternalStorageDirectory() + "";
                String pathName = SDCard + "/" + fileName;

                File file = new File(pathName);
                InputStream input = conn.getInputStream();

                output = new FileOutputStream(file);
                int len;
                // 根据大小读取数据
                byte[] buffer = new byte[4 * 1024];
                while ((len = input.read(buffer))!= -1) {
                    output.write(buffer , 0, len);
                }
                output.flush();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                    Log.e("download", "success");
                } catch (IOException e) {
                    Log.e("download", "fail");
                    e.printStackTrace();
                }
            }
        }
    }


}
