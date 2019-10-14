package com.example.dynamikseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import static com.example.dynamikseekbar.DynamicSeekBar.ROUND_CIRCLE;
import static com.example.dynamikseekbar.DynamicSeekBar.ROUND_RECT;

public class DrawFieldsAndBords extends DrawFields {

    private boolean dVisibleBordBackground;

    private boolean dVisibleBordButtons;

    private boolean dVisibleBordMark;

    private float dWidthBord;

    private int dColorBordBackground;

    private int dColorBordMark;


    public DrawFieldsAndBords(CreateFieldsAndBords fields) {
        super(fields);
        dWidthBord = dFields.getBord();
        dColorBordBackground = 0;
        dColorBordMark = 0;
    }

    @Override
    public void drawMark(Canvas canvas, long location) {
        if(dVisibleBordBackground){
                dPaintSeek.setStyle(Paint.Style.FILL);
                dPaintSeek.setColor(dColorMark);
                if (dRoundMark.equals(ROUND_CIRCLE)) {
                    canvas.drawRoundRect(correctInBord(dFields.mark(location)), dFields.getRound(), dFields.getRound(), dPaintSeek);
                } else if (dRoundMark.equals(ROUND_RECT)) {
                    canvas.drawRect(correctInBord(dFields.mark(location)), dPaintSeek);
                }

        }else
            super.drawMark(canvas, location);
    }

    @Override
    public void drawButtons(Canvas canvas) {
        if(dVisibleBordBackground){
            if (!dFields.isVisibleButtons()) return;
            dPaintSeek.setStyle(Paint.Style.FILL);
            dPaintSeek.setColor(dColorMark);
            if (dRoundMark.equals(ROUND_CIRCLE)) {
                canvas.drawRoundRect(correctInBord(dFields.getButton1()), dFields.getRound(), dFields.getRound(), dPaintSeek);
                canvas.drawRoundRect(correctInBord(dFields.getButton2()), dFields.getRound(), dFields.getRound(), dPaintSeek);

            } else if (dRoundMark.equals(ROUND_RECT)) {
                canvas.drawRect(correctInBord(dFields.getButton1()), dPaintSeek);
                canvas.drawRect(correctInBord(dFields.getButton2()), dPaintSeek);

            }
        }else super.drawButtons(canvas);
    }

    public void initDefColors(Context c){
       dColorBordBackground = c.getResources().getColor(R.color.colorBackground);
       dColorMark = c.getResources().getColor(R.color.colorWay);
       dColorProgress =  c.getResources().getColor(R.color.colorMark);
       dColorWay = c.getResources().getColor(R.color.colorWay);
       dColorBordMark = c.getResources().getColor(R.color.colorMark);
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

    public void bordMark(Canvas canvas){
        if(dVisibleBordMark&&dColorBordMark!=0&&dWidthBord>0){
            dPaintSeek.setColor(dColorBordMark);
            dPaintSeek.setStyle(Paint.Style.STROKE);
            dPaintSeek.setStrokeWidth(dWidthBord);
            drawRect(canvas,correctInBord(fields().getMark()));
        }
    }

    public void bordButtons(Canvas canvas){
        if(fields().isVisibleButtons()&&dVisibleBordButtons&&dColorBordMark!=0&&dWidthBord>0){
            dPaintSeek.setColor(dColorBordMark);
            dPaintSeek.setStyle(Paint.Style.STROKE);
            dPaintSeek.setStrokeWidth(dWidthBord);
            drawRect(canvas,correctInBord(fields().getButton1()));
            drawRect(canvas,correctInBord(fields().getButton2()));
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


    private RectF correctInBord(RectF r){
        if(dVisibleBordBackground) return new RectF(r.left+dWidthBord,r.top+dWidthBord,r.right-dWidthBord,r.bottom-dWidthBord);
        else return r;
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
