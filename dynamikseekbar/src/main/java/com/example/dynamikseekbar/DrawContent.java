package com.example.dynamikseekbar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

import javax.inject.Inject;

public class DrawContent {

    private CreateFields dFields;

    private Drawable dButton1,dButton2,dMark;

    private boolean dVectorButton1, dVectorButton2, dVectorMark;

    private Paint dPaint;

    private String dGravity;

    private String dRounded;

    private float dTextSize;

    private int dColorContent;

    private boolean dVisibleText;

    private boolean dReversText;

    private Path dClip;

    private Path dText;


    @Inject
    public DrawContent(CreateFieldsAndBords fields) {
        this.dFields = fields;
        dColorContent = Color.WHITE;
        dTextSize = 5;
        dPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dPaint.setTextAlign(Paint.Align.CENTER);
        dPaint.setColor(dColorContent);
        dPaint.setTextSize(dTextSize);
        dGravity = DynamicSeekBar.GRAVITY_BOTTOM;
        dVisibleText = false;
        dClip = new Path();
        dText = new Path();
        dRounded = DynamicSeekBar.ROUND_CIRCLE;
        dRounded = DynamicSeekBar.ROUND_CIRCLE;
    }

    public DrawContent rounded(String round){
        dRounded = round;
        return this;
    }

    public DrawContent textSize(float size){
        dTextSize = size;
        dPaint.setTextSize(dTextSize);
        return this;
    }

    public DrawContent visibleText(boolean visible){
        dVisibleText = visible;
        return this;
    }

    public DrawContent gravityText(String gravity){
        dGravity = gravity;
        return this;
    }

    public DrawContent colorContent(int color){
        dColorContent = color;
        dPaint.setColor(dColorContent);

        return this;
    }

    public DrawContent srcMark(Drawable d){
        dMark = d;
        return this;
    }

    public DrawContent srcButtons(Drawable d1, Drawable d2){
        dButton1 = d1;
        dButton2 = d2;
        return this;
    }

    public DrawContent srcButton1(Drawable d){
        dButton1 = d;
        applyColor(dButton1);
        return this;
    }

    public DrawContent srcButton2(Drawable d){
        dButton2 = d;
        applyColor(dButton2);
        return this;
    }

    public DrawContent reversText(boolean revers){
        dReversText = revers;
        return this;
    }

    public void resetSrc(){
        dMark = null;
        dButton2 = null;
        dButton1 = null;
    }

    public void drawContentMark(int content, Canvas canvas){
        drawDrawable(dMark,canvas,dFields.getMark());
        drawText(content,canvas,dFields.getMark());
    }

    public void drawContentButtons(int content, Canvas canvas){
        if(dFields.isVisibleButtons()){
            if(dReversText&&dVisibleText){
                    drawText(content, canvas, dFields.getButton1());
                    drawText(content, canvas, dFields.getButton2());

            }else{
                drawDrawable(dButton1,canvas,dFields.getButton1());
                drawDrawable(dButton2,canvas,dFields.getButton2());
            }
        }
    }


    public int getColorContent(){
        return dColorContent;
    }

    public String getGravity() {
        return dGravity;
    }

    public float getTextSize() {
        return dTextSize;
    }

    public boolean isVisibleText() {
        return dVisibleText;
    }

    public Drawable getMark(){
        return dMark;
    }

    public Drawable getButton1(){
        return dButton1;
    }

    public Drawable getButton2(){
        return dButton2;
    }

    private void applyColor(Drawable d){
        if(d!=null)d.setTint(dColorContent);

    }
    private void drawDrawable(Drawable d, Canvas canvas, RectF r){
         if(d!=null){
           canvas.save();
           canvas.clipPath(clipField(r));
           d.setBounds(rect(r));
           d.draw(canvas);
           canvas.restore();
                    }
    }

    private void drawText(int content, Canvas canvas, RectF r){
        if(dVisibleText) {
            String text = Integer.toString(content);
            canvas.save();
            canvas.clipPath(clipField(r));
            canvas.restore();
            pathText(r);
            canvas.drawTextOnPath(text,dText,0,dTextSize/3.0f,dPaint);
        }
    }

    private void pathText(RectF r){
        dText.reset();
        if(dGravity.equals(DynamicSeekBar.GRAVITY_BOTTOM)){
            dText.moveTo(r.left,r.centerY());
            dText.lineTo(r.right,r.centerY());
        }else if(dGravity.equals(DynamicSeekBar.GRAVITY_TOP)){
            dText.moveTo(r.right,r.centerY());
            dText.lineTo(r.left,r.centerY());
        }else if(dGravity.equals(DynamicSeekBar.GRAVITY_LEFT)){
            dText.moveTo(r.centerX(),r.top);
            dText.lineTo(r.centerX(),r.bottom);
        }else if(dGravity.equals(DynamicSeekBar.GRAVITY_RIGHT)){
            dText.moveTo(r.centerX(),r.bottom);
            dText.lineTo(r.centerX(),r.top);
        }else {
            dText.moveTo(r.left,r.centerY());
            dText.lineTo(r.right,r.centerY());
        }
    }



    protected Path clipField(RectF r){
        dClip.reset();
        float radius = r.width()<r.height()?r.width()/2:r.height()/2;
        if(dRounded.equals(DynamicSeekBar.ROUND_CIRCLE)){
            dClip.addRoundRect(r,radius,radius, Path.Direction.CCW);
        }else {
            dClip.addRect(r, Path.Direction.CCW);
        }
        return dClip;
    }

    private Rect rect(RectF r){
        return new Rect((int)r.left,(int)r.top,(int)r.right,(int)r.bottom);
    }

    private void MASSAGE(String t){
        Log.e("____________CONTENT",t);
    }


}
