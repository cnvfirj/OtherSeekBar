package com.example.dynamikseekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ProgressBar;

public class DynamicSeekBar extends ProgressBar {

    public static final String GRAVITY_TOP = "top";
    public static final String GRAVITY_BOTTOM = "bottom";
    public static final String GRAVITY_LEFT = "left";
    public static final String GRAVITY_RIGHT = "right";

    public static final String ROUND_CIRCLE = "circle";
    public static final String ROUND_RECT = "rect";

    public static final String ORIENTATION_VERTICAL = "vertical";
    public static final String ORIENTATION_HORIZONTAL = "horizontal";

    public static final String DIRECT_STRAIGHT = "straight";
    public static final String DIRECT_BACK = "back";

    private OnSeekBarChangeListener dListener;

    private CreateFieldsAndBords dFields;

    private TouchEvent dTouch;

    private DrawFieldsAndBords dDrawFields;

    private DrawContent dDrawContent;

    private String dRoundMarc;

    private boolean dVisibleBackground;

    private boolean dReversText;

    private float dProgressValue;

    private float dPointTouchMark;

    private int dMaxValue;

    private int dMinValue;

    private float dStepValue;

    private boolean dExit;

    public DynamicSeekBar(Context context) {
        super(context);
        initDefaultVar();
    }

