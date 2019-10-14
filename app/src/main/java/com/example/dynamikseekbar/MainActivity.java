package com.example.dynamikseekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DynamicSeekBar seek1 = findViewById(R.id.seek1);
        final DynamicSeekBar seek2 = findViewById(R.id.seek2);
        seek1.setOnSeekBarChangeListener(new DynamicSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(DynamicSeekBar seekBar, int progress, boolean isTouch) {
                  seek2.setProgress(progress*15);
            }

            @Override
            public void onStartTrackingTouch(DynamicSeekBar seekBar) {
                  seek2.setVisibleButtons(false);
            }

            @Override
            public void onStopTrackingTouch(DynamicSeekBar seekBar) {
                 seek2.setVisibleButtons(true);
            }
        });
    }
}
