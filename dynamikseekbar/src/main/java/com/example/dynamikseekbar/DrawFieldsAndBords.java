package com.example.dynamikseekbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class DrawFieldsAndBords extends DrawFields {

    private boolean dVisibleBordBackground;

    private boolean dVisibleBordButtons;

    private boolean dVisibleBordMark;

    private float dWidthBord;

    private int dColorBordBackground;

    private int dColorBordMark;


    public DrawFieldsAndBords(CreateFields fields) {
        super(fields);
        dWidthBord = 0;
        dColorBordBackground = 0;
        dColorBordMark = 0;
    }

    public void width(float width){
        dWidthBord = width;
    }

    public void setVisibleBordBackground(boolean dVisibleBordBackground) {
        this.dVisibleBordBackground = dVisibleBordBackground;
    }

    public void setVisibleBordButtons(boolean dVisibleBordButtons) {
        this.dVisibleBordButtons = dVisibleBordButtons;
    }

    public void setVisibleBordMark(boolean dVisibleBordMark) {
        this.dVisibleBordMark = dVisibleBordMark;
    }

    public void setColorBordBackground(int dColorBordBackground) {
        this.dColorBordBackground = dColorBordBackground;
    }

    public void setColorBordMark(int dColorBordMark) {
        this.dColorBordMark = dColorBordMark;
    }

    public boolean isDrawBords(){
        return dWidthBord>0;
    }

    public void bordMark(Canvas canvas){
        if(dVisibleBordMark&&dColorBordMark!=0&&dWidthBord>0){
            dPaintSeek.setColor(dColorBordMark);
            dPaintSeek.setStyle(Paint.Style.STROKE);
            dPaintSeek.setStrokeWidth(dWidthBord);
            drawRect(canvas,fields().getMark());
        }
    }

    public void bordButtons(Canvas canvas){
        if(fields().isVisibleButtons()&&dVisibleBordButtons&&dColorBordMark!=0&&dWidthBord>0){
            dPaintSeek.setColor(dColorBordMark);
            dPaintSeek.setStyle(Paint.Style.STROKE);
            dPaintSeek.setStrokeWidth(dWidthBord);
            drawRect(canvas,fields().getButton1());
            drawRect(canvas,fields().getButton2());
        }
    }

    public void bordBackground(Canvas canvas){
        if(dVisibleBordBackground&&dColorBordBackground!=0&&dWidthBord>0){
            dPaintSeek.setColor(dColorBordBackground);
            dPaintSeek.setStyle(Paint.Style.STROKE);
            dPaintSeek.setStrokeWidth(dWidthBord);
            drawRect(canvas,fields().getBackground());
        }
    }





    private void drawRect(Canvas canvas, RectF r) {
        if (round().equals(DynamicSeekBar.ROUND_RECT)) {
            canvas.drawRect(shiftRect(r), dPaintSeek);
        } else {
            canvas.drawRoundRect(shiftRect(r), shiftRound(), shiftRound(), dPaintSeek);

        }
    }
    private float shiftRound(){
        return fields().getRound()-correct();
    }

    private RectF shiftRect(RectF r){
        return new RectF(r.left+correct(),r.top+correct(),r.right-correct(),r.bottom-correct());
    }

    private float correct(){
        return dWidthBord/2;
    }
}
