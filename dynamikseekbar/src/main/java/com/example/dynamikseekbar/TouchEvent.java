package com.example.dynamikseekbar;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

public class TouchEvent {

    public static final int NON_TOUCH = 0;
    public static final int TOUCH_WAY = 1;
    public static final int TOUCH_MARK = 2;
    public static final int TOUCH_BUTTON_1 = 3;
    public static final int TOUCH_BUTTON_2 = 4;
    public static final int TOUCH_FON = 5;


    private CreateFields fields;

    public static TouchEvent get(CreateFields fields){
        return new TouchEvent(fields);
    }

    public TouchEvent(@NonNull CreateFields fields) {
        this.fields = fields;
    }

    public int touch(MotionEvent event){
        return defineTouch(new PointF(event.getX(),event.getY()));
    }

    private int defineTouch(PointF p){
        if(belongRect(fields.getMark(),p))return TOUCH_MARK;
        else if(belongRect(fields.getWay(),p))return TOUCH_WAY;
        else if(belongRect(fields.getButton1(),p))return TOUCH_BUTTON_1;
        else if(belongRect(fields.getButton2(),p))return TOUCH_BUTTON_2;
        else if(belongRect(fields.getBackground(),p))return TOUCH_FON;
        else return NON_TOUCH;

    }



    private boolean belongRect(RectF r, PointF p){
       return p.x>=r.left-30&&p.y>=r.top-30&&p.x<=r.right+30&&p.y<=r.bottom+30;
    }


}
