package yifeiyuan.practice.practicedemos.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import yifeiyuan.practice.practicedemos.R;

public class CameraActivity extends AppCompatActivity {

    public static final String TAG = "CameraActivity";
    private File mUriFile;
    private static final int CAMERA_PIC = 1;
    private static final int CROP_PIC = 2;

    private ImageView mIvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        mIvResult = (ImageView) findViewById(R.id.iv_result);

    }


    private void takePic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mUriFile = new File(Environment.getExternalStorageDirectory() + "/Download/" + System.currentTimeMillis());
        try {
            mUriFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mUriFile));
        startActivityForResult(intent, CAMERA_PIC);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: " + "requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        switch (requestCode) {
            case CAMERA_PIC:
                cropPhoto(Uri.fromFile(mUriFile));
                break;
            case CROP_PIC:
               Bitmap obj =  data.getParcelableExtra("data");
                if (null != obj) {
                    mIvResult.setImageBitmap(obj);
                }else {
//                    obj = BitmapFactory.decodeFile(mUriFile.getAbsolutePath());
//                    if (null != obj) {
//                        mIvResult.setImageBitmap(obj);
//                    }
                    Uri uri = data.getData();
                    if (null != uri) {
                        Toast.makeText(CameraActivity.this, "URI 不为null" + uri.getPath(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 144);//5.1.1 两个outputX设置成一样就奔溃 坑爹啊
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_PIC);
    }

}
