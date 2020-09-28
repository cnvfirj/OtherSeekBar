package com.example.dynamikseekbar.dagger;


import com.example.dynamikseekbar.CreateFieldsAndBords;
import com.example.dynamikseekbar.DrawContent;
import com.example.dynamikseekbar.DrawFieldsAndBords;
import com.example.dynamikseekbar.TouchEvent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SeekModule.class})
public interface AllComponents {
    CreateFieldsAndBords fields();
    @Singleton
    TouchEvent touch();
    @Singleton
    DrawFieldsAndBords drawFields();
    @Singleton
    DrawContent drawContent();
}