    public DynamicSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultVar();
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.DynamicSeekBar);
        if(check(t, R.styleable.DynamicSeekBar_orientation))setOrientation(t.getString(R.styleable.DynamicSeekBar_orientation));
        if(check(t, R.styleable.DynamicSeekBar_gravity_text))setGravityText(t.getString(R.styleable.DynamicSeekBar_gravity_text));
        if(check(t, R.styleable.DynamicSeekBar_rounded))setRound(t.getString(R.styleable.DynamicSeekBar_rounded));
        if(check(t, R.styleable.DynamicSeekBar_direct))setDirect(t.getString(R.styleable.DynamicSeekBar_direct));

        if(check(t, R.styleable.DynamicSeekBar_color_mark))setColorMark(t.getInt(R.styleable.DynamicSeekBar_color_mark,dDrawFields.getColorMark()));
        if(check(t, R.styleable.DynamicSeekBar_color_way))setColorWay(t.getInt(R.styleable.DynamicSeekBar_color_way,dDrawFields.getColorWay()));
        if(check(t, R.styleable.DynamicSeekBar_color_progress))setColorProgress(t.getInt(R.styleable.DynamicSeekBar_color_progress,dDrawFields.getColorProgress()));
        if(check(t, R.styleable.DynamicSeekBar_color_background))setColorBackground(t.getInt(R.styleable.DynamicSeekBar_color_background,dDrawFields.getColorBackground()));
        if(check(t, R.styleable.DynamicSeekBar_color_content))setColorContent(t.getInt(R.styleable.DynamicSeekBar_color_content,Color.WHITE));

        if(check(t, R.styleable.DynamicSeekBar_min_value))setMinValue(t.getInt(R.styleable.DynamicSeekBar_min_value,dMinValue));
        if(check(t, R.styleable.DynamicSeekBar_max_value))setMaxValue(t.getInt(R.styleable.DynamicSeekBar_max_value,dMaxValue));
        if(check(t, R.styleable.DynamicSeekBar_progress_value))setProgress(t.getInt(R.styleable.DynamicSeekBar_progress_value,0));
        if(check(t, R.styleable.DynamicSeekBar_step_value))setStepValue(t.getFloat(R.styleable.DynamicSeekBar_step_value,dStepValue));
        if(check(t, R.styleable.DynamicSeekBar_text_size))setTextSize(t.getInt(R.styleable.DynamicSeekBar_text_size,10)
                *context.getApplicationContext().getResources().getDisplayMetrics().density);


        if(check(t, R.styleable.DynamicSeekBar_radius_way))setRadiusWay(t.getFloat(R.styleable.DynamicSeekBar_radius_way,10)
                *context.getApplicationContext().getResources().getDisplayMetrics().density);

        if(check(t, R.styleable.DynamicSeekBar_visible_background))setVisibleBackground(t.getBoolean(R.styleable.DynamicSeekBar_visible_background,false));
        if(check(t, R.styleable.DynamicSeekBar_visible_text))setVisibleText(t.getBoolean(R.styleable.DynamicSeekBar_visible_text,false));
        if(check(t, R.styleable.DynamicSeekBar_visible_buttons))setVisibleButtons(t.getBoolean(R.styleable.DynamicSeekBar_visible_buttons,false));
        if(check(t, R.styleable.DynamicSeekBar_revers_text))setReversText(t.getBoolean(R.styleable.DynamicSeekBar_revers_text,false));

        if(check(t, R.styleable.DynamicSeekBar_drw_mark))setDrawableMark(extDrawable(t.getString(R.styleable.DynamicSeekBar_drw_mark)));
        if(check(t, R.styleable.DynamicSeekBar_drw_b1))setDrawableB1(extDrawable(t.getString(R.styleable.DynamicSeekBar_drw_b1)));
        if(check(t, R.styleable.DynamicSeekBar_drw_b2))setDrawableB2(extDrawable(t.getString(R.styleable.DynamicSeekBar_drw_b2)));

        if(check(t, R.styleable.DynamicSeekBar_visible_bord_background))visibleBordBackground(t.getBoolean(R.styleable.DynamicSeekBar_visible_bord_background,false));
        if(check(t, R.styleable.DynamicSeekBar_visible_bord_buttons))visibleBordButtons(t.getBoolean(R.styleable.DynamicSeekBar_visible_bord_buttons,false));
        if(check(t, R.styleable.DynamicSeekBar_visible_bord_mark))visibleBordMark(t.getBoolean(R.styleable.DynamicSeekBar_visible_bord_mark,false));

        if(check(t, R.styleable.DynamicSeekBar_width_bord))setWidthBord(t.getFloat(R.styleable.DynamicSeekBar_width_bord,0)
                *context.getApplicationContext().getResources().getDisplayMetrics().density);

        if(check(t, R.styleable.DynamicSeekBar_color_bord_mark))setColorBordMark(t.getInt(R.styleable.DynamicSeekBar_color_bord_mark,0));
        if(check(t, R.styleable.DynamicSeekBar_color_bord_background))setColorBordBackground(t.getInt(R.styleable.DynamicSeekBar_color_bord_background,0));


    }

    private boolean check(TypedArray t, int id){
        return t.hasValue(id);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener){
        dListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        dFields.setParams(canvas);
        if(checkParams()) {
            drawBackground(canvas);
            drawWay(canvas);
            drawProgress(canvas);
            drawMark(canvas);
            drawButtons(canvas);
            drawBord(canvas);
        }else {
            drawError(canvas);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
               int touch = dTouch.touch(event);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                actionDown(event,touch);

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                actionUp(event,touch);
                dDrawContent.reversText(false);

            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                actionMove(event,touch);

            }

        invalidate();
        return true;
    }

    private void actionDown(MotionEvent event, int touch){
        if (touch == TouchEvent.TOUCH_BUTTON_1) touchButton1();
        else if (touch == TouchEvent.TOUCH_BUTTON_2) touchButton2();
        else if (touch == TouchEvent.TOUCH_MARK) touchMark(new PointF(event.getX(), event.getY()));
        else if (touch == TouchEvent.TOUCH_WAY) touchWay(new PointF(event.getX(), event.getY()));
    }

    private void actionUp(MotionEvent event, int touch){
        if (touch == TouchEvent.TOUCH_WAY || touch == TouchEvent.TOUCH_MARK) correctPosition();
    }

    private void actionMove(MotionEvent event, int touch){
        if (touch == TouchEvent.TOUCH_WAY || touch == TouchEvent.TOUCH_MARK) translatePosition(new PointF(event.getX(), event.getY()));
        else if(touch== TouchEvent.NON_TOUCH) exit();
    }

    public void setDrawableMark(Drawable d){
        dDrawContent.srcMark(d);
    }

    public void setDrawableButtons(Drawable b1, Drawable b2){
         dDrawContent.srcButtons(b1!=null?b1:dDrawContent.getButton1(),b2!=null?b2:dDrawContent.getButton2());
    }

    public void setDrawableB1(Drawable d){
        dDrawContent.srcButton1(d);
    }

    public void setDrawableB2(Drawable d){
       dDrawContent.srcButton2(d);
    }

    public void setReversText(boolean revers){
        dReversText = revers;
    }

    public  boolean isReversText(){
        return dReversText;
    }

    public void setRadiusWay(float r){
        dFields.radiusWay(r);
        invalidate();
    }

    public float getRadiusWay(){
        return dFields.getRadiusWay();
    }


    public void setColorBordMark(int color){
        dDrawFields.setColorBordMark(color);
    }


    public void setColorBordBackground(int color){
        dDrawFields.setColorBordBackground(color);
    }



    public void setWidthBord(float width){
        dFields.widthBord(width);
      dDrawFields.width(width);
    }

    public void visibleBordButtons(boolean visible){
       dDrawFields.setVisibleBordButtons(visible);
    }

    public void visibleBordBackground(boolean visible){
        dFields.visibleBord(visible);
        dDrawFields.setVisibleBordBackground(visible);
    }

    public void visibleBordMark(boolean visible){
        dDrawFields.setVisibleBordMark(visible);
    }


    public void setVisibleBackground(boolean visible){
        dVisibleBackground = visible;
        dDrawFields.visibilityBackground(dVisibleBackground);
        invalidate();
    }

    public boolean isVisibleBackground(){
        return dVisibleBackground;
    }

    public void setVisibleButtons(boolean visible){
        dFields.buttons(visible);
        invalidate();
    }

    public boolean isVisibleButtons(){
        return dFields.isVisibleButtons();
    }

    public void setDirect(String direct){
        dFields.direct(direct);
        invalidate();
    }

    public String getDirect(){
        return dFields.getDirect();
    }

    public void setOrientation(String orientation){
        dFields.orientation(orientation);
        invalidate();
    }

    public String getOrientation(){
        return dFields.getOrientation();
    }

    public void setStepValue(float value) {
        dStepValue = value;
        invalidate();
    }

    public float getStepValue() {
        return dStepValue;
    }

    public void setMaxValue(int value) {
        dMaxValue = value;
        invalidate();
    }

    public void setMinValue(int value) {
        dMinValue = value;
        setProgressValue(getProgressValue()-getMinValue());
        report(false);
        invalidate();
    }

    public int getMaxValue() {
        return dMaxValue;
    }

    public int getMinValue() {
        return dMinValue;
    }

    public void setRound(String round){
        dRoundMarc = round;
        dDrawFields.rounded(dRoundMarc);
        dDrawContent.rounded(dRoundMarc);
        invalidate();
    }

    public String getRound(){
        return dRoundMarc;
    }

    public void setVisibleText(boolean visible){
        dDrawContent.visibleText(visible);
        invalidate();
    }

    public boolean isVisibleText(){
        return dDrawContent.isVisibleText();
    }

    public void setTextSize(float size){
        dDrawContent.textSize(size);
        invalidate();
    }

    public float getTextSize(){
        return dDrawContent.getTextSize();
    }

    public void setGravityText(String gravity){
        dDrawContent.gravityText(gravity);
        invalidate();
    }

    public String getGravityText(){
        return dDrawContent.getGravity();
    }

    public void setColorContent(int color){
        dDrawContent.colorContent(color);
        invalidate();
    }

    public int getColorContent(){
        return dDrawContent.getColorContent();
    }

    public void setColorProgress(int color){
        dDrawFields.setColorProgress(color);
        invalidate();
    }

    public int getColorProgress(){
        return dDrawFields.getColorProgress();
    }

    public void setColorWay(int color){
        dDrawFields.setColorWay(color);
        invalidate();
    }

    public int getColorWay() {
        return dDrawFields.getColorWay();
    }
    public void setColorBackground(int color){
        dDrawFields.setColorBackground(color);
        invalidate();
    }

    public int getColorBackground(){
        return dDrawFields.getColorBackground();
    }

    public void setColorMark(int color){
        dDrawFields.setColorMark(color);
        invalidate();
    }

    public int getColorMark(){
        return dDrawFields.getColorMark();
    }

    @Override
    public synchronized void setProgress(int progress) {
        setProgressValue(progress-getMinValue());
        report(false);
        invalidate();
    }

    @Override
    public void setProgress(int progress, boolean animate) {
        setProgress(progress);
        report(false);
    }

    @Override
    public synchronized int getProgress() {
            return Math.round(getProgressValue()+getMinValue());
    }

    private void drawError(Canvas canvas){
        canvas.drawColor(Color.RED);
    }


    private void drawBord(Canvas canvas){
        dDrawFields.bordButtons(canvas);
        dDrawFields.bordMark(canvas);
        dDrawFields.bordBackground(canvas);
    }
    private void drawButtons(Canvas canvas){
        if(!dFields.isVisibleButtons())return;
        dDrawFields.drawButtons(canvas);
        dDrawContent.drawContentButtons(getProgress(),canvas);


    }

    private void drawMark(Canvas canvas){
        dDrawFields.drawMark(canvas,getLocation());
        dDrawContent.drawContentMark(getProgress(),canvas);
    }

    private void drawProgress(Canvas canvas){
        dDrawFields.drawProgress(canvas,getLocation());

    }

    private void drawWay(Canvas canvas){
        dDrawFields.drawWay(canvas);

    }

    private void drawBackground(Canvas canvas){
        dDrawFields.drawBackground(canvas);

    }

    private long getLocation(){
        long loc = (long) (dFields.indent()+getPositionPixel());
        if(dFields.getDirect().equals(DIRECT_BACK))return getLength()-loc;

        return loc;
    }


    private float getSegment(PointF p){
        return dFields.getOrientation().equals(ORIENTATION_VERTICAL)?p.y:p.x;
    }

    private void exit(){
        if(dExit) {
            correctPosition();
            dExit = false;

        }

    }
    private void correctPosition(){
        if(getProgressValue()!=Math.round(getProgressValue())||dExit) {
                setProgressValue(Math.round(getProgressValue()));
                report(true);
                reportFin();
                dDrawContent.reversText(false);
        }
    }

    private void touchWay(PointF p){
        dExit = true;
        final float step = dFields.getLengthWay()/getSegmentValue();
        float pix = p.x;
        if(dFields.getOrientation().equals(ORIENTATION_VERTICAL))pix = p.y;
        float value = (pix-dFields.indent())/step;
        if(dFields.getDirect().equals(DIRECT_BACK)) value = (getLength()-(pix + dFields.indent())) / step;
        setProgressValue(value);
        dPointTouchMark = getSegment(p);
        reportStart();
        dDrawContent.reversText(dReversText);
    }

    private void touchMark(PointF p){
        dExit = true;
        dPointTouchMark = getSegment(p);
        reportStart();
        dDrawContent.reversText(dReversText);
    }

    private void translatePosition(PointF point) {
        if (dExit){
        float step = getSegment(point) - dPointTouchMark;
        float loc = getLocation() + step;

        float stepPix = dFields.getLengthWay() / getSegmentValue();

        float value = (loc - dFields.indent()) / stepPix;
        if (dFields.getDirect().equals(DIRECT_BACK))
            value = (getLength() - (loc + dFields.indent())) / stepPix;

        int old = Math.round(getProgressValue());
        setProgressValue(value);
        if (old != Math.round(getProgressValue())) report(true);

        dPointTouchMark = getSegment(point);
        dDrawContent.reversText(dReversText);
    }

    }

    private void touchButton1(){
        dExit = true;
        float step = getProgressValue()-getStepValue();
        if(dFields.getDirect().equals(DIRECT_BACK))step = getProgressValue()+getStepValue();
        setProgressValue(step);
        report(true);
    }

   private void touchButton2(){
       dExit = true;
       float step = getProgressValue()+getStepValue();
       if(dFields.getDirect().equals(DIRECT_BACK))step = getProgressValue()-getStepValue();
       setProgressValue(step);
       report(true);
    }

    private void report(boolean user){
        if(dListener!=null){
            dListener.onProgressChanged(this,getProgress(),user);
        }
    }

    private void reportStart(){
        if(dListener!=null)dListener.onStartTrackingTouch(this);
    }

    private void reportFin(){
        if(dListener!=null)dListener.onStopTrackingTouch(this);
    }


    private float getPositionPixel(){
        return getProgressValue()*getStepPixels();
    }

    private float getStepPixels(){
        return dFields.getLengthWay() / getSegmentValue();
    }

    private long getLength(){
        return dFields.getLength();
    }

    private void setProgressValue(float progress){
        if(progress<0)dProgressValue=0;
        else if(progress>getSegmentValue())dProgressValue=getSegmentValue();
        else dProgressValue = progress;
    }


    private float getProgressValue(){

        if(dProgressValue<0)dProgressValue=0;
        if(dProgressValue>getSegmentValue())dProgressValue=getSegmentValue();
        return dProgressValue;
   }

   private int getSegmentValue(){
        return getMaxValue()-getMinValue();
   }
    private boolean checkParams(){
        if(dFields.isVisibleButtons()){
            if (dFields.getOrientation().equals(ORIENTATION_VERTICAL)) {
                return getHeight() >= getWidth()*3;
            } else if (dFields.getOrientation().equals(ORIENTATION_HORIZONTAL)) {
                return getWidth() >= getHeight()*3;
            }
        }else {
            if (dFields.getOrientation().equals(ORIENTATION_VERTICAL)) {
                return getHeight() >= getWidth();
            } else if (dFields.getOrientation().equals(ORIENTATION_HORIZONTAL)) {
                return getWidth() >= getHeight();
            }
        }

        return false;
    }

    private Drawable extDrawable(String path){
        String[] names = getNames(path);
        int i = getContext().getResources().getIdentifier(names[0],names[1],getContext().getPackageName());
        return getContext().getDrawable(i);
    }

    private String[] getNames(String path){
       String[]arr = path.split("[./-]");
       return new String[]{arr[arr.length-2],arr[1]};
    }

    private void initDefaultVar(){
        dFields = CreateFieldsAndBords.get();
        dTouch = new TouchEvent(dFields);
        dDrawFields = new DrawFieldsAndBords(dFields);
        dDrawFields.initDefColors(getContext());
        dDrawContent = new DrawContent(dFields);
        dFields.buttons(false).radiusWay(10);
        dVisibleBackground = true;
        dRoundMarc = ROUND_CIRCLE;
        dMaxValue = 100;
        dMinValue = 0;
        dProgressValue = 0;
        dStepValue = 1;
    }

    public static void MASSAGE(String t){
        Log.d("________DYNAMIK", t);
    }

    public interface OnSeekBarChangeListener{;

        void onProgressChanged(DynamicSeekBar seekBar, int progress, boolean isTouch);

        void onStartTrackingTouch(DynamicSeekBar seekBar);

        void onStopTrackingTouch(DynamicSeekBar seekBar);
    }

}
