package com.musicplay.yuhwan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class FromPic12 extends Activity {
    private GestureDetectorCompat gestureComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frompic12);
        gestureComp = new GestureDetectorCompat(this, new FromPic12.ControlGesutre());
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.gestureComp.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class ControlGesutre extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                Intent intent = new Intent(FromPic12.this, FromPic11.class);
                startActivity(intent);
                finish();
            } else if (event2.getX() < event1.getX()) {
                //왼쪽
                 Intent intent = new Intent(FromPic12.this,FromStory1.class);
                   startActivity(intent);
                   finish();
            }
            return true;
        }
    }
}