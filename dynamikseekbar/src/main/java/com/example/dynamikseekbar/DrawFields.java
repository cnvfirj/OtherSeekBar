package com.example.dynamikseekbar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import static com.example.dynamikseekbar.DynamicSeekBar.ROUND_CIRCLE;
import static com.example.dynamikseekbar.DynamicSeekBar.ROUND_RECT;

public class DrawFields {

    protected Paint dPaintSeek;

    private Path dClipWay;

    private RectF dWayProgress;

    private String dRoundMark;

    private CreateFields dFields;

    private boolean dVisibleBackground;

    protected int dColorMark;

    protected int dColorBackground;

    protected int dColorWay;

    protected int dColorProgress;

    public DrawFields(CreateFields fields) {
        dPaintSeek = new Paint(Paint.ANTI_ALIAS_FLAG);
        dClipWay = new Path();
        dWayProgress = new RectF();
        dRoundMark = ROUND_CIRCLE;
        dColorMark = Color.MAGENTA;
        dColorProgress = Color.MAGENTA;
        dColorWay = Color.BLUE;
        dColorBackground = Color.LTGRAY;
        this.dFields = fields;
    }

    public DrawFields visibilityBackground(boolean visible){
        dVisibleBackground = visible;
        return this;
    }

    public DrawFields rounded(String round){
        dRoundMark = round;
        return this;
    }

    public void setColorMark(int dColorMark) {
        this.dColorMark = dColorMark;
    }

    public void setColorBackground(int dColorBackground) {
        this.dColorBackground = dColorBackground;
    }

    public void setColorWay(int dColorWay) {
        this.dColorWay = dColorWay;
    }

    public void setColorProgress(int dColorProgress) {
        this.dColorProgress = dColorProgress;
    }

    public int getColorMark() {
        return dColorMark;
    }

    public int getColorBackground() {
        return dColorBackground;
    }

    public int getColorWay() {
        return dColorWay;
    }

    public int getColorProgress() {
        return dColorProgress;
    }

    public boolean isVisibleBackground(){
        return dVisibleBackground;
    }

    public void drawMark(Canvas canvas,  long location){
        if(dFields==null)drawError(canvas);
        else {
            dPaintSeek.setStyle(Paint.Style.FILL);
            dPaintSeek.setColor(dColorMark);
            if (dRoundMark.equals(ROUND_CIRCLE)) {
                canvas.drawRoundRect(dFields.mark(location), dFields.getRound(), dFields.getRound(), dPaintSeek);
            } else if (dRoundMark.equals(ROUND_RECT)) {
                canvas.drawRect(dFields.mark(location), dPaintSeek);
            }
        }
    }

    public void drawWay(Canvas canvas){
        if(dFields==null)drawError(canvas);
        else {
            dPaintSeek.setStyle(Paint.Style.FILL);
            dPaintSeek.setColor(dColorWay);
            if (dRoundMark.equals(ROUND_CIRCLE)) {
                canvas.drawRoundRect(correctRect(dFields.getWay()), dFields.getRadiusWay(), dFields.getRadiusWay(), dPaintSeek);
            } else if (dRoundMark.equals(ROUND_RECT)) {
                canvas.drawRect(correctRect(dFields.getWay()), dPaintSeek);
            }
        }
    }

    public void drawProgress(Canvas canvas, float location){
        if(dFields==null)drawError(canvas);
        else {
            dPaintSeek.setStyle(Paint.Style.FILL);
            dPaintSeek.setColor(dColorProgress);
            canvas.save();
            canvas.clipPath(clipWay());
            if (dRoundMark.equals(ROUND_CIRCLE)) {
                canvas.drawRoundRect(correctRect(dFields.progress(location)), dFields.getRadiusWay(), dFields.getRadiusWay(), dPaintSeek);

            } else if (dRoundMark.equals(ROUND_RECT)) {
                canvas.drawRect(correctRect(dFields.progress(location)), dPaintSeek);
            }
            canvas.restore();
        }
    }

    public void drawError(Canvas canvas){
        canvas.drawColor(Color.RED);

    }

    public void drawBackground(Canvas canvas ){
        if(dFields==null)drawError(canvas);
        else {
            if (dVisibleBackground) {
                dPaintSeek.setStyle(Paint.Style.FILL);
                dPaintSeek.setColor(dColorBackground);
            } else dPaintSeek.setColor(Color.TRANSPARENT);
            drawRect(canvas, dFields.getBackground());
        }
    }
    public void drawButtons(Canvas canvas){
        if(dFields==null)drawError(canvas);
        else {
            if (!dFields.isVisibleButtons()) return;
            dPaintSeek.setStyle(Paint.Style.FILL);
            dPaintSeek.setColor(dColorMark);
            if (dRoundMark.equals(ROUND_CIRCLE)) {
                canvas.drawRoundRect(dFields.getButton1(), dFields.getRound(), dFields.getRound(), dPaintSeek);
                canvas.drawRoundRect(dFields.getButton2(), dFields.getRound(), dFields.getRound(), dPaintSeek);

            } else if (dRoundMark.equals(ROUND_RECT)) {
                canvas.drawRect(dFields.getButton1(), dPaintSeek);
                canvas.drawRect(dFields.getButton2(), dPaintSeek);

            }
        }
    }

    protected String round() {
        return dRoundMark;
    }

    protected CreateFields fields(){
        return dFields;
    }
    protected RectF correctRect(RectF r){
        float radius = dFields.getRadiusWay()/2;
        if(dFields.getOrientation().equals(DynamicSeekBar.ORIENTATION_VERTICAL)){
            dWayProgress.set(r.left ,r.top-radius,r.right,r.bottom+radius);
        }else {
            dWayProgress.set(r.left -radius,r.top,r.right+radius,r.bottom);
        }
        return dWayProgress;
    }

    private  void drawRect(Canvas canvas, RectF rect){
        if(dRoundMark.equals(ROUND_CIRCLE)){
            canvas.drawRoundRect(rect, dFields.getRound(), dFields.getRound(), dPaintSeek);
        }else {
            canvas.drawRect(rect,dPaintSeek);
        }
    }

    private Path clipWay(){
        dClipWay.reset();
        RectF r = correctRect(dFields.getWay());
        if(dFields.getOrientation().equals(DynamicSeekBar.ORIENTATION_VERTICAL)){
            if (dRoundMark.equals(DynamicSeekBar.ROUND_CIRCLE)) {
                dClipWay.addRoundRect(r, r.height() / 2, r.height() / 2, Path.Direction.CCW);
            } else {
                dClipWay.addRect(r, Path.Direction.CCW);
            }
        }else {
            if (dRoundMark.equals(DynamicSeekBar.ROUND_CIRCLE)) {
                dClipWay.addRoundRect(r, r.width() / 2, r.width() / 2, Path.Direction.CCW);
            } else {
                dClipWay.addRect(r, Path.Direction.CCW);
            }
        }
        return dClipWay;
    }

}
