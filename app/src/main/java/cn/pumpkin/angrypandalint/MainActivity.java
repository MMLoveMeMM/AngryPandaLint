package cn.pumpkin.angrypandalint;

import android.app.Activity;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button myButton;
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

        addSum(11,22);

        Panda panda = new Panda();

        myButton = (Button)findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"this is toast !",Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println("onCreate end !");

    }

    public int addSum(int a,int b){
        return a+b;
    }
}
