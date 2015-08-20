package yifeiyuan.practice.practicedemos.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;
import yifeiyuan.practice.practicedemos.R;

public class AboutMeActivity extends ToolbarActivity {


//    @InjectView(R.id.toolbar)
//    Toolbar mToolbar;
//    @InjectView(R.id.collapsing_toolbar)
//    CollapsingToolbarLayout mCollapsingToolbar;
    @InjectView(R.id.appbar)
    AppBarLayout mAppbar;

    public static Intent start(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        //Notice 必须要自己设置
        mCollapsingToolbar.setTitle("程序亦非猿");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_case, menu);
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
}
