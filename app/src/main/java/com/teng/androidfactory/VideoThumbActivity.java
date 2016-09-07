package com.teng.androidfactory;

import android.graphics.Bitmap;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by teng on 16/9/7.
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_thumb);
        bind = ButterKnife.bind(this);

//        MediaController controller = new MediaController(this);
//        videoView.setMediaController(controller);

        metadataRetriever = new MediaMetadataRetriever();

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String absolutePath = externalStorageDirectory.getAbsolutePath();
        uriString = absolutePath + "/V60907-111428.mp4";

        //uriString = "android.resource://"+getPackageName()+"/"+R.raw.shuai_dan_ge;

        File file = new File(uriString);

        if (file.exists()) {

            metadataRetriever.setDataSource(uriString);

            String time = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            videoView.setVideoURI(Uri.parse("file:///"+ uriString));
            videoView.start();

            seekBar.setMax(Integer.parseInt(time) * 1000);
            seekBar.setOnSeekBarChangeListener(new SeekBarListener());

        }

    }

    private boolean isContraling;

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener{

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
            progressBar.setVisibility(View.GONE);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
