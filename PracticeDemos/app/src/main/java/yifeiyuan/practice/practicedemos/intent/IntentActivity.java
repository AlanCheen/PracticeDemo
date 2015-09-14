package yifeiyuan.practice.practicedemos.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yifeiyuan.practice.practicedemos.R;

public class IntentActivity extends AppCompatActivity {

    @InjectView(R.id.tv_xs)
    TextView mTvXs;
    @InjectView(R.id.tv_ys)
    TextView mTvYs;
    @InjectView(R.id.tv_scheme)
    TextView mTvScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_one);
        ButterKnife.inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_intent_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_scheme,R.id.tv_xs,R.id.tv_ys})
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.tv_scheme:
                intent.setAction("yifeiyuan.practice.test");
                intent.setData(Uri.parse("yifeiyuan://practice.practicedemos:1314"));
                intent.putExtra("data", "这个是通过scheme跳转的");
                break;
            case R.id.tv_xs:
                intent.setClass(IntentActivity.this, SchemeHPActivity.class);
                intent.putExtra("data", "这个是通过显示跳转的");
                break;
            case R.id.tv_ys:
                intent.setAction("yifeiyuan.practice.test");
                intent.putExtra("data", "这个是通过隐式跳转的");
                break;

            case R.id.tv_pkg:
                getPackageManager().getLaunchIntentForPackage("com.sina.weibo");
                break;

        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Toast.makeText(IntentActivity.this, "找不到对应的Activity", Toast.LENGTH_SHORT).show();
        }
    }

}
