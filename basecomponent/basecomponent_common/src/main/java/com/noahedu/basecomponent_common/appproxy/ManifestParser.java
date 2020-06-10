package com.noahedu.basecomponent_common.appproxy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 该类主要是负责解析各个组件中包含的<meta-data></> 语句
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 10:05
 */
public class ManifestParser {
    private Context mContext;
    private ApplicationInfo applicationInfo;
    private AppLife appLife;
    private List<AppLife> appLifeList = new ArrayList<AppLife>();
    /**
     * 该值就是在清单文件中设置的value值，要和清单文件中的值一致
     */
    private String metaDaTaValue = "ModuleConfig";


    public ManifestParser(Context mContext) {
        this.mContext = mContext;
    }

    public List<AppLife> getManifestParserData() {
        try {
            if (null == applicationInfo) {
                applicationInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            }
            if (null != applicationInfo.metaData) {
                //通过遍历applicationInfo.metaData.keySet() 来拿到我们在清单文件中设置的meta_data的value值
                //applicationInfo.metaData.keySet() 的长度  表示有当前依赖了几个组件数   key 表示有 在清单文件中meda_data 的name
                appLifeList.clear();
                for (String key : applicationInfo.metaData.keySet()) {
                    if (null != key) {
                        Log.e("huangjialin", "----获取到的key值-----> " + key);
                        String value = (String) applicationInfo.metaData.get(key);
                        if (!TextUtils.isEmpty(value) && metaDaTaValue.equals(value)) {
                            Log.e("huangjialin", "----获取到的value值-----> " + value);
                            AppLife appLife = parseModule(key);
                            appLifeList.add(appLife);
                        }
                    }
                }
            }
            return appLifeList;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射拿到对应的实例
     */
    private AppLife parseModule(String className) {
        try {
            Class clazz = Class.forName(className);
            appLife = (AppLife) clazz.newInstance();
            return appLife;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
