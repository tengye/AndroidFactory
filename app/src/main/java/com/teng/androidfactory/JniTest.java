package com.teng.androidfactory;

/**
 * Created by teng on 17/12/14.
 */

public class JniTest {

    static {
        System.loadLibrary("JniTest");
    }
    //java调C中的方法都需要用native声明且方法名必须和c的方法名一样
    public native String getJniString();

}
