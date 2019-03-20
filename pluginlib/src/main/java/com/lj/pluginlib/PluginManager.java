package com.lj.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created time : 2019/3/20 15:36.
 *
 * @author HPA
 */
public class PluginManager {

    private static final PluginManager sPluginManager=new PluginManager();

    private Context mContext;

    private PluginAPK mPluginAPK;

    private PluginManager(){

    }
    public static PluginManager getInstance(){
        return sPluginManager;
    }

    public void init(Context context){

        mContext=context.getApplicationContext();
    }
    //加载插件APK

    public void loadAPK(String apkPath){
        PackageInfo packageInfo=mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
          if (apkPath==null){
              return;
          }
        DexClassLoader dexClassLoader=createDexClassLoader(apkPath);

        AssetManager assetManager = createAssetManager(apkPath);

        Resources resources=createResource(assetManager);

        mPluginAPK=new PluginAPK(packageInfo,resources,dexClassLoader);
    }


    //创建访问插件APk的DexClassLoader对象
    private DexClassLoader createDexClassLoader(String apkPath) {

        File file=mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

    //创建AssetManager

    private AssetManager createAssetManager(String apkPath) {

        try {
            AssetManager am=AssetManager.class.newInstance();
            Method method=AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //创建Resource对象
    private Resources createResource(AssetManager assetManager) {

        Resources resources=mContext.getResources();

        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    public PluginAPK getPluginAPK(){

        return mPluginAPK;
    }

}
