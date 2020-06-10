package com.noahedu.basecomponent_common.appproxy;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: application的代理类，主要在这里通过反射来获取到那些组件需要application的生命周期
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 9:56
 */
public class AppProxy {
    private Context mContext;
    private List<AppLife> appLives;
    //    private Map<String, AppLife> mapPriority;
    private List<AppLife> highList = new ArrayList<>();
    private List<AppLife> normalList = new ArrayList<>();
    private List<AppLife> lowList = new ArrayList<>();

    public AppProxy(Context context) {
        this.mContext = context;
        init();
    }

    private void clear() {
        highList.clear();
        normalList.clear();
        lowList.clear();
    }

    private void init() {
        appLives = new ManifestParser(mContext).getManifestParserData();
        if (null != appLives && appLives.size() != 0) {
            //由于module中存在优先级，所以在这里获取到对应的module的优先级别
            clear();
            //TODO 这里使用数组，后期有更合适的方案在更改
            for (AppLife appLife : appLives) {
                if (appLife.getPriority().equals(Priorty.HIGH)) {
                    highList.add(appLife);
                } else if (appLife.getPriority().equals(Priorty.NORMAL)) {
                    normalList.add(appLife);
                } else if (appLife.getPriority().equals(Priorty.LOW)) {
                    lowList.add(appLife);
                }
            }
        }
    }

    //==========================下面的代码重复过多，等后面又更好的方案在修改掉===================================================

    public void onCreate(Application application) {
        if (null != highList && highList.size() != 0) {
            for (AppLife appLife : highList) {
                appLife.onCreate(application);
            }
        }
        if (null != normalList && normalList.size() != 0) {
            for (AppLife appLife : normalList) {
                appLife.onCreate(application);
            }
        }
        if (null != lowList && lowList.size() != 0) {
            for (AppLife appLife : lowList) {
                appLife.onCreate(application);
            }
        }
    }

    public void onLowMemory(Application application) {
        if (null != highList && highList.size() != 0) {
            for (AppLife appLife : highList) {
                appLife.onLowMemory(application);
            }
        }
        if (null != normalList && normalList.size() != 0) {
            for (AppLife appLife : normalList) {
                appLife.onLowMemory(application);
            }
        }
        if (null != lowList && lowList.size() != 0) {
            for (AppLife appLife : lowList) {
                appLife.onLowMemory(application);
            }
        }
    }

    public void onTerminate(Application application) {
        if (null != highList && highList.size() != 0) {
            for (AppLife appLife : highList) {
                appLife.onTerminate(application);
            }
        }
        if (null != normalList && normalList.size() != 0) {
            for (AppLife appLife : normalList) {
                appLife.onTerminate(application);
            }
        }
        if (null != lowList && lowList.size() != 0) {
            for (AppLife appLife : lowList) {
                appLife.onTerminate(application);
            }
        }
    }


}
