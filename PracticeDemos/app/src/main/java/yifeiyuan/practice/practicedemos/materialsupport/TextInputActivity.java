package yifeiyuan.practice.practicedemos.materialsupport;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import yifeiyuan.practice.practicedemos.R;
import yifeiyuan.practice.practicedemos.base.ToolbarActivity;

public class TextInputActivity extends ToolbarActivity {


    //java.lang.IllegalArgumentException: We already have an EditText, can only have one
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_input, menu);
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
