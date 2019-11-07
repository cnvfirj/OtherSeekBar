# OtherSeekBar

   ![Image alt](https://github.com/cnvfirj/OtherSeekBar/issues/1)

Vertical, with progress and reverse SeekBar. Adding adjustment buttons.

  - Step 1.

        dependencies {
           implementation 'com.github.cnvfirj:dynamikseekbar:0.0.3'
        }
     
  - Step 2.
  
        <com.example.dynamikseekbar.DynamicSeekBar
          android:layout_width="match_parent"
          android:layout_height="50dp"
          android:id="@+id/seek"/>
          
  - Step 3.

        DynamicSeekBar seek = findViewById(R.id.seek);
        seek.setOnSeekBarChangeListener(new DynamicSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(DynamicSeekBar seekBar, int progress, boolean isTouch) {
                
            }

            @Override
            public void onStartTrackingTouch(DynamicSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DynamicSeekBar seekBar) {

            }
        });

- Properties

   1. Set parameters based on orientation:
   
           android:layout_width="match_parent"
           android:layout_height="50dp"
           
   2. default orientation is «horizontal»:
   
           app:orientation="vertical"
           app:orientation="horizontal"
           setOrientation(DynamicSeekBar.ORIENTATION_HORIZONTAL);
           setOrientation(DynamicSeekBar.ORIENTATION_VERTICAL);
           
   3. the default shape is «circle»:
   
           app:rounded="rect"
           app:rounded="circle"
           setRound(DynamicSeekBar.ROUND_CIRCLE);
           setRound(DynamicSeekBar.ROUND_RECT);
           
   4. default direction of progress «stright»:
   
          app:direct="back"
          app:direct="straigth"
          setDirect(DynamicSeekBar.DIRECT_BACK);
          setDirect(DynamicSeekBar.DIRECT_STRAIGHT);
          
  5. progress visibility by default «false»:
  
          app:visible_text="true"
          setVisibleText(true);
          
  6. text size:
  
          app:text_size="10"
          setTextSize(10);
          
  7. side to which the default text is rotated «bottom». Options «top», «left», «right»:
     
          app:gravity_text="top"
          setGravityText(DynamicSeekBar.GRAVITY_BOTTOM);
          setGravityText(DynamicSeekBar.GRAVITY_TOP);
          setGravityText(DynamicSeekBar.GRAVITY_RIGHT);
          setGravityText(DynamicSeekBar.GRAVITY_LEFT);
          
  8. shows buttons for fine-tuning the progress, invisible by default:
  
         app:visible_buttons="true"
         setVisibleButtons(true);
         
  9. while moving progress shows the text on the buttons by default is missing:
  
         app:revers_text="true"
         setReversText(true);
         
  10. the width of the track, as well as progress:
  
          app:radius_way="5"
          setRadiusWay(10);
          
  11. minimum step size, meaning of progress, minimum value, maximum value:
     
          app:step_value="2"
          app:progress_value="50"
          app:min_value="1"
          app:max_value="2000"
          setStepValue(2);
          setProgress(5);
          setMinValue(1);
          setMaxValue(2000);
          
  12. border width:
  
          app:width_bord="2"
          setWidthBord(2);
          
  13. visibility of the background and background border, slider border, buttons border:
  
          app:visible_background="true"
          app:visible_bord_background="true"
          app:visible_bord_mark="true"
          app:visible_bord_buttons="true"
          setVisibleBackground(true);
          visibleBordBackground(true);
          visibleBordMark(true);
          visibleBordButtons(true);
          
  14. pictures for buttons and slider:
   
          app:drw_b1="@drawable/ic_add"
          app:drw_b2="@drawable/ic_add"
          app:drw_mark="@drawable/ic_add"
          setDrawableB1(getResources().getDrawable(R.drawable.ic_add));
          setDrawableB2(getResources().getDrawable(R.drawable.ic_add));
          setDrawableMark(getResources().getDrawable(R.drawable.ic_add));
          
  15. colors for components:
  
          app:color_background="@color/colorPrimary"
          app:color_mark="@color/colorPrimary"
          app:color_progress="@color/colorPrimary"
          app:color_way="@color/colorPrimary"
          app:color_content="@color/colorPrimary"
          app:color_bord_background="@color/colorPrimary"
          app:color_bord_mark="@color/colorPrimary"
          setColorBackground(Color.BLUE);
          setColorMark(Color.BLUE);
          setColorProgress(Color.BLUE);
          setColorWay(Color.BLUE);
          setColorContent(Color.BLUE);
          setColorBordBackground(Color.BLUE);
          setColorBordMark(Color.BLUE);










