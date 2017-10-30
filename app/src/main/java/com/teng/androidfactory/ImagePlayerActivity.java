package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teng on 17/10/30.
 */

public class ImagePlayerActivity extends FragmentActivity {

    ViewPager viewPager;
    List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_player);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        urlList = new ArrayList<>();
        urlList.add("http://img4.imgtn.bdimg.com/it/u=540293922,788232604&fm=27&gp=0.jpg");
        urlList.add("http://img2.imgtn.bdimg.com/it/u=1188970075,4160509163&fm=27&gp=0.jpg");
        urlList.add("http://img1.imgtn.bdimg.com/it/u=1603290829,2123509302&fm=27&gp=0.jpg");

        ImageAdapter adapter = new ImageAdapter();
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    int current = viewPager.getCurrentItem();
                    int lastReal = viewPager.getAdapter().getCount() - 2;
                    if (current == 0){
                        viewPager.setCurrentItem(lastReal, false);
                    }else if (current == lastReal + 1){
                        viewPager.setCurrentItem(1, false);
                    }
                }
            }
        });

    }

    class ImageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return urlList.size()+2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ImagePlayerActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(ImagePlayerActivity.this).load(urlList.get(toRealPosition(position))).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            if (object != null)
                object = null;
        }
    }

    /**
     * 返回真实的位置
     * @param position
     * @return
     */
    private int toRealPosition(int position) {
        int realPosition;

        realPosition = (position - 1) % urlList.size();
        if (realPosition < 0)
            realPosition += urlList.size();

        return realPosition;
    }



}
