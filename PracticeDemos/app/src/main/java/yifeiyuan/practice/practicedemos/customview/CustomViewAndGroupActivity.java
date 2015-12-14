package yifeiyuan.practice.practicedemos.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;

public class CustomViewAndGroupActivity extends ToolbarActivity {

    Fragment mViewGroupFragment ;
    Fragment mViewFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        ButterKnife.inject(this);
        mViewGroupFragment = CustomViewGroupFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.custom_container,mViewGroupFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.view_group) {
            if (null == mViewGroupFragment) {
                mViewGroupFragment = CustomViewGroupFragment.newInstance();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.custom_container, mViewGroupFragment).commit();
            return true;
        }else if (id == R.id.view) {
            if (null == mViewFragment) {
                mViewFragment = CustomViewFragment.newInstance();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.custom_container, mViewGroupFragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
