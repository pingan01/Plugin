package com.lj.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Created time : 2019/3/20 15:32.
 *
 * @author HPA
 *
 * 插件APK的实体类
 */
public class PluginAPK {

    //插件apk的实体对象

    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;


    public PluginAPK(PackageInfo packageInfo, Resources resources,DexClassLoader dexClassLoader) {
        mPackageInfo = packageInfo;
        mResources = resources;
        mAssetManager =resources.getAssets();
        mDexClassLoader = dexClassLoader;
    }
}
