package yifeiyuan.practice.practicedemos.base;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import yifeiyuan.practice.practicedemos.R;


/**
 * 有toolbar的 统一继承这个activity
 *
 * 统一:
 * 设置toolbar
 * 设置title
 * 处理左上返回按钮事件
 */
public class ToolbarActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;

    @Optional
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Optional
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mContext = this;
        ButterKnife.inject(this);
        setupToolbar();
    }

    protected void setupToolbar() {
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //统一设置 title
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        Log.d(TAG, "onTitleChanged() called with " + "title = [" + title + "], color = [" + color + "]");
        super.onTitleChanged(title, color);
        //Notice 如果有collapsingtoolbar 就不能在toolbar设置(无效果)
        if (null != mCollapsingToolbar) {
            mCollapsingToolbar.setTitle(title);
        } else if (null != mToolbar) {
            mToolbar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
