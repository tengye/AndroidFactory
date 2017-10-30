package com.teng.androidfactory;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;


public class SurfaceView2TextureView extends SurfaceView implements TextureView.SurfaceTextureListener{

    public TextureView mTextureView;
    protected SurfaceHolder.Callback mSurfaceHolderCallback;
    protected boolean isConvert;

    public boolean isConvert() {
        return isConvert;
    }

    public void setConvert(boolean convert) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            isConvert = convert;
            ViewGroup parent = ((ViewGroup) getParent());
            if (isConvert && parent != null) {
                parent.removeView(this);
                if (mTextureView.getParent() == null) {
                    setLayoutParams(super.getLayoutParams());
                    parent.addView(mTextureView);
                }
            }
        }
    }

    protected void init() {
        mTextureView = new TextureView(getContext());
        mTextureView.setSurfaceTextureListener(this);
        mTextureView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

            }
        });
        mTextureView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SurfaceView2TextureView.this.performClick();
            }
        });
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        if(isConvert) {
            mTextureView.setLayoutParams(params);
        }
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        if(isConvert) {
            return mTextureView.getLayoutParams();
        }
        return super.getLayoutParams();
    }

    public SurfaceView2TextureView(Context context) {
        super(context);
        init();
    }

    public SurfaceView2TextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceView2TextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Surface getSurface() {
        if(isConvert) {
            return new Surface(mTextureView.getSurfaceTexture());
        } else {
            return getHolder().getSurface();
        }
    }



    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(isConvert) {
            mTextureView.setVisibility(visibility);
        }
    }

    public void setSurfaceCallback(SurfaceHolder.Callback callback) {
        mSurfaceHolderCallback = callback;
        if(!isConvert) {
            getHolder().addCallback(callback);
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
//        surfaceCreated();
        if(mSurfaceHolderCallback != null) {
            mSurfaceHolderCallback.surfaceCreated(null);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//        surfaceChanged(surface, 0, width, height);
        if(mSurfaceHolderCallback != null) {
            mSurfaceHolderCallback.surfaceChanged(null, 0, width, height);
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//        surfaceDestroyed(surface);
//        return false;
        if(mSurfaceHolderCallback != null) {
            mSurfaceHolderCallback.surfaceDestroyed(null);
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//        requestRender();
    }

    public void onVideoSizeChanged(int width, int height, int unAppliedRotationDegrees,
                                   float pixelWidthHeightRatio) {
        // fix using ExoPlayer to play mp4 video has orientation bug under Android L
        if (isConvert && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            float mVideoWidthHeightAspectRatio = ((height == 0) ? 1 : (width * pixelWidthHeightRatio) / height);
            int viewWidth = getWidth();
            int viewHeight = getHeight();

            if(mVideoWidthHeightAspectRatio >= 1) {
                viewHeight = (int) (viewWidth * mVideoWidthHeightAspectRatio);
            }

            float pivotX = viewWidth / 2f;
            float pivotY = viewHeight / 2f;

            Matrix transform = new Matrix();
            transform.postRotate(unAppliedRotationDegrees, pivotX, pivotY);
            if (unAppliedRotationDegrees == 90 || unAppliedRotationDegrees == 270) {
                float viewAspectRatio = (float) viewHeight / viewWidth;
                transform.postScale(1 / viewAspectRatio, viewAspectRatio, pivotX, pivotY);
            }
            mTextureView.setTransform(transform);
        }
    }
}
