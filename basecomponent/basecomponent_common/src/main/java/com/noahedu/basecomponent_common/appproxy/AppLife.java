package com.noahedu.basecomponent_common.appproxy;

import android.app.Application;

/**
 * @Description: 给组件设置生命周期
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 9:56
 */
public interface AppLife {

    void onCreate(Application application);

    void onLowMemory(Application application);

    void onTerminate(Application application);


    /**
     * 设置组件的优先级
     * @return
     */
    String getPriority();


}
