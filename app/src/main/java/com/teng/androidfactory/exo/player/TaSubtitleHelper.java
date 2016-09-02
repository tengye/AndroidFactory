package com.teng.androidfactory.exo.player;

import android.text.TextUtils;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.exoplayer.CodecCounters;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.teng.androidfactory.exo.TaVideoAnnotation;

/**
 * A helper class for periodically updating debug information displayed by a {@link TextView}.
 */
public final class TaSubtitleHelper implements Runnable {
  /**
   * Provides debug information about an ongoing playback.
   */
  public interface Provider {

    /**
     * Returns the current playback position, in milliseconds.
     */
    long getCurrentPosition();

    /**
     * Returns a format whose information should be displayed, or null.
     */
    Format getFormat();

    /**
     * Returns a {@link BandwidthMeter} whose estimate should be displayed, or null.
     */
    BandwidthMeter getBandwidthMeter();

    /**
     * Returns a {@link CodecCounters} whose information should be displayed, or null.
     */
    CodecCounters getCodecCounters();

  }

  private static final int REFRESH_INTERVAL_MS = 500;

  private final TextView textView;
  private final Provider debuggable;
  private final TaVideoAnnotationReader videoAnnotationReader;
  private final SeekBar seekBar;
  private long duration;
  /**
   * @param debuggable The {@link Provider} from which debug information should be obtained.
   * @param textView The {@link TextView} that should be updated to display the information.
   */
  public TaSubtitleHelper(Provider debuggable, TextView textView, SeekBar seekBar, TaVideoAnnotationReader videoAnnotationReader) {
    this.debuggable = debuggable;
    this.textView = textView;
    this.seekBar = seekBar;
    this.videoAnnotationReader = videoAnnotationReader;
  }

  /**
   * Starts periodic updates of the {@link TextView}.
   * <p>
   * Should be called from the application's main thread.
   */
  public void start() {
    stop();
    run();
  }

  /**
   * Stops periodic updates of the {@link TextView}.
   * <p>
   * Should be called from the application's main thread.
   */
  public void stop() {
    textView.removeCallbacks(this);
  }

  @Override
  public void run() {
    String subTitle = getRenderString();
    if(!TextUtils.isEmpty(subTitle)) {
      textView.setText(subTitle);
      textView.setVisibility(View.VISIBLE);
    } else {
      textView.setVisibility(View.GONE);
    }
    textView.postDelayed(this, REFRESH_INTERVAL_MS);

    seekBar.setProgress((int) debuggable.getCurrentPosition());
    if (duration != - 1) {
      seekBar.setMax((int) duration);
    }

  }

  private String getRenderString() {
    TaVideoAnnotation va = videoAnnotationReader.get(debuggable.getCurrentPosition());
    if (va != null){
      return va.getText();
    }
    return "";
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }
}
