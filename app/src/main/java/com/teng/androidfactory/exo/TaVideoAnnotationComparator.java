package com.teng.androidfactory.exo;

import java.util.Comparator;

/**
 * Created by sunny on 8/25/16.
 */
public class TaVideoAnnotationComparator implements Comparator<TaVideoAnnotation>
{
    @Override
    public int compare(TaVideoAnnotation lhs, TaVideoAnnotation rhs) {
        if (lhs.startTime < rhs.startTime) return -1;
        if (lhs.startTime > rhs.startTime) return 1;
        return 0;
    }
}
