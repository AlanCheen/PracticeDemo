package yifeiyuan.practice.yutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by alanchen on 15/8/12.
 *
 * 用于获取应用的信息
 */
public class AppUtils {

    /**
     * 获取app版本名称
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager pm = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (null!=info) {
                versionName = info.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;

    }

    /**
     * 获取app code版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){

        PackageManager pm = context.getPackageManager();
        int versionCode = 1;
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (null!=info) {
                versionCode = info.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;

    }
}
