package com.nextstacks.sharedprefernce.camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.nextstacks.sharedprefernce.R;

public class CameraActivity extends AppCompatActivity {

    private int cameraID;

    private FrameLayout mCameraFrame;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mCameraFrame = findViewById(R.id.camera_holder);

        ImageView mIvFlipCamera = findViewById(R.id.iv_camera_flip);
        ImageView mIvCaptureImage = findViewById(R.id.iv_camera_capture);
        ImageView mIvPreviewImge = findViewById(R.id.iv_image_preview);

        mIvFlipCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    camera.stopPreview();

                    if (cameraID == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        startCamera(false);
                    } else {
                        startCamera(true);
                    }
                }
            }
        });

        mIvCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Bitmap displayImgBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        mIvPreviewImge.setImageBitmap(displayImgBitmap);
                        camera.startPreview();
                    }
                });
            }
        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startCamera(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 345);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startCamera(true);
            } else {
                Toast.makeText(CameraActivity.this, "User Denied Permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startCamera(boolean isBackCamera) {
        cameraID = isBackCamera ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;


        camera = Camera.open(cameraID);
        CameraSurfaceView surfaceView = new CameraSurfaceView(this, camera);
        mCameraFrame.addView(surfaceView);
    }
}