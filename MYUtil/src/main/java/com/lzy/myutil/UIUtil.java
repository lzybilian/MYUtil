package com.lzy.myutil;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

/**
 * @author 刘智渊
 * @time 2021/1/13 15:10
 * @describe 一些UI效果工具类  比如activity置灰，view置灰
 */
public class UIUtil {

    /**
     * 整个activity 中的view都设置为灰色
     * @param activity
     */
    public static void setGrayActivity(Activity activity){
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        if (activity != null) {
            activity.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }
    }

    /**
     * 整个View置灰
     * @param view
     */
    public static void setGrayView(View view){
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        if (view != null){
            view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }
    }

}
