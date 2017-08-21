package com.teng.androidfactory;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wseemann.media.FFmpegMediaMetadataRetriever;

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

        isNetVideo = false;

        initData();


    }

    private void initData() {

        thumbImage.setImageResource(R.mipmap.ic_launcher);

        if (isNetVideo) {
            uriString = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4").toString();
            metadataRetriever.setDataSource(uriString, new HashMap<String, String>());
        } else {
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/esShare/1502415687348.mp4"); // new 1502415687348  old 1502418514733
            if (f.exists()){
                uriString = f.getAbsolutePath();//Environment.getExternalStorageDirectory().getAbsolutePath() + "/V60907-111428.mp4";
                metadataRetriever.setDataSource(uriString);
            }
        }
        ///storage/emulated/0/Chaji/Video/1502350053625.mp4

        videoView.stopPlayback();
//        metadataRetriever.setDataSource(uriString, new HashMap<String, String>());
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
            FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
            mmr.setDataSource(uriString);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
            mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
            Bitmap bitmap = mmr.getFrameAtTime(seekBar.getProgress(), FFmpegMediaMetadataRetriever.OPTION_CLOSEST); // frame at 2 seconds
//            byte [] artwork = mmr.getEmbeddedPicture();

            thumbImage.setImageBitmap(bitmap);

            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            Rect bounds = seekBar.getThumb().getBounds();
            drawable.setBounds(bounds);
            seekBar.setThumb(drawable);
            progressBar.setVisibility(View.GONE);

            mmr.release();
//            Bitmap bitmap = metadataRetriever.getFrameAtTime(seekBar.getProgress(),
//                    MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
