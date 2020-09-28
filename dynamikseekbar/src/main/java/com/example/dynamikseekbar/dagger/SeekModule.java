package com.example.dynamikseekbar.dagger;


import android.graphics.RectF;

import com.example.dynamikseekbar.CreateFieldsAndBords;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SeekModule {

    @Provides
    @Singleton
    CreateFieldsAndBords getFields(){
        return new CreateFieldsAndBords(new RectF(),new RectF(),new RectF(),new RectF(),new RectF(),new RectF());
    }
}
