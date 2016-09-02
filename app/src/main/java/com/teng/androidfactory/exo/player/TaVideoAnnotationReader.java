package com.teng.androidfactory.exo.player;


import com.teng.androidfactory.exo.TaVideoAnnotation;
import com.teng.androidfactory.exo.TaVideoAnnotationComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunny on 8/25/16.
 */
public class TaVideoAnnotationReader {
    private List<TaVideoAnnotation> subtitles;
    public void add(TaVideoAnnotation videoAnnotation){
        if (subtitles == null){
            subtitles = new ArrayList<TaVideoAnnotation>();
        }
        subtitles.add(videoAnnotation);
    }
    public void addAll(List<TaVideoAnnotation> subtitleList) {
        if(subtitles == null) {
            subtitles = subtitleList;
        } else {
            subtitles.addAll(subtitleList);
        }
    }

    public void sort(){
        Collections.sort(subtitles, new TaVideoAnnotationComparator());
    }

    public TaVideoAnnotation get(long time){
        if (subtitles != null){
            for (int i=subtitles.size()-1; i>=0; i--){
                TaVideoAnnotation subtitle = subtitles.get(i);
                if (subtitle.getStartTime()<= time
                        && time <= subtitle.getStartTime() + subtitle.getDuration()){
                    return subtitle;
                }
            }
        }
        return null;
    }
}
