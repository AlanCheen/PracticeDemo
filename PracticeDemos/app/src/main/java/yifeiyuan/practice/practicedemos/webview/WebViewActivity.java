package yifeiyuan.practice.practicedemos.webview;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;
import yifeiyuan.practice.yutils.HttpUtils;

public class WebViewActivity extends BaseActivity {

    @InjectView(R.id.webview)
    YWebView mWebview;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    String url = "http://www.baidu.com";
    @Override
    protected void init(Bundle savedInstanceState) {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebview.loadUrl(url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int status = HttpUtils.getPageStatusCode(url);
                Log.d(TAG, "init status:" + status);
            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_open) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
