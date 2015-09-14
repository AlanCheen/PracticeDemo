package yifeiyuan.practice.practicedemos.viewdrager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

public class SwipeBackActivity extends AppCompatActivity {

    @InjectView(R.id.swipe_back)
    SwipeBackFrameLayout mSwipeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_back);
        ButterKnife.inject(this);
        mSwipeBack.setCallback(new SwipeBackFrameLayout.Callback() {
            @Override
            public void onShouldFinish() {
                finish();
                overridePendingTransition(R.anim.no_anim, R.anim.out_to_right);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
