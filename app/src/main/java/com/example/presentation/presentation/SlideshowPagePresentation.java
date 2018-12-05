package com.example.presentation.presentation;

import android.app.Presentation;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.example.presentation.R;
import com.example.presentation.util.LogUtil;

public class SlideshowPagePresentation extends Presentation {
    private static final String TAG = "SlideshowPagePresentation";

    public SlideshowPagePresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.slideshow_layout);
        LogUtil.i(TAG, "onCreate");
        GLSurfaceView glv = (GLSurfaceView) findViewById(R.id.gl_root_view);
    }
}
