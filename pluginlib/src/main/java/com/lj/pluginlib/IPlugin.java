package com.lj.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created time : 2019/3/20 17:16.
 *
 * @author HPA
 */
public interface IPlugin {

    int FROM_INRERNAL = 0;

    int FROM_EXTERNAL=1;

    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
