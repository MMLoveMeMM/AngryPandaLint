package cn.pumpkin.angrypandalint;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("tag", "msg");
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).run();

    }
}
