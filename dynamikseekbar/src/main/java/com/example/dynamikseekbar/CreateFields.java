package com.example.dynamikseekbar;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import static com.example.dynamikseekbar.DynamicSeekBar.ORIENTATION_HORIZONTAL;
import static com.example.dynamikseekbar.DynamicSeekBar.ORIENTATION_VERTICAL;

public class CreateFields {

    private RectF mark;

    private RectF button1, button2;

    private RectF background;

    private RectF way;

    private RectF progress;

    private String direct, orientation;

    private boolean visibleButtons;

    private float radiusWay;

    private float width, height;


    public CreateFields() {
        mark = new RectF();
        button1 = new RectF();
        button2 = new RectF();
        background = new RectF();
        way = new RectF();
        progress = new RectF();
        direct = DynamicSeekBar.DIRECT_STRAIGHT;
        orientation = ORIENTATION_HORIZONTAL;
        visibleButtons = false;
        radiusWay = 5;
    }

    public CreateFields(RectF mark, RectF button1, RectF button2, RectF background, RectF way, RectF progress) {
        this.mark = mark;
        this.button1 = button1;
        this.button2 = button2;
        this.background = background;
        this.way = way;
        this.progress = progress;
        direct = DynamicSeekBar.DIRECT_STRAIGHT;
        orientation = ORIENTATION_HORIZONTAL;
        visibleButtons = false;
        radiusWay = 5;
    }

    public static CreateFields get(){
        return new CreateFields();
    }

    public CreateFields setParams(Canvas c){
        width = c.getWidth();
        height = c.getHeight();
        return this;
    }


    public CreateFields direct(String direct){
        this.direct = direct;
        return this;
    }

    public CreateFields orientation(String orientation){
        this.orientation = orientation;
        return this;
    }

    public CreateFields buttons(boolean visible){
        visibleButtons = visible;
        return this;
    }

    public CreateFields radiusWay(float radius){
        radiusWay = radius;
        return this;
    }

    public RectF mark(float loc){
        createMark(loc);
        return mark;
    }

    public RectF progress(float loc){
        createProgress(loc);
        return progress;
    }

    public RectF getProgress(){
        return progress;
    }
    protected void createProgress(float position){
        if(direct.equals(DynamicSeekBar.DIRECT_BACK)){
            if(orientation.equals(ORIENTATION_VERTICAL)){
                progress.set(way.left,position,way.right,way.bottom);
            }else {
                progress.set(position,way.top,way.right,way.bottom);
            }
        }else {
            if(orientation.equals(ORIENTATION_VERTICAL)){
                progress.set(way.left,way.top,way.right,position);
            }else {
                progress.set(way.left,way.top,position,way.bottom);
            }
        }

    }

    protected void createMark(float position){
        if(direct.equals(DynamicSeekBar.DIRECT_BACK)){
            float loc = (getLength()-indent())-(getLength()-indent()-position);
            if(orientation.equals(ORIENTATION_VERTICAL)){
                mark.set(0,loc-getRound()/2,getRound(),loc+getRound()/2);
            }else {
                mark.set(loc-getRound()/2,0,loc+getRound()/2,getRound());
            }
        }else {
            if(orientation.equals(ORIENTATION_VERTICAL)){
                mark.set(0,position-getRound()/2,getRound(),position+getRound()/2);
            }else {
                mark.set(position-getRound()/2,0,position+getRound()/2,getRound());
            }
        }

    }

    protected void createButton1(){
        if(visibleButtons){
                if(orientation.equals(ORIENTATION_VERTICAL)){
                    button1.set(0,0,getRound(),getRound());
                }else {
                    button1.set(0, 0, getRound(), getRound());
                }
        }else {
            button1 = new RectF();
        }
    }

    protected void createButton2(){
        if(visibleButtons){
                if(orientation.equals(ORIENTATION_VERTICAL)){
                    button2.set(0,getLength()-getRound(),getRound(),getLength());
                }else {
                    button2.set(getLength() - getRound(), 0, getLength(), getRound());
                }
        }else {
            button2 = new RectF();
        }
    }

    protected void createWay(){
        if(orientation.equals(ORIENTATION_VERTICAL)){
            way.set(getCenterRound()-radiusWay/2,(indent()),getCenterRound()+radiusWay/2,getLength()-(indent()));
        }else {
            way.set((indent()),getCenterRound()-radiusWay/2,getLength()-(indent()),getCenterRound()+radiusWay/2);

        }

    }

    private void createBackground(){
        background.set(0,0,width,height);
    }

    private float getCenterRound(){
        return getRound()/2;
    }

    private float getCenterLength(){
        return getLength()/2;
    }

    public float getLengthWay(){
        if(orientation.equals(ORIENTATION_VERTICAL))return way.height();
        else return way.width();
    }

    public float getRound(){
        if(orientation.equals(ORIENTATION_VERTICAL))return background.width();
        else return background.height();
    }

    public long getLength(){
        if(orientation.equals(ORIENTATION_VERTICAL))return (long) background.height();
        else return (long) background.width();
    }
    public float indent(){
        if(visibleButtons)return getRound()*1.5f;
        else return getRound()*0.5f;


    }

    public RectF getButton1() {
        createButton1();
        return button1;
    }

    public RectF getButton2() {
        createButton2();
        return button2;
    }

    public RectF getBackground() {
        createBackground();
        return background;
    }

    public RectF getWay() {
        createWay();
        return way;
    }

    public RectF getMark(){
        return mark;
    }

    public boolean isVisibleButtons() {
        return visibleButtons;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getDirect() {
        return direct;
    }

    public float getRadiusWay(){
        return radiusWay;
    }

    private void MASSAGE(String t){
        Log.d("TAG",t);
    }

 }
