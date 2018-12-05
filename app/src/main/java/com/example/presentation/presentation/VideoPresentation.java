package com.example.presentation.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.example.presentation.R;
import com.example.presentation.util.LogUtil;

public class VideoPresentation extends Presentation {
    private static final String TAG = "VideoPresentation";

    public VideoPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.video_layout);
        LogUtil.i(TAG, "onCreate");
    }
}
