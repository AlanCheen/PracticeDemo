package yifeiyuan.practice.practicedemos.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.Const;
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

    public static Intent start(Context context,String url) {
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("Url", url);
        return intent;
    }

    String url = Const.URL_GITHUB;
    @Override
    protected void init(Bundle savedInstanceState) {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            url = bundle.getString("Url");
        }
        mWebview.loadUrl(url);

        mWebview.setCallback(new YWebView.Callback() {
            @Override
            public void onReceivedTitle(String title) {
                mToolbar.setTitle(title);
            }

            @Override
            public void onProgressChanged(int newProgress) {
                //todo 做个进度条

            }
        });


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

        switch (id) {
            case R.id.action_open:
                //从其他浏览器打开
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                //Notice resolveActivity 可以判断是否有能接受该action的activity
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }else{
//                }
                startActivity(Intent.createChooser(intent, "请选择浏览器"));
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
