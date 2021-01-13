package com.lzy.myutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 刘智渊
 * @time 2021/1/13 17:05
 * @describe SharedPreferences工具类
 */
public class SPUtils {
    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap<>();

    private SharedPreferences sp;

    /**
     * Return the single {@link SPUtils} instance
     *
     * @return the single {@link SPUtils} instance
     */
    public static SPUtils getInstance(Context context) {
        return getInstance(context, "");
    }

    /**
     * Return the single {@link SPUtils} instance
     *
     * @param spName The name of sp.
     * @return the single {@link SPUtils} instance
     */
    public static SPUtils getInstance(Context context,String spName) {
        if(TextUtils.isEmpty(spName)){
            spName = "spUtils";
        }
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                spUtils = SP_UTILS_MAP.get(spName);
                if (spUtils == null) {
                    spUtils = new SPUtils(context,spName);
                    SP_UTILS_MAP.put(spName, spUtils);
                }
            }
        }
        return spUtils;
    }

    private SPUtils(Context context,String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * Put the  value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    public void put(String key, Object value) {
        if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).apply();
        }else if(value instanceof Long){
            sp.edit().putLong(key, (Long) value).apply();
        }else if(value instanceof String){
            sp.edit().putString(key, (String) value).apply();
        }else if(value instanceof Integer){
            sp.edit().putInt(key, (Integer) value).apply();
        }else if(value instanceof Float){
            sp.edit().putFloat(key, (Float) value).apply();
        }
    }

    /**
     *
     * @param key   The key of sp.
     */
    public float getFloat(String key) {
        return sp.getFloat(key,-1f);
    }

    /**
     *
     * @param key   The key of sp.
     */
    public int getInt(String key) {
        return sp.getInt(key,-1);
    }

    /**
     *
     * @param key   The key of sp.
     */
    public String getString(String key) {
        return sp.getString(key,"");
    }

    /**
     *
     * @param key   The key of sp.
     */
    public boolean getBoolean(String key) {
        return sp.getBoolean(key,false);
    }

    /**
     *
     * @param key   The key of sp.
     */
    public long getLong(String key) {
        return sp.getLong(key,-1);
    }


    /**
     * Return all values in sp.
     *
     * @return all values in sp
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * Return whether the sp contains the preference.
     *
     * @param key The key of sp.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * Remove the preference in sp.
     *
     * @param key The key of sp.
     */
    public void remove(@NonNull final String key) {
        sp.edit().remove(key).apply();
    }

}
