package yifeiyuan.practice.practicedemos;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RVDemo extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.rv_demo)
    RecyclerView mRvDemo;
    @InjectView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvdemo);
        ButterKnife.inject(this);
        String ss = "";



         class XXX extends IBook.Stub{

             @Override
             public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

             }
         }

        abstract class YYY implements IBook{

        }
    }

}
