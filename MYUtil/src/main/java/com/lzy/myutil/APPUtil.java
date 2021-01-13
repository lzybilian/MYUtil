package com.lzy.myutil;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @author 刘智渊
 * @time 2021/1/13 15:39
 * @describe App framwork相关的工具类
 */
public class APPUtil {

    /**
     * 安装APK
     * @param filePath 安装路径
     */
    public static void installApp(Context context, String filePath) {
        try{
            File file = new File(filePath);
            Uri uri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                uri = Uri.fromFile(file);
            } else {
                String authority = context.getPackageName() + ".utilcode.provider";
                uri = FileProvider.getUriForFile(context, authority, file);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String type = "application/vnd.android.package-archive";
            intent.setDataAndType(uri, type);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 安装APK
     * @param file apk文件
     */
    public static void installApp(Context context, File file) {
        try{
            Uri uri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                uri = Uri.fromFile(file);
            } else {
                String authority = context.getPackageName() + ".utilcode.provider";
                uri = FileProvider.getUriForFile(context, authority, file);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String type = "application/vnd.android.package-archive";
            intent.setDataAndType(uri, type);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 判断应用是否安装
     * @param pkgName
     * @return
     */
    public static boolean isAppInstalled(Context context, String pkgName) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getApplicationInfo(pkgName, 0).enabled;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断应用是否有root权限
     * @return
     */
    public static boolean isAppRoot() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("echo root", true);
        return result.result == 0;
    }

    /**
     * 判断是否是系统白名单应用
     * @return
     */
    public static boolean isAppSystem(Context context,String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
