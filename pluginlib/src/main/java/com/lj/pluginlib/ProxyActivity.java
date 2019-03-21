package com.lj.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ProxyActivity extends Activity implements IPlugin {

    //插件的上下文
    private Activity mPxoxyActivity;

    private int mFrom = FROM_INRERNAL;

    @Override
    public void attach(Activity activity) {

        mPxoxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt("FROM");
        }
        if (mFrom == FROM_INRERNAL) {
            //apk来自内部---单独运行-不需要传输上下文-
            super.onCreate(savedInstanceState);
            mPxoxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == FROM_INRERNAL) {
            super.setContentView(layoutResID);
        } else {
            mPxoxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {

        if (mFrom == FROM_INRERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onRestart() {

        if (mFrom == FROM_INRERNAL) {
            super.onRestart();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFrom == FROM_INRERNAL) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResume() {

        if (mFrom == FROM_INRERNAL) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {

        if (mFrom == FROM_INRERNAL) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INRERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {

        if (mFrom == FROM_INRERNAL) {
            super.onDestroy();
        }
    }
}
