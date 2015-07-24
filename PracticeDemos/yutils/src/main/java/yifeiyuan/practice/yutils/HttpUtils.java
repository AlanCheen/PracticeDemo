package yifeiyuan.practice.yutils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by yifeiyuan on 15/7/23.
 */
public class HttpUtils {

    public static final String TAG = "HttpUtils";

    /**
     * 可以提前获取网页的 statusCode
     * Notice 需要异步
     * @param url 网页的url
     * @return   statusCode 默认-1
     */
    public static int getPageStatusCode(String url) {
        int status = -1;
        try {
            HttpHead head = new HttpHead(url);
            HttpClient client = new DefaultHttpClient();
            HttpResponse resp = client.execute(head);
            status = resp.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
}
