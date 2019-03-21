package com.lj.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lj.pluginlib.PluginLibActivity;
import com.lj.pluginlib.PluginManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //加载插件APK

                String path = null;
                try {
                    path = Utils.copyAssetAndWrite(MainActivity.this,"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PluginManager.getInstance().loadAPK(path);

            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //跳转插件APK
                Intent intent = new Intent(MainActivity.this, PluginLibActivity.class);

                intent.putExtra("className", "com.lj.pluginapk.PluginActivity");

                startActivity(intent);
            }
        });
    }
}
