package com.noahedu.basecomponent_common;

import android.util.Log;

/**
 * @Description: 日志工具类
 * @Author: huangjialin
 * @CreateDate: 2020/6/4 17:08
 */
public class LogUtils {
    private static final String TAG = "LogUtils";

    public static void e(String str) {
        Log.e(TAG, str);
    }

}
