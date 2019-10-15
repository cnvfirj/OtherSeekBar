# OtherSeekBar
First Start Seek
Vertical, with progress and reverse SeekBar. Adding adjustment buttons.

  - Step 1.

        dependencies {
           implementation 'com.github.cnvfirj:dynamikseekbar:0.0.1'
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
