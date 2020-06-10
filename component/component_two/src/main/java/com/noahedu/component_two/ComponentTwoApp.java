package com.noahedu.component_two;

import android.app.Application;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.appproxy.AppLife;
import com.noahedu.basecomponent_common.appproxy.Priorty;

/**
 * @Description: application
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 9:47
 */
public class ComponentTwoApp implements AppLife {
    @Override
    public void onCreate(Application application) {
        LogUtils.e("------ComponentTwoApp-------onCreate-----");

    }

    @Override
    public void onLowMemory(Application application) {
        LogUtils.e("------ComponentTwoApp-------onLowMemory-----");
    }

    @Override
    public void onTerminate(Application application) {
        LogUtils.e("------onTerminate-------onLowMemory-----");
    }

    @Override
    public String getPriority() {
        return Priorty.HIGH;
    }
}
