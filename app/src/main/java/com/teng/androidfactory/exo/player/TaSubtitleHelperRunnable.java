package com.teng.androidfactory.exo.player;

import android.view.View;

import com.teng.androidfactory.exo.TaVideoAnnotation;

import java.util.List;

/**
 * Created by lang on 08/31/2016.
 */
public abstract class TaSubtitleHelperRunnable implements Runnable{
    private static final int REFRESH_INTERVAL_MS = 500;
    private TaVideoAnnotationReader annotationReader = new TaVideoAnnotationReader();
    private View subtitleView;
    private List<TaVideoAnnotation> subtitleList;

    public TaSubtitleHelperRunnable(View subtitleView, List<TaVideoAnnotation> subtitleList) {
        this.subtitleView = subtitleView;
        this.subtitleList = subtitleList;
        if(subtitleList != null) {
            annotationReader.addAll(subtitleList);
            annotationReader.sort();
        }
    }

    public void start() {
        stop();
        run();
    }

    @Override
    public void run() {
        update(subtitleView);
        subtitleView.postDelayed(this, REFRESH_INTERVAL_MS);
    }

    public void stop() {
        subtitleView.removeCallbacks(this);
    }

    public abstract long getCurrentPosition();

    protected abstract void update(View subtitleView);

    public String getRenderString() {
        TaVideoAnnotation va = annotationReader.get(getCurrentPosition());
        if (va != null){
            return va.getText();
        }
        return "";
    }
}
