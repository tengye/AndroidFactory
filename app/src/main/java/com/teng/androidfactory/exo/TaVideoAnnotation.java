package com.teng.androidfactory.exo;

import java.io.Serializable;

/**
 * Created by sunny on 8/25/16.
 */
public class TaVideoAnnotation implements Serializable{
    String text;
    String timeReference;
    float startTime;  //ms
    float duration;   //ms

    public TaVideoAnnotation(String text, String timeReference){
        this.text = text;
        this.timeReference = timeReference;
        processTimeReference(timeReference);
    }

    private boolean processTimeReference(String timeReference){
        try{
            String[] timeReferenceArray = timeReference.split(",");
            startTime = Float.parseFloat(timeReferenceArray[0])*1000;
            duration = Float.parseFloat(timeReferenceArray[1])*1000;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeReference() {
        return timeReference;
    }

    public void setTimeReference(String timeReference) {
        this.timeReference = timeReference;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }




}
