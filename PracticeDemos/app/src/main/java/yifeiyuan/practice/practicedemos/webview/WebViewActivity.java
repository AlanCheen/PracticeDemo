package yifeiyuan.practice.practicedemos.webview;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.BaseActivity;

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
//    String url = "http://www.baidu.com";
//    String url = "http://www.baidu.com";
    @Override
    protected void init(Bundle savedInstanceState) {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        mWebview.loadUrl("http://www.jianshu.com/users/ec59bd61433a/latest_articles");
//        mWebview.loadUrl("http://www.baidu.com");
        mWebview.loadUrl(url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int status = getRespStatus(url);
                Log.d(TAG, "init status:" + status);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int netstatus = getPageStatus("http://www.baiduadfadfs.com");
                Log.d(TAG, "run netstatus:"+netstatus);
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

    /**
     * 需要异步
     *
     * @param url
     * @return
     */
    private int getRespStatus(String url) {
        int status = -1;
        try {
            HttpHead head = new HttpHead(url);
            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(head);
            status = resp.getStatusLine().getStatusCode();
            Log.d(TAG, "getRespStatus status:"+status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }


    private int getPageStatus(String url) {

        int status = -1;
        try {
            HttpURLConnection.setFollowRedirects(false);
            URL testURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) testURL
                    .openConnection();
            con.setRequestMethod("HEAD");
            status = con.getResponseCode();
            Log.d(TAG, "getPageStatus status:"+status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


}
