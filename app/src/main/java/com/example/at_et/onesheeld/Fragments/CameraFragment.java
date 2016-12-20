package com.example.at_et.onesheeld.Fragments;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.at_et.onesheeld.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by at-et on 10/27/16.
 */

public class CameraFragment extends Fragment implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceView surfaceView;
    FloatingActionButton captureBtn;
    SurfaceHolder surfaceHolder;
    Camera.PictureCallback jpegCallBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.camera, container, false);
        captureBtn = (FloatingActionButton) rootView.findViewById(R.id.capture);
        surfaceView = (SurfaceView) rootView.findViewById(R.id.surface);
        surfaceHolder = surfaceView.getHolder();
        //install a surfaceHolder.CallBack so we get notified when the underlying surface is created and destroyed
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraImage();
            }
        });
        jpegCallBack = new Camera.PictureCallback() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                FileOutputStream outputStream = null;
                File fileImage = getDir();
                if (!fileImage.exists() && !fileImage.mkdir()) {
                    Toast.makeText(getContext(), "Can't create dir t save image", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
                String date = simpleDateFormat.format(new Date());
                String photoFile = "Cam_Demo" + date + ".jpg";
                String fileName = fileImage.getAbsolutePath() + "/" + photoFile;
                File picFile = new File(fileName);
                try {
                    outputStream = new FileOutputStream(picFile);
                    outputStream.write(bytes);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "picture saved", Toast.LENGTH_SHORT).show();
                refreshCamera();
//                refreshGallery(picFile);
            }
        };

        return rootView;

    }

    //        private void refreshGallery(File file){
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        intent.setData(Uri.fromFile(file));
//
//        sendBroadcast(intent);
//    }
    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        //stop preview before making changes
        try {
            camera.startPreview();
        } catch (Exception e) {

        }
        //set preview size and make any resize , rotate or reformatting changes here
        //start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDir() {
        File disc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(disc, "Camera_Demo");
    }

    public void cameraImage() {
        camera.takePicture(null, null, jpegCallBack);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        //open the camera
        try {
            camera = Camera.open();
        } catch (RuntimeException e) {

        }
        Camera.Parameters parameters;
        parameters = camera.getParameters();
        //modifying the parameters
        parameters.setPreviewFrameRate(20);
        parameters.setPreviewSize(1920, 1080);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (Exception e) {

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //stop preview and release camera
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}
