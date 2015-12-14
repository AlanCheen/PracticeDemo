package yifeiyuan.practice.practicedemos.itemtouchhelper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;


/**
 *
 * todo 排序 拖动
 *
 */
public class TouchHelperActivity extends ToolbarActivity {

    SimpleSwipedismissFragment simpleSwipedismissFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss);
        simpleSwipedismissFragment = new SimpleSwipedismissFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container,simpleSwipedismissFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_swipe_dismiss, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //todo
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
