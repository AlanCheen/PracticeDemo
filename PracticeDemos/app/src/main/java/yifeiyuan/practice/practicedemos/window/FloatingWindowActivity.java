package yifeiyuan.practice.practicedemos.window;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.periscope.FavorLayout;


/**
 * title : http://gold.xitu.io/entry/5621a9cb60b27457e85c8c07/view
 * <p>
 * 无需 android.permission.SYSTEM_ALERT_WINDOW
 */
public class FloatingWindowActivity extends AppCompatActivity {

    @InjectView(R.id.tv_window)
    TextView mTvWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_window);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v ->

                {

                    FavorLayout layout = new FavorLayout(FloatingWindowActivity.this);
                    ViewGroup group = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    group.addView(layout, lp);

                    layout.setOnClickListener(favor->{
                        layout.addFavor();
                        layout.addFavor();
                        layout.addFavor();
                        layout.addFavor();
                        layout.addFavor();
                        layout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                group.removeView(favor);

                            }
                        },10000);
                    });
                }

        );

        mTvWindow.setOnClickListener(v -> {
            addWindow();
        });
    }

    private void addWindow() {
        //WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager manager = getWindowManager();
//        Point point = new Point();
//        manager.getDefaultDisplay().getSize(point);

        //默认出现在中心
        int w = WindowManager.LayoutParams.MATCH_PARENT;
        int h = WindowManager.LayoutParams.MATCH_PARENT;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(w, h, WindowManager.LayoutParams.TYPE_TOAST, 0, PixelFormat.TRANSLUCENT);

// lp.height = 100;
//        lp.width = 100;
//        lp.horizontalMargin = 20;
//        lp.verticalMargin = 20;
        lp.gravity = Gravity.TOP;
//        lp.x = 50;
//        lp.y = 50;


        View v = new View(FloatingWindowActivity.this);
        v.setBackgroundColor(Color.rgb(122, 121, 122));

        v.setOnClickListener(view -> {
            Toast.makeText(FloatingWindowActivity.this, "CLicked", Toast.LENGTH_SHORT).show();
            manager.removeView(view);
        });
        manager.addView(v, lp);
    }

}
