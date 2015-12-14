package yifeiyuan.practice.practicedemos.thirdparty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aService;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

public class TwitterActivity extends AppCompatActivity {

    private static final String TAG = "TwitterActivity";
    @InjectView(R.id.btn_twitter)
    TwitterLoginButton mBtnTwitter;
    @InjectView(R.id.tv_result)
    TextView mTvResult;
    @InjectView(R.id.tv_custom)
    TextView mTvCustom;
    @InjectView(R.id.tv_info)
    TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TweetComposer.Builder builder = new TweetComposer.Builder(TwitterActivity.this)
                        .text("just setting up my Fabric.  http://www.baidu.com  ")
                        //必须本地文件  坑
                        .image(Uri.parse("http://cdn.myanimelist.net/s/common/uploaded_files/1444594417-07dca13b3dad5ede2c548ddb26158c8f.jpeg"));
//                        .image(myImageUri);
                try {
                    builder.url(new URL("http://www.baidu.com"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                builder.show();
            }
        });
        setUpTwitterButton();

        mTvCustom.setOnClickListener(v -> {
            new TwitterAuthClient().authorize(TwitterActivity.this, new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    String name = result.data.getUserName();
                    long userId = result.data.getUserId();//3313987080
                    long id = result.data.getId();
                    TwitterAuthToken token = result.data.getAuthToken();
                    String secret = token.secret;
                    String strToken = token.token;

                    StringBuilder sb = new StringBuilder();
                    sb.append("\nTwitter success:")
                            .append("bookName:")
                            .append(name + "\n")
                            .append("userId:")
                            .append(userId + "\n")
                            .append("id:")
                            .append(id + "\n")
                            .append("secret:")
                            .append(secret + "\n")
                            .append("strToken:")
                            .append(strToken + "\n")
                    ;
                    Log.d("Twitter", "success: name:" + name + "\n" + userId + ":" + userId + "\n"
                                    + "id:" + id + "\n"
                                    + "secret" + ":" + secret + "\n"
                                    + "strToken" + ":" + strToken + "\n"
                    );
                    mTvResult.setText(sb.toString());

                    getInfo(token);


                }

                @Override
                public void failure(TwitterException e) {
                    Log.d(TAG, "failure() called with: " + "e = [" + e + "]");
                }
            });
        });
    }

    private void setUpTwitterButton() {
        mBtnTwitter.setCallback(new Callback<TwitterSession>() {//userName alancheen06   secret  4H6cMSpc59EbLSlcQ5QI0SzAUXAAZnxDZ40KUqQkJkl7s  token 3313987080-L3UPicTGjEZrNjpjsobeDQRCbWGFyczUCV2O408
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d("Twitter", "success() called with: " + "result = [" + result + "]++++data" + result.data);

                String name = result.data.getUserName();
                long userId = result.data.getUserId();//3313987080
                long id = result.data.getId();
                TwitterAuthToken token = result.data.getAuthToken();
                String secret = token.secret;
                String strToken = token.token;

//                TypedInput input = result.response.getBody(); Notice result.response为null
//                input.length();
//                input.mimeType();
//                String url = result.response.getUrl();
//                String reason = result.response.getReason();
//                int status = result.response.getStatus();
//                List<Header> header =  result.response.getHeaders();

                StringBuilder sb = new StringBuilder();
                sb.append("\nTwitter success:")
                        .append("name:")
                        .append(name + "\n")
                        .append("userId:")
                        .append(userId + "\n")
                        .append("id:")
                        .append(id + "\n")
                        .append("secret:")
                        .append(secret + "\n")
                        .append("strToken:")
                        .append(strToken + "\n")
                ;
                Log.d("Twitter", sb.toString());
                mTvResult.setText(sb.toString());
                getInfo(token);
            }

            //Cannonball
            @Override
            public void failure(TwitterException exception) {
                Log.d("Twitter", "failure() called with: " + "exception = [" + exception.getMessage() + "]");
            }
        });
    }

    /***
     * {"id":3313987080,"id_str":"3313987080","bookName":"\u7a0b\u5e8f\u4ea6\u975e\u733fAlan","screen_name":"alancheen06","location":"","profile_location":null,"description":"An android developer from China.","url":null,"entities":{"description":{"urls":[]}},"protected":false,"followers_count":1,"friends_count":34,"listed_count":0,"created_at":"Thu Aug 13 05:29:54 +0000 2015","favourites_count":2,"utc_offset":-25200,"time_zone":"Pacific Time (US & Canada)","geo_enabled":false,"verified":false,"statuses_count":13,"lang":"en","status":{"created_at":"Mon Oct 12 04:23:09 +0000 2015","id":653425648577286144,"id_str":"653425648577286144","text":"@Support How to add phone num?Thanks a lot!","source":"\u003ca href=\"http:\/\/twitter.com\" rel=\"nofollow\"\u003eTwitter Web Client\u003c\/a\u003e","truncated":false,"in_reply_to_status_id":null,"in_reply_to_status_id_str":null,"in_reply_to_user_id":17874544,"in_reply_to_user_id_str":"17874544","in_reply_to_screen_name":"Support","geo":null,"coordinates":null,"place":null,"contributors":null,"retweet_count":0,"favorite_count":1,"entities":{"hashtags":[],"symbols":[],"user_mentions":[{"screen_name":"Support","bookName":"Twitter Support","id":17874544,"id_str":"17874544","indices":[0,8]}],"urls":[]},"favorited":false,"retweeted":false,"lang":"en"},"contributors_enabled":false,"is_translator":false,"is_translation_enabled":false,"profile_background_color":"C0DEED","profile_background_image_url":"http:\/\/abs.twimg.com\/images\/themes\/theme1\/bg.png","profile_background_image_url_https":"https:\/\/abs.twimg.com\/images\/themes\/theme1\/bg.png","profile_background_tile":false,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/631699830872498176\/yThxaLUe_normal.jpg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/631699830872498176\/yThxaLUe_normal.jpg","profile_link_color":"0084B4","profile_sidebar_border_color":"C0DEED","profile_sidebar_fill_color":"DDEEF6","profile_text_color":"333333","profile_use_background_image":true,"has_extended_profile":false,"default_profile":true,"default_profile_image":false,"following":false,"follow_request_sent":false,"notifications":false}
     * @param token
     */
    private void getInfo(final TwitterAuthToken token) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
                OAuthSigning oauthSigning = new OAuthSigning(authConfig, token);
                Map<String, String> authHeaders = oauthSigning.getOAuthEchoHeadersForVerifyCredentials();
                URL url = null;
                try {
//                    url = new URL("https://api.twitter.com/1.1/users/show.json?");
                    url = new URL("https://api.twitter.com/1.1/users/show.json?screen_name=alancheen06&user_id=3313987080");
//                    url = new URL("http://api.yourbackend.com/check_credentials.json");
                    HttpURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//                    for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
//                        connection.setRequestProperty(entry.getKey(), entry.getValue());
//                    }
//                    connection.addRequestProperty("Authorization","OAuth oauth_consumer_key=\"7Ud4sn002Jquc6hR6XFREEqds\", oauth_nonce=\"456e1966e1db379f36f1ea9551ce0ad8\", oauth_signature=\"QWGBWcurKrLd5RvtDwjzmqtasBc%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1444727699\", oauth_token=\"3313987080-h6f0ecdEc100MCQjpKyTuiNVakj6nZ0bdV2HvUw\", oauth_version=\"1.0\"");

                    Map<String, String> par = new HashMap<String, String>();
                    par.put("screen_name", "alancheen06");
                    par.put("user_id", "3313987080");
                    OAuth1aService.signRequest(authConfig, token, conn, par);

                    conn.connect();
                    int status = conn.getResponseCode();
                    //得到读取的内容(流)
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    // 为输出创建BufferedReader
                    BufferedReader buffer = new BufferedReader(in);
                    String inputLine = null;
                    String resultData = "";
                    //使用循环来读取获得的数据
                    while (((inputLine = buffer.readLine()) != null)) {
                        //我们在每一行后面加上一个"\n"来换行
                        resultData += inputLine + "\n";
                    }
                    JSONObject jo = new JSONObject(resultData);
                    String imgUrl = jo.getString("profile_image_url");
                    Log.d("Twitter", "run: resultData" + resultData);

                    final String aa = resultData;
                    runOnUiThread(()->mTvInfo.setText(aa));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mBtnTwitter.onActivityResult(requestCode, resultCode, data);//140 requestCode
    }
}
