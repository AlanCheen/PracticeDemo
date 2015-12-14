package yifeiyuan.practice.practicedemos.surfaceview;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

public class GlsurfaceActivity extends AppCompatActivity {


    public static final String TAG = "GlsurfaceActivity";
    @InjectView(R.id.gl_sv)
    GLSurfaceView mGlSv;

    MyRenderer mRender;
    MyRunnable mRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glsurface);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isOn = true;
                mGlSv.queueEvent(mRunnable);
            }
        });

        mRunnable = new MyRunnable();

        mRender = new MyRenderer();
        mGlSv.setRenderer(mRender);

    }


    @Override
    protected void onPause() {
        super.onPause();
        isOn = false;
        mGlSv.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlSv.onResume();
    }

    private boolean isOn = false;

    private class MyRunnable implements Runnable{
        @Override
        public void run() {
            while (isOn) {

            }
        }
    }


    private class MyRenderer implements GLSurfaceView.Renderer{

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }
}
