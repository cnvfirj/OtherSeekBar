package com.example.dynamikseekbar;

import android.graphics.RectF;

public class CreateFieldsAndBords extends CreateFields{

    private float bord;

    private boolean visibleBord;

    public static CreateFieldsAndBords get(){
        return new CreateFieldsAndBords(new RectF(),new RectF(),new RectF(),new RectF(),new RectF(),new RectF());
    }


    public CreateFieldsAndBords(RectF mark, RectF button1, RectF button2, RectF background, RectF way, RectF progress) {
        super(mark, button1, button2, background, way, progress);
        bord = 5;
    }

    public CreateFieldsAndBords visibleBord(boolean visible){
        visibleBord = visible;
        return this;
    }

    public CreateFieldsAndBords widthBord(float width){
        bord = width;
        return this;
    }

    @Override
    public float indent() {
        float i = super.indent();
        if(visibleBord){
            if(isVisibleButtons()) i-=getBord()*2;
        }
        return i;
    }

    public float getBord(){
        return bord;
    }
}
