package yifeiyuan.practice.practicedemos.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.DownloadService;
import yifeiyuan.practice.practicedemos.R;

public class ServiceActivity extends AppCompatActivity {

    public static final String TAG = "MyService";
    @InjectView(R.id.start_service)
    TextView mStartService;
    @InjectView(R.id.stop_service)
    TextView mStopService;
    @InjectView(R.id.bind_service)
    TextView mBindService;
    @InjectView(R.id.unbind_service)
    TextView mUnbindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", v -> {
                    Intent intent = new Intent();
                    intent.putExtra(DownloadService.EXTRA_PATH, "http://download.fir.im/v2/app/install/559d229d692d791592000016?download_token=6adb9ccc081979064c9d453b1a9d7bf5");
                    intent.setClass(this, DownloadService.class);
                    startService(intent);
                }).show());

    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.bind_service, R.id.unbind_service})
    public void onClick(View view) {

        Intent intent = new Intent(ServiceActivity.this,MyService.class);
        switch (view.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                bindService(intent,con, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(con);
                break;
        }
    }


    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() called with: " + "name = [" + name + "], service = [" + service + "]");
            ((MyService.MyBinder)service).dosth();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() called with: " + "name = [" + name + "]");
        }
    };

}
