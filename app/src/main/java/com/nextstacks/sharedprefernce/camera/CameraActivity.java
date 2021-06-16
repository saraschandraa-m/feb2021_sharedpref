package com.nextstacks.sharedprefernce.camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.nextstacks.sharedprefernce.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    private int cameraID;

    private FrameLayout mCameraFrame;
    private Camera camera;
    private ImageView mIvPreviewImge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mCameraFrame = findViewById(R.id.camera_holder);

        ImageView mIvFlipCamera = findViewById(R.id.iv_camera_flip);
        ImageView mIvCaptureImage = findViewById(R.id.iv_camera_capture);
        mIvPreviewImge = findViewById(R.id.iv_image_preview);

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
//                        mIvPreviewImge.setImageBitmap(displayImgBitmap);
//                        camera.startPreview();
                        storageImageToDevice(displayImgBitmap);
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

    private void storageImageToDevice(Bitmap bitmap) {
        File appDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "CameraAppNextstacks");

        if (!appDirectory.exists()) {
            appDirectory.mkdir();
        }

        File imgFile = new File(appDirectory, "IMG_" + System.currentTimeMillis() + ".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mIvPreviewImge.setImageBitmap(bitmap);
        camera.startPreview();

    }

    private void readImagesFromDevice() {
        Uri imageURI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};

        ArrayList<String> imagePaths = new ArrayList<>();

        Cursor cursor = getApplicationContext().getContentResolver().query(imageURI, projection, null, null, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String image = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                imagePaths.add(image);
            }
        }
    }
}