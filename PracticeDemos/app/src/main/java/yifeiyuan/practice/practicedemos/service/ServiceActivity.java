package yifeiyuan.practice.practicedemos.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import yifeiyuan.practice.practicedemos.DownloadService;
import yifeiyuan.practice.practicedemos.R;

public class ServiceActivity extends AppCompatActivity {

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

}
