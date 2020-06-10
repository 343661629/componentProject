package com.noahedu.basemodule;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.appproxy.AppProxy;


/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 9:51
 */
public class ComponentApp extends Application {
    private AppProxy proxy;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("------ComponentMainApp--------onCreate--------");

        //if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
       // }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        proxy = new AppProxy(this);
        proxy.onCreate(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        proxy.onLowMemory(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        proxy.onTerminate(this);
        //ARouter.getInstance().destroy();
    }
}
