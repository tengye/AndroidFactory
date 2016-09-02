package com.teng.androidfactory.exo.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * A {@link FrameLayout} that resizes itself to match a specified aspect ratio.
 */
public final class TaAspectRatioFrameLayout extends FrameLayout {

    /**
     * The {@link FrameLayout} will not resize itself if the fractional difference between its natural
     * aspect ratio and the requested aspect ratio falls below this threshold.
     * <p>
     * This tolerance allows the view to occupy the whole of the screen when the requested aspect
     * ratio is very close, but not exactly equal to, the aspect ratio of the screen. This may reduce
     * the number of view layers that need to be composited by the underlying system, which can help
     * to reduce power consumption.
     */
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;

    private float videoAspectRatio;

    public TaAspectRatioFrameLayout(Context context) {
        super(context);
    }

    public TaAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Set the aspect ratio that this view should satisfy.
     *
     * @param widthHeightRatio The width to height ratio.
     */
    public void setAspectRatio(float widthHeightRatio) {
        if (this.videoAspectRatio != widthHeightRatio) {
            this.videoAspectRatio = widthHeightRatio;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (videoAspectRatio == 0) {
            // Aspect ratio not set.
            return;
        }

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float viewAspectRatio = (float) width / height;
        float aspectDeformation = videoAspectRatio / viewAspectRatio - 1;
        if (Math.abs(aspectDeformation) <= MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
            // We're within the allowed tolerance.
            return;
        }

        if (aspectDeformation > 0) {
            height = (int) (width / videoAspectRatio);
        } else {
            width = (int) (height * videoAspectRatio);
        }

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        while (width < widthPixels || height < heightPixels){
            width = (int) (width * 1.02);
            height = (int)(height * 1.02);
        }


        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

}
