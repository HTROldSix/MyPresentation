package com.example.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.example.presentation.presentation.SlideshowPagePresentation;
import com.example.presentation.presentation.VideoPresentation;
import com.example.presentation.util.LogUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String[] PERMISSIONS_ARRAY = {};
    private static final int OVERLAYS_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_PHONE = 2;

    private DisplayManager mDisplayManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermissions();
        mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        if (displays.length > 1) {
            if (Settings.canDrawOverlays(this)) {
                SlideshowPagePresentation mPresentation = new SlideshowPagePresentation(this, displays[displays.length - 1]);
                mPresentation.show();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, OVERLAYS_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void requestAppPermissions() {
        ArrayList<String> permissionList = new ArrayList<String>();
        for (String permission : PERMISSIONS_ARRAY) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        int size = permissionList.size();
        if (size == 0) {
            return;
        }
        String[] permissionsArray = new String[size];
        for (int i = 0; i < size; i++) {
            permissionsArray[i] = permissionList.get(i);
        }
        requestPermissions(permissionsArray, PERMISSION_REQUEST_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGrant = checkPermissionGrantResults(grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_PHONE: {
                if (!isGrant)
                    finish();
            }
        }
    }

    private boolean checkPermissionGrantResults(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case OVERLAYS_REQUEST_CODE: {
                break;
            }
        }
    }
}
