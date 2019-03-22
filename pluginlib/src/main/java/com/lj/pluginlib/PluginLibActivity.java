package com.lj.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created time : 2019/3/21 10:29.
 *
 * @author HPA
 */
public class PluginLibActivity extends Activity {

    private String mClassName;

    private PluginAPK mPluginAPK;

    private IPlugin mIPlugin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");

        mPluginAPK = PluginManager.getInstance().getPluginAPK();

        launchProxyActivity();
    }

    private void launchProxyActivity() {

        if (mPluginAPK == null) {
            throw new RuntimeException("请先加载插件APK");
        }
        try {
            Class<?> clazz = mPluginAPK.mDexClassLoader.loadClass(mClassName);
            //这里是Activity的实例化对象--但是没上下文对象与生命周期
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mIPlugin = (IPlugin) object;
                mIPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginAPK != null ? mPluginAPK.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginAPK != null ? mPluginAPK.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginAPK != null ? mPluginAPK.mDexClassLoader : super.getClassLoader();
    }
}
