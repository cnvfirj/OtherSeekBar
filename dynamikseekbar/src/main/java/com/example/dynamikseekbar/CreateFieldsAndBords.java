package com.example.dynamikseekbar;

import android.graphics.RectF;

public class CreateFieldsAndBords extends CreateFields{

    private float bord;

    private boolean visibleBord;

    private CreateFieldsAndBords() {
        bord = 5;
    }

    public static CreateFieldsAndBords get(){
        return new CreateFieldsAndBords();
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
