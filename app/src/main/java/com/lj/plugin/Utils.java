package com.lj.plugin;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created time : 2019/3/21 11:07.
 *
 * @author HPA
 */
public class Utils {

    /**
     *将Assets目录下的fileName文件拷贝至app缓存目录
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String copyAssetAndWrite(Context context, String fileName) throws IOException {

        File cacheDir = context.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File outFile = new File(cacheDir, fileName);
        if (!outFile.exists()) {
            boolean res = outFile.createNewFile();
            if (res) {
                InputStream is = context.getAssets().open(fileName);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[is.available()];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        }else {
            Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
            return outFile.getAbsolutePath();
        }
        return "";
    }
}
