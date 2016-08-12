package com.teng.androidfactory.model;

import com.google.auto.value.AutoValue;

/**
 * Created by teng on 16/8/10.
 */

@AutoValue
public abstract class User {
    public abstract long id();
    public abstract String name();

    /**
     *  build ---> make project product AutoValue_User Class
     * @param id
     * @param name
     * @return
     */
    public static User create(long id , String name){
        return new AutoValue_User(id , name);
    }



}
