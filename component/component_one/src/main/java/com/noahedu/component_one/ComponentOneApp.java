package com.noahedu.component_one;

import android.app.Application;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.appproxy.AppLife;
import com.noahedu.basecomponent_common.appproxy.Priorty;

/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 9:49
 */
public class ComponentOneApp implements AppLife {
    @Override
    public void onCreate(Application application) {
        LogUtils.e("------ComponentOneApp-------onCreate--------");
    }

    @Override
    public void onLowMemory(Application application) {
        LogUtils.e("------ComponentOneApp-------onLowMemory--------");
    }

    @Override
    public void onTerminate(Application application) {
        LogUtils.e("------ComponentOneApp-------onTerminate--------");
    }

    @Override
    public String getPriority() {
        return Priorty.HIGH;
    }
}
