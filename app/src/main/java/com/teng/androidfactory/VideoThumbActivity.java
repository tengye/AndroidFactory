package com.teng.androidfactory;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by teng on 16/9/7.
 *
 * 1、MediaMetadataRetriever可以获取任意时刻的缩略图,注意第一个参数是微秒
 *
 * 2、ThumbnailUtils好像只能获取第一帧
 *
 */
public class VideoThumbActivity extends FragmentActivity {

    private Unbinder bind;
    @BindView(R.id.thumb_image)
    ImageView thumbImage;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private MediaMetadataRetriever metadataRetriever;
    private String uriString;
    private boolean isNetVideo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_thumb);
        bind = ButterKnife.bind(this);

        metadataRetriever = new MediaMetadataRetriever();

        isNetVideo = true;

        initData();


    }

    private void initData() {

        thumbImage.setImageResource(R.mipmap.ic_launcher);

        if (isNetVideo) {
            uriString = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        } else {
            uriString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/V60907-111428.mp4";
        }

        videoView.stopPlayback();
        metadataRetriever.setDataSource(uriString, new HashMap<String, String>());
        String time = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        videoView.setVideoPath(uriString);
        videoView.start();
        seekBar.setMax(Integer.parseInt(time) * 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBarListener());
    }


    @OnClick(R.id.sd_video)
    public void sdVideo(View view){
        isNetVideo = false;
        initData();
    }

    @OnClick(R.id.net_video)
    public void netVideo(View view){
        isNetVideo = true;
        initData();
    }


    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Bitmap bitmap = metadataRetriever.getFrameAtTime(seekBar.getProgress(),
                    MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            thumbImage.setImageBitmap(bitmap);

            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            Rect bounds = seekBar.getThumb().getBounds();
            drawable.setBounds(bounds);
            seekBar.setThumb(drawable);
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
