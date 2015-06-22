package yifeiyuan.practice.practicedemos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import yifeiyuan.practice.practicedemos.base.Practice;
import yifeiyuan.practice.practicedemos.periscope.BezierActivity;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Practice> mPractices;
    private ListView mListView;
    private Context mContext;
    private MyAdapter mAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initData();
        mAdapter = new MyAdapter();
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Practice practice = mPractices.get(position);
                startActivity(practice.intent);
            }
        });
    }


    void initData(){

        mPractices = new ArrayList<>();
        mPractices.add(new Practice("Periscope点赞效果", new Intent(mContext, BezierActivity.class)));

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mPractices.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (null == convertView) {
                viewHolder =  new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.mooc_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Practice mooc = mPractices.get(position);
            viewHolder.title.setText(mooc.title);
            return convertView;
        }


        class ViewHolder {
            TextView title;
        }
    }


}
