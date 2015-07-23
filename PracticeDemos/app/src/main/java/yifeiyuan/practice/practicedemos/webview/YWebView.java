package yifeiyuan.practice.practicedemos.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by alanchen on 15/7/23.
 *
 * Notice 直接loadurl 不走 shouldOverrideUrlLoading
 *
 * 当点击网页里的一个链接,伪流程:    ...代表多个
 * shouldOverrideUrlLoading
 * onPageStarted
 * onProgressChanged
 * onLoadResource...
 * onReceivedTitle
 * onProgressChanged...
 * onPageFinished
 *
 */
public class YWebView extends WebView {

    public static final String TAG = YWebView.class.getSimpleName();
    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;

    public YWebView(Context context) {
        this(context, null);
    }

    public YWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mWebChromeClient = new YWebChromeClient();
        mWebViewClient = new YWebViewClient();

        setWebChromeClient(mWebChromeClient);
        setWebViewClient(mWebViewClient);

    }

    private class YWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.d(TAG, "onProgressChanged() called with " + "view = [" + view + "], newProgress = [" + newProgress + "]");
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.d(TAG, "onReceivedTitle() called with " + "view = [" + view + "], title = [" + title + "]");
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(TAG, "onJsAlert() called with " + "view = [" + view + "], url = [" + url + "], message = [" + message + "], result = [" + result + "]");
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d(TAG, "onConsoleMessage() called with " + "consoleMessage = [" + consoleMessage + "]");
            return super.onConsoleMessage(consoleMessage);
        }
    }


    private class YWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading() called with " + "view = [" + view + "], url = [" + url + "]");
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "onPageStarted() called with " + "view = [" + view + "], url = [" + url + "], favicon = [" + favicon + "]");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished() called with " + "view = [" + view + "], url = [" + url + "]");
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d(TAG, "onLoadResource() called with " + "view = [" + view + "], url = [" + url + "]");
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d(TAG, "onReceivedError() called with " + "view = [" + view + "], errorCode = [" + errorCode + "], description = [" + description + "], failingUrl = [" + failingUrl + "]");
            if (ERROR_FILE_NOT_FOUND == errorCode) {
                Toast.makeText(view.getContext(),"加载出错", Toast.LENGTH_SHORT).show();
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    public interface CallBack{

    }
}